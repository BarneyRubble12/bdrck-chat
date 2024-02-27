package mx.bdrck.chat.back.service;

import com.vaadin.flow.spring.security.AuthenticationContext;
import mx.bdrck.chat.back.domain.Message;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.time.Instant;

@Service
public class ChatService {

    private final Sinks.Many<Message> messages = Sinks.many().multicast().directBestEffort();
    private final Flux<Message> messagesFlux = messages.asFlux();
    private final AuthenticationContext authenticationContext;

    public ChatService(AuthenticationContext authenticationContext) {
        this.authenticationContext = authenticationContext;
    }

    public Flux<Message> join() {
        return this.messagesFlux;
    }

    public void add(String message) {
        var userName =  authenticationContext.getPrincipalName().orElse("Anonymous");
        this.messages.tryEmitNext(
                new Message(userName, message, Instant.now())
        );
    }

}
