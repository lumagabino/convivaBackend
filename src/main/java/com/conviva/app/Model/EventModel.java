package com.conviva.app.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) // Gambiarra encontrada na internet.
public class EventModel {
    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", length = 60, nullable = false)
    private String name;

    @Column
    private String description;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "cust", nullable = false)
    private int cust;

    @Column(name = "justif", nullable = false)
    private String justification;

    @Column(name = "date", nullable = false)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date date;

    @Column(name = "complaint", nullable = false)
    private int complaint;

    @Column(name = "adm", length = 60, nullable = false)
    private String adm;


    public EventModel() {

    }

    public EventModel(String name, String description){
        this.name = name;
        this.description = description;
    }

    public EventModel(long id, String name, String description){
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getCust() {
        return cust;
    }

    public void setCust(int cust) {
        this.cust = cust;
    }

    public String getJustification() {
        return justification;
    }

    public void setJustification(String justification) {
        this.justification = justification;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getComplaint() {
        return complaint;
    }

    public void setComplaint(int complaint) {
        this.complaint = complaint;
    }

    public String getAdm() {
        return adm;
    }

    public void setAdm(String adm) {
        this.adm = adm;
    }

    @Override
    public String toString() {
        return "Event {" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

}
