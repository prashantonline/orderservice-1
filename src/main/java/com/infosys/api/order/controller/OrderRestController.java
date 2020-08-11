package com.infosys.api.order.controller;


import com.infosys.api.order.aggregate.Cart;
import com.infosys.api.order.model.CartBean;
import com.infosys.api.order.model.ItemsBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
public class OrderRestController {

    @PostMapping("/order/cart")
    public String addCart(@RequestBody CartBean cart) {
        return "Saved";
    }

    @GetMapping("/order/cart/{uuid}")
    public Cart getCart(@PathVariable String uuid) throws InterruptedException, ExecutionException {
        String[] items = {"fan", "chocolates", "paint", "toys"};
        Cart cart = new Cart("cd9d42c7-997c-4ba2-b70e-86aafbf2c5e7", "SampleCart", Arrays.asList(items));
        return cart;
    }

    @PostMapping("/order/cart/{cart}/items")
    public String addItem(@PathVariable String cart, @RequestBody ItemsBean itemsBean) {
        return "Saved";
    }

    @GetMapping("/order/cart/{cart}/items")
    public List<ItemsBean> getItems(@PathVariable String cart) throws InterruptedException, ExecutionException {

        List<ItemsBean> itemsBeans = new ArrayList<>();
        itemsBeans.add(new ItemsBean("fan", 20.00, 2));
        itemsBeans.add(new ItemsBean("chocolates", 2.00, 5));
        itemsBeans.add(new ItemsBean("paint", 1.00, 1));
        itemsBeans.add(new ItemsBean("toys", 20.00, 2));


        return itemsBeans;
    }
    
    /*
    @GetMapping("/health")
    public String getHealth() throws InterruptedException, ExecutionException {
        return "{\"status\": \"UP\"}";
    }
    */
}
