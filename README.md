# Carrito de Compras (Backend)

Este proyecto consiste en el desarrollo de un backend para gestionar información relacionada con productos, los usuarios que utilizan la aplicación y las compras que realiza cada usuario. El proyecto esta construido con **Java**, **Spring Boot** y **MySql**, está diseñado para proporcionar una API REST que permita manejar productos, usuarios, sus compras (de manera resumida) y sus compras (de manera detallada).

## Tecnologías utilizadas

- **Java**: Lenguaje de programación principal. Para este proyecto en específico se utilizó el `JDK 17`.
- **Maven**: Para la gestión de dependencias y construcción del proyecto.
- **Spring Boot**: Framework para construir aplicaciones Java. Particularmente en este proyecto se utiliza la versión `3.4.0`.
  - **Hibernate/JPA**: Para la gestión de la base de datos relacional.
  - **Jakarta Validation**: Para la validación de datos de entrada.
  - **Programación orientada a aspectos (POA)**
- **MySQL**: Gestor de base de datos relacional para almacenar la información de los clientes, productos y compras.
- **Swagger / OpenAPI**: Herramienta para documentar y probar los endpoints de la API de forma interactiva.
- **Postman**: Para simular ser un cliente que hace peticiones al servidor y probar los endpoints.

## Características

### EndPoints

Rutas organizadas para interactuar con los productos, usuarios y compras que realiza el usuario. Operaciones soportadas:

- **Product**:
  
  - Obtener la lista de todos los productos.
  - Obtener la lista de todos los productos con `status` 1 (productos activos).
  - Obtener la lista de todos los productos con `status` 1 y pertenecientes a cierta categoría.
  - Obtener la lista de todos los productos con `status` 1 y pertenecientes a cierta marca.
  - Obtener la lista de todos los productos pertenecientes a cierta categoría.
  - Obtener la lista de todos los productos pertenecientes a cierta marca.
  - Obtener la información de un producto específico por su ID.
  - Obtener la información de un producto específico por su nombre.
  - Crear un nuevo producto.
  - Actualizar la información de un producto existente.
  - Cambiar el `status` de un producto de 0 a 1 y viceversa.

- **User**:
  
  - Obtener la lista de todos los usuarios.
  - Obtener la información de un usuario específico por su ID.
  - Obtener la información de un usuario específico por su nombre.
  - Crear un nuevo usuario.
  - Actualizar la información de un usuario existente.
  - Eliminar un usuario por su ID.

- **Purchase**:
  
  - Agregar una nueva compra a un usuario específico. Esto también implica agregar la información detallada de la compra. Para realizar esta acción se necesita recibir una lista de productos junto con las unidades que se solicitan de cada producto.
  - Obtener el historial de compras (de manera resumida) de un usuario en específico.

- **Purchase History**:
  
  - Obtener todas las compras con determinado status.

- **Purchase Details**:
  
  - Obtener el detalle de una sola compra de un usuario en específico.

### Gestor de base de datos

- Integración con MySQL para la manipulación de datos.
- La base de datos SQL cuenta con cuatro tablas que gestionan la información de los productos, usuarios y sus compras.
- **Restricciones en la base de datos**:
  - No se permite que un mismo usuario se registre dos veces en la base de datos.
- **Manejo de excepciones**:
  - Si se rompe la restricción para la entidad `User` al intentar registrar dos veces a un mismo usuario, se dispara la excepción `DataIntegrityViolationException`. Esta excepción se maneja mediante dos clases que, en conjunto, permiten capturarla y generar un mensaje personalizado indicando la razón por la cual la restricción se rompió.

### Programación Orientada a Aspectos (POA)

- La clase `UserAspect` incluye un método que intercepta, antes de su ejecución, al método encargado de guardar nuevos usuarios en la base de datos. Su objetivo es eliminar los espacios en blanco al inicio y al final de los atributos **nombre** y **apellido**.
- La clase `ProductAspect` incluye un método que intercepta, antes de su ejecución, al método encargado de guardar nuevos productos en la base de datos. Su objetivo es eliminar los espacios en blanco al inicio y al final de los atributos **nombre**, **descripción**, **marca** y **categoría**.

