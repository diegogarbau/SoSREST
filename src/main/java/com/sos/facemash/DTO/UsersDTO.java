package com.sos.facemash.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import java.util.ArrayList;
import java.util.List;

@JsonRootName(value = "Users")
public class UsersDTO {
    @JsonProperty(value = "users")
    private List<UserSummaryDTO> users;

    public UsersDTO() {
        super();
        users = new ArrayList<>();
    }

    public List<UserSummaryDTO> getUsers() {
        return users;
    }

    public void setUsers(List<UserSummaryDTO> users) {
        this.users = users;
    }

    public UsersDTO insertAll(List<UserSummaryDTO> newUsers) {
        users.addAll(newUsers);
        return this;
    }
}
