package com.SENA.CRUD_MODULE.userClass;
import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="users")
public class User implements Serializable{
    
    private static Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private String surname;
    private Integer number;
    private String email;
    private String password;
    private String register_date;


    public User(){

    }

    public User(String name, String surname, Integer number, String email, String password){
        this.name = name;
        this.surname = surname;
        this.number = number;
        this.email = email;
        this.password = password;

        if (this.number < 5 && this.number > 13){
            throw new IllegalArgumentException("Number must be between 5 and 13");
        }
    }
}
