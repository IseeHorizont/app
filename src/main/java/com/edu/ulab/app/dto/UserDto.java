package com.edu.ulab.app.dto;

import lombok.Data;

@Data
public class UserDto {
    private Long userId;
    private String fullName;
    private String title;
    private int age;
}
