package com.work.vladimirs.rocketscloud.inventory.jpa;

import lombok.Data;
import org.springframework.data.relational.core.mapping.Column;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
//@Table("Rocket_Order") // нужно для Spring Data, явное задание имени таблицы
@Entity // нужно для JPA
@Table(name = "Rocket_Order") // нужно для JPA
public class RocketOrderJpa implements Serializable {

    private static final long serialVersionUID = 1L;

//    @Id // нужно для Spring Data
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Date placeAt = new Date();

    @Column("delivery_name") // нужно для Spring Data, явное задание имени столбца
    @NotBlank(message = "Delivery name is required")
    private String deliveryName;
    @NotBlank(message = "Street is required")
    private String deliveryStreet;
    @NotBlank(message = "City is required")
    private String deliveryCity;
    @NotBlank(message = "State is required")
    private String deliveryState;
    @NotBlank(message = "Zip is required")
    private String deliveryZip;
//    @CreditCardNumber(message = "Not valid credit card number")
    private String ccNumber;
    @Pattern(regexp = "^(0[1-9]|1[0-2])([\\/]([2-9][0-9]))$",
            message = "Must be formatted MM/YY")
    private String ccExpiration;
    @Digits(integer = 3, fraction = 0, message = "Invalid CVV")
    private String ccCVV;
    @OneToMany(cascade = CascadeType.ALL)
    private List<RocketJpa> rockets = new ArrayList<>();

    public void addRocket(RocketJpa rocket) {
        this.rockets.add(rocket);
    }
}
