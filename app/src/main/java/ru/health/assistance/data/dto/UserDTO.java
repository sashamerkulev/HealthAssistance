package ru.health.assistance.data.dto;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by sasha_merkulev on 17.03.2018.
 */

public class UserDTO {
    @SerializedName("id")
    private String id;
    @SerializedName("micardSerial")
    private String micardSerial;
    @SerializedName("micardNumber")
    private String micardNumber;
    @SerializedName("surname")
    private String surname;
    @SerializedName("name")
    private String name;
    @SerializedName("patronymic")
    private String patronymic;
    @SerializedName("birthDate")
    private Date birthDate;
    @SerializedName("sex")
    private int sex;
    @SerializedName("document")
    private String document;
    @SerializedName("visaNumber")
    private String visaNumber;
    @SerializedName("purpose")
    private String purpose;
    @SerializedName("host")
    private String host;
    @SerializedName("photo")
    private String photo;
    @SerializedName("from")
    private Date from;
    @SerializedName("to")
    private Date to;

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
