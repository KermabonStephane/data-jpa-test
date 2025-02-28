package org.demis27.example.jpa;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository repository;

    List<Employee> getAll() {
        return StreamSupport.stream(repository.findAll().spliterator(), false).toList();
    }
}
