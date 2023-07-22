package io.tzk.restful.generator.admin.rest.config;

import io.tzk.restful.generator.admin.rest.filter.ExceptionHandlerFilter;
import io.tzk.restful.generator.admin.rest.filter.JwtAuthenticationFilter;
import io.tzk.restful.generator.admin.rest.filter.ResourceCheckFilter;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.PathMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Collection;

import static org.springframework.security.config.Customizer.withDefaults;

@EnableWebSecurity
@RequiredArgsConstructor
@Configuration
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final ExceptionHandlerFilter exceptionHandlerFilter;
    private final ResourceCheckFilter resourceCheckFilter;
    private final PathMatcher pathMatcher;

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(conf -> conf.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(conf -> conf
                        .requestMatchers(HttpMethod.POST, "/token").permitAll()
                        .requestMatchers(HttpMethod.GET, "/token").permitAll()
                        .requestMatchers(HttpMethod.GET, "/swagger-ui/*").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api-docs/*").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api-docs").permitAll()
                        .requestMatchers("/api/**").permitAll()
                        .anyRequest().access(((authenticationSupplier, requestAuthorizationContext) -> {
                            Collection<? extends GrantedAuthority> authorities = authenticationSupplier.get().getAuthorities();
                            HttpServletRequest request = requestAuthorizationContext.getRequest();
                            String requestMethod = request.getMethod();
                            String requestURI = request.getRequestURI();
                            boolean granted = authorities.stream()
                                    .map(authority -> authority.getAuthority().split(":"))
                                    .anyMatch(authority -> authority[0].equals(requestMethod) && pathMatcher.match(authority[1], requestURI));
                            return new AuthorizationDecision(granted);
                        }))
                )
                .cors(withDefaults())
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(resourceCheckFilter, JwtAuthenticationFilter.class)
                .addFilterBefore(exceptionHandlerFilter, ResourceCheckFilter.class)
                .build();
    }

    @Bean
    public CorsFilter corsFilter() {
        //1.添加CORS配置信息
        CorsConfiguration config = new CorsConfiguration();
        //放行哪些原始域
        config.addAllowedOriginPattern("*");
        //是否发送Cookie信息
        config.setAllowCredentials(true);
        //放行哪些原始域(请求方式)
        config.addAllowedMethod("*");
        //放行哪些原始域(头部信息)
        config.addAllowedHeader("*");
        //暴露哪些头部信息（因为跨域访问默认不能获取全部头部信息）
        config.addExposedHeader("*");

        //2.添加映射路径
        UrlBasedCorsConfigurationSource configSource = new UrlBasedCorsConfigurationSource();
        configSource.registerCorsConfiguration("/**", config);

        //3.返回新的CorsFilter.
        return new CorsFilter(configSource);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

}
