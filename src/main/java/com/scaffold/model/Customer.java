package com.scaffold.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(description = "Details of a Customer")
@Getter
@Setter
public class Customer {

    @ApiModelProperty(notes = "Id of the Customer")
    private String id;

    @ApiModelProperty(notes = "First Name of the Customer")
    private String firstName;

    @ApiModelProperty(notes = "Last Name of the Customer")
    private String lastName;

    @ApiModelProperty(notes = "Email Id  of the Customer")
    private String email;

}
