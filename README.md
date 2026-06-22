# Sistema de Gestión de Proyectos

Proyecto desarrollado con Spring Boot, Spring Data JPA y PostgreSQL como parte del Taller Práctico de Desarrollo Backend.

---

## Descripción

Este sistema permite administrar:

- Proyectos  
- Empleados  
- Tareas  
- Asignación de empleados a tareas  
- Relación entre proyectos y tareas  

El proyecto implementa una API REST utilizando una arquitectura en capas:

- Entidades (JPA)  
- Repositories  
- Services  
- Controllers  

---

## Tecnologías Utilizadas

- Java 25
- Spring Boot 3
- Spring Web
- Spring Data JPA
- PostgreSQL
- Maven
- Hibernate
- Postman

---

## Modelo de Datos

### Proyecto

| Campo        | Tipo      |
|--------------|-----------|
| id           | Integer   |
| nombre       | String    |
| descripcion  | String    |
| fechaInicio  | LocalDate |

### Empleado

| Campo  | Tipo    |
|--------|--------|
| id     | Integer |
| nombre | String  |
| cargo  | String  |

### Tarea

| Campo          | Tipo      |
|----------------|-----------|
| id             | Integer   |
| descripcion    | String    |
| fechaLimite    | LocalDate |
| costoEstimado  | Double    |

---

## Relaciones

### Proyecto → Tarea

Un proyecto puede tener muchas tareas.

Proyecto 1 ----- N Tareas

### Tarea → Empleado

Una tarea puede tener varios empleados y un empleado puede participar en varias tareas.

Tarea N ----- N Empleados

Tabla intermedia: tarea_empleados

---

## Configuración de Base de Datos

```sql
CREATE DATABASE proyectos_db;
