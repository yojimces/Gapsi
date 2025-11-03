package com.gapsi.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO para datos de bienvenida de la aplicación.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WelcomeDTO {
    private String welcomeMessage;
    private String version;
}
