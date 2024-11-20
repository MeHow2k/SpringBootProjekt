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

/**
 * Konfiguracja bezpieczeństwa aplikacji.
 * Zawiera definicję usług uwierzytelniania użytkowników, konfiguracji CORS, oraz zasad dostępu do różnych endpointów.
 *
 * @author Michał Pasieka
 * @version 1.0, 20.11.2024
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * Definiuje usługę pobierania danych użytkownika na podstawie nazwy użytkownika.
     *
     * @param userRepository repozytorium, które zapewnia dostęp do danych użytkowników
     * @return użytkownik na podstawie nazwy użytkownika
     * @throws UsernameNotFoundException gdy użytkownik o podanej nazwie nie istnieje
     */
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
                authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
            }

            return new org.springframework.security.core.userdetails.User(
                    user.getUsername(),
                    user.getPassword(),
                    true, true, true, true, authorities);
        };
    }

    /**
     * Bean do enkodowania haseł użytkowników przy użyciu algorytmu BCrypt.
     *
     * @return enkoder do haseł użytkowników
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Bean odpowiedzialny za konfigurację zabezpieczeń aplikacji.
     * Zawiera zasady autoryzacji dla różnych endpointów.
     *
     * @param http obiekt do konfiguracji zabezpieczeń aplikacji
     * @return zbudowana konfiguracja zabezpieczeń
     * @throws Exception gdy wystąpi błąd konfiguracji
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(withDefaults())  // Umożliwia obsługę CORS
                .csrf(csrf -> csrf.disable()) // Wyłączanie CSRF dla prostoty, w produkcji powinno być włączone
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/index.html", "/info", "/*.png", "/login").permitAll()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/client/**").hasRole("CLIENT")
                        .anyRequest().authenticated()
                )
                .httpBasic(withDefaults()); // Użycie podstawowego uwierzytelniania HTTP

        return http.build();
    }

    /**
     * Konfiguracja CORS (Cross-Origin Resource Sharing) dla aplikacji.
     * Umożliwia dostęp do zasobów API z aplikacji frontendowej działającej na innym porcie.
     *
     * @return konfiguracja CORS
     */
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:3000") // Dostosuj to do adresu twojej aplikacji front-end
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowCredentials(true);
            }
        };
    }
}
