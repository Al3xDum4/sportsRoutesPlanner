package com.project.sportsRoutesPlanner.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //start from 1 on each table
    private Integer userId;

    private String username;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String password;
    private Integer age;
    private String phoneNo;
    private Integer history;

    @ManyToOne(fetch = FetchType.LAZY)
    private Role role;

    @ManyToMany(mappedBy = "users")
    @JsonIgnore
    private List<Event> eventsList;
}
