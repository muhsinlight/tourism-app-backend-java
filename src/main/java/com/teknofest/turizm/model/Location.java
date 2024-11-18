package com.teknofest.turizm.model;

import com.teknofest.turizm.model.audit.AuditAll;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "locations")
public class Location extends AuditAll<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private Float latitude;
    private Float longitude;

    @Column(name = "is_current")
    private Boolean isCurrent;

    // FIXME: anlamadım
    private LocalDateTime timestamp;

    public Location(Integer id, User user, Float latitude, Float longitude, Boolean isCurrent, LocalDateTime timestamp) {
        this.id = id;
        this.user = user;
        this.latitude = latitude;
        this.longitude = longitude;
        this.isCurrent = isCurrent;
        this.timestamp = timestamp;
    }
    public Location() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    public Boolean getCurrent() {
        return isCurrent;
    }

    public void setCurrent(Boolean current) {
        isCurrent = current;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
