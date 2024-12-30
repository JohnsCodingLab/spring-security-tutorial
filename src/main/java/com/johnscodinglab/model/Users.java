package com.johnscodinglab.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Users {
    @Id
    private Integer id;
    private String username;
    private String password;
}
