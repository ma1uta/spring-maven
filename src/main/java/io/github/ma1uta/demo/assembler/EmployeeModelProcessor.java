package io.github.ma1uta.demo.assembler;

import io.github.ma1uta.demo.controller.EmployeeController;
import io.github.ma1uta.demo.dto.EmployeeDto;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class EmployeeModelProcessor implements RepresentationModelProcessor<EmployeeDto> {

    @Override
    public EmployeeDto process(EmployeeDto model) {
        return model.add(
            linkTo(methodOn(EmployeeController.class).getEmployee(model.getId())).withSelfRel().withType("core:employee"),
            linkTo(methodOn(EmployeeController.class).getAddresses(model.getId())).withRel("addresses").withType("core:address"),
            linkTo(methodOn(EmployeeController.class).getEmployees()).withRel("employees")
        );
    }
}
