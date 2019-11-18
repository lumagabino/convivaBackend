package com.conviva.app.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;

// Modelo da tabela/classe/json a ser criada
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) // Gambiarra encontrada na internet.
public class EventModel {
    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Tem que instanciar id da coluna como identidade dela
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
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss") // Define o padrão do Json pra não aceitar qualquer String como dado
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

    // get: pra pegar o valor da variável
    // set: mudar o valor da variável
    // As variáveis estão private, então tem que usar isso pra manipular elas nas outras classes
    // Por alguma escrotisse de Java, é melhor fazer isso do que deixar a variável como public ou protected
    // (i.e., deixar classes que herdam dela acessarem e mudarem essas variáveis)
    // CorreçãO: é bom encapsular as coisas em orientação objeto pra uma classe não conseguir fuder com a outra
    // (como  Swift é meio várzea porque é 30 paradigmas misturados, lá não tem tanto essa preocupação)
    // Também quando estiver debugando dá pra saber o ponto que a variável mudou e deu treta
    // As classes que herdam conseguem dar override nesses métodos pra customizar do jeito que for mais relevante
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

    // Como fica o objeto Event quando transformado em String pro json
    @Override
    public String toString() {
        return "Event {" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

}
