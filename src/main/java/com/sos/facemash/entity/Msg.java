package com.sos.facemash.entity;


import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "messages")
public class Msg {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String body;
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDate date;
    @Column(nullable = false)
    private String idOwner;
    private String idDestination;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getIdOwner() {
        return idOwner;
    }

    public void setIdOwner(String idOwner) {
        this.idOwner = idOwner;
    }

    public String getIdDestination() {
        return idDestination;
    }

    public void setIdDestination(String idDestination) {
        this.idDestination = idDestination;
    }

    public static class Builder{
        private Long id;
        private String body;
        private LocalDate date;
        private String idOwner;
        private String idDestination;

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setBody(String body) {
            this.body = body;
            return this;
        }

        public Builder setDate(LocalDate date) {
            this.date = date;
            return this;
        }

        public Builder setIdOwner(String idOwner) {
            this.idOwner = idOwner;
            return this;
        }

        public Builder setIdDestination(String idDestination) {
            this.idDestination = idDestination;
            return this;
        }
        public Msg build(){
            Msg msg = new Msg();
            msg.setId(id);
            msg.setBody(body);
            msg.setDate(date);
            msg.setIdDestination(idDestination);
            msg.setIdOwner(idOwner);
            return msg;

        }
    }
}
