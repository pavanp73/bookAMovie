package com.lld.bookamovie.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupResponseDto {

    private Long userId;
    private ResponseStatus responseStatus;
}
