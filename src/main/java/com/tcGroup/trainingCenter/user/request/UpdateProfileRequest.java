package com.tcGroup.trainingCenter.user.request;

import java.io.Serializable;
import java.util.Date;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString
public class UpdateProfileRequest implements Serializable {
    
    private Long accountId;

    private String firstName;

    private String lastName;

    private String identifier;

    private Date birthdate;

    private String description;
}