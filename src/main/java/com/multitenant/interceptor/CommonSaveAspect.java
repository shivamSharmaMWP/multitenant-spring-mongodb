package com.multitenant.interceptor;

import com.multitenant.TenantHolder;
import com.multitenant.entity.Employee;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class CommonSaveAspect {

    @Pointcut("execution(* com.multitenant.*.save(..))")
    public void commonSave() {
    }

    @Around("commonSave()")
    public Object addTenantId(final ProceedingJoinPoint pjp) throws Throwable {

        Object[] args = pjp.getArgs();

        if (Iterable.class.isAssignableFrom(args[0].getClass())) {
            //noinspection unchecked
            Iterable<Employee> entities = (Iterable<Employee>) args[0];
            entities.forEach(entity -> {
                // set the fields here...
                entity.setTenantId(TenantHolder.getTenantId());
            });
        }

        if (args[0] instanceof Employee) {
            Employee entity = (Employee) args[0];
            // set the fields here...
            entity.setTenantId(TenantHolder.getTenantId());
        }

        return pjp.proceed(args); 
    }

    /*

    @Pointcut("execution(* com.multitenant.*.findAll(..))")
    public void findAllPointcut() {
    }

    @Around("findAllPointcut()")
    public Object findAllAspect(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("findAll aspect");
        return joinPoint.proceed();
    }


    @Pointcut("execution(* org.springframework.data.mongodb.core.MongoTemplate.find(..))")
    public void findAllPointcut() {
    }

    @Around("findAllPointcut()")
    public Object findAllAspect(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("findAll aspect");

        Object[] args = joinPoint.getArgs();

        if (args[0] instanceof Query) {
            Query query = (Query) args[0];
            query.addCriteria(Criteria.where("tenantId").is(TenantHolder.getTenantId()));
        }
        return joinPoint.proceed(args);
    }

    */

}
