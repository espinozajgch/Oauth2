package com.espinozajg.oauth2.security;

import com.nimbusds.jose.shaded.gson.internal.LinkedTreeMap;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JWTAuthenticationConverter  implements Converter<Jwt, AbstractAuthenticationToken> {

    @Override
    public AbstractAuthenticationToken convert(Jwt jwt) {

        // Convertir las cadenas de autoridad en objetos SimpleGrantedAuthority
        Collection<GrantedAuthority> authorities = getAuthorities(jwt);

        // Crear el objeto de autenticación
        return new UsernamePasswordAuthenticationToken(jwt.getSubject(), null, authorities);
    }

    private Collection<GrantedAuthority> getAuthorities(Jwt jwt) {
        return this.getClaims(jwt).stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    private Collection<String> getClaims(Jwt jwt) {
        List<String> result = new ArrayList<>();

        if(jwt.getClaims().get("realm_access") instanceof LinkedTreeMap){
            final LinkedTreeMap realAccess = (LinkedTreeMap) jwt.getClaims().get("realm_access");
            if(realAccess.get("roles") instanceof ArrayList){
                final ArrayList roles = (ArrayList) realAccess.get("roles");
                for(int i=0; i< roles.size(); i++){
                    result.add((String) roles.get(i));
                }
            }
        }

        return result;
    }
}




