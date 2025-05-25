package com.finsavvy.api.finsavvy_api.v1.entities.account;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.finsavvy.api.finsavvy_api.v1.entities.Budget;
import com.finsavvy.api.finsavvy_api.v1.entities.User;
import jakarta.persistence.*;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(
        name = "accounts",
        uniqueConstraints = {
                @UniqueConstraint(name = "unique_bank", columnNames = {
                        "bank_account_type_id",
                        "user_id",
                        "bankName"
                })
        },
        indexes = {
                @Index(name = "idx_user_account", columnList = "user_id"),
                @Index(name = "idx_user_account_uuid", columnList = "uuid,user_id")
        }
)
@SQLDelete(sql = "UPDATE accounts SET deleted_at = CURRENT_TIMESTAMP WHERE id = ?")
@SQLRestriction("deleted_at IS NULL")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bank_account_type_id", nullable = false)
    @JsonBackReference
    private BankAccountType bankAccountType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_currency_id", nullable = false)
    @JsonBackReference
    private Budget.CountryCurrency countryCurrency;

    @Column(nullable = false)
    private String bankName;

    @Column(nullable = false, unique = true)
    @UuidGenerator
    private String uuid;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    private LocalDateTime deletedAt;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AccountUser> accountUsers;
}