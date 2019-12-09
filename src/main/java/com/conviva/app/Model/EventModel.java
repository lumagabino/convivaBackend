package com.conviva.app.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class EventModel {

    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY) // instantiates id as column identity
    private long id;

    @Column(name = "name", length = 60, nullable = false)
    private String name;

    @Column
    private String description;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "cost", nullable = false)
    private int cost;

    @Column(name = "justification", nullable = false)
    private String justification;

    @Column(name = "date", nullable = false)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm") // defines the json pattern so that it only accepts date strings
    private Date date;

    @Column(name = "complaint", nullable = false)
    private int complaint;

    @Column(name = "adm", length = 60, nullable = false)
    private long adm;

    @Column(name = "latitude", nullable = false)
    private Double latitude;

    @Column(name = "longitude", nullable = false)
    private Double longitude;

    @Column(name = "item", nullable = false)
    private String item;

    @Column(name = "people", nullable = false)
    private String people;

    // Instantiates default class constructor so that it is not discarded in case we add customized class initializers
    public EventModel() {
    }

    // Get and set methods
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

    public int getCost() {
        return cost;
    }
    public void setCost(int cost) {
        this.cost = cost;
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

    public long getAdm() {
        return adm;
    }
    public void setAdm(long adm) {
        this.adm = adm;
    }

    public Double getLatitude() { return latitude; }
    public void setLatitude(Double latitude) { this.latitude = latitude; }

    public Double  getLongitude() { return longitude; }
    public void setLongitude(Double longitude) { this.longitude = longitude; }

    public String getItem() {
        return item;
    }

    public void setItens(String item) {
        this.item = item;
    }

    public String getPeople() {
        return people;
    }

    public void setPeople(String people) {
        this.people = people;
    }

    @Override
    public String toString() {
        return "EventModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", address='" + address + '\'' +
                ", cost=" + cost + '\'' +
                ", justification='" + justification + '\'' +
                ", date=" + date + '\'' +
                ", complaint=" + complaint + '\'' +
                ", adm='" + adm + '\'' +
                ", latitude=" + latitude + '\'' +
                ", longitude=" + longitude + '\'' +
                ", item=" + item + '\'' +
                ", people=" + people +
                '}';
    }
}
