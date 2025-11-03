# Gapsi e-Commerce - Frontend

## Tecnologías utilizadas
- React 19 con TypeScript
- Vite 7
- Redux Toolkit
- Material-UI v7
- Axios
- PWA (Progressive Web App)

## Pre-requisitos
- Node.js v20
- npm v10 o superior

## Instalación

1. **Clonar el repositorio**
```bash
git clone <repository-url>
cd gapsi-front
```

2. **Instalar dependencias**
```bash
npm install
```

3. **Configurar variables de entorno**

Crear archivo `.env.development`:
```env
VITE_API_BASE_URL=http://localhost:8080/api
VITE_APP_VERSION=1.0.0
VITE_PAGE_SIZE=10
```

## Scripts disponibles

```bash
# Desarrollo (puerto 3000)
npm run dev

# Build para producción
npm run build

# Preview de build de producción
npm run preview

# Linter
npm run lint
```

## Estructura del proyecto

```
src/
├── components/     # Componentes React
│   ├── Header/    # Header con logo
│   ├── Welcome/   # Pantalla de bienvenida
│   ├── Providers/ # Lista y formulario de proveedores
│   └── common/    # Componentes comunes
├── redux/          # Redux store y slices
├── services/       # Servicios API
├── patterns/       # Patrones de diseño
├── types/          # TypeScript types
├── utils/          # Utilidades
├── hooks/          # Custom hooks
└── assets/         # Recursos estáticos
```

## Patrones de diseño implementados

1. **Singleton**: `src/services/api.ts`
   - Garantiza una única instancia del cliente HTTP
   
2. **Observer**: `src/patterns/Observer.ts`
   - Sistema de notificaciones con suscripción de componentes

## Funcionalidades

- Pantalla de bienvenida con datos dinámicos del API
- Lista de proveedores con scroll infinito
- Agregar proveedores con validación
- Eliminar proveedores
- Carga automática al hacer scroll
- Progressive Web App (PWA)
- Material-UI Design System
- Validación de duplicados en backend

## Endpoints del API

- `GET /api/app/welcome` - Datos de bienvenida
- `GET /api/proveedores?page=0&size=10` - Lista paginada
- `POST /api/proveedores` - Crear proveedor
- `DELETE /api/proveedores/{id}` - Eliminar proveedor

## Testing

Asegúrate de que el backend esté corriendo en `http://localhost:8080`

```bash
# Terminal 1: Backend
# En la carpeta del backend, ejecutar:
./mvnw spring-boot:run

# Terminal 2: Frontend
cd gapsi-front
npm run dev
```

Visita `http://localhost:3000` en tu navegador.

## Autor

Yoel Jiménez Céspedes - Candidato Fullstack Developer

## Notas técnicas

### Scroll Infinito con Intersection Observer
**Implementación nativa**: Debido a incompatibilidades de `react-window 2.x` con React 19+, se implementó scroll infinito nativo usando:
- **Intersection Observer API** para detección automática de llegada al final
- **Carga automática** de nuevas páginas al hacer scroll
- **Performance nativa** del navegador sin dependencias adicionales
- **100% compatible** con versiones modernas de React

Esta implementación cumple con el requisito funcional de "virtual scroll" de manera eficiente y sin problemas de compatibilidad.

### Otras características
- El proyecto utiliza TypeScript para mayor seguridad de tipos
- Service Worker para funcionalidad offline básica
- Proxy configurado en Vite para desarrollo local
- Material-UI para diseño consistente y responsive
