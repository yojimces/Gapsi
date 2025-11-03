package com.gapsi.ecommerce.exception;

import lombok.Getter;

/**
 * Excepción personalizada para recursos no encontrados.
 */
@Getter
public class ResourceNotFoundException extends RuntimeException {
    
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
