package com.gapsi.ecommerce.controller;

import com.gapsi.ecommerce.dto.WelcomeDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador para endpoints generales de la aplicación.
 * Requisito: [ peso 5 ] - Servicio REST para pantalla de bienvenida
 */
@RestController
@RequestMapping("/api/app")
@Slf4j
public class AppController {

    @Value("${app.version:1.0.0}")
    private String appVersion;

    /**
     * Endpoint para datos de la pantalla de bienvenida.
     * GET /api/app/welcome
     */
    @GetMapping("/welcome")
    public ResponseEntity<WelcomeDTO> getWelcomeData() {
        log.info("Solicitando datos de bienvenida");
        
        WelcomeDTO welcomeDTO = new WelcomeDTO();
        welcomeDTO.setWelcomeMessage("Bienvenido Yoel Jiménez");
        welcomeDTO.setVersion(appVersion);
        
        return ResponseEntity.ok(welcomeDTO);
    }

    /**
     * Health check endpoint.
     */
    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("API funcionando correctamente");
    }
}
