package com.Officeapp.backendev.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document(collection = "users")
public class User {

    @Id
    private String id;            // MongoDB auto-generates this

    private String name;
    private String email;
    private String phone;
    private String password;

    private String status = "pending";   // default value
    private String role = "user";        // default value
}
