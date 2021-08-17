package hu.volgyvaros.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .csrf().disable()
                .authorizeRequests()
                .anyRequest().permitAll()
//            .antMatchers("/", "/static/**", "index.html", "/js/**", "/api", "/public/**",
//                    "/favicon.ico", "/browserconfig.xml", "/manifest.json", "/*icon*.png", "/logo.png",
////                    "/login", "/registration", "/organisations/**", "/projects/**", "/documents/**", "/files/**", "/help",
//                    "/api/authenticate", "/api/info", "/api/organisations/{\\d+}/logo", "/api/organisations/{\\d+}/css", "/api/files/{\\d+}/download", "/api/files/{\\d+}/image", "/api/documents/{\\d+}/download/pdf", "/api/people/exists", "/api/people", "/api/public/**").permitAll()
//            .anyRequest().authenticated()
        ;
    }
}
