package com.finsavvy.api.finsavvy_api.v1.entities;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "budgets")
public class Budget {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    @UuidGenerator
    private String uuid;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Entity
    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Table(name = "country_currencies")
    @SQLDelete(sql = "UPDATE country_currencies SET deleted_at = CURRENT_TIMESTAMP WHERE id = ?")
    @SQLRestriction("deleted_at IS NULL")
    public static class CountryCurrency {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(nullable = false, unique = true)
        @UuidGenerator
        private String uuid;

        @Column(nullable = false, unique = true, length = 100)
        private String countryName;

        @Column(unique = true, length = 2)
        private String iso;

        @Column(unique = true, length = 3)
        private String iso3;

        @Column(nullable = false, length = 10)
        private String dialExtension;

        @Column(nullable = false, length = 3)
        private String currencyCode;

        @Column(nullable = false, length = 10)
        private String currencySymbol;

        @Column(nullable = false, length = 100)
        private String currencyName;

        @Column(nullable = false, updatable = false)
        @CreationTimestamp
        private LocalDateTime createdAt;

        @UpdateTimestamp
        private LocalDateTime updatedAt;

        private LocalDateTime deletedAt;
    }
}
