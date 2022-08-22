package io.github.pedroermarinho.comandalivreapi.infra.config;

import io.github.pedroermarinho.comandalivreapi.domain.usecases.auth.LoadUserByEmail;
import io.github.pedroermarinho.comandalivreapi.infra.config.security.JWTAuthenticationFilter;
import io.github.pedroermarinho.comandalivreapi.infra.config.security.JWTAuthorizationFilter;
import io.github.pedroermarinho.comandalivreapi.infra.config.security.JWTUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserDetailsService userDetailsService;

    private final LoadUserByEmail loadUserByEmail;

    private final JWTUtil jwtUtil;

    public SecurityConfig(UserDetailsService userDetailsService, LoadUserByEmail loadUserByEmail, JWTUtil jwtUtil) {
        this.userDetailsService = userDetailsService;
        this.loadUserByEmail = loadUserByEmail;
        this.jwtUtil = jwtUtil;
    }

    private AuthenticationManager authenticationManager;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
        authenticationManager = authenticationManagerBuilder.build();

        http.headers().frameOptions().disable();
        http.cors().disable().csrf().disable();

        http.authorizeHttpRequests(
                authz -> authz
                        .antMatchers("/swagger-ui/**").permitAll()
                        .antMatchers("/login").permitAll()
                        .antMatchers("/v3/api-docs/**").permitAll()
                        .antMatchers("/api/v1/auth/**").permitAll()
                        .antMatchers("/h2-console/**").permitAll()
                        .anyRequest()
                        .authenticated()
        );

        http.addFilter(new JWTAuthenticationFilter(authenticationManager, jwtUtil));
        http.addFilter(new JWTAuthorizationFilter(authenticationManager, jwtUtil, loadUserByEmail));
        http.authenticationManager(authenticationManager).sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        return http.build();
    }


    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring().antMatchers("/swagger-ui/**", "/v3/api-docs/**")
                .antMatchers("/api/v1/auth/**");
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedMethods("*");
            }
        };
    }

    @Bean
    public AuthenticationManager authenticationManagerBean() {
        return authenticationManager;
    }
}
