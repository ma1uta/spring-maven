package io.github.ma1uta.demo.dto;

public record AddressDto(
    Long id,
    String street,
    String city,
    String state,
    String zip,
    String country
) {
}
