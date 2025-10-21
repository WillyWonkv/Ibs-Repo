package com.example.libreriafilm.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Ruolo{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(
            name = "ruolo_permesso",
            joinColumns = @JoinColumn(name = "ruolo_id"),
            inverseJoinColumns = @JoinColumn(name = "permesso_id")
    )
    private Set<Permesso> permesso = new HashSet<>();

}
