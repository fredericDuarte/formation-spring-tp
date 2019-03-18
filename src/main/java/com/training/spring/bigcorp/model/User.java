package com.training.spring.bigcorp.model;



import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.*;


import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Id
    @Size(max = 200)
    private String username;
    @NotNull
    @Size(max = 200)
    private String password;
    @NotNull
    private boolean enabled;
    @OneToMany
    private Set<Authority> authorities;




}