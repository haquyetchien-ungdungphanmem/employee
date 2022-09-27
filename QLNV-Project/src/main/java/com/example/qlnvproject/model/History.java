package com.example.qlnvproject.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "history")
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String username;
    private String method;
    private String requesturi;
    private String request;
    private int statuss;
    private String response;
    private int timetaken;

    private Date implementdate;

    public History(String username, String method, String requestURI, String requestBody, int status, String responseBody, long timeTaken, Date date) {
        this.username = username;
        this.method = method;
        this.requesturi = requestURI;
        this.request = requestBody;
        this.statuss = status;
        this.response = responseBody;
        this.timetaken = (int) timeTaken;
        this.implementdate = date;
    }

    public History() {

    }
}
