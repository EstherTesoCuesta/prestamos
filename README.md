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

- Java (versión 21).
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

Procedemos a dar algo más de información sobre las decisiones técnicas tomadas al respecto de los puntos presentados.

##### Clase de tipo préstamo

Se ha definido una clase Loan con la idea de contener toda la información relativa a la solicitud de préstamo salvo un supuesto integer que sirva como identificador único, este podría ser utilizado por la base de datos o se podría decidir otra tupla de datos que sirviesen para identificar únicamente cada solicitud de préstamos. Con la propuesta aquí desarrollada este clase tiene los siguiente atributos: nombre completo del cliente, cantidad de dinero, divisa, documento de identidad, fecha de realización de la solicitud y estado de la solicitud.

El documento de identidad se ha decidido que sea una clase abstracta y sellada IDType que podría ser DNI o Passport, podrían implementarse y añadirse más clases de documento de identidad; como IDType cada una de estas clases tienen que tener implementada función que evalúa la validez de la información en base al tipo de documento de identidad. En el caso del DNI y Passport de esta propuesta se comprueba por simplicidad que la información tiene 9 caracteres, pero esta condición se podría ampliar, por ejemplo en el caso del DNI que fuesen 8 números y 1 letra mayúscula.

La clase Loan además cuenta con una función que siguiendo la descripción del flujo de estátuses de una solicitud, devuelve los posibles siguientes estatus de una solicitud en función de su estatus actual. Si es Pending puede ser Aprobado o Rechazado, y si es Aprobado puede ser Cancelado; en cualquier de los otros casos devuelve una lista vacía. Esta información es utilizada a la hora de permitir al gestor cambiar el estado de una solicitud de préstamo.

Finalmente la clase LoanDTO es empleada para recoger la información que a través de un formulario el cliente provee para crear una nueva solicitud de préstamo, por ello presenta los siguientes atributos: nombre completo del cliente, cantidad de dinero, divisa, tipo de documento de identidad e información del documento de identidad. Cuando un cliente provee estos datos para crear una solicitud, se crea un objeto de clase Loan empleando un constructor que recibe un objeto LoanDTO que utiliza para inicializar sus datos, tomando la fecha de creación como el tiempo actual y asignando el estado de la solicitud como Pendiente.

#### Front-end

Se han implementado templates HTML junto a las funciones del controlador y la librería Thymeleaf para permitir probar las funcionalidades de una forma más fluida pero no forman parte del desarrollo Back-end propuesto en la prueba técnica. El diseño de la propuesta permite entrar a una primera página que sirve como índice para acceder a las funcionalidades que tendría un cliente, un gestor o el sistema.
Accediendo a la página de cliente se puede rellenar un formulario para realizar una solicitud de préstamos, esta información es gestionada desde el controlador y se añade a los datos cargados en memoria.
Accediendo a la página de gestor se pueden revisar todas las solicitudes existentes en el sistema, y pedir modificar el estado de una de ellas; se procede a otra página con sólo la información de la solicitud a modificar y el nuevo estado se recoge a través de un formulario y este es de nuevo gestionado por el controlador y se actualiza el estado de esa solicitud en memoria haciendo uso del identificador único de la solicitud.
Accediendo a la página de sistema se puede ver la lista completa de todas las solicituded cargadas en memoria.

## Propuesta de ampliación

El proyecto se ha desarrollado con unas limitaciones técnicas y de tiempo que han condicionado su diseño pero que podría completarse o ampliarse en diversas direcciones: 
-	La gestión del almacenamiento de datos de los préstamos a través de una base de datos, lo cual permitiría por ejemplo eficientes búsquedas de solicitudes en base a parámetros de la solicitud que podrían ser consultados en el acceso de cliente (filtrando por cliente), gestor o sistema.
-	La comprobando la validez de los valores introducidos por el usuario. Por ejemplo implementando una lista de divisas a escoger o ampliando las clases de documentos identificativos que pueden proveerse y añadiendo complejidad a su método de verificación de la validez.
-	La incrementación de la complejidad del Front-end, aunque queda fuera del alcance de esta prueba técnica sería un ejercicio necesario para la completitud de la aplicación.
-	La implementación de un mecanismo de inicio de sesión para los diferentes actores: clienta, gestor y sistema, utilizando credenciales gestionables por la base de datos.
-	La ampliación de las funcionalidades para cada tipo de actor, por ejemplo que el usuario cliente pueda consultar la lista de sus solicitudes y estados; que el gestor sólo reciba la lista de solicitudes a las que puede cambiar el estado (si es Pendiente o Aceptado).
