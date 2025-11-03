package com.gapsi.ecommerce.repository;

import com.gapsi.ecommerce.entity.Proveedor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repositorio JPA para la entidad Proveedor.
 * Proporciona operaciones CRUD y consultas personalizadas.
 */
@Repository
public interface ProveedorRepository extends JpaRepository<Proveedor, Long> {

    /**
     * Busca un proveedor por nombre exacto.
     * Útil para validar duplicados.
     */
    Optional<Proveedor> findByNombre(String nombre);

    /**
     * Verifica si existe un proveedor con el nombre dado.
     */
    boolean existsByNombre(String nombre);

    /**
     * Busca proveedores por nombre (búsqueda parcial).
     */
    @Query("SELECT p FROM Proveedor p WHERE LOWER(p.nombre) LIKE LOWER(CONCAT('%', :nombre, '%'))")
    Page<Proveedor> searchByNombre(@Param("nombre") String nombre, Pageable pageable);

    /**
     * Busca proveedores activos ordenados por fecha de creación.
     */
    Page<Proveedor> findAllByOrderByCreatedAtDesc(Pageable pageable);
}
