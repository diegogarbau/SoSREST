package com.sos.facemash.entity;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "messages")
public class Msg {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String body;
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @ManyToOne
    @JoinColumn(name = "messagesOwner", nullable = false)
    private User owner;
    @ManyToOne
    @JoinColumn(name = "messagesDestination")
    private User destination;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getOwner() {
        return owner;
    }

    public Msg setOwner(User owner) {
        this.owner = owner;
        return this;
    }

    public User getDestination() {
        return destination;
    }

    public void setDestination(User destination) {
        this.destination = destination;
    }

    public Msg updateMsg(Msg msg) {
        this.setTitle(msg.getTitle());
        this.setBody(msg.getBody());
        this.setDate(msg.getDate());
        return this;
    }


    public static class Builder {
        private Long id;
        private String title;
        private String body;
        private Date date;
        private User owner;
        private User destination;

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setBody(String body) {
            this.body = body;
            return this;
        }

        public Builder setDate(Date date) {
            this.date = date;
            return this;
        }

        public Builder setOwner(User owner) {
            this.owner = owner;
            return this;
        }

        public Builder setDestination(User destination) {
            this.destination = destination;
            return this;
        }

        public Msg build() {
            Msg msg = new Msg();
            msg.setId(id);
            msg.setTitle(title);
            msg.setBody(body);
            msg.setDate(date);
            msg.setDestination(destination);
            msg.setOwner(owner);
            return msg;

        }
    }
}
