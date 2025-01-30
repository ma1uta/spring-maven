package io.github.ma1uta.demo.dto;

import jakarta.validation.constraints.NotNull;

public record SumRequestDto(
    @NotNull Integer x,
    @NotNull Integer y
) {
}
