package com.infosys.api.order.aggregate;

import com.infosys.api.order.commands.RegisterItemCommand;
import com.infosys.api.order.commands.RegisterCartCommand;
import com.infosys.api.order.events.ItemCreatedEvent;
import com.infosys.api.order.events.CartCreatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Aggregate
public class Cart {

    @AggregateIdentifier
    private UUID cartIdentifier;

    private String name;

    private List<String> items;

    protected Cart() {
        // For Axon instantiation
    }

    @CommandHandler
    public Cart(RegisterCartCommand cmd) {
        Assert.notNull(cmd.getCartIdentifier(), "ID should not be null");
        Assert.notNull(cmd.getName(), "Name should not be null");

        AggregateLifecycle.apply(new CartCreatedEvent(cmd.getCartIdentifier(), cmd.getName()));
    }

    public UUID getCartIdentifier() {
        return cartIdentifier;
    }

    public String getName() {
        return name;
    }

    public List<String> getItems() {
        return items;
    }

    @CommandHandler
    public void addItem(RegisterItemCommand cmd) {
        Assert.notNull(cmd.getCartIdentifier(), "ID should not be null");
        Assert.notNull(cmd.getItemName(), "item should not be null");
        AggregateLifecycle.apply(new ItemCreatedEvent(cmd.getCartIdentifier(), cmd.getItemName(), cmd.getQuantity(), cmd.getPrice()));
    }

    @EventSourcingHandler
    private void handleCreatedEvent(CartCreatedEvent event) {
        cartIdentifier = event.getCartIdentifier();
        name = event.getName();
        items = new ArrayList<>();
    }

    @EventSourcingHandler
    private void addBook(ItemCreatedEvent event) {
        items.add(event.getItemName());
    }

}
