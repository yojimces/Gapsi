package com.gapsi.ecommerce.controller;

import com.gapsi.ecommerce.dto.ProveedorDTO;
import com.gapsi.ecommerce.service.ProveedorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;

/**
 * Controlador REST para operaciones CRUD de Proveedores.
 * Requisito: [ peso 6 ] - Servicios REST para proveedores
 */
@RestController
@RequestMapping("/api/proveedores")
@RequiredArgsConstructor
@Slf4j
@Validated
public class ProveedorController {

    private final ProveedorService proveedorService;

    /**
     * Obtiene lista paginada de proveedores.
     * GET /api/proveedores?page=0&size=10
     */
    @GetMapping
    public ResponseEntity<Page<ProveedorDTO>> getAllProveedores(
            @RequestParam(defaultValue = "0") @Min(0) int page,
            @RequestParam(defaultValue = "10") @Min(1) int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "ASC") String sortDir) {
        
        log.info("GET /api/proveedores - page: {}, size: {}", page, size);
        
        Sort sort = sortDir.equalsIgnoreCase("DESC") 
                ? Sort.by(sortBy).descending() 
                : Sort.by(sortBy).ascending();
        
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<ProveedorDTO> proveedores = proveedorService.getAllProveedores(pageable);
        
        return ResponseEntity.ok(proveedores);
    }

    /**
     * Obtiene un proveedor por ID.
     * GET /api/proveedores/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<ProveedorDTO> getProveedorById(@PathVariable Long id) {
        log.info("GET /api/proveedores/{}", id);
        
        ProveedorDTO proveedor = proveedorService.getProveedorById(id);
        return ResponseEntity.ok(proveedor);
    }

    /**
     * Crea un nuevo proveedor.
     * POST /api/proveedores
     */
    @PostMapping
    public ResponseEntity<ProveedorDTO> createProveedor(
            @Valid @RequestBody ProveedorDTO proveedorDTO) {
        
        log.info("POST /api/proveedores - Creando proveedor: {}", proveedorDTO.getNombre());
        
        ProveedorDTO createdProveedor = proveedorService.createProveedor(proveedorDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProveedor);
    }

    /**
     * Actualiza un proveedor existente.
     * PUT /api/proveedores/{id}
     */
    @PutMapping("/{id}")
    public ResponseEntity<ProveedorDTO> updateProveedor(
            @PathVariable Long id,
            @Valid @RequestBody ProveedorDTO proveedorDTO) {
        
        log.info("PUT /api/proveedores/{} - Actualizando proveedor", id);
        
        ProveedorDTO updatedProveedor = proveedorService.updateProveedor(id, proveedorDTO);
        return ResponseEntity.ok(updatedProveedor);
    }

    /**
     * Elimina un proveedor.
     * DELETE /api/proveedores/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProveedor(@PathVariable Long id) {
        log.info("DELETE /api/proveedores/{}", id);
        
        proveedorService.deleteProveedor(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Busca proveedores por nombre.
     * GET /api/proveedores/search?nombre={nombre}
     */
    @GetMapping("/search")
    public ResponseEntity<Page<ProveedorDTO>> searchProveedores(
            @RequestParam String nombre,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        
        log.info("GET /api/proveedores/search?nombre={}", nombre);
        
        Pageable pageable = PageRequest.of(page, size);
        Page<ProveedorDTO> proveedores = proveedorService.searchProveedoresByNombre(nombre, pageable);
        
        return ResponseEntity.ok(proveedores);
    }
}
