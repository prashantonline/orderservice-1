package com.infosys.api.order.events.listeners;

import com.infosys.api.order.events.ItemCreatedEvent;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
public class ItemsCreatedEventListener {

    @EventHandler
    public void onEvent(ItemCreatedEvent event) {
        System.out.println("Received ItemAddedEvent id:" + event.getItemName() + " on thread named "
                + Thread.currentThread().getName());
    }
}
