package com.blog.yataverse.config;

import com.blog.yataverse.service.ExService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@EnableWebSecurity		//spring security 를 적용한다는 Annotation
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final ExService exService;

    /**
     * 규칙 설정
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
            csrf().disable();
        http.authorizeRequests()
                    .antMatchers("/chat","/chat/*","/sell","/sellProcess","/trade", "/qna").hasRole("USER")
                    .antMatchers("/font-awesome/**","/css/**","/","/login","/join","/info").permitAll()
//                    .anyRequest().authenticated()
                .and()
                    .formLogin()
                    .loginPage("/login")
                    .loginProcessingUrl("/loginProcess")
                    .defaultSuccessUrl("/")
                    .failureUrl("/login")
                    .usernameParameter("email")
                    .passwordParameter("userpwd")
                    .permitAll()

                .and()
                    .logout()
                    .logoutSuccessUrl("/")
                    .permitAll()

                .and()
                    .exceptionHandling().accessDeniedPage("/access-denied")
                ;	//로그인 창

    }

    /**
     * 로그인 인증 처리 메소드
     * @param auth
     * @throws Exception
     */
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(exService).passwordEncoder(new BCryptPasswordEncoder());
    }
}
