InventarioApp - JavaFX & JDBC
Aplicación de escritorio desarrollada en Java utilizando JavaFX para la interfaz gráfica y JDBC (con base de datos embebida H2) para la persistencia de datos. El proyecto implementa una arquitectura limpia dividida en capas (Modelo, Vista, Controlador, DAO y Navegación) siguiendo el patrón de diseño MVC.
---
Características Principales
Sistema de Autenticación (`Login`): Pantalla de inicio de sesión para validar el acceso de usuarios al sistema.
Gestión y Listado de Registros (`Main`): Vista principal equipada con una `TableView` que muestra en tiempo real los registros almacenados en la base de datos (`ID`, `Nombre`, `Fecha`, `Estado`).
Creación de Registros (`Create`): Formulario interactivo con validación de campos (`Nombre`, `Descripción`, `Fecha` y `Estado`) para insertar nuevos elementos en la base de datos.
Navegación Centralizada: Controlador dedicado (`NavigationController`) que gestiona de forma dinámica el cambio entre escenas dentro de un único `Stage`, evitando la apertura innecesaria de múltiples ventanas.
Persistencia con JDBC y DAO: Capa de acceso a datos (`InventarioDAO`) desacoplada que gestiona las conexiones SQL mediante `PreparedStatement` y `ResultSet`.
---
Tecnologías Utilizadas
Lenguaje: Java 27 (LTS)
Framework UI: JavaFX 27 (Controls & FXML)
Gestor de Dependencias: Apache Maven
Base de Datos: H2 Database (JDBC embebido, sin necesidad de instalar servidor externo)
Arquitectura: Patrón DAO (Data Access Object) + MVC + JPMS (`module-info.java`)
---
Estructura del Proyecto
```text
ejerciciointegradorclase/
├── src/
│   └── main/
│       ├── java/
│       │   ├── module-info.java
│       │   └── org/example/ejerciciointegradorclase/
│       │       ├── MainApp.java                    
│       │       ├── controller/
│       │       │   ├── LoginController.java       
│       │       │   ├── MainController.java         
│       │       │   └── CreateController.java       
│       │       ├── dao/
│       │       │   └── InventarioDAO.java          
│       │       ├── model/
│       │       │   └── Registro.java               
│       │       └── navigation/
│       │           └── NavigationController.java   
│       └── resources/
│           └── org/example/ejerciciointegradorclase/view/
│               ├── login.fxml                      
│               ├── main.fxml                       
│               └── create.fxml                     
└── pom.xml                                         
```
---
Requisitos y Ejecución
Prerrequisitos
JDK 27 instalado y configurado en las variables de entorno.
Apache Maven (integrado en IntelliJ IDEA o instalado en el sistema).
---
Credenciales de Acceso por Defecto
Usuario: `admin`
Contraseña: `admin`
