# byma-fondos-up-especies

## Descripción General
Esta es una API basada en Spring Boot que permite gestionar especies. Puedes crear, obtener, actualizar y eliminar especies utilizando esta API. La API está documentada con Swagger y puedes interactuar con ella a través de la interfaz de usuario de Swagger.

## Tecnologías

- **Spring Boot** - Framework para el backend
- **Java** - Lenguaje de programación
- **Maven** - Gestión de dependencias
- **Lombok** - Para reducir código repetitivo
- **Jakarta Validation** - Validación de DTOs
- **Swagger/OpenAPI** - Documentación de la API
- **Base de Datos H2 (Opcional)** - Base de datos en memoria para desarrollo/pruebas
- **Postman** - Testing de la API
- **JUnit 5** - Framework de pruebas unitarias
- **Mockito** - Framework de mocking para pruebas unitarias
- **Docker (Opcional)** - Contenerización
- **Spring Data JPA** - Capa de acceso a datos

## Comenzando

### 1. Clonar el Repositorio

```bash
git clone https://github.com/teamcubation/byma-back-especies.git
cd byma-back-especies
```

### 2. Construir el Proyecto
Usa Maven para instalar las dependencias y construir el proyecto:
```bash
mvn clean install
```

### 3. Ejecutar la Aplicación
```bash
mvn spring-boot:run
```

Por defecto, la aplicación se ejecuta en `http://localhost:10002`. Puedes acceder a la API a través de esta URL.

### 4. Acceder a Swagger UI
Una vez que la aplicación esté en ejecución, puedes ver la documentación de la API e interactuar con ella a través de Swagger UI:
```bash
http://localhost:10002/swagger-ui/index.html
```

### 5. Ejemplos de Solicitudes a la API
A continuación, algunos ejemplos para interactuar con la API utilizando Postman:

1. Crear un Especie (POST /api/v1/especies)

**POST** `http://localhost:10002/api/v1/especies`
```json
{ 
  "idEspecie": 123456,
  "codigoCVSA": "ABC123",
  "denominacion": "Especie de prueba",
  "laminaMinima": 100,
  "precio": 5000.75,
  "cafci": "CAFDI001",
  "cuentaDeEmision": "Cuenta01",
  "estado": "Activo",
  "idEmisor": 123456,
  "idGerente": 789012,
  "vigencia": "2024-11-11T00:00:00",
  "plazoDeLiquidacion": "2024-12-01T00:00:00",
  "codigoCNV": "CNV001",
  "isin": "ISIN12345",
  "familiaDeFondos": "Fondos Generales",
  "observaciones": "Esta es una especie de prueba",
  "fechaAlta": "2024-11-01T00:00:00"
}
```

2. Obtener todas las especies (GET /api/v1/especies)

**GET** `http://localhost:10002/api/v1/especies`

3. Obtener especie por ID (GET /api/v1/especies/{id})

**GET** `http://localhost:10002/api/v1/especies/{id}`

4. Actualizar un Especie (PUT /api/v1/especies/{id})

**PUT** `http://localhost:10002/api/v1/especies/{id}`
```json
{
  "idEspecie": 123456,
  "codigoCVSA": "ABC123",
  "denominacion": "Especie de prueba",
  "laminaMinima": 100,
  "precio": 5000.75,
  "cafci": "CAFCI001",
  "cuentaDeEmision": "Cuenta01",
  "estado": "Activo",
  "idEmisor": 123456,
  "idGerente": 789012,
  "vigencia": "2024-11-11T00:00:00",
  "plazoDeLiquidacion": "2024-12-01T00:00:00",
  "codigoCNV": "CNV001",
  "isin": "ISIN12345",
  "familiaDeFondos": "Fondos Generales",
  "observaciones": "Esta es una especie de prueba",
  "idMoneda": 123,
  "fechaAlta": "2024-11-01T00:00:00"
}
```

5. Eliminar un Especie (DELETE /api/v1/especies/{id})

**DELETE** `http://localhost:10002/api/v1/especies/{id}`