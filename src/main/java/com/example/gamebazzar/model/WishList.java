package com.example.gamebazzar.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "_wishlists")
public class WishList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long wishlistId;

    @Column(nullable = false, name = "CreationDate")
    private LocalDate creationDate;

    @ManyToOne
    @JsonBackReference
    private User user;

    @OneToMany(mappedBy = "wishList", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<WishListItem> wishlistItems;

    public WishList(){

    }

    public WishList(User user, LocalDate creationDate) {
        this.user = user;
        this.creationDate = creationDate;
    }
}
