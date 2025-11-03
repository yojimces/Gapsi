package com.gapsi.ecommerce.service;

import com.gapsi.ecommerce.dto.ProveedorDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Interfaz del servicio de Proveedores.
 * Define el contrato de operaciones de negocio.
 */
public interface ProveedorService {

    /**
     * Obtiene todos los proveedores de forma paginada.
     */
    Page<ProveedorDTO> getAllProveedores(Pageable pageable);

    /**
     * Obtiene un proveedor por su ID.
     */
    ProveedorDTO getProveedorById(Long id);

    /**
     * Crea un nuevo proveedor.
     * Valida que el nombre no esté duplicado.
     */
    ProveedorDTO createProveedor(ProveedorDTO proveedorDTO);

    /**
     * Actualiza un proveedor existente.
     */
    ProveedorDTO updateProveedor(Long id, ProveedorDTO proveedorDTO);

    /**
     * Elimina un proveedor por su ID.
     */
    void deleteProveedor(Long id);

    /**
     * Busca proveedores por nombre.
     */
    Page<ProveedorDTO> searchProveedoresByNombre(String nombre, Pageable pageable);
}
