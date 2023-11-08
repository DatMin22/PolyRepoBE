package com.PolyRepo.PolyRepo.config;


import com.PolyRepo.PolyRepo.filter.JwtFilter;
import com.PolyRepo.PolyRepo.provider.CustomAuthenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Autowired
    private CustomAuthenProvider authenticationProvider;

    @Autowired
    private JwtFilter jwtFilter;

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.getSharedObject(AuthenticationManagerBuilder.class)
                .authenticationProvider(authenticationProvider)
                .build();
    }



//    Đầy là filter dùng để custom rule liên quan tới link hoặc
//    cấu hình của security
//    Java 8, 11 : antMatchers
//    Java 17~ : requestAntMatchers
//    CORS
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeHttpRequests() // Quy định lại các rule liên quan tới chứng thực cho link được gọi
//                .antMatchers("/signin","/signup").permitAll()
//                .anyRequest().authenticated() //Tất cả các link còn lại đều phải chứng thực
//                .and()
//                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)

//                .requestMatchers("/signin","/signup","/uploadfile/**","/downloadfile/**","/posts/**","/user/**").permitAll()

                .requestMatchers("/signin","/signup","/uploadfile/**","/downloadfile/**","/posts/**","/user/**","/cate/**","/comment/**","/like/**").permitAll()
//>>>>>>> 69eddf533d2b20a936dcd79ba8268af4c0bed0e9
                .anyRequest().authenticated()
                .and().addFilterBefore(jwtFilter,UsernamePasswordAuthenticationFilter.class)
                .build();
    }

}
