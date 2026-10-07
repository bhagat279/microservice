package com.ratingservice.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Rating {

    @Id
    private String ratingId;

    private String userId;
    private String hotelId;
    private String rating;
    private String feedback;
}
