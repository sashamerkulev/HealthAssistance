package ru.health.assistance.data.database.entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.Date;

/**
 * Created by sasha_merkulev on 27.03.2018.
 */

@Entity(tableName = "users")
public class UserEntity {

    @PrimaryKey
    @NonNull
    private String id;
    private String micardSerial;
    private String micardNumber;
    private String surname;
    private String name;
    private String patronymic;
    private Date birthDate;
    private int sex;
    private String document;
    private String visaNumber;
    private String purpose;
    private String host;
    private String photo;
    private Date from;
    private Date to;

    public UserEntity(@NonNull String id, String micardSerial, String micardNumber,
                      String surname, String name, String patronymic, Date birthDate,
                      int sex, String document, String visaNumber, String purpose, String host, String photo,
                      Date from, Date to) {
        this.id = id;
        this.micardSerial = micardSerial;
        this.micardNumber = micardNumber;
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.birthDate = birthDate;
        this.sex = sex;
        this.document = document;
        this.visaNumber = visaNumber;
        this.purpose = purpose;
        this.host = host;
        this.photo = photo;
        this.from = from;
        this.to = to;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public String getMicardSerial() {
        return micardSerial;
    }

    public void setMicardSerial(String micardSerial) {
        this.micardSerial = micardSerial;
    }

    public String getMicardNumber() {
        return micardNumber;
    }

    public void setMicardNumber(String micardNumber) {
        this.micardNumber = micardNumber;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getVisaNumber() {
        return visaNumber;
    }

    public void setVisaNumber(String visaNumber) {
        this.visaNumber = visaNumber;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Date getFrom() {
        return from;
    }

    public void setFrom(Date from) {
        this.from = from;
    }

    public Date getTo() {
        return to;
    }

    public void setTo(Date to) {
        this.to = to;
    }
}
