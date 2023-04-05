package com.vaadin.demo.entities;

import jakarta.persistence.*;
import java.util.Date;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotEmpty;

/**
 * Created by mstahv
 */
@MappedSuperclass
public abstract class Person extends AbstractEntity {

    @NotEmpty
    private String firstName;
    @NotNull
    private String lastName;
    private String middleName;
    private String title;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Temporal(jakarta.persistence.TemporalType.DATE)
    private Date birthDate;
    private String ssn;
    private String pictureUrl;

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Person() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(firstName);
        if (middleName != null) {
            sb.append(" ");
            sb.append(middleName);
        }
        sb.append(" ");
        sb.append(lastName);
        return sb.toString();
    }
}
