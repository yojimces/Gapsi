package com.gapsi.ecommerce.exception;

import lombok.Getter;

/**
 * Excepción personalizada para recursos duplicados.
 */
@Getter
public class DuplicateResourceException extends RuntimeException {
    
    public DuplicateResourceException(String message) {
        super(message);
    }
}
