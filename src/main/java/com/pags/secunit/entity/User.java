package com.pags.secunit.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private Double money;

    private Integer cpf;

    private String address;

    @Column(name = "ADDRESS_NUMBER")
    private Integer addressNumber;

    private String email;

    private String password;
}
