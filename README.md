# Carrito de Compras (Backend)

Este proyecto consiste en el desarrollo de un backend para gestionar información relacionada con productos, los usuarios que utilizan la aplicación y las compras que realiza cada usuario. Utiliza el framework Spring Boot y está diseñado para proporcionar una API REST que permita manejar productos, usuarios, sus compras (de manera resumida) y sus compras (de manera detallada).

## Tecnologías utilizadas

- **Java**: Lenguaje de programación principal. Para este proyecto en específico se utilizó el `JDK 17`.
- **Spring Boot**: Framework para construir aplicaciones Java. Particularmente en este proyecto se utiliza la versión `3.4.0`.
  - **Hibernate/JPA**: Para la gestión de la base de datos relacional.
  - **Jakarta Validation**: Para la validación de datos de entrada.
  - **Manejo de Excepciones**
  - **Programación orientada a aspectos (POA)**
- **Maven**: Para la gestión de dependencias y construcción del proyecto.
- **MySQL**: Gestor de base de datos relacional para almacenar la información de los clientes, productos y compras.
- **Postman**: Para simular ser un cliente que hace peticiones al servidor y probar los endpoints.

## Características

- **API REST** con rutas organizadas para interactuar con el backend. Operaciones soportadas:
  - **Product**:
    - Obtener la lista de todos los productos.
    - Obtener la lista de todos los productos con `status` 1 (productos activos).
    - Obtener la lista de todos los productos con `status` 1 y pertenecientes a cierta categoría.
    - Obtener la lista de todos los productos con `status` 1 y pertenecientes a cierta marca.
    - Obtener la información de un producto específico por su ID.
    - Crear un nuevo producto.
    - Actualizar la información de un producto existente.
    - Cambiar el `status` de un producto de 0 a 1 y viceversa.
  - **User**:
    - Obtener la lista de todos los usuarios.
    - Obtener la información de un usuario específico por su ID.
    - Crear un nuevo usuario.
    - Actualizar la información de un usuario existente.
    - Eliminar un usuario por su ID.
  - **Purchase**:
    - Agregar una nueva compra a un usuario específico. Esto también implica agregar la información detallada de la compra. Para realizar esta acción se necesita recibir una lista de productos junto con las unidades que se solicitan de cada producto.
    - Obtener el historial de compras (de manera resumida) de un usuario en específico.
  - **Purchase Details**:
    - Obtener el detalle de una sola compra de un usuario en específico.
- Integración con MySQL para la manipulación de datos.
- La base de datos SQL cuenta con cuatro tablas que gestionan la información de los productos, usuarios y sus compras.
- **Restricciones en la base de datos**:
  - No se permite que un mismo usuario se registre dos veces en la base de datos.
- **Manejo de excepciones**:
  - Si se rompe la restricción para la entidad `User` al intentar registrar dos veces a un mismo usuario, se dispara la excepción `DataIntegrityViolationException`. Esta excepción se maneja mediante dos clases que, en conjunto, permiten capturarla y generar un mensaje personalizado indicando la razón por la cual la restricción se rompió.
- **Implementación de programación orientada a aspectos (POA)**:
  - La clase `UserAspect` incluye un método que intercepta, antes de su ejecución, al método encargado de guardar nuevos usuarios en la base de datos. Su objetivo es eliminar los espacios en blanco al inicio y al final de los atributos **nombre** y **apellido**.
  - La clase `ProductAspect` incluye un método que intercepta, antes de su ejecución, al método encargado de guardar nuevos productos en la base de datos. Su objetivo es eliminar los espacios en blanco al inicio y al final de los atributos **nombre**, **descripción**, **marca** y **categoría**.
- **Validación de datos de entrada**:
  - `Product`:
    - No se permite que los atributos **nombre**, **descripción**, **marca**, **imagen** y **categoría** se reciban vacíos o con solo espacios en blanco.
    - No se permite que el atributo **precio** se reciba vacío o con una cantidad menor a 1.
  - `User`:
    - No se permite que los atributos **nombre** y **apellido** se reciban vacíos o con solo espacios en blanco.
- **Eventos del ciclo de vida para objetos `Entity`**:
  - **`PurchaseHistory`**:
    - Antes de guardar una nueva compra realizada por un usuario en la base de datos, se captura la fecha actual del sistema y se asigna al campo `date` correspondiente al registro.
- Se emplea el patrón de diseño arquitectónico conocido como **MVC**, para separar en diferentes capas el código del proyecto.

## Estructura del proyecto

- `aop/`: Carpeta donde se almacenan las clases que manejan la lógica relacionada con la programación orientada a aspectos.
- `controllers/`: Carpeta donde se almacenan las clases que manejan las solicitudes HTTP y definen los endpoints de la API.
- `services/`: Carpeta donde se almacenan las clases que contienen el código relacionado con la lógica de negocio.
- `repositories/`: Carpeta donde se almacenan las interfaces que extienden de una interfaz que permite el manejo de datos.
- `entities/`: Carpeta donde se almacenan las clases que se mapean con sus respectivas tablas en la base de datos.
- `dto/`: Carpeta donde se almacenan las clases diseñadas específicamente para la transferencia de datos entre diferentes capas de la aplicación.
- `utils/`: Carpeta donde se almacenan las clases las cuales tienen métodos utilitarios que se pueden usar de manera transversal en la aplicación.

## Futuras mejoras

