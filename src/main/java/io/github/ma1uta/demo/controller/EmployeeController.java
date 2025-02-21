package io.github.ma1uta.demo.controller;

import io.github.ma1uta.demo.assembler.AddressModelAssembler;
import io.github.ma1uta.demo.assembler.EmployeeModelAssembler;
import io.github.ma1uta.demo.dto.AddressDto;
import io.github.ma1uta.demo.dto.EmployeeDto;
import io.github.ma1uta.demo.model.Address;
import io.github.ma1uta.demo.model.Employee;
import io.github.ma1uta.demo.service.EmployeeService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    public ResponseEntity<CollectionModel<EmployeeDto>> getEmployees() {
        return ResponseEntity.ok(PagedModel.of(employeeModelAssembler.toCollectionModel(employeeService.getEmployees())));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> getEmployee(@PathVariable("id") Long id) {
        Employee employee = employeeService.getEmployee(id);
        return employee != null ? ResponseEntity.ok(employeeModelAssembler.toModel(employee)) : ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}/address")
    public ResponseEntity<CollectionModel<AddressDto>> getAddresses(@PathVariable("id") Long id) {
        List<Address> employeeAddresses = employeeService.getEmployeeAddresses(id);
        return employeeAddresses != null
            ? ResponseEntity.ok(addressModelAssembler.toCollectionModel(employeeAddresses))
            : ResponseEntity.notFound().build();
    }
}
