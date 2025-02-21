package io.github.ma1uta.demo.assembler;

import io.github.ma1uta.demo.controller.AddressController;
import io.github.ma1uta.demo.dto.AddressDto;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class AddressModelProcessor implements RepresentationModelProcessor<AddressDto> {
    @Override
    public AddressDto process(AddressDto model) {
        return model.add(
            linkTo(methodOn(AddressController.class).getAddress(model.getId())).withSelfRel().withType("core:address")
        );
    }
}
