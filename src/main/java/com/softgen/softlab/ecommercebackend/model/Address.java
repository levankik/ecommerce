package com.softgen.softlab.ecommercebackend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "address_lioe_1", nullable = false, length = 512)
    private String addressLine1;

    @Column(name = "address_lioe_2", length = 512)
    private String addressLine2;

    @Column(name = "city", length = 255)
    private String city;

    @Column(name = "country", nullable = false, length = 45)
    private String country;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private LocalUser user;

}
