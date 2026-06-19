package com.alejandro.carritodecompras.user.models.dtos;

import com.alejandro.carritodecompras.user.models.entities.User;

public class UserResponseDto {
    Long id;
    String name;
    String lastname;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    // Fabric method
    // Converts a User object (entity) into a UserResponse (DTO).
    public static UserResponseDto fromUser(User user) {
        UserResponseDto dto = new UserResponseDto();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setLastname(user.getLastname());

        return dto;
    }
    
}
