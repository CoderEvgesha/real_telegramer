package real.telegramer.message.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;

@Component
public class ChatService {
    private final ApplicationContext appContext;
    private final Map<Long, AbstractMap.SimpleEntry<BackService, OrderService>> chatServices;

    public ChatService(@Autowired ApplicationContext appContext) {
        this.appContext = appContext;
        this.chatServices = new HashMap<>();
    }

    public BackService getBackServiceForCurrentUser(Long chatId) {
        AbstractMap.SimpleEntry<BackService, OrderService> pair = chatServices.get(chatId);
        if (pair == null) {
            createNewPairForNewChat(chatId);
            return getBackServiceForCurrentUser(chatId);
        } else {
            return pair.getKey();
        }
    }

    public OrderService getOrderServiceForCurrentUser(Long chatId) {
        AbstractMap.SimpleEntry<BackService, OrderService> pair = chatServices.get(chatId);
        if (pair == null) {
            createNewPairForNewChat(chatId);
            return getOrderServiceForCurrentUser(chatId);
        } else {
            return pair.getValue();
        }
    }

    private void createNewPairForNewChat(Long chatId) {
        BackService backService = appContext.getBean(BackService.class);
        OrderService orderService = appContext.getBean(OrderService.class);
        chatServices.put(chatId, new AbstractMap.SimpleEntry<>(backService, orderService));
    }
}