### Validaciones

Se emplean las siguientes validaciones:

- `Product`:

  - No se permite que los atributos **nombre**, **descripción**, **marca**, **imagen** y **categoría** se reciban vacíos o con solo espacios en blanco.
  - No se permite que el atributo **precio** se reciba vacío o con una cantidad menor a 1.
  - No se permite que el atributo **stock** se reciba vacío o con una cantidad menor a 0.

- `User`:

  - No se permite que los atributos **nombre** y **apellido** se reciban vacíos o con solo espacios en blanco.

### Eventos de ciclo de vida de objetos de las clases entity

- **`PurchaseHistory`**:

  - Antes de guardar una nueva compra realizada por un usuario en la base de datos, se captura la fecha actual del sistema y se asigna al campo `date` correspondiente al registro.

### Patrones

#### Patrón arquitectónico

- **MVC**: Usado para separar en diferentes capas el código del proyecto.
- **DTO**: Usado para transferir datos entre las diferentes capas de la aplicación sin exponer directamente las entidades del dominio.

#### Patrón creacional

- **Factory Method**: Usados para encapsular la creación de objetos, de modo que no se necesite conocer la clase concreta que está instanciando.

## Estructura del proyecto

- `aop/`: Carpeta donde se almacenan las clases que manejan la lógica relacionada con la programación orientada a aspectos.
- `controllers/`: Carpeta donde se almacenan las clases que manejan las solicitudes HTTP y definen los endpoints de la API.
- `services/`: Carpeta donde se almacenan las clases que contienen el código relacionado con la lógica de negocio.
- `repositories/`: Carpeta donde se almacenan las interfaces que extienden de una interfaz que permite el manejo de datos.
- `entities/`: Carpeta donde se almacenan las clases que se mapean con sus respectivas tablas en la base de datos.
- `dto/`: Carpeta donde se almacenan las clases diseñadas específicamente para la transferencia de datos entre diferentes capas de la aplicación.
- `utils/`: Carpeta donde se almacenan las clases las cuales tienen métodos utilitarios que se pueden usar de manera transversal en la aplicación.

## Demo

Puedes ver una demo del proyecto en el siguiente enlace: [Carrito de Compras](CarritodeCompras).

## Futuras mejoras

- Agregar el servicio que devuelva el catalogo de orden de status porque este lo utilizara el fron en un selector.
- Mandarle un correo al usuario indicando que su compra fue realizada.
- Ver si es posible crear un Servicio de cancelar compra.
- Integrar spring security con jwt para autenticar y autorizar a los usuarios de forma segura.

  - Se debe comprobar que, cuando se elimina un usuario, se elimine en cascada toda su información.

- Desarrollar pruebas unitarias
- Desarrollar pruebas de integracion
- Dockerizar la app.
- Agregar redis a los catalogos. Ver si es conveniente porque como el stock de los productos y que el usuario este habilitado o no, tambien cambia, quizas no sea lo mejor.
- kubernitizar la app.
- Desplegar el proyecto en AWS.
- Integrar jenkins

---

# Shopping Cart (Backend)

This project consists of the development of a backend application for managing information related to products, users, and the purchases made by each user. The project is built with **Java**, **Spring Boot**, and **MySQL**, and is designed to provide a REST API for managing products, users, purchase history (summary), and purchase details.

## Technologies Used

- **Java**: Main programming language. This project uses **JDK 17**.
- **Maven**: Dependency management and project build tool.
- **Spring Boot**: Framework for building Java applications. This project uses version **3.4.0**.

  - **Hibernate / JPA**: For relational database management.
  - **Jakarta Validation**: For input data validation.
  - **Aspect-Oriented Programming (AOP)**.
