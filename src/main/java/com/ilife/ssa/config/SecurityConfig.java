package com.ilife.ssa.config;

import lombok.NoArgsConstructor;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.cglib.proxy.NoOp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    /**
     * 添加授权账户,可使用mysql
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("lijun").password("lijun").authorities("get");
        auth.inMemoryAuthentication()
                .withUser("lisi").password("lisi").authorities("/");
    }

    /**
     * 没有登录的时候会进入对应的认证
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
/*        http.authorizeRequests().antMatchers("/**")
                .fullyAuthenticated()
                .and()
                .formLogin() //formLogin 认证（默认）
                //.httpBasic() // httpBasic认证
        ;*/
        // 可以理解为用户haha角色的人具有查询/getUser的权限
        http.authorizeRequests().antMatchers("/getUser").hasAnyAuthority("get")
                .antMatchers("/testUser").hasAnyAuthority("test")
                .antMatchers("/deleteUser").hasAnyAuthority("delete")
                .antMatchers("/login").permitAll()
                .antMatchers("/**").fullyAuthenticated()
                .and().formLogin().loginPage("/login").and().csrf().disable();
    }

    /**
     * 密码加解密（spring security 5以后如果不加会报错）
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

}
