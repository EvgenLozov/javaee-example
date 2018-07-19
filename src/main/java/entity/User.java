package entity;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.json.Json;
import javax.json.JsonObject;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "user")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "sureName")
    private String sureName;

    @Column(name = "dateOfBirth")
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSureName() {
        return sureName;
    }

    public void setSureName(String sureName) {
        this.sureName = sureName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public static JsonObject toJson(User user){
        if (user == null)
            return null;

        return Json.createObjectBuilder()
                .add("id", user.id)
                .add("firstName", user.firstName)
                .add("sureName", user.sureName)
//                .add("dateOfBirth", dateOfBirth.toString())
                .add("status", user.status.name()).build();
    }
}
