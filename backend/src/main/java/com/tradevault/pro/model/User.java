package com.tradevault.pro.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@Document(collection = "users")
public class User {
    @Id
    private String id;

    private String userName;
    private String email;
    private String password; //encrypt it later

    private List<String> roles; //["USER"], ["USER", "ADMIN"]

    private LocalDateTime createdAt;
    private LocalDateTime lastLogin;
}
