package mx.bdrck.chat;

import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.component.page.Push;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Push
public class BdrckChatApplication implements AppShellConfigurator {

    public static void main(String[] args) {
        SpringApplication.run(BdrckChatApplication.class, args);
    }

}
