package io.github.ma1uta.demo.controller;

import io.github.ma1uta.demo.assembler.AddressModelAssembler;
import io.github.ma1uta.demo.assembler.EmployeeModelAssembler;
import io.github.ma1uta.demo.dto.AddressDto;
import io.github.ma1uta.demo.dto.EmployeeDto;
import io.github.ma1uta.demo.model.Address;
import io.github.ma1uta.demo.model.Employee;
import io.github.ma1uta.demo.service.EmployeeService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    private final EmployeeService employeeService;
    private final EmployeeModelAssembler employeeModelAssembler;
    private final AddressModelAssembler addressModelAssembler;

    public EmployeeController(
        EmployeeService employeeService,
        EmployeeModelAssembler employeeModelAssembler,
        AddressModelAssembler addressModelAssembler
    ) {
        this.employeeService = employeeService;
        this.employeeModelAssembler = employeeModelAssembler;
        this.addressModelAssembler = addressModelAssembler;
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
        Employee employee = employeeService.getEmployee(id);
        return employee != null ? ResponseEntity.ok(employeeModelAssembler.toModel(employee)) : ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}/address")
    public ResponseEntity<CollectionModel<EntityModel<AddressDto>>> getAddresses(@PathVariable("id") Long id) {
        List<Address> employeeAddresses = employeeService.getEmployeeAddresses(id);
        return employeeAddresses != null ? ResponseEntity.ok(
            CollectionModel.of(
                employeeAddresses.stream().map(addressModelAssembler::toModel).collect(Collectors.toList()),
                linkTo(methodOn(EmployeeController.class).getAddresses(id)).withSelfRel()
            )
        ) : ResponseEntity.notFound().build();
    }
}
