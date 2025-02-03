package io.github.ma1uta.demo.controller;

import io.github.ma1uta.demo.dto.AddressDto;
import io.github.ma1uta.demo.mapper.AddressMapper;
import io.github.ma1uta.demo.model.Address;
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
@RequestMapping("/api/employee/{id}/address")
public class AddressController {

    private final EmployeeService employeeService;
    private final AddressMapper addressMapper;

    public AddressController(
        EmployeeService employeeService,
        AddressMapper addressMapper
    ) {
        this.employeeService = employeeService;
        this.addressMapper = addressMapper;
    }

    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<AddressDto>>> getAddresses(@PathVariable("id") Long id) {
        return employeeService.getEmployees()
            .stream()
            .filter(x -> x.getId().equals(id))
            .findFirst()
            .map(value -> ResponseEntity.ok(
                CollectionModel.of(
                    value.getAddress().stream().map(x -> wrap(value.getId(), x)).collect(Collectors.toList()),
                    linkTo(methodOn(AddressController.class).getAddresses(id)).withSelfRel()
                )
            ))
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{addressId}")
    public ResponseEntity<EntityModel<AddressDto>> getAddress(@PathVariable("id") Long id, @PathVariable("addressId") Long addressId) {
        return employeeService.getEmployees()
            .stream()
            .filter(x -> x.getId().equals(id))
            .flatMap(x -> x.getAddress().stream())
            .filter(x -> x.getId().equals(addressId))
            .findFirst()
            .map(value -> ResponseEntity.ok(wrap(id, value)))
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    private EntityModel<AddressDto> wrap(Long employeeId, Address address) {
        return EntityModel.of(
            addressMapper.toDto(address),
            linkTo(methodOn(AddressController.class, employeeId).getAddress(employeeId, address.getId())).withSelfRel()
        );
    }
}
