package com.expense.jforce.enity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="reg_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    private String userName;
    private String email;
    private String password;
    private String fullName;
}
