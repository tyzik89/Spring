package com.work.vladimirs.shawermacloud.entity;

import org.hibernate.validator.constraints.CreditCardNumber;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order {

    private Long id;
    private Date placedAt;
    private List<Shawerma> shawermas = new ArrayList<Shawerma>();

    @NotBlank(message="Заполните!")
    private String name;

    @NotBlank(message="Заполните!")
    private String street;

    @NotBlank(message="Заполните!")
    private String city;

    @NotBlank(message="Заполните!")
    private String state;

    @NotBlank(message="Fill!")
    private String zip;

    //@CreditCardNumber(message = "Неправильный номер карты")
    private String ccNumber;

    //@Pattern(regexp="^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$", message="Должен быть формата MM/YY")
    private String ccExpiration;

    @Digits(integer=3, fraction=0, message="Неверный CVV")
    private String ccCVV;




    public Order(Long id, Date placedAt, String name, String street, String city,
                 String state, String zip, String ccNumber, String ccExpiration, String ccCVV) {
        this.id = id;
        this.placedAt = placedAt;
        this.name = name;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.ccNumber = ccNumber;
        this.ccExpiration = ccExpiration;
        this.ccCVV = ccCVV;
    }

    public Order() {
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", placedAt=" + placedAt +
                ", name='" + name + '\'' +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zip='" + zip + '\'' +
                ", ccNumber='" + ccNumber + '\'' +
                ", ccExpiration='" + ccExpiration + '\'' +
                ", ccCVV='" + ccCVV + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (!id.equals(order.id)) return false;
        if (!placedAt.equals(order.placedAt)) return false;
        if (!name.equals(order.name)) return false;
        if (!street.equals(order.street)) return false;
        if (!city.equals(order.city)) return false;
        if (!state.equals(order.state)) return false;
        if (!zip.equals(order.zip)) return false;
        if (!ccNumber.equals(order.ccNumber)) return false;
        if (!ccExpiration.equals(order.ccExpiration)) return false;
        return ccCVV.equals(order.ccCVV);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + placedAt.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + street.hashCode();
        result = 31 * result + city.hashCode();
        result = 31 * result + state.hashCode();
        result = 31 * result + zip.hashCode();
        result = 31 * result + ccNumber.hashCode();
        result = 31 * result + ccExpiration.hashCode();
        result = 31 * result + ccCVV.hashCode();
        return result;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getPlacedAt() {
        return placedAt;
    }

    public void setPlacedAt(Date placedAt) {
        this.placedAt = placedAt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCcNumber() {
        return ccNumber;
    }

    public void setCcNumber(String ccNumber) {
        this.ccNumber = ccNumber;
    }

    public String getCcExpiration() {
        return ccExpiration;
    }

    public void setCcExpiration(String ccExpiration) {
        this.ccExpiration = ccExpiration;
    }

    public String getCcCVV() {
        return ccCVV;
    }

    public void setCcCVV(String ccCVV) {
        this.ccCVV = ccCVV;
    }

    public void addShawerma(Shawerma shawerma) {
        shawermas.add(shawerma);
    }

    public List<Shawerma> getShawermas () {
        return shawermas;
    }
}
