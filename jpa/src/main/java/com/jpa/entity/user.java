package com.jpa.entity;

import lombok.Data;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "user")
@Data
public class user {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private String id;
    @Column
    private String name;
    @Column
    private String email;
    @Column
    private Date birth;
}
