package com.cydeo.entity;

import com.cydeo.enums.Gender;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class User {

    @NotBlank(message = "First Name is a required field")
    @Size(max = 15, min = 2, message = "First Name must be between 2 and 15 characters long")
    @Pattern(regexp = "[A-Z]\\w*", message = "First Name must starts with Uppercase character")
    private String firstName;

    @NotBlank(message = "First Name is a required field")
    @Size(max = 15, min = 2, message = "First Name must be between 2 and 15 characters long")
    @Pattern(regexp = "[A-Z]\\w*", message = "First Name must starts with Uppercase character")
    private String lastName;


    @Email(message = "Username is a required field")
    @Pattern(regexp = "^(?=.{1,64}@)[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)(\\.[A-Za-z]{2,})$",
            message = "Provide a suitable email address ex: michael23jordan23@test.com")
    private String userName;


    @NotBlank
    @Pattern(regexp = "(^(?=.[0-9])(?=.[a-z])(?=.[A-Z])(?=.[@#$%^&+=!])(?=\\S+$).{8,}$)")
    private String passWord;



    @NotBlank
    private String confirmPassWord;



    @NotNull(message = "Please select a role")
    private Role role;


    @NotNull(message = "Please select a Gender")
    private Gender gender;

    @Valid
    @NotNull
    private Address address;



    private void checkConfirmPassword() {
        if (this.passWord == null || this.confirmPassWord == null) {
            return;
        } else if (!this.passWord.equals(this.confirmPassWord)) {
            this.confirmPassWord = null;
        }
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
        checkConfirmPassword();

    }

    public void setConfirmPassWord(String confirmPassWord) {
        this.confirmPassWord = confirmPassWord;
        checkConfirmPassword();

    }
}


