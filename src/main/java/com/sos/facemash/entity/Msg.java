package com.sos.facemash.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "msgs")
public class Msg {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "body")
    private String body;
    @Column(name = "date")
    private LocalDate date;
    @Column(name = "owner")
    private User owner;
    @Column(name = "destination")
    private User destination;
}