Se planea integrar la tecnología de **JWT** para autenticar y autorizar a los usuarios de forma segura.

Además, se planea agregar el atributo `stock` a la entidad `Product` para tener un control sobre cuántas unidades se disponen de un mismo producto. De tal manera que, si el `stock` llega a 0, ese producto no se mostrará más en la lista de productos disponibles.

Falta agregar una entidad administradora que pueda:
- Agregar nuevos productos.
- Actualizar la información de productos.
- Cambiar el estado de productos.
- Ver todos los productos por categoría o por marca, sin importar el estado.
- Encontrar un producto por su nombre, independientemente de su estado.

El usuario podrá encontrar un producto por su nombre solo si su estado es activo.

Se debe comprobar que, cuando se elimina un usuario, se elimine en cascada toda su información.

## Demo

Puedes ver una demo del proyecto en el siguiente enlace: [Carrito de Compras (Backend)](Carrito de Compras (Backend)).

---

# Shopping Cart (Backend)

This project involves developing a backend to manage information related to products, users of the application, and the purchases made by each user. It uses the Spring Boot framework and is designed to provide a REST API that handles products, users, their purchases (in summary), and their purchases (in detail).

## Technologies Used

- **Java**: Main programming language. For this project, `JDK 17` was used.
- **Spring Boot**: Framework for building Java applications. This project specifically uses version `3.4.0`.
  - **Hibernate/JPA**: For managing the relational database.
  - **Jakarta Validation**: For input data validation.
  - **Exception Handling**
  - **Aspect-Oriented Programming (AOP)**
- **Maven**: For dependency management and project build.
- **MySQL**: Relational database management system used to store information about customers, products, and purchases.
- **Postman**: Used to simulate a client making requests to the server and to test endpoints.

## Features

- **REST API** with organized routes to interact with the backend. Supported operations:
  - **Product**:
    - Retrieve a list of all products.
    - Retrieve a list of all products with `status` 1 (active products).
    - Retrieve a list of all products with `status` 1 that belong to a specific category.
    - Retrieve a list of all products with `status` 1 that belong to a specific brand.
    - Retrieve the information of a specific product by its ID.
    - Create a new product.
    - Update the information of an existing product.
    - Change the `status` of a product from 0 to 1 and vice versa.
  - **User**:
    - Retrieve a list of all users.
    - Retrieve the information of a specific user by their ID.
    - Create a new user.
    - Update the information of an existing user.
    - Delete a user by their ID.
  - **Purchase**:
    - Add a new purchase for a specific user. This also involves adding detailed information about the purchase. This action requires a list of products along with the requested quantity for each product.
    - Retrieve a user's purchase history (in summary).
  - **Purchase Details**:
    - Retrieve the details of a specific purchase made by a user.
- Integration with MySQL for data manipulation.
- The SQL database consists of four tables that manage information about products, users, and their purchases.
- **Database Constraints**:
  - The same user cannot be registered twice in the database.
- **Exception Handling**:
  - If the `User` entity constraint is violated by attempting to register the same user twice, a `DataIntegrityViolationException` is triggered. This exception is handled through two classes that work together to capture it and generate a custom message indicating the reason for the violation.
- **Aspect-Oriented Programming (AOP) Implementation**:
  - The `UserAspect` class includes a method that intercepts, before execution, the method responsible for saving new users in the database. Its purpose is to trim leading and trailing spaces from the **name** and **last name** attributes.
  - The `ProductAspect` class includes a method that intercepts, before execution, the method responsible for saving new products in the database. Its purpose is to trim leading and trailing spaces from the **name**, **description**, **brand**, and **category** attributes.
- **Input Data Validation**:
  - `Product`:
    - The attributes **name**, **description**, **brand**, **image**, and **category** cannot be empty or contain only whitespace.
    - The attribute **price** cannot be empty or less than 1.
  - `User`:
    - The attributes **name** and **last name** cannot be empty or contain only whitespace.
- **Entity Lifecycle Events**:
  - **`PurchaseHistory`**:
    - Before saving a new purchase made by a user in the database, the current system date is captured and assigned to the `date` field of the record.
- The **MVC** architectural design pattern is used to separate the project code into different layers.

## Project Structure

- `aop/`: Folder containing classes that handle logic related to aspect-oriented programming.
- `controllers/`: Folder containing classes that handle HTTP requests and define API endpoints.
- `services/`: Folder containing classes with business logic.
- `repositories/`: Folder containing interfaces that extend a base interface for data handling.
- `entities/`: Folder containing classes mapped to their corresponding database tables.
- `dto/`: Folder containing classes specifically designed for transferring data between different layers of the application.
- `utils/`: Folder containing utility classes with methods that can be used across the application.

## Future Improvements

The integration of **JWT** technology is planned to securely authenticate and authorize users.

Additionally, the `stock` attribute will be added to the `Product` entity to keep track of how many units of the same product are available. If the `stock` reaches 0, that product will no longer appear in the list of available products.

An administrative entity needs to be added to:
- Add new products.
- Update product information.
- Change product status.
- View all products by category or brand, regardless of their status.
- Find a product by name, regardless of its status.

Users will only be able to find a product by its name if its status is active.

It must be ensured that when a user is deleted, all their information is deleted in cascade.

## Demo

You can view a demo of the project at the following link: [Shopping Cart (Backend)](Shopping Cart (Backend)).  
