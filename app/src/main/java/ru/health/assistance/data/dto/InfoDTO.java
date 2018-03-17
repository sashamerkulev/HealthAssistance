package ru.health.assistance.data.dto;

import java.util.Date;

/**
 * Created by sasha_merkulev on 17.03.2018.
 */

public class InfoDTO {

    private String id;
    private String name;
    private String lastName;
    private String fatherName;
    private String sex;
    private Date birthDate;
    private String birthPlace;
    private String nationality;
    private String livingCity;
    private String livingPostcode;
    private String livingAddress;
    private String cause;

    public InfoDTO(String id, String name, String lastName, String fatherName,
                   String sex, Date birthDate, String birthPlace, String nationality,
                   String livingCity, String livingPostcode, String livingAddress, String cause) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.fatherName = fatherName;
        this.sex = sex;
        this.birthDate = birthDate;
        this.birthPlace = birthPlace;
        this.nationality = nationality;
        this.livingCity = livingCity;
        this.livingPostcode = livingPostcode;
        this.livingAddress = livingAddress;
        this.cause = cause;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getLivingCity() {
        return livingCity;
    }

    public void setLivingCity(String livingCity) {
        this.livingCity = livingCity;
    }

    public String getLivingPostcode() {
        return livingPostcode;
    }

    public void setLivingPostcode(String livingPostcode) {
        this.livingPostcode = livingPostcode;
    }

    public String getLivingAddress() {
        return livingAddress;
    }

    public void setLivingAddress(String livingAddress) {
        this.livingAddress = livingAddress;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InfoDTO infoDTO = (InfoDTO) o;
        return id != null ? id.equals(infoDTO.id) : infoDTO.id == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (fatherName != null ? fatherName.hashCode() : 0);
        result = 31 * result + (sex != null ? sex.hashCode() : 0);
        result = 31 * result + (birthDate != null ? birthDate.hashCode() : 0);
        result = 31 * result + (birthPlace != null ? birthPlace.hashCode() : 0);
        result = 31 * result + (nationality != null ? nationality.hashCode() : 0);
        result = 31 * result + (livingCity != null ? livingCity.hashCode() : 0);
        result = 31 * result + (livingPostcode != null ? livingPostcode.hashCode() : 0);
        result = 31 * result + (livingAddress != null ? livingAddress.hashCode() : 0);
        result = 31 * result + (cause != null ? cause.hashCode() : 0);
        return result;
    }
}
