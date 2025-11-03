package com.gapsi.ecommerce.pattern;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * PATRÓN DE DISEÑO: Singleton
 * Garantiza una única instancia del logger de auditoría en toda la aplicación.
 * Centraliza el registro de eventos importantes del sistema.
 */
@Component
@Slf4j
public class AuditLogger {

    private final List<String> auditLogs;

    // Spring maneja el Singleton automáticamente con @Component
    public AuditLogger() {
        this.auditLogs = new ArrayList<>();
        log.info("AuditLogger inicializado - Singleton pattern (Spring)");
    }

    /**
     * Registra un evento de auditoría.
     */
    public void log(String message) {
        String timestamp = LocalDateTime.now().toString();
        String logMessage = String.format("[%s] %s", timestamp, message);
        auditLogs.add(logMessage);
        log.info(logMessage);
    }

    /**
     * Obtiene todos los logs de auditoría.
     */
    public List<String> getAuditLogs() {
        return new ArrayList<>(auditLogs);
    }

    /**
     * Limpia todos los logs de auditoría.
     */
    public void clearLogs() {
        auditLogs.clear();
        log.info("Logs de auditoría limpiados");
    }
}
