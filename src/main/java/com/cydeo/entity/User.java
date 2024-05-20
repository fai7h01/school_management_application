package com.cydeo.entity;

import com.cydeo.enums.Gender;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@ToString

public class User {

    @NotBlank(message = "First Name is a required field")
    @Size(max = 15, min = 2, message = "First Name must be between 2 and 15 characters long")
    @Pattern(regexp = "[A-Z]\\w*", message = "First Name must starts with Uppercase character")
    private String firstName;

    @NotBlank(message = "Last Name is a required field")
    @Size(max = 15, min = 2, message = "Last Name must be between 2 and 15 characters long")
    @Pattern(regexp = "[A-Z]\\w*", message = "Last Name must starts with Uppercase character")
    private String lastName;


    @Email(message = "UserName is a required field")
    @Pattern(regexp = "^(?=.{1,64}@)[A-Za-z0-9-]+(.[A-Za-z0-9-]+)@[^-][A-Za-z0-9-]+(.[A-Za-z0-9-]+)(.[A-Za-z]{2,})$", message = "Provide a suitable email address ex: michael23jordan23@test.com")
    private String userName;


    @NotBlank
    @Pattern(regexp ="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,20}$", message = "Contains at least one digit. Contains at least one lowercase letter. Contains at least one uppercase letter. Contains at least one special character from the provided set. Does not contain any whitespace.  Is at least 8 characters long.")
    private String password;



    @NotNull
    private String confirmPassword;



    @NotNull(message = "Please select a role")
    private Role role;


    @NotNull(message = "Please select a Gender")
    private Gender gender;

    @Valid
    @NotNull
    private Address address;



    public String getPassword() {
        return password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }



    public void setPassword(String password) {
        this.password = password;
        checkConfirmPassword();
    }



    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        checkConfirmPassword();
    }

    private void checkConfirmPassword() {
        if (this.password == null || this.confirmPassword == null) {
            return;
        } else if (!this.password.equals(this.confirmPassword)) {
            this.confirmPassword = null;
        }
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    public void setRole(Role role) {
        this.role = role;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUserName() {
        return userName;
    }



    public Role getRole() {
        return role;
    }

    public Gender getGender() {
        return gender;
    }

    public Address getAddress() {
        return address;
    }
}


