package com.gapsi.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

/**
 * DTO (Data Transfer Object) para Proveedor.
 * Representa los datos transferidos en las peticiones HTTP.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProveedorDTO {

    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 3, max = 100, message = "El nombre debe tener entre 3 y 100 caracteres")
    private String nombre;

    @NotBlank(message = "La razón social es obligatoria")
    @Size(min = 3, max = 200, message = "La razón social debe tener entre 3 y 200 caracteres")
    private String razonSocial;

    @NotBlank(message = "La dirección es obligatoria")
    @Size(min = 10, max = 500, message = "La dirección debe tener entre 10 y 500 caracteres")
    private String direccion;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
