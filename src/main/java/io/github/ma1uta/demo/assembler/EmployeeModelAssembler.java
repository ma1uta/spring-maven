package io.github.ma1uta.demo.assembler;

import io.github.ma1uta.demo.controller.EmployeeController;
import io.github.ma1uta.demo.dto.AddressDto;
import io.github.ma1uta.demo.dto.EmployeeDto;
import io.github.ma1uta.demo.mapper.EmployeeMapper;
import io.github.ma1uta.demo.model.Address;
import io.github.ma1uta.demo.model.Employee;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class EmployeeModelAssembler extends RepresentationModelAssemblerSupport<Employee, EmployeeDto> {

    private final EmployeeMapper employeeMapper;
    private final RepresentationModelAssembler<Address, AddressDto> addressModelAssembler;
    private final RepresentationModelProcessor<AddressDto> addressModelProcessor;

    public EmployeeModelAssembler(
        EmployeeMapper employeeMapper,
        RepresentationModelAssembler<Address, AddressDto> addressModelAssembler,
        RepresentationModelProcessor<AddressDto> addressModelProcessor
    ) {
        super(EmployeeController.class, EmployeeDto.class);
        this.employeeMapper = employeeMapper;
        this.addressModelAssembler = addressModelAssembler;
        this.addressModelProcessor = addressModelProcessor;
    }

    @Override
    public EmployeeDto toModel(Employee entity) {
        EmployeeDto dto = employeeMapper.toDto(entity);
        dto.setAddresses(
            entity.getAddress()
                .stream()
                .map(addressModelAssembler::toModel)
                .map(addressModelProcessor::process)
                .collect(Collectors.toList()));
        return dto;
    }
}
