package com.project.sportsRoutesPlanner.model;

import lombok.*;
import javax.persistence.*;
import java.util.*;


@Entity
@Table
@Data
@NoArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer roleId;

    private String roleName;

    @OneToMany(mappedBy = "role" , cascade = CascadeType.ALL)
    private List<User> users;
}
