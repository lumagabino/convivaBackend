package com.conviva.app.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ItemModel {
    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY) // instantiates id as column identity
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "event_id", length = 60, nullable = false)
    private long event_id;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "quantity", nullable = false)
    private long quantity;

    @Column(name = "comment", nullable = false)
    private String comment;

    // Instantiates default class constructor so that it is not discarded in case we add customized class initializers
    public ItemModel() {
    }

    // Get and set methods
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public long getEventId() { return event_id; }
    public void setEventId(long event_id) { this.event_id = event_id; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public long getQuantity() { return quantity; }
    public void setQuantity(long quantity) { this.quantity = quantity; }

    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }

    @Override
    public String toString() {
        return "ItemModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", event_id=" + event_id +
                ", type='" + type + '\'' +
                ", quantity=" + quantity +
                ", comment='" + comment + '\'' +
                '}';
    }
}
