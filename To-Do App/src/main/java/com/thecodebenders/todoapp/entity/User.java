package com.thecodebenders.todoapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Optional;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User extends EntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column (nullable = false, unique = true)
    private String userName;

    @Column(nullable = false)
    private String password;

    public User mergeUser(User userToMerge){
        this.setPassword(Optional.ofNullable(userToMerge.getPassword()).orElse(this.password));
        return this;
    }
}
