package com.project.sportsRoutesPlanner.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.sql.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "events")
public class Event {

    private Integer maxParticipants;
    private Date signUpDeadline;
    private Date startEvent;
    private Date endEvent;
    private Integer price;

    private Guide guide;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int eventId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Route route;

    @ManyToMany
    @JsonIgnore
    @JoinTable(name = "event_users", joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> usersList;

}
