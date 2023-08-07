package com.multitenant;

import com.multitenant.entity.Employee;
import com.multitenant.entity.Planet;
import com.multitenant.entity.PlanetRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * User: rajeshgupta
 * Date: 08/03/21
 */
@RestController
@AllArgsConstructor
public class EmployeeController {

    private EmployeeService employeeService;

    private PlanetRepository planetRepository;

    @GetMapping(path = "/employee")
    public ResponseEntity<List<Employee>> getEmployees() {
        return new ResponseEntity(employeeService.findAllByTenantId(), HttpStatus.OK);
    }

    @GetMapping(path = "/employee/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") final String id) {
        return new ResponseEntity(employeeService.findById(id), HttpStatus.OK);
    }

    @PostMapping(path = "/employee")
    public ResponseEntity<Employee> createEmployee(@RequestBody final Employee employee) {
        return new ResponseEntity(employeeService.save(employee), HttpStatus.CREATED);
    }

    @GetMapping(path = "/planet")
    public ResponseEntity<List<Employee>> getPlanets() {
        return new ResponseEntity(planetRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/planet/{id}")
    public ResponseEntity<Employee> getPlanetById(@PathVariable("id") final String id) {
        return new ResponseEntity(planetRepository.findById(id), HttpStatus.OK);
    }

    @PostMapping(path = "/planet")
    public ResponseEntity<Employee> createPlanet(@RequestBody final Planet planet) {
        return new ResponseEntity(planetRepository.save(planet), HttpStatus.CREATED);
    }
}
