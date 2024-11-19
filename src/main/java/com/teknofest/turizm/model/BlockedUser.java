package com.teknofest.turizm.model;

import com.teknofest.turizm.model.audit.AuditAll;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "blocked_users")
public class BlockedUser extends AuditAll<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "blocked_user_id")
    private User blockedUser;

    public BlockedUser(Integer id, User user, User blockedUser) {
        this.id = id;
        this.user = user;
        this.blockedUser = blockedUser;
    }

    public BlockedUser() {
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

    public User getBlockedUser() {
        return blockedUser;
    }

    public void setBlockedUser(User blockedUser) {
        this.blockedUser = blockedUser;
    }

}
