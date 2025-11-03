package com.gapsi.ecommerce.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

/**
 * Entidad JPA que representa un Proveedor en la base de datos.
 * Utiliza Lombok para reducir código boilerplate.
 */
@Entity
@Table(name = "proveedor", 
       uniqueConstraints = @UniqueConstraint(columnNames = "nombre"))
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Proveedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 3, max = 100, message = "El nombre debe tener entre 3 y 100 caracteres")
    @Column(nullable = false, unique = true, length = 100)
    private String nombre;

    @NotBlank(message = "La razón social es obligatoria")
    @Size(min = 3, max = 200, message = "La razón social debe tener entre 3 y 200 caracteres")
    @Column(nullable = false, length = 200, name = "razon_social")
    private String razonSocial;

    @NotBlank(message = "La dirección es obligatoria")
    @Size(min = 10, max = 500, message = "La dirección debe tener entre 10 y 500 caracteres")
    @Column(nullable = false, length = 500)
    private String direccion;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
