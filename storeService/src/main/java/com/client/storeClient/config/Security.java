package com.client.storeClient.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


import java.util.List;
import java.util.stream.Stream;

@Configuration
@EnableWebSecurity
public class Security {
    @Bean
    public SecurityFilterChain SecurityFilterChain(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity.cors(cors->cors.disable())
                .csrf(csrf->csrf.disable())
                .authorizeHttpRequests(configurer -> configurer.requestMatchers("/store/products/getRequest").permitAll()
                        .requestMatchers("/store/addCpu").hasRole("MANAGER")
                        .anyRequest().authenticated())
                .oauth2ResourceServer(configurer -> configurer.jwt(jwt -> {
                    JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
                    jwtAuthenticationConverter.setPrincipalClaimName("preferred_username");
                    jwt.jwtAuthenticationConverter(jwtAuthenticationConverter);

                    JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
                    jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(token -> {
                        var authorities = jwtGrantedAuthoritiesConverter.convert(token);
                        var roles = (List<String>)token.getClaimAsMap("realm_access").get("roles");
                        return Stream.concat(authorities.stream(),
                                roles.stream()
                                        .filter(role -> role.startsWith("ROLE_"))
                                        .map(SimpleGrantedAuthority::new)
                                        .map(GrantedAuthority.class::cast)).toList();
                    });
                        }))
        .build();
    }

    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter(){
        var converter = new JwtAuthenticationConverter();
        var jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        converter.setPrincipalClaimName("preferred_username");
        System.out.println(converter);
        converter.setJwtGrantedAuthoritiesConverter(jwt -> {
            var authorities = jwtGrantedAuthoritiesConverter.convert(jwt);
            var roles = (List<String>)jwt.getClaimAsMap("realm_access").get("roles");
            return Stream.concat(authorities.stream(),
                    roles.stream()
                            .filter(role -> role.startsWith("ROLE_"))
                            .map(SimpleGrantedAuthority::new)
                            .map(GrantedAuthority.class::cast)).toList();
        });
        return converter;
    }

}
