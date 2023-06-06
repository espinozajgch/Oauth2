"# Oauth2" 

### Autenticación basada en JWT (JSON Web Token)

### Versiones de librerias implementadas en Junio del 2023

- Spring Boot : 3.1.0
- Spring Security (Oauth2-jose / Oauth2-resource-server) : 6.1.0
- Nimbus-jose-jwt : 9.31

### Objetivo

Habiliar la aunteticacion en la aplicacion y utilizar Oauth2 para validar y decodificar el Bearer Token recibido

### Explicación

La clase WebSecurityConfig habilita la configuración de seguridad en la aplicación. Aquí está la explicación de lo que hace:

***@EnableWebSecurity:*** Esta anotación habilita la configuración de seguridad basada en web en la aplicación. Es una anotación específica de Spring Security que se coloca en una clase de configuración para indicar que se debe habilitar la seguridad web.

***configure(HttpSecurity http):*** Este método configura la seguridad de la aplicación utilizando el objeto HttpSecurity proporcionado por Spring Security. El método se encarga de configurar la política de seguridad de la aplicación.

En el método configure, se realizan las siguientes configuraciones:

- Se deshabilita la protección CSRF (Cross-Site Request Forgery) utilizando http.csrf().disable(). CSRF es un ataque que explota la confianza de un sitio web en la autenticidad de las solicitudes enviadas por un usuario malintencionado.

- Se configura la autorización para las solicitudes entrantes utilizando http.authorizeRequests(). En este caso, se utiliza anyRequest().authenticated() para indicar que todas las solicitudes deben ser autenticadas.

- Se configura el servidor de recursos OAuth 2.0 utilizando http.oauth2ResourceServer(). Aquí se configura el servidor de recursos para utilizar tokens JWT (JSON Web Tokens) como mecanismo de autenticación.

- Se especifica el JWTAuthenticationConverter utilizando jwt.jwtAuthenticationConverter(jwtAuthenticationConverter). Esto indica que se utilizará este convertidor para convertir y autenticar los tokens JWT recibidos.

Finalmente, se llama al método http.build() para construir y devolver la cadena de filtros de seguridad configurada en forma de SecurityFilterChain.

---

La clase ***JWTAuthenticationConverter*** implementa la interfaz **Converter<Jwt, AbstractAuthenticationToken>** y se utiliza en el contexto de la autenticación basada en JWT (JSON Web Token) en un proyecto de Spring.

El objetivo principal de esta clase es convertir un objeto Jwt en un objeto AbstractAuthenticationToken que represente la autenticación del usuario basada en el token JWT. El método convert() se encarga de realizar esta conversión.

El método **convert()** recibe un objeto Jwt y extrae la información relevante del token, como el sujeto (subject) y las autoridades. Utiliza el método getAuthorities(jwt) para obtener las autoridades del token y luego crea un objeto UsernamePasswordAuthenticationToken utilizando el sujeto y las autoridades obtenidas. Este objeto AbstractAuthenticationToken representa la autenticación del usuario.

El método ***getAuthorities(Jwt jwt)*** se encarga de extraer las autoridades del token JWT. En el ejemplo dado, se asume que las autoridades se encuentran en la reclamación "realm_access" del token. Se verifica si el valor de "realm_access" es una instancia de LinkedTreeMap y luego se obtiene la lista de roles. Estos roles se agregan a una lista de cadenas y finalmente se convierten en objetos SimpleGrantedAuthority. Estos objetos representan las autoridades del usuario en la autenticación.

En resumen, la clase ***JWTAuthenticationConverter*** se utiliza para convertir un objeto Jwt en un objeto AbstractAuthenticationToken que representa la autenticación del usuario basada en el token JWT. La clase se encarga de extraer y mapear las autoridades del token, lo que permite establecer la autoridad del usuario en el contexto de la autenticación.

---
### Obtener la secret Key para decodificar el token

application.properties:

```
spring.security.oauth2.resourceserver.jwt.jwk-set-uri=http://localhost:8080/.well-known/jwks.json
```

Asegúrate de reemplazar http://localhost:8080/.well-known/jwks.json con la URL correcta donde se encuentra el conjunto de claves JWK en tu servidor de autenticación.