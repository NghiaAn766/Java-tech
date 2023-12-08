package com.example.lab09.service;

import com.example.lab09.dto.OrderDTO;
import com.example.lab09.entity.Order;
import com.example.lab09.entity.OrderDetail;
import com.example.lab09.entity.Product;
import com.example.lab09.exception.NotFoundException;
import com.example.lab09.repository.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class OrderServiceImpl implements OrderService{
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private AccountService accountService;

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderDetailService orderDetailService;

    @Override
    public List<OrderDTO> getAllOrders() {
        return orderRepository.findAll().stream().map(this::mapToModel).collect(Collectors.toList());
    }

    @Override
    public OrderDTO getOrder(Long id) {
        Optional<Order> order = orderRepository.findById(id);
        if (order.isPresent()){
            return mapToModel(order.get());
        }
        throw new NotFoundException("Order not found");
    }

    @Override
    public void createOrder(Long accountId, Long productId, int quantity) throws Exception {
        Product product = productService.getProduct(productId);
        double totalPrice = quantity * product.getPrice();

        Order order = new Order();
        order.setAccount(accountService.get(accountId));
        order.setTotalPrice(totalPrice);

        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setTotalPrice(totalPrice);
        orderDetail.setQuantity(quantity);
        orderDetail.setProduct(product);
        orderDetail.setOrder(order);

        List<OrderDetail> orderDetails = new ArrayList<>();
        orderDetails.add(orderDetail);
        order.setOrderDetails(orderDetails);

        orderRepository.save(order);
    }

    @Override
    public void update(Long id, double totalPrice) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        order.setTotalPrice(totalPrice);
        orderRepository.save(order);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        orderDetailService.deleteAllByOrderId(id);
        orderRepository.deleteById(id);
    }

    private OrderDTO mapToModel(Order order){
        OrderDTO orderDTO = new OrderDTO();

        orderDTO.setFullName(order.getAccount().getFirstName() + " " +  order.getAccount().getLastName());
        orderDTO.setTotalPrice(order.getTotalPrice());

        if (order.getOrderDetails() != null){
            List<String> productNameList = new ArrayList<>();
            for (OrderDetail orderDetail : order.getOrderDetails()){
                productNameList.add(orderDetail.getProduct().getName());
            }
            orderDTO.setProductNameList(productNameList);
        }
        return orderDTO;
    }
}
