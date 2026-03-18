package com.crm.customerservice.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true)
    private String firstName;

    @Column(nullable = true)
    private String lastName;

    @Column(unique = true, nullable = true)
    private String email;

    @Column(nullable = true)
    private String phone;

    @Column(nullable = true)
    private Long idUserCreate;

    @Column(nullable = true)
    private LocalDateTime createdAt;

    @Column(nullable = true)
    private Long idUserUpdate;

    @Column(nullable = true)
    private LocalDateTime updatedAt;

    public Customer() {}

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public String getEmail() { return email; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getPhone() { return phone; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public Long getIdUserCreate() { return idUserCreate; }

    public void setEmail(String email) {
        this.email = email;
    }
    public void setFirstName(String firstName) { this.firstName = firstName;}
    public void setLastName(String lastName) { this.lastName = lastName;}
    public void setPhone(String phone) { this.phone = phone;}
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt;}
    public void setIdUserCreate(Long idUserCreate) { this.idUserCreate = idUserCreate;}


    public Long getIdUserUpdate() {
        return idUserUpdate;
    }

    public void setIdUserUpdate(Long idUserUpdate) {
        this.idUserUpdate = idUserUpdate;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
