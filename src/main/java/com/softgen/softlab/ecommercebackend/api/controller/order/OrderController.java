package com.softgen.softlab.ecommercebackend.api.controller.order;

import com.softgen.softlab.ecommercebackend.model.LocalUser;
import com.softgen.softlab.ecommercebackend.model.WebOrder;
import com.softgen.softlab.ecommercebackend.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.LifecycleState;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public List<WebOrder> getOrders(@AuthenticationPrincipal LocalUser user) {
        return orderService.getOrders(user);
    }

}
