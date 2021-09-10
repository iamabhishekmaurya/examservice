package com.exam.model;

import lombok.*;

import javax.persistence.*;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "user_roles")
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userRoleId;
    // User mapping
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    // Role Mapping
    @ManyToOne
    private Role role;

}
