package com.gamestore.gamestore.domain.dtos;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import static com.gamestore.gamestore.constants.ErrorMessages.*;
import static com.gamestore.gamestore.constants.GlobalRegex.EMAIL_REGEX;
import static com.gamestore.gamestore.constants.GlobalRegex.PASSWORD_REGEX;

public class UserRegisterDto {

    private String email;
    private String password;
    private String fullName;

    public UserRegisterDto() {
    }

    public UserRegisterDto(String email, String password, String fullName) {
        this.email = email;
        this.password = password;
        this.fullName = fullName;
    }

    @Pattern(regexp = EMAIL_REGEX, message = EMAIL_INVALID)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Pattern(regexp = PASSWORD_REGEX, message = PASSWORD_INVALID)
    @Size(min = 6, message = PASSWORD_LENGTH_INVALID)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @NotNull(message = NAME_NOT_NULL)
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
