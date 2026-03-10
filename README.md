# Challege Foro Hub - Back End

API REST desarrollada con **Spring Boot** que permite gestionar tópicos de un foro.
El proyecto implementa autenticación con **JWT**, validaciones de datos y operaciones CRUD para la gestión de tópicos.

Este proyecto fue desarrollado como parte del challenge

---

# Tecnologías utilizadas

* Java 17
* Spring Boot
* Spring Security
* JWT (JSON Web Token)
* Spring Data JPA
* MySQL
* Flyway
* Maven
* Postman (para pruebas de API)

---

# Arquitectura del proyecto

El proyecto sigue una arquitectura en capas:

src/main/java/com/forohub

controller → Controladores REST
domain → Entidades y DTOs
repository → Acceso a base de datos
infra/security → Configuración de seguridad y JWT

---

# Configuración del proyecto

## 1 Clonar el repositorio

git clone https://github.com/kevingcord/forohub.git

cd forohub

## 2 Configurar base de datos MySQL

Crear una base de datos:

CREATE DATABASE forohub;

Configurar el archivo:

src/main/resources/application.properties

spring.datasource.url=jdbc:mysql://localhost/forohub
spring.datasource.username=root
spring.datasource.password=tu_password

## 3 Ejecutar el proyecto

./mvnw spring-boot:run

o ejecutar la clase principal:

ForohubApplication

---

# Autenticación

La API utiliza **JWT** para autenticación.

## Login

POST /login

Body:

{
"login": "admin",
"password": "123456"
}

Respuesta:

{
"token": "jwt_token_generado"
}

Este token debe enviarse en las siguientes peticiones en el header:

Authorization: Bearer TOKEN

---

# Endpoints de la API

## Crear tópico

POST /topicos

Body:

{
"titulo": "Error Spring Boot",
"mensaje": "No puedo conectar a MySQL",
"autor": "Kevin",
"curso": "Spring Boot"
}

---

## Listar tópicos

GET /topicos

---

## Detalle de tópico

GET /topicos/{id}

---

## Actualizar tópico

PUT /topicos/{id}

Body:

{
"titulo": "Spring Boot Error",
"mensaje": "Error solucionado",
"autor": "Kevin",
"curso": "Spring Boot"
}

---

## Eliminar tópico

DELETE /topicos/{id}

---

# Seguridad

El proyecto utiliza:

* Spring Security
* JWT para autenticación
* Filtro de seguridad para validar el token en cada petición

Rutas públicas:

/login

Rutas protegidas:

/topicos

---

# Validaciones implementadas

* Campos obligatorios usando @NotBlank
* Prevención de tópicos duplicados
* Autenticación requerida para operaciones CRUD

---

# Pruebas de la API

Las pruebas de los endpoints se realizaron con **Postman**.

Flujo de prueba:

1. Login para obtener token
2. Crear tópico
3. Listar tópicos
4. Actualizar tópico
5. Eliminar tópico

---

# Autor

Proyecto desarrollado por:

Kevin Gamarra

# Licencia

Este proyecto se utiliza únicamente con fines educativos.
