package com.project.sportsRoutesPlanner.model;

import lombok.*;
import javax.persistence.*;
import java.util.*;



public enum Role {
    MEMBER("member"),
    GUIDE("guide"),
    ADMIN("admin");

    Role(String roleName) {
        this.roleName = roleName;
    }

    private String roleName;


}
