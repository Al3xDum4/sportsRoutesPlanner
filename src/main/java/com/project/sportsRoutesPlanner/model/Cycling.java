package com.project.sportsRoutesPlanner.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Cycling {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // start from 1 on each table
    private Integer cyclingRouteId;
}
