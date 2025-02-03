package io.github.ma1uta.demo.controller;

import io.github.ma1uta.demo.assembler.EmployeeModelAssembler;
import io.github.ma1uta.demo.dto.EmployeeDto;
import io.github.ma1uta.demo.service.EmployeeService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    private final EmployeeService employeeService;
    private final EmployeeModelAssembler employeeModelAssembler;

    public EmployeeController(
        EmployeeService employeeService,
        EmployeeModelAssembler employeeModelAssembler
    ) {
        this.employeeService = employeeService;
        this.employeeModelAssembler = employeeModelAssembler;
    }

    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<EmployeeDto>>> getEmployees() {
        return ResponseEntity.ok(CollectionModel.of(
            employeeService.getEmployees().stream().map(employeeModelAssembler::toModel).collect(Collectors.toList()),
            linkTo(methodOn(EmployeeController.class).getEmployees()).withSelfRel()
        ));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<EmployeeDto>> getEmployee(@PathVariable("id") Long id) {
        return employeeService.getEmployees()
            .stream()
            .filter(x -> x.getId().equals(id))
            .findFirst()
            .map(value -> ResponseEntity.ok(employeeModelAssembler.toModel(value)))
            .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
