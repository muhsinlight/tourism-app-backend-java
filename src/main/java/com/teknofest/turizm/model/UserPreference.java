package com.teknofest.turizm.model;

import com.teknofest.turizm.model.audit.AuditAll;
import jakarta.persistence.*;

@Entity
@Table(name = "user_preferences")
public class UserPreference extends AuditAll<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    private Boolean showLocation;
    private Boolean allowMessages;

    public UserPreference(Integer id, User user, Boolean showLocation, Boolean allowMessages) {
        this.id = id;
        this.user = user;
        this.showLocation = showLocation;
        this.allowMessages = allowMessages;
    }

    public UserPreference() {

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

    public Boolean getShowLocation() {
        return showLocation;
    }

    public void setShowLocation(Boolean showLocation) {
        this.showLocation = showLocation;
    }

    public Boolean getAllowMessages() {
        return allowMessages;
    }

    public void setAllowMessages(Boolean allowMessages) {
        this.allowMessages = allowMessages;
    }
}

