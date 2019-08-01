package com.sos.facemash.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import java.util.List;

@JsonRootName(value = "Users")
public class UsersDTO {
    @JsonProperty(value = "users")
    private List<UserSummaryDTO> users;

    public UsersDTO(List<UserSummaryDTO> users) {
        this.users = users;
    }

    public List<UserSummaryDTO> getUsers() {
        return users;
    }
}
