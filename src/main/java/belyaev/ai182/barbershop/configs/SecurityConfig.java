package belyaev.ai182.barbershop.configs;

import belyaev.ai182.barbershop.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserService userService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/css/**", "/images/**").permitAll();
        http.csrf().disable()
                .authorizeRequests()
                //Доступ только для не зарегистрированных пользователей
                    .antMatchers("/registration").not().fullyAuthenticated()
                    //Доступ только для пользователей с ролью Администратор
                    .antMatchers("/work/**").hasRole("ADMIN")
                    .antMatchers("/appointment_add/**").hasRole("USER")
                    .antMatchers("/appointment_delete/**").hasAnyRole("WORKER", "ADMIN")
                    //Доступ разрешен всем пользователей
                    .antMatchers("/", "/appointment").permitAll()
                    //Все остальные страницы требуют аутентификации
                    .anyRequest().authenticated()
                .and()
                //Настройка для входа в систему
                    .formLogin()
                    .loginPage("/login")
                    .defaultSuccessUrl("/", true)
                    //Перенарпавление на главную страницу после успешного входа
                    .permitAll()
                .and()
                    .logout()
                    .logoutUrl("/logout")
                    .permitAll()
                    .logoutSuccessUrl("/");
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
