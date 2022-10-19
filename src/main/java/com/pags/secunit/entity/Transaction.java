package com.pags.secunit.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "BANK_TRANSACTION")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "SENT_ID")
    private Integer sentId;

    @Column(name = "TRANSACTION_VALUE")
    private Double value;

    @Column(name = "RECEIVER_ID")
    private Integer receiverId;

    private Boolean success;

    @Nullable
    private String reason;
}
