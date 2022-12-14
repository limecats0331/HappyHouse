package com.ssafy.happyhouse.domain.dto;


import lombok.*;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginDTO {
    @NotBlank
    private String email;
    @NotBlank
    private String password;
}
