package com.scaffold.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Details of a Customer")
public class Customer {

    @ApiModelProperty(notes = "Id of the Customer")
    private String id;

    @ApiModelProperty(notes = "First Name of the Customer")
    private String firstName;

    @ApiModelProperty(notes = "Last Name of the Customer")
    private String lastName;

    @ApiModelProperty(notes = "Email Id  of the Customer")
    private String email;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
