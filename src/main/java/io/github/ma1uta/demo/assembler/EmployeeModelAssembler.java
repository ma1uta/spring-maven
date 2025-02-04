package io.github.ma1uta.demo.assembler;

import io.github.ma1uta.demo.controller.EmployeeController;
import io.github.ma1uta.demo.dto.EmployeeDto;
import io.github.ma1uta.demo.mapper.EmployeeMapper;
import io.github.ma1uta.demo.model.Employee;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class EmployeeModelAssembler implements RepresentationModelAssembler<Employee, EntityModel<EmployeeDto>> {

    private final EmployeeMapper employeeMapper;

    public EmployeeModelAssembler(EmployeeMapper employeeMapper) {
        this.employeeMapper = employeeMapper;
    }

    @Override
    public EntityModel<EmployeeDto> toModel(Employee entity) {
        return EntityModel.of(
            employeeMapper.toDto(entity),
            linkTo(methodOn(EmployeeController.class).getEmployee(entity.getId())).withSelfRel().withType("core.employee"),
            linkTo(methodOn(EmployeeController.class).getAddresses(entity.getId())).withRel("addresses").withType("core:address")
        );
    }
}
