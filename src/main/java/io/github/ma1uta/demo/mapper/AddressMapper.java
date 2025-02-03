package io.github.ma1uta.demo.mapper;

import io.github.ma1uta.demo.dto.AddressDto;
import io.github.ma1uta.demo.model.Address;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AddressMapper {

    AddressDto toDto(Address address);

    List<AddressDto> toDto(List<Address> addresses);
}
