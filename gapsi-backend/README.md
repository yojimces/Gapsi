# Gapsi e-Commerce - Backend

## Tecnologías utilizadas

- Java 11
- Spring Boot 2.7.18
- Spring Data JPA
- H2 Database (In-memory)
- Maven
- Lombok
- Bean Validation

## Pre-requisitos

- Java 11 o superior
- Maven 3.6+
- IDE (IntelliJ IDEA, Eclipse, VS Code)

## Instalación y Configuración

### 1. Verificar Java

```bash
java -version
# Debe mostrar: openjdk version "11.x.x"
```

### 2. Compilar el proyecto

```bash
cd gapsi-backend
mvn clean install
```

### 3. Ejecutar la aplicación

```bash
mvn spring-boot:run
```

La API estará disponible en: `http://localhost:8080`

### 4. Acceder a H2 Console

```
URL: http://localhost:8080/h2-console
JDBC URL: jdbc:h2:mem:gapsidb
Username: sa
Password: (vacío)
```

## Estructura del Proyecto

```
src/main/java/com/gapsi/ecommerce/
├── config/          # Configuraciones (CORS)
├── controller/      # Controladores REST
├── dto/            # Data Transfer Objects
├── entity/         # Entidades JPA
├── exception/      # Manejo de excepciones
├── pattern/        # Patrones de diseño
├── repository/     # Repositorios JPA
├── service/        # Servicios de negocio
└── util/           # Utilidades
```

## Patrones de Diseño Implementados

### 1. Singleton: `AuditLogger`

- Garantiza una única instancia del logger de auditoría
- Centraliza el registro de eventos del sistema
- Implementado con `@Component` de Spring

### 2. Strategy: `ProveedorValidator`

- Define estrategia de validación intercambiable
- Permite diferentes algoritmos de validación
- Separación de responsabilidades

## Endpoints API

### App Endpoints

- `GET /api/app/welcome` - Datos de bienvenida
- `GET /api/app/health` - Health check

### Proveedores Endpoints

- `GET /api/proveedores?page=0&size=10` - Lista paginada
- `GET /api/proveedores/{id}` - Obtener por ID
- `POST /api/proveedores` - Crear nuevo proveedor
- `PUT /api/proveedores/{id}` - Actualizar proveedor
- `DELETE /api/proveedores/{id}` - Eliminar proveedor
- `GET /api/proveedores/search?nombre={nombre}` - Buscar por nombre

## Ejemplos de Uso

### Crear Proveedor

```bash
curl -X POST http://localhost:8080/api/proveedores \
  -H "Content-Type: application/json" \
  -d '{
    "nombre": "Proveedor Test",
    "razonSocial": "Razón Social Test S.A.",
    "direccion": "Calle Principal 123, Ciudad"
  }'
```

### Obtener Proveedores Paginados

```bash
curl http://localhost:8080/api/proveedores?page=0&size=10
```

### Buscar Proveedores

```bash
curl http://localhost:8080/api/proveedores/search?nombre=Test
```

## 🧪 Testing

### Ejecutar Tests

```bash
mvn test
```

### Ejecutar con Cobertura

```bash
mvn clean test jacoco:report
```

## Configuración

### Variables importantes en `application.properties`

- `server.port=8080` - Puerto del servidor
- `spring.jpa.hibernate.ddl-auto=create-drop` - Crear/esborrar tabla al iniciar
- `spring.h2.console.enabled=true` - Habilitar consola H2
- `app.version=1.0.0` - Versión de la aplicación

## 📊 Base de Datos

### H2 In-Memory Database

- **Tipo**: Memoria
- **Persistencia**: Se reinicia cada vez que la app se inicia
- **Datos iniciales**: Cargados desde `data.sql`

### Schema

```sql
proveedor (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  nombre VARCHAR(100) UNIQUE NOT NULL,
  razon_social VARCHAR(200) NOT NULL,
  direccion VARCHAR(500) NOT NULL,
  created_at TIMESTAMP,
  updated_at TIMESTAMP
)
```

## Funcionalidades Implementadas

- CRUD completo de Proveedores
- Paginación con Spring Data
- Validación de datos con Bean Validation
- Manejo centralizado de excepciones
- CORS configurado para frontend
- Logging con SLF4J/Lombok
- Auditoría de operaciones
- Búsqueda por nombre
- Validación de duplicados

## Validaciones

### Campos obligatorios

- Nombre: 3-100 caracteres, único
- Razón Social: 3-200 caracteres
- Dirección: 10-500 caracteres

### Errores manejados

- 400: Validación fallida
- 404: Recurso no encontrado
- 409: Recurso duplicado
- 500: Error interno del servidor

## Autor

Yoel Jiménez Céspedes - Candidato Fullstack Developer

## Referencias

- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- [H2 Database](https://www.h2database.com/)
- [Lombok](https://projectlombok.org/)
