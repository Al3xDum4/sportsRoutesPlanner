package com.project.sportsRoutesPlanner.model;

import lombok.*;

import javax.persistence.*;
import java.util.*;

public enum Role {
    MEMBER("member"),
    GUIDE("guide"),
    ADMIN("admin");

    private String roleName;

    Role(String roleName) {
        this.roleName = roleName;
    }
}
