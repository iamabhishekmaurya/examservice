package com.exam.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    @NotNull
    private String username;
    @NotNull
    private String password;
    @NotNull
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String profile;
    @NotNull
    private List<Long> role;
}
