package com.gapsi.ecommerce.util;

/**
 * Clase de constantes utilizadas en toda la aplicación.
 */
public final class Constants {

    private Constants() {
        throw new AssertionError("Utility class should not be instantiated");
    }

    // Paginación
    public static final int DEFAULT_PAGE_SIZE = 10;
    public static final int MAX_PAGE_SIZE = 100;

    // Mensajes
    public static final String MSG_RESOURCE_NOT_FOUND = "Recurso no encontrado";
    public static final String MSG_DUPLICATE_RESOURCE = "Recurso duplicado";
    public static final String MSG_VALIDATION_ERROR = "Error de validación";

    // Endpoints API
    public static final String API_BASE_PATH = "/api";
    public static final String API_PROVEEDORES = API_BASE_PATH + "/proveedores";
    public static final String API_APP = API_BASE_PATH + "/app";
}
