package com.work.vladimirs.rocketscloud.models.services;

import com.work.vladimirs.rocketscloud.models.entities.User;
import com.work.vladimirs.rocketscloud.models.inventory.Rocket;
import org.hibernate.validator.constraints.CreditCardNumber;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity   //Using for JPA
@Table(name = "Rocket_Order")   //Using for JPA
public class Order implements Serializable {   //Using for JPA

    private static final long serialVersionUID = 1L;   //Using for JPA

    @Id   //Using for JPA
    @GeneratedValue(strategy = GenerationType.AUTO)   //Using for JPA
    private Long id;

    private Date placedAt;

    @NotBlank(message="Name is required")
    private String deliveryName;

    @NotBlank(message="Street is required")
    private String deliveryStreet;

    @NotBlank(message="City is required")
    private String deliveryCity;

    @NotBlank(message="State is required")
    private String deliveryState;

    @NotBlank(message="Zip is required")
    private String deliveryZip;

    @CreditCardNumber(message="Not a valid credit card number")
    private String ccNumber;

    @Pattern(regexp="^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$", message="Must be formatted MM/YY")
    private String ccExpiration;

    @Digits(integer = 3, fraction = 0, message = "Invalid CVV")
    private String ccCVV;

    @ManyToMany(targetEntity = Rocket.class)   //Using for JPA
    private final List<Rocket> rockets = new ArrayList<>();

    @ManyToOne              // User may have many orders and order belongs to a single user
    private User user;      //Used to for saving order in user account

    public Order() {
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

    public String getDeliveryName() {
        return deliveryName;
    }

    public void setDeliveryName(String deliveryName) {
        this.deliveryName = deliveryName;
    }

    public String getDeliveryStreet() {
        return deliveryStreet;
    }

    public void setDeliveryStreet(String deliveryStreet) {
        this.deliveryStreet = deliveryStreet;
    }

    public String getDeliveryCity() {
        return deliveryCity;
    }

    public void setDeliveryCity(String deliveryCity) {
        this.deliveryCity = deliveryCity;
    }

    public String getDeliveryState() {
        return deliveryState;
    }

    public void setDeliveryState(String deliveryState) {
        this.deliveryState = deliveryState;
    }

    public String getDeliveryZip() {
        return deliveryZip;
    }

    public void setDeliveryZip(String deliveryZip) {
        this.deliveryZip = deliveryZip;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Rocket> getRockets() {
        return rockets;
    }

    public void addRocket(Rocket rocket) {
        this.rockets.add(rocket);
    }

    @PrePersist   //Using for JPA
    void placedAt() {
        this.placedAt = new Date();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (!id.equals(order.id)) return false;
        if (!placedAt.equals(order.placedAt)) return false;
        if (!deliveryName.equals(order.deliveryName)) return false;
        if (!deliveryStreet.equals(order.deliveryStreet)) return false;
        if (!deliveryCity.equals(order.deliveryCity)) return false;
        if (!deliveryState.equals(order.deliveryState)) return false;
        if (!deliveryZip.equals(order.deliveryZip)) return false;
        if (!ccNumber.equals(order.ccNumber)) return false;
        if (!ccExpiration.equals(order.ccExpiration)) return false;
        if (!ccCVV.equals(order.ccCVV)) return false;
        return rockets.equals(order.rockets);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + placedAt.hashCode();
        result = 31 * result + deliveryName.hashCode();
        result = 31 * result + deliveryStreet.hashCode();
        result = 31 * result + deliveryCity.hashCode();
        result = 31 * result + deliveryState.hashCode();
        result = 31 * result + deliveryZip.hashCode();
        result = 31 * result + ccNumber.hashCode();
        result = 31 * result + ccExpiration.hashCode();
        result = 31 * result + ccCVV.hashCode();
        result = 31 * result + rockets.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", placedAt=" + placedAt +
                ", deliveryName='" + deliveryName + '\'' +
                ", deliveryStreet='" + deliveryStreet + '\'' +
                ", deliveryCity='" + deliveryCity + '\'' +
                ", deliveryState='" + deliveryState + '\'' +
                ", deliveryZip='" + deliveryZip + '\'' +
                ", ccNumber='" + ccNumber + '\'' +
                ", ccExpiration='" + ccExpiration + '\'' +
                ", ccCVV='" + ccCVV + '\'' +
                ", rockets=" + rockets +
                '}';
    }
}
