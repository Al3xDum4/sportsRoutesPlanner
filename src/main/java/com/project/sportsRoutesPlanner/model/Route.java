package com.project.sportsRoutesPlanner.model;

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

    private double distance;
    private double maxAltitude;
    private double duration;

    private List<Photo> photoList;

    private RouteCategory routeCategory;

    private DifficultyLevel difficultyLevel;

}
