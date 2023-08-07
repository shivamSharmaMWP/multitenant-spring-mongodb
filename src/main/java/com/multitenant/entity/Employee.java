package com.multitenant.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * User: rajeshgupta
 * Date: 08/03/21
 */
@Data
@Builder
@Document
public class Employee {
/*
{"firstName": "shivam", "lastName": "sharma", "age": 29, "salary": 30303}
 */
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private int age;
    private long salary;

    private String tenantId;
}
