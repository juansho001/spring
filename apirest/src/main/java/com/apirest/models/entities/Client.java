package com.apirest.models.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "Client")
public class Client implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "client_id_sequence_gen")
    @SequenceGenerator(name = "client_id_sequence_gen", sequenceName = "client_id_sequence")
    private Integer id;
    @NotEmpty
    @Size(max = 12, min = 4)
    @Column(nullable = false)
    private String name;
    @NotEmpty
    @Column(name = "last_name")
    private String lastName;
    @NotEmpty
    @Email                  
    @Column(nullable = false, unique = true)
    private String email;
    @Column(name = "created_at")
    @Temporal(TemporalType.DATE)
    private Date createdAt;

}
