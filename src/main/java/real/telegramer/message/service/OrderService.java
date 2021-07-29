package real.telegramer.message.service;

import org.springframework.stereotype.Component;

@Component
public class OrderService {
    private boolean isOrder;

    public boolean isOrder() {
        return isOrder;
    }

    public void setOrder() {
        isOrder = true;
    }

    public void sendOrder() {
        isOrder = false;
    }
}
