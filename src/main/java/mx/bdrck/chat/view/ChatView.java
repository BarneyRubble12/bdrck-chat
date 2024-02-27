package mx.bdrck.chat.view;

import com.vaadin.flow.component.messages.MessageInput;
import com.vaadin.flow.component.messages.MessageList;
import com.vaadin.flow.component.messages.MessageListItem;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;
import mx.bdrck.chat.back.service.ChatService;

import java.util.ArrayList;

@Route("")
@PermitAll
public class ChatView extends VerticalLayout {

    public ChatView(ChatService chatService) {
        var messageList = new MessageList();
        var textInput = new MessageInput();

        setSizeFull();
        add(messageList, textInput);
        expand(messageList);
        textInput.setWidthFull();

        chatService
                .join()
                .subscribe(message -> {
                    var nl = new ArrayList<>(messageList.getItems());
                    nl.add(new MessageListItem(message.text(), message.time(), message.userName()));
                    getUI().ifPresent(ui ->
                                ui.access(() ->
                                        messageList.setItems(nl)));
                });
        textInput.addSubmitListener(event -> chatService.add(event.getValue()));
    }
}
