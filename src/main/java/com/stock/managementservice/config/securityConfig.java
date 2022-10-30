package com.stock.managementservice.config;

import com.stock.managementservice.service.CustomOAuth2Service;
import com.stock.managementservice.service.loginService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class securityConfig extends WebSecurityConfigurerAdapter {
    private loginService loginService;
    private final CustomOAuth2Service customOAuth2Service;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) throws Exception{
        web.ignoring().antMatchers("/static/**").antMatchers("/h2-console/**");
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http/*.csrf().disable()*/
                .headers().frameOptions().disable()
                .and()
                    .authorizeRequests()
                    // 페이지 권한 설정
                    /*.antMatchers("/").hasRole("ADMIN")
                    .antMatchers("/").hasRole("MEMBER")*/
                    .antMatchers("/**").permitAll()
                    .antMatchers("/h2-console/**").permitAll()
                    .anyRequest().authenticated()
                .and()
                    .csrf()
                    .ignoringAntMatchers("/h2-console/**")    // 여기!
                .and() // 로그인 설정
                    .formLogin()
                    .loginPage("/login/loginForm")
                    .defaultSuccessUrl("/",true)
                    .permitAll()
                .and() // 로그아웃 설정
                    .logout()
                    .logoutRequestMatcher(new AntPathRequestMatcher("/login/logout"))
                    .logoutSuccessUrl("/")
                    .invalidateHttpSession(true)
                .and()
                     // 403 예외처리 핸들링(403: 사이트 관리자가 의도적으로 액세스를 차단)
                    .exceptionHandling().accessDeniedPage("/sidebar/404")
                .and()
                    .oauth2Login()
                        .userInfoEndpoint()
                            .userService(customOAuth2Service);

    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(loginService).passwordEncoder(passwordEncoder());
    }
}
