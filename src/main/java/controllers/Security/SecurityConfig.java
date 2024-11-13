package controllers.Security;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import controllers.models.DBRole;
import controllers.models.DBUser;
import controllers.repositories.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static org.springframework.security.config.Customizer.withDefaults;


@Configuration
@EnableWebSecurity
public class SecurityConfig {


    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        return username -> {
            DBUser user = userRepository.findByUsername(username);

            if (user == null) {
                throw new UsernameNotFoundException("User not found");
            }


            Set<DBRole> roles = user.getRoles();

            List<GrantedAuthority> authorities = new ArrayList<>();
            for (DBRole role : roles) {
                authorities.add(new SimpleGrantedAuthority("ROLE_"+role.getName()));
            }

            return new org.springframework.security.core.userdetails.User(user.getUsername(),
                    user.getPassword(), true, true, true, true, authorities);
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {


        http
                .cors(withDefaults())  // Umożliwia obsługę CORS
                .csrf(csrf -> csrf.disable()) // Wyłączanie CSRF dla prostoty, w produkcji powinno być włączone
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/index.html", "/info","/*.png","/login").permitAll()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/client/**").hasRole("CLIENT")
                        .anyRequest().authenticated()
                )
                .httpBasic(withDefaults()); // Użycie podstawowego uwierzytelniania HTTP

        return http.build();
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() { //Obsługa CORS
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:3000") // Dostosuj to do adresu twojej aplikacji front-end
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*")
                        .exposedHeaders("*")
                        .allowCredentials(true);
            }
        };
    }

}
