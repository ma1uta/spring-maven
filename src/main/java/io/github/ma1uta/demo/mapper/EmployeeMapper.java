package io.github.ma1uta.demo.mapper;

import io.github.ma1uta.demo.dto.EmployeeDto;
import io.github.ma1uta.demo.model.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface EmployeeMapper {

    EmployeeDto toDto(Employee employee);

    List<EmployeeDto> toDto(List<Employee> employees);
}
