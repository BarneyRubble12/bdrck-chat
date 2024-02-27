package mx.bdrck.chat.back;

import com.vaadin.flow.spring.security.VaadinWebSecurity;
import mx.bdrck.chat.view.LoginView;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

import java.util.Set;

@Configuration
public class SecurityConfig extends VaadinWebSecurity {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
        setLoginView(http, LoginView.class);
    }

    @Bean
    UserDetailsManager userDetailsManager() {
        var users = Set.of("UserChat1", "UserChat2", "UserChat3")
                .stream()
                .map(name ->
                        User.withDefaultPasswordEncoder()
                                .username(name)
                                .password("password")
                                .roles("USER")
                                .build())
                .toList();
        return new InMemoryUserDetailsManager(users);
    }

}
