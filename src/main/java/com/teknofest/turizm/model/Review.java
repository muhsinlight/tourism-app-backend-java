package com.teknofest.turizm.model;

import com.teknofest.turizm.model.audit.AuditAll;
import jakarta.persistence.*;

@Entity
@Table(name = "reviews")
public class Review extends AuditAll<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

    private Integer rating;
    private String comment;

    // FIXME:
    //@Type(type = "json")
    //@Column(columnDefinition = "json")
    @Column
    private String photos;

    @Column(name = "likes_count")
    private Integer likesCount;

    @Column(name = "visibility_status")
    private String visibilityStatus;

    public Review(Integer id, User user, Location location, Integer rating, String comment, String photos, Integer likesCount, String visibilityStatus) {
        this.id = id;
        this.user = user;
        this.location = location;
        this.rating = rating;
        this.comment = comment;
        this.photos = photos;
        this.likesCount = likesCount;
        this.visibilityStatus = visibilityStatus;
    }

    public Review() {

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

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getPhotos() {
        return photos;
    }

    public void setPhotos(String photos) {
        this.photos = photos;
    }

    public Integer getLikesCount() {
        return likesCount;
    }

    public void setLikesCount(Integer likesCount) {
        this.likesCount = likesCount;
    }

    public String getVisibilityStatus() {
        return visibilityStatus;
    }

    public void setVisibilityStatus(String visibilityStatus) {
        this.visibilityStatus = visibilityStatus;
    }
}
