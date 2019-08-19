package ru.medweather.documentflow.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import ru.medweather.documentflow.service.FirmService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final FirmService firmService;

    public WebSecurityConfig(FirmService firmService) {
        this.firmService = firmService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/").permitAll()
                    .anyRequest().authenticated()
                .and()
                    .formLogin()
                        .loginPage("/login")
                        .permitAll()
                .and()
                    .logout()
                        .permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(firmService)
                .passwordEncoder(NoOpPasswordEncoder.getInstance());

    }
}
