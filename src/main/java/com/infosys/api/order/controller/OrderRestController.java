package com.infosys.api.order.controller;

import com.infosys.api.order.aggregate.Cart;
import com.infosys.api.order.commands.RegisterItemCommand;
import com.infosys.api.order.commands.RegisterCartCommand;
import com.infosys.api.order.models.ItemsBean;
import com.infosys.api.order.models.CartBean;
import com.infosys.api.order.queries.GetItemsQuery;
import com.infosys.api.order.queries.GetCartQuery;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
public class OrderRestController {

    private final CommandGateway commandGateway;
    private final QueryGateway queryGateway;

    @Autowired
    public OrderRestController(CommandGateway commandGateway, QueryGateway queryGateway) {
        this.commandGateway = commandGateway;
        this.queryGateway = queryGateway;
    }

    @PostMapping("/order/cart")
    public String addCart(@RequestBody CartBean cart) {
        commandGateway.send(new RegisterCartCommand(UUID.fromString(cart.getCartIdentifier()), cart.getName()));
        return "Saved";
    }

    @GetMapping("/order/cart/{uuid}")
    public Cart getLibrary(@PathVariable String uuid) throws InterruptedException, ExecutionException {
        CompletableFuture<Cart> future = queryGateway.query(new GetCartQuery(UUID.fromString(uuid)), Cart.class);
        return future.get();
    }

    @PostMapping("/order/cart/{cart}/items")
    public String addItem(@PathVariable String cart, @RequestBody ItemsBean itemsBean) {
        commandGateway.send(new RegisterItemCommand(UUID.fromString(cart), itemsBean.getItemName(), itemsBean.getPrice(), itemsBean.getQuantity()));
        return "Saved";
    }

    @GetMapping("/order/cart/{cart}/items")
    public List<ItemsBean> getItems(@PathVariable String cart) throws InterruptedException, ExecutionException {
        return queryGateway.query(new GetItemsQuery(UUID.fromString(cart)), ResponseTypes.multipleInstancesOf(ItemsBean.class)).get();
    }
    
    @GetMapping("/health")
    public String getHealth() throws InterruptedException, ExecutionException {
        return "{\"status\": \"UP\"}";
    }
}
