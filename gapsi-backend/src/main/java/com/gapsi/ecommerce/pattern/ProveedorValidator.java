package com.gapsi.ecommerce.pattern;

import com.gapsi.ecommerce.dto.ProveedorDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

/**
 * PATRÓN DE DISEÑO: Strategy
 * Define una estrategia de validación para Proveedores que puede ser intercambiada.
 * Permite implementar diferentes estrategias de validación sin modificar el código cliente.
 */
@Component
@Slf4j
@AllArgsConstructor
public class ProveedorValidator {

    private final Validator validator;

    /**
     * Valida un ProveedorDTO según la estrategia configurada.
     * Strategy Pattern: El algoritmo de validación está encapsulado aquí.
     */
    public void validate(ProveedorDTO proveedorDTO) {
        log.debug("Validando proveedor: {}", proveedorDTO.getNombre());
        
        Set<ConstraintViolation<ProveedorDTO>> violations = validator.validate(proveedorDTO);
        
        if (!violations.isEmpty()) {
            log.warn("Validación fallida para proveedor: {}", proveedorDTO.getNombre());
            violations.forEach(v -> log.warn("  - {}", v.getMessage()));
            throw new IllegalArgumentException(
                    "Error de validación: " + violations.iterator().next().getMessage());
        }
        
        // Validaciones de negocio adicionales
        validateBusinessRules(proveedorDTO);
        
        log.debug("Validación exitosa para proveedor: {}", proveedorDTO.getNombre());
    }

    /**
     * Valida reglas de negocio específicas.
     * Strategy Pattern: Permite agregar nuevas reglas sin modificar la estructura principal.
     */
    private void validateBusinessRules(ProveedorDTO proveedorDTO) {
        // Ejemplo: Validar que el nombre no contenga caracteres especiales
        if (proveedorDTO.getNombre().matches(".*[^a-zA-Z0-9\\s].*")) {
            log.warn("Nombre contiene caracteres especiales: {}", proveedorDTO.getNombre());
            // Permitir por ahora, pero se puede ajustar según requerimientos
        }
        
        // Ejemplo: Validar formato de dirección
        if (proveedorDTO.getDireccion().split(",").length < 2) {
            log.warn("Dirección podría necesitar más detalle: {}", proveedorDTO.getDireccion());
            // Información pero no error crítico
        }
    }

    /**
     * Valida formato específico de datos (Estrategia alternativa).
     */
    public boolean isValidFormat(ProveedorDTO proveedorDTO) {
        try {
            validate(proveedorDTO);
            return true;
        } catch (Exception e) {
            log.error("Formato inválido: {}", e.getMessage());
            return false;
        }
    }
}
