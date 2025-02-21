package io.github.ma1uta.demo.assembler;

import io.github.ma1uta.demo.controller.AddressController;
import io.github.ma1uta.demo.dto.AddressDto;
import io.github.ma1uta.demo.mapper.AddressMapper;
import io.github.ma1uta.demo.model.Address;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class AddressModelAssembler extends RepresentationModelAssemblerSupport<Address, AddressDto> {

    private final AddressMapper addressMapper;

    public AddressModelAssembler(AddressMapper addressMapper) {
        super(AddressController.class, AddressDto.class);
        this.addressMapper = addressMapper;
    }

    @Override
    public AddressDto toModel(Address entity) {
        return addressMapper.toDto(entity);
    }
}
