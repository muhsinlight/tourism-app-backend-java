package com.teknofest.turizm.model;

import jakarta.persistence.*;

@Entity
@Table(name = "notification_types")
public class NotificationType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String typeName;

    public NotificationType(Integer id, String typeName) {
        this.id = id;
        this.typeName = typeName;
    }

    public NotificationType() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

}

