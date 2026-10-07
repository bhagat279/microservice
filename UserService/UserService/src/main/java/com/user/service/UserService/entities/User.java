package com.user.service.UserService.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "micro_user")
public class User {

    @Id
    private  String userId;

    @JsonProperty("user-name")
    private  String name;
    private  String email;
    private  String about;

    @Transient  //It means rating will not store in database
    private List<Rating> ratings=new ArrayList<>();





}
