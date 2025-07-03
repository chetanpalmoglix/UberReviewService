package com.example.UberReviewService.models;

//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "bookingreview")
public class Review extends BaseModel{

    @Column(nullable = false)
    private String content;

    private Double rating;

    @Override
    public String toString() {
        return "Review: " + this.content + " " + this.rating + " " + this.createdAt;
    }
}
