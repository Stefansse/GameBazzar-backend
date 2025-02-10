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

    @Column(nullable = true, name = "CreationDate")
    private LocalDate creationDate;

    @OneToOne
    @JoinColumn(name = "user_id")  // This column will store the user id in the WishList table
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
