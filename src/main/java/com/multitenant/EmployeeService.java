package com.multitenant;

import com.multitenant.entity.Employee;
import com.multitenant.entity.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * User: rajeshgupta
 * Date: 08/03/21
 */
@Service
@AllArgsConstructor
public class EmployeeService {

    private EmployeeRepository employeeRepository;

    private MongoTemplate mongoTemplate;

    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public List<Employee> findAllByTenantId() {

        Query query = new Query();
        query.with(Sort.by(Sort.Direction.ASC, "firstName"));
        query.addCriteria(Criteria.where("tenantId").is(TenantHolder.getTenantId()));
        List<Employee> users = mongoTemplate.find(query, Employee.class);
        return users;
    }

    public Employee findById(final String id) {
        return employeeRepository.findById(id).get();
    }

    public Employee save(final Employee employee) {
        return employeeRepository.save(employee);
    }
}
