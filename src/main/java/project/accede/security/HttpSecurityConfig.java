package project.accede.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import project.accede.security.components.AuthEntryPointExceptionHandling;
import project.accede.security.jwt.AuthTokenFilter;
import project.accede.security.services.UserDetailsServiceImpl;

import java.security.SecureRandom;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class HttpSecurityConfig {

    @Autowired
    AuthEntryPointExceptionHandling authEntryPointExceptionHandling;

    @Autowired
    UserDetailsServiceImpl userDetailsService;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .cors(withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .exceptionHandling(exception->exception.authenticationEntryPoint(authEntryPointExceptionHandling))
                .authorizeHttpRequests(auth ->
                auth
                        .anyRequest().permitAll()
//                        .requestMatchers(HttpMethod.POST, "auth/**").permitAll()
//                    .requestMatchers(HttpMethod.GET, "user").hasAuthority("ROLE_USER")
//                    .requestMatchers(HttpMethod.PUT, "user").hasAuthority("ROLE_ADMIN")
//                    .requestMatchers(HttpMethod.GET, "location").hasAuthority("ROLE_USER")
//                    .requestMatchers(HttpMethod.POST, "location").hasAuthority("ROLE_OWNER")
//                    .requestMatchers(HttpMethod.GET, "match").hasAuthority("ROLE_USER")
//                    .requestMatchers(HttpMethod.PUT, "match/player").hasAuthority("ROLE_USER")
//                    .requestMatchers(HttpMethod.PUT, "match/sync").hasAuthority("ROLE_OWNER")
//                    .requestMatchers(HttpMethod.POST, "match").hasAuthority("ROLE_OWNER")
//                    .anyRequest().authenticated()
                );

        httpSecurity.authenticationProvider(authenticationProvider());
        httpSecurity.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();

    }

    @Bean
    public PasswordEncoder passwordEncoder(){return new BCryptPasswordEncoder();}

    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {return new AuthTokenFilter();}

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception{
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }
}