- **MySQL**: Relational database management system used to store users, products, and purchases.
- **Swagger / OpenAPI**: Tool for documenting and testing API endpoints interactively.
- **Postman**: Used to simulate client requests and test API endpoints.

## Features

### Endpoints

Endpoints are organized to interact with products, users, and purchases. Supported operations include:

#### Product

- Retrieve all products.
- Retrieve all active products (`status = 1`).
- Retrieve all active products belonging to a specific category.
- Retrieve all active products belonging to a specific brand.
- Retrieve all products belonging to a specific category.
- Retrieve all products belonging to a specific brand.
- Retrieve a specific product by its ID.
- Retrieve a specific product by its name.
- Create a new product.
- Update an existing product.
- Change a product's status between `0` and `1`.

#### User

- Retrieve all users.
- Retrieve a specific user by ID.
- Retrieve a specific user by name.
- Create a new user.
- Update an existing user.
- Delete a user by ID.

#### Purchase

- Add a new purchase for a specific user. This also includes storing the purchase details. To perform this operation, a list of products and the quantity requested for each product must be provided.
- Retrieve the purchase history (summary view) of a specific user.

#### Purchase History

- Retrieve all purchases with a specific status.

#### Purchase Details

- Retrieve the details of a single purchase for a specific user.

## Database Management

- Integration with MySQL for data persistence.
- The database contains four tables used to manage products, users, and purchases.

### Database Constraints

- A user cannot be registered more than once.

### Exception Handling

- If the `User` entity constraint is violated by attempting to register the same user twice, a `DataIntegrityViolationException` is thrown. Two classes work together to capture this exception and generate a custom error message explaining why the constraint was violated.

## Aspect-Oriented Programming (AOP)

- The `UserAspect` class contains a method that intercepts the user creation process before execution. Its purpose is to trim leading and trailing whitespace from the **first name** and **last name** attributes.
- The `ProductAspect` class contains a method that intercepts the product creation process before execution. Its purpose is to trim leading and trailing whitespace from the **name**, **description**, **brand**, and **category** attributes.

## Validations

### Product

- The **name**, **description**, **brand**, **image**, and **category** fields cannot be null, empty, or contain only whitespace.
- The **price** field cannot be null and must be greater than or equal to 1.
- The **stock** field cannot be null and must be greater than or equal to 0.

### User

- The **first name** and **last name** fields cannot be null, empty, or contain only whitespace.

## Entity Lifecycle Events

### `PurchaseHistory`

- Before saving a new purchase, the current system date is captured and assigned to the corresponding `date` field.

## Design Patterns

### Architectural Pattern

- **MVC**: Used to separate the application into different layers.
- **DTO**: Used to transfer data between layers without exposing domain entities directly.

### Creational Pattern

- **Factory Method**: Used to encapsulate object creation so that the client does not need to know the concrete class being instantiated.

## Project Structure

- `aop/`: Contains classes responsible for Aspect-Oriented Programming logic.
- `controllers/`: Contains classes that handle HTTP requests and define API endpoints.
- `services/`: Contains business logic.
- `repositories/`: Contains interfaces responsible for data access.
- `entities/`: Contains entity classes mapped to database tables.
- `dto/`: Contains classes used for transferring data between application layers.
- `utils/`: Contains utility classes and reusable helper methods.

## Demo

You can watch a demo of the project here:

[Shopping Cart Demo](ShoppingCart)

## Future Improvements

- Add a service that returns the status catalog so the frontend can populate dropdown lists.
- Send a confirmation email when a purchase is completed.
- Evaluate the possibility of implementing a purchase cancellation service.
- Integrate Spring Security with JWT for secure authentication and authorization.

  - Verify that deleting a user also removes all related data through cascading operations.
- Develop unit tests.
- Develop integration tests.
- Dockerize the application.
- Add Redis caching for catalogs. Evaluate whether it is convenient, since product stock and user availability change frequently.
- Containerize the application with Kubernetes.
- Deploy the project to AWS.
- Integrate Jenkins for CI/CD.
