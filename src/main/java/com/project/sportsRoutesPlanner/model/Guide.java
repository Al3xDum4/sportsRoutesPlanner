package com.project.sportsRoutesPlanner.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.persistence.*;
import java.util.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "guides")
public class Guide {
    private String firstName;
    private String lastName;
    private Integer age;
    private String phoneNo;
    private GuideLevel guideLevel;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer guideId;

    @ManyToMany
    private List<Event> eventList;

}
