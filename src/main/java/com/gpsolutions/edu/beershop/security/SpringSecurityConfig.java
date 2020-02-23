package com.gpsolutions.edu.beershop.security;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.gpsolutions.edu.beershop.security.Roles.ADMIN;
import static com.gpsolutions.edu.beershop.security.Roles.CLIENT;

@EnableWebSecurity
@AllArgsConstructor
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    private final LoadClientDetailService loadClientDetailService;
    private final JwtRequestFilter jwtRequestFilter;
    private final PasswordEncoder passwordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //.httpBasic()
                .csrf().disable()
                .authorizeRequests()
                    .antMatchers("/**").permitAll()
//                .antMatchers(HttpMethod.POST, "/beer-shop-app/client/sign-in", "/beer-shop-app/client/sign-up").permitAll()
//                .antMatchers(HttpMethod.GET,"/beer-shop-app/admin/orders").hasRole(ADMIN.name())
//                .antMatchers(HttpMethod.POST,"/beer-shop-app/admin/orders/*/*").hasRole(ADMIN.name())
//                .antMatchers(HttpMethod.POST,"/beer-shop-app/catalog/add-new-beer").hasRole(ADMIN.name())
//                .antMatchers(HttpMethod.POST,"/beer-shop-app/catalog/delete-beer/*").hasRole(ADMIN.name())
//                .antMatchers(HttpMethod.POST,"/beer-shop-app/orders/make-order").hasRole(CLIENT.name())
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .formLogin().disable();
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(loadClientDetailService).passwordEncoder(passwordEncoder);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
