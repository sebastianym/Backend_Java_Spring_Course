package com.giweb.supply.business.primertallergiweb.dto;

import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String email;
    private String username;
    private Name name;

    @Data
    public static class Name {
        private String firstname;
        private String lastname;
    }
}
