package com.ioritz.CarRentingSystem_SERVER.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

/**
 * This class represents the invoice
 * @since 0.0.1
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Invoice {
    /**
     * This is the id of the invoice
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * The rental of this invoice
     */
    @OneToOne(mappedBy = "invoice")
    private Rental rental;

    /**
     * The date when the invoice is issued
     */
    private LocalDate issueDate;
    /**
     * The amount of the invoice
     */
    private Double amount;
}
