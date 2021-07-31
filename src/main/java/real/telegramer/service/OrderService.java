package real.telegramer.service;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
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
