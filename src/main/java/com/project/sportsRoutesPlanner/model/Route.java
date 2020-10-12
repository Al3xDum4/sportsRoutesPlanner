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
@Table(name = "routes")
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // start from 1 on each table
    private Integer routeId;

    private String routeName;

    private String description;

    private Double distance;
    private Double maxAltitude;
    private Double duration;

    private RouteCategory routeCategory;

    private DifficultyLevel difficultyLevel;

    @Lob
    private byte[] image;

    @OneToMany(mappedBy = "route", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Event> event;

    @OneToMany(mappedBy = "route", cascade = CascadeType.ALL)
    private List<Photo> photosList;
}
