package com.gapsi.ecommerce.service.impl;

import com.gapsi.ecommerce.dto.ProveedorDTO;
import com.gapsi.ecommerce.entity.Proveedor;
import com.gapsi.ecommerce.exception.DuplicateResourceException;
import com.gapsi.ecommerce.exception.ResourceNotFoundException;
import com.gapsi.ecommerce.pattern.AuditLogger;
import com.gapsi.ecommerce.pattern.ProveedorValidator;
import com.gapsi.ecommerce.repository.ProveedorRepository;
import com.gapsi.ecommerce.service.ProveedorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementación del servicio de Proveedores.
 * Contiene la lógica de negocio y validaciones.
 */
@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ProveedorServiceImpl implements ProveedorService {

    private final ProveedorRepository proveedorRepository;
    private final ProveedorValidator proveedorValidator;
    private final AuditLogger auditLogger;

    @Override
    @Transactional(readOnly = true)
    public Page<ProveedorDTO> getAllProveedores(Pageable pageable) {
        log.info("Obteniendo proveedores - Página: {}, Tamaño: {}", 
                 pageable.getPageNumber(), pageable.getPageSize());
        
        Page<Proveedor> proveedores = proveedorRepository.findAll(pageable);
        
        auditLogger.log("Consulta de proveedores realizada - Total: " + proveedores.getTotalElements());
        
        return proveedores.map(this::convertToDTO);
    }

    @Override
    @Transactional(readOnly = true)
    public ProveedorDTO getProveedorById(Long id) {
        log.info("Buscando proveedor con ID: {}", id);
        
        Proveedor proveedor = proveedorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Proveedor no encontrado con ID: " + id));
        
        return convertToDTO(proveedor);
    }

    @Override
    public ProveedorDTO createProveedor(ProveedorDTO proveedorDTO) {
        log.info("Creando nuevo proveedor: {}", proveedorDTO.getNombre());
        
        // PATRÓN: Strategy - Validación con estrategia personalizable
        proveedorValidator.validate(proveedorDTO);
        
        // Validar duplicado por nombre
        if (proveedorRepository.existsByNombre(proveedorDTO.getNombre())) {
            throw new DuplicateResourceException(
                    "Ya existe un proveedor con el nombre: " + proveedorDTO.getNombre());
        }
        
        Proveedor proveedor = convertToEntity(proveedorDTO);
        Proveedor savedProveedor = proveedorRepository.save(proveedor);
        
        auditLogger.log("Proveedor creado exitosamente - ID: " + savedProveedor.getId());
        
        return convertToDTO(savedProveedor);
    }

    @Override
    public ProveedorDTO updateProveedor(Long id, ProveedorDTO proveedorDTO) {
        log.info("Actualizando proveedor con ID: {}", id);
        
        Proveedor proveedor = proveedorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Proveedor no encontrado con ID: " + id));
        
        // Validar duplicado si se cambió el nombre
        if (!proveedor.getNombre().equals(proveedorDTO.getNombre()) &&
            proveedorRepository.existsByNombre(proveedorDTO.getNombre())) {
            throw new DuplicateResourceException(
                    "Ya existe un proveedor con el nombre: " + proveedorDTO.getNombre());
        }
        
        proveedor.setNombre(proveedorDTO.getNombre());
        proveedor.setRazonSocial(proveedorDTO.getRazonSocial());
        proveedor.setDireccion(proveedorDTO.getDireccion());
        
        Proveedor updatedProveedor = proveedorRepository.save(proveedor);
        
        auditLogger.log("Proveedor actualizado - ID: " + id);
        
        return convertToDTO(updatedProveedor);
    }

    @Override
    public void deleteProveedor(Long id) {
        log.info("Eliminando proveedor con ID: {}", id);
        
        if (!proveedorRepository.existsById(id)) {
            throw new ResourceNotFoundException(
                    "Proveedor no encontrado con ID: " + id);
        }
        
        proveedorRepository.deleteById(id);
        
        auditLogger.log("Proveedor eliminado - ID: " + id);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ProveedorDTO> searchProveedoresByNombre(String nombre, Pageable pageable) {
        log.info("Buscando proveedores por nombre: {}", nombre);
        
        Page<Proveedor> proveedores = proveedorRepository.searchByNombre(nombre, pageable);
        
        return proveedores.map(this::convertToDTO);
    }

    // Métodos auxiliares de conversión
    
    private ProveedorDTO convertToDTO(Proveedor proveedor) {
        ProveedorDTO dto = new ProveedorDTO();
        dto.setId(proveedor.getId());
        dto.setNombre(proveedor.getNombre());
        dto.setRazonSocial(proveedor.getRazonSocial());
        dto.setDireccion(proveedor.getDireccion());
        dto.setCreatedAt(proveedor.getCreatedAt());
        dto.setUpdatedAt(proveedor.getUpdatedAt());
        return dto;
    }

    private Proveedor convertToEntity(ProveedorDTO dto) {
        Proveedor proveedor = new Proveedor();
        proveedor.setNombre(dto.getNombre());
        proveedor.setRazonSocial(dto.getRazonSocial());
        proveedor.setDireccion(dto.getDireccion());
        return proveedor;
    }
}
