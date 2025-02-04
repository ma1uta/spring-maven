package io.github.ma1uta.demo.assembler;

import io.github.ma1uta.demo.controller.AddressController;
import io.github.ma1uta.demo.dto.AddressDto;
import io.github.ma1uta.demo.mapper.AddressMapper;
import io.github.ma1uta.demo.model.Address;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class AddressModelAssembler implements RepresentationModelAssembler<Address, EntityModel<AddressDto>> {

    private final AddressMapper addressMapper;

    public AddressModelAssembler(AddressMapper addressMapper) {
        this.addressMapper = addressMapper;
    }

    @Override
    public EntityModel<AddressDto> toModel(Address entity) {
        return EntityModel.of(
            addressMapper.toDto(entity),
            linkTo(methodOn(AddressController.class).getAddress(entity.getId())).withSelfRel().withType("core:address")
        );
    }
}
