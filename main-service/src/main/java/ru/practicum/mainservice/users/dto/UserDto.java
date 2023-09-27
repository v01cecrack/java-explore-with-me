package ru.practicum.mainservice.users.dto;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long id;

    @NotBlank
    private String name;
    @NotBlank
    @Email
    private String email;


}
