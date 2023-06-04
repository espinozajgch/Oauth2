"# Oauth2" 

### Autenticación basada en JWT (JSON Web Token)

### Versiones de librerias implementadas en Junio del 2023

- Spring Boot : 3.1.0
- Spring Security (Oauth2-jose / Oauth2-resource-server) : 6.1.0
- Nimbus-jose-jwt : 9.31

### Explicación

La clase ***JWTAuthenticationConverter*** implementa la interfaz **Converter<Jwt, AbstractAuthenticationToken>** y se utiliza en el contexto de la autenticación basada en JWT (JSON Web Token) en un proyecto de Spring.

El objetivo principal de esta clase es convertir un objeto Jwt en un objeto AbstractAuthenticationToken que represente la autenticación del usuario basada en el token JWT. El método convert() se encarga de realizar esta conversión.

El método **convert()** recibe un objeto Jwt y extrae la información relevante del token, como el sujeto (subject) y las autoridades. Utiliza el método getAuthorities(jwt) para obtener las autoridades del token y luego crea un objeto UsernamePasswordAuthenticationToken utilizando el sujeto y las autoridades obtenidas. Este objeto AbstractAuthenticationToken representa la autenticación del usuario.

El método ***getAuthorities(Jwt jwt)*** se encarga de extraer las autoridades del token JWT. En el ejemplo dado, se asume que las autoridades se encuentran en la reclamación "realm_access" del token. Se verifica si el valor de "realm_access" es una instancia de LinkedTreeMap y luego se obtiene la lista de roles. Estos roles se agregan a una lista de cadenas y finalmente se convierten en objetos SimpleGrantedAuthority. Estos objetos representan las autoridades del usuario en la autenticación.

En resumen, la clase ***JWTAuthenticationConverter*** se utiliza para convertir un objeto Jwt en un objeto AbstractAuthenticationToken que representa la autenticación del usuario basada en el token JWT. La clase se encarga de extraer y mapear las autoridades del token, lo que permite establecer la autoridad del usuario en el contexto de la autenticación.