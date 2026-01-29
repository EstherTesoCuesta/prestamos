# Introducción
Documentación de la implementación de una API Rest para gestión de solicitudes de préstamos; empleando Java 11 o superior, Spring y otras librerías.

## Cómo lanzar la aplicación y utilizarla
La aplicación API Rest está preparada para ser lanzada empleando Maven, en caso de que una versión Maven compatible está instalada puede utilizarse directamente esta pero se recomienda utilizar el wrapper de Maven que está presente en el repositorio (versión 4.0.0).
La aplicación puede ser lanzada desde un terminal del sistema operativo, colocándolo en el directorio del proyecto y ejecutando los siguientes comandos:

-	.\mvnw.cmd clean
-	.\mvnw.cmd install
-	.\mvnw.cmd spring-boot:run

Nótese que en caso de emplear la versión de Maven instalada en el sistema, el comando “.\mvnw.cdm” debe ser sustituido por “mvn”.
Una vez realizados estos pasos, la aplicación estará montada de forma local en la siguiente dirección http://localhost:8082 y a partir de aquí se podrá navegar por las distintas funcionalidades empleando solo los elementos de la web.

## Descripción general

### Tecnologías empleadas

Este proyecto se ha llevado a cabo empleando las siguientes tecnologías integradas en una estructura API Rest:

-	Maven (versión 4.0.0): que es una herramienta de software para la gestión y automatización de aplicaciones Java.
-	Spring-boot (versión 4.0.2): que es un framework de código abierto que la gestión de microservicios de Spring para el desarrollo de aplicación Java.

Más información sobre las capacidades de Spring-boot empleadas se detallará en la sección de dependencias del proyecto.

### Aclaraciones previas

El acceso y lectura de datos previos al funcionamiento de la aplicación se ha implementado a través de variables en el código y funciones inicializadoras, pero la idea de un proyecto como este completo es emplear un almacenamiento de datos funcional, como la integración de una base de datos.
Por el mismo motivo, no se ha implementado una gestión de datos usuarios e inicio de sesión a la hora de navegar por las funcionalidades de los actores de los casos de uso, pero esto debería ser un requisito en un proyecto completo.
Casos de uso
 
### Descripción de la estructura

#### Back-end

El proyecto sigue una estructura habitual de proyecto Spring-boot.

- La función principal PrestamosApplication inicializa el almacenamiento de datos en una variable de tipo Mapa simulando el identificador único de la solicitud de préstamos que aportaría el uso funcional de una base de datos.
- Luego se presenta una clase HomeController que implementa todos los endpoint necesarios para cubrir el microservicio de préstamos, funciones post para introducir información datos para generar una solicitud de préstamo (por parte de un cliente) como id y estado de para modificar el estado de una solicitud existente (por parte de un gestor).
- Por último están las clases Loan y LoanDTO, las cuáles respectivamente se utilizan para encapsular la información de las solicitudes de préstamo y la información que provee el usuario cliente a través de un formulario para que posterioremente se convierta en una solicitud (LoanDTO representa la información del cliente y se utiliza para generar un objeto Loan que poder ser gestionado por el sistema).

#### Front-end

Se han implementado templates HTML que utilizados junto a las funciones del controller y la librería themleaf se utilizan para; esta parte está ideada para permitir probar las funcionalidades de una forma más fluida pero no forma parte del desarrollo Back-end propuesto en la prueba técnica.

## Propuesta de ampliación

El proyecto se ha desarrollado con unas limitaciones técnicas y de tiempo que han condicionado su diseño pero que podría completarse o ampliarse en diversas direcciones: 
-	La gestión del almacenamiento de datos de los préstamos a través de una base de datos, lo cual permitiría por ejemplo eficientes búsquedas de solicitudes en base a parámetros de la solicitud que podrían ser consultados en el acceso de cliente (filtrando por cliente), gestor o sistema.
-	La comprobando la validez de los valores introducidos por el usuario. Por ejemplo implementando una lista de divisas a escoger o ampliando las clases de documentos identificativos que pueden proveerse y añadiendo complejidad a su método de verificación de la validez.
-	La incrementación de la complejidad del Front-end, aunque queda fuera del alcance de esta prueba técnica sería un ejercicio necesario para la completitud de la aplicación.
-	La implementación de un mecanismo de inicio de sesión para los diferentes actores: clienta, gestor y sistema, utilizando credenciales gestionables por la base de datos.
-	La ampliación de las funcionalidades para cada tipo de actor, por ejemplo que el usuario cliente pueda consultar la lista de sus solicitudes y estados; que el gestor sólo reciba la lista de solicitudes a las que puede cambiar el estado (si es Pendiente o Aceptado).
