package com.teknofest.turizm.model;


import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.List;

public class Place {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private String city;
    @Column
    private String address; // kullanıcıya tam adres gösterilebilir
    @Column
    private String region; // mahalle, bölge adı, semt gibi
    @Column
    private Double latitude;
    @Column
    private Double longitude;
    @Column(name="is_approved")
    private Boolean isApproved; // default: false
    @Column
    private String type;
    @Column
    private String description;
    @Column(name="image_url")
    private String imageUrl;
    @Column
    private Float rating;
    @Column
    private List<String> reviews;
}
