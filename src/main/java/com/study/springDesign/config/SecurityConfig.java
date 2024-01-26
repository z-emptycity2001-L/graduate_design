package com.study.springDesign.config;

import com.study.springDesign.detailService.UserDetailsService;
import com.study.springDesign.filter.JwtAuthenticationTokenFilter;
import com.study.springDesign.handler.RestAuthorizationEntryPoint;
import com.study.springDesign.handler.RestfulAccessDeniedHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {


    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    @Autowired
    private RestAuthorizationEntryPoint restAuthorizationEntryPoint;
    @Autowired
    private RestfulAccessDeniedHandler restfulAccessDeniedHandler;
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        Customizer<LogoutConfigurer<HttpSecurity>> logoutConfigurerCustomizer = new Customizer<LogoutConfigurer<HttpSecurity>>() {
            @Override
            public void customize(LogoutConfigurer<HttpSecurity> httpSecurityLogoutConfigurer) {
                // 禁用默认登出页
                httpSecurityLogoutConfigurer.disable();
            }
        };
        httpSecurity

                // CSRF禁用，因为不使用session
                .csrf(AbstractHttpConfigurer::disable)
                // 禁用basic明文验证
                .httpBasic(AbstractHttpConfigurer::disable)
                // 禁用默认登录页
                .formLogin(AbstractHttpConfigurer::disable)
                // 禁用默认登出页
                .logout(logoutConfigurerCustomizer);
        // 前后端分离是无状态的，不需要session了，直接禁用。
        httpSecurity.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                    .authorizeHttpRequests(authorizeHttpRequests->authorizeHttpRequests
                    //允许直接访问的接口，不需要认证
                    .requestMatchers(HttpMethod.POST,"/login","/user/register","/index").permitAll()
                    // 允许 SpringMVC 的默认错误地址匿名访问
                    .requestMatchers("/error").permitAll()
                    .anyRequest().authenticated()

        );
        //        添加自定义未授权和未登录返回结果
        httpSecurity.exceptionHandling(exceptionHandling-> exceptionHandling
//                        未登录（请求头未设置token，或token错误）
                        .accessDeniedHandler(restfulAccessDeniedHandler)
//                        未授权(可能token过期)
                        .authenticationEntryPoint(restAuthorizationEntryPoint)
        );
        httpSecurity.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        // 提供自定义loadUserByUsername
        authProvider.setUserDetailsService(userDetailsService);
        // 指定密码编辑器
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
