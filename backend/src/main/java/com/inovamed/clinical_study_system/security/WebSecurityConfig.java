package com.inovamed.clinical_study_system.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    @Autowired
    private SecurityFilter securityFilter;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .sessionManagement(session -> {
                    session.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                })
                .authorizeHttpRequests(authorize -> {
                    //public
                    authorize.requestMatchers(HttpMethod.OPTIONS).permitAll();
                    authorize.requestMatchers(HttpMethod.POST, "/auth/login", "/auth/register", "/doctor","/clinical-representative","patient").permitAll();
                    authorize.requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll();


                    // Protected
                    // Patient Endpoints
                    authorize.requestMatchers(HttpMethod.GET, "/patient").hasAnyRole("DOCTOR","PATIENT");
                    authorize.requestMatchers(HttpMethod.GET, "/patient/**").hasAnyRole("DOCTOR", "PATIENT");
                    authorize.requestMatchers(HttpMethod.PUT, "/patient/**").hasRole("DOCTOR");
                    authorize.requestMatchers(HttpMethod.DELETE, "/patient/**").hasRole("DOCTOR");

                    // Attachment Endpoints
                    authorize.requestMatchers(HttpMethod.POST, "/attachment").hasRole("STUDY_REPRESENTATIVE");
                    authorize.requestMatchers(HttpMethod.GET, "/attachment/**").hasAnyRole("STUDY_REPRESENTATIVE", "DOCTOR", "PATIENT");

                    // Research Endpoints
                    authorize.requestMatchers(HttpMethod.GET, "/research/code/**").hasAnyRole("STUDY_REPRESENTATIVE","PATIENT","DOCTOR");
                    authorize.requestMatchers(HttpMethod.GET, "/research/feature-all").hasAnyRole("STUDY_REPRESENTATIVE","PATIENT","DOCTOR");
                    authorize.requestMatchers(HttpMethod.POST, "/research").hasRole("STUDY_REPRESENTATIVE");
                    authorize.requestMatchers(HttpMethod.GET, "/research/**").hasAnyRole("STUDY_REPRESENTATIVE", "DOCTOR", "PATIENT");
                    authorize.requestMatchers(HttpMethod.PUT,"/research/**").hasRole("STUDY_REPRESENTATIVE");
                    // Notification Endpoint
                    authorize.requestMatchers(HttpMethod.POST, "/notification").hasRole("STUDY_REPRESENTATIVE");
                    authorize.requestMatchers(HttpMethod.GET,"/notification/**").hasAnyRole("STUDY_REPRESENTATIVE", "DOCTOR", "PATIENT");
                    authorize.requestMatchers(HttpMethod.GET,"/notification/patient").hasRole("PATIENT");
                    authorize.requestMatchers(HttpMethod.GET,"/notification/doctor").hasRole("DOCTOR");
                    // Digital Signature Endpoints
                    authorize.requestMatchers(HttpMethod.POST, "/digital-signature").hasRole("PATIENT");
                    authorize.requestMatchers(HttpMethod.GET, "/digital-signature/verify/**").hasRole("PATIENT");
                    authorize.requestMatchers(HttpMethod.DELETE, "/digital-signature/**").hasRole("PATIENT");

                    // Clinical representative Endpoints

                    authorize.requestMatchers(HttpMethod.GET,"/clinical-representative/**").hasRole("STUDY_REPRESENTATIVE");
                    // Doctor EndPoints
                    authorize.requestMatchers(HttpMethod.GET,"/doctor/**").hasRole("DOCTOR");

                    // Application Endpoints
                    authorize.requestMatchers(HttpMethod.POST,"/application").hasRole( "DOCTOR");
                    authorize.requestMatchers(HttpMethod.GET,"/application").hasAnyRole("STUDY_REPRESENTATIVE", "DOCTOR", "PATIENT");
                    authorize.requestMatchers(HttpMethod.GET,"/application/**").hasAnyRole("STUDY_REPRESENTATIVE", "DOCTOR", "PATIENT");

                    //authorize.anyRequest().permitAll();

                }).addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


}

