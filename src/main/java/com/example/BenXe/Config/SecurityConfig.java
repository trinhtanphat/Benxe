package com.example.BenXe.Config;


import com.example.BenXe.Repository.IKhachHangRepository;
import com.example.BenXe.Service.CustomUserDetailService;
import jakarta.servlet.http.HttpSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomUserDetailService();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userDetailsService());
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf().disable()
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/css/**", "/js/**", "/assets/**","/fonts/**","/img/**", "/", "/register", "/error","/contact")
                        .permitAll()
                        .requestMatchers("/admin")
                        .hasAnyAuthority("Admin")
                        .requestMatchers("/admin/nhanvien/edit","/admin/nhanvien/delete")
                        .hasAnyAuthority("Admin")
                        .requestMatchers("/nhanvien")
                        .hasAnyAuthority("NhanVien")
                        .requestMatchers("/khachhang/**")
                        .hasAnyAuthority("KhachHang")
                        .requestMatchers("/nhaxe")
                        .hasAnyAuthority("NhaXe")
                        .requestMatchers("/xe")
                        .hasAnyAuthority("Xe")
                        .anyRequest()
                        .permitAll())
//                        .authenticated())
                .logout(logout -> logout.logoutUrl("/logout")
                        .logoutSuccessUrl("/")
                        .deleteCookies("JSESSIONID")
                        .invalidateHttpSession(true)
                        .clearAuthentication(true)
                        .permitAll())
                .formLogin(formLogin -> formLogin.loginPage("/login")
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/")
                        .usernameParameter("TenDangNhap")
                        .passwordParameter("MatKhau")
                        .permitAll()
                        .successHandler((request, response, authentication) -> {
                            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
                            HttpSession session = request.getSession();
                            session.setAttribute("infoUser", userDetails);
                            if (userDetails.getAuthorities().stream()
                                    .anyMatch(auth -> auth.getAuthority().equals("NhanVien"))) {
                                response.sendRedirect("/nhanvien");
                            } else if (userDetails.getAuthorities().stream()
                                    .anyMatch(auth -> auth.getAuthority().equals("Admin"))) {
                                response.sendRedirect("/admin");
                            } else if (userDetails.getAuthorities().stream()
                                    .anyMatch(auth -> auth.getAuthority().equals("KhachHang"))) {
                                response.sendRedirect("/khachhang");
                            } else if (userDetails.getAuthorities().stream()
                            .anyMatch(auth -> auth.getAuthority().equals("NhaXe"))) {
                                response.sendRedirect("/nhaxe");
                            } else if (userDetails.getAuthorities().stream()
                            .anyMatch(auth -> auth.getAuthority().equals("Xe"))) {
                                response.sendRedirect("/xe");
                            } else {
                                response.sendRedirect("/");
                            }
                        }))
                .rememberMe(rememberMe -> rememberMe.key("uniqueAndSecret")
                        .tokenValiditySeconds(86400)
                        .userDetailsService(userDetailsService()))
                .exceptionHandling(exceptionHandling -> exceptionHandling.accessDeniedPage("/403"))
                .build();
    }
}
