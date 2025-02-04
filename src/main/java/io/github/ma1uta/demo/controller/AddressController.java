package io.github.ma1uta.demo.controller;

import io.github.ma1uta.demo.assembler.AddressModelAssembler;
import io.github.ma1uta.demo.dto.AddressDto;
import io.github.ma1uta.demo.model.Address;
import io.github.ma1uta.demo.service.AddressService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/address")
public class AddressController {

    private final AddressService addressService;
    private final AddressModelAssembler addressModelAssembler;

    public AddressController(
        AddressService addressService,
        AddressModelAssembler addressModelAssembler
    ) {
        this.addressService = addressService;
        this.addressModelAssembler = addressModelAssembler;
    }

    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<AddressDto>>> getAddresses() {
        return ResponseEntity.ok(addressModelAssembler.toCollectionModel(addressService.getAddresses()));
    }

    @GetMapping("/{addressId}")
    public ResponseEntity<EntityModel<AddressDto>> getAddress(@PathVariable("addressId") Long addressId) {
        Address address = addressService.getAddress(addressId);
        return address != null ? ResponseEntity.ok(addressModelAssembler.toModel(address)) : ResponseEntity.notFound().build();
    }
}
