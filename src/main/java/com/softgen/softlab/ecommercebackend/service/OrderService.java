package com.softgen.softlab.ecommercebackend.service;

import com.softgen.softlab.ecommercebackend.model.LocalUser;
import com.softgen.softlab.ecommercebackend.model.WebOrder;
import com.softgen.softlab.ecommercebackend.model.dao.WebOrderDAO;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final WebOrderDAO webOrderDAO;

    public List<WebOrder> getOrders(LocalUser user) {
        return webOrderDAO.findByUser(user);
    }
}
