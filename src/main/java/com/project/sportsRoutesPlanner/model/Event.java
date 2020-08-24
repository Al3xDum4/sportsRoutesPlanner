package com.project.sportsRoutesPlanner.model;

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

private int maxParticipants;
private Date signUpDeadline;
private Date startEvent;
private Date endEvent;

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int eventId;

private Route route;
private Guide guide;
private List<Member> members;

}
