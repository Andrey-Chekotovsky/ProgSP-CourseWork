package org.service.Service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.db.JpaRepositories.OrderProductInfoRepository;
import org.db.JpaRepositories.OrderRepository;
import org.db.Models.Message;
import org.db.Models.Order;
import org.db.Models.OrderProductInfo;
import org.service.Dto.OrderDetailsDto;
import org.service.Dto.OrderDto;
import org.service.Dto.OrderProductInfoDto;
import org.service.Mappers.Mapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderProductInfoRepository orderProductInfoRepository;
    private final Mapper<OrderDto, Order> orderDtoOrderMapper;
    private final Mapper<Order, OrderDto> orderOrderDtoMapper;
    private final Mapper<OrderProductInfo, OrderProductInfoDto> orderProductInfoMapper;
    private final  Mapper<OrderProductInfoDto , OrderProductInfo> orderProductInfoDtoMapper;

    public List<OrderDto> getOrders() {
        var orders = orderRepository.findAll();
        return orders.stream().map(orderOrderDtoMapper::map).toList();
    }
    public OrderDetailsDto getOrder(int id) {
        var order = orderOrderDtoMapper.map(orderRepository.getReferenceById(id));
        var details = orderProductInfoRepository.findByOrder(id).stream()
                .map(orderProductInfoMapper::map).toList();
        return new OrderDetailsDto(order, details);
    }
    public void deleteOrder(int id) {
        orderRepository.deleteById(id);
    }
    public OrderDto createOrder(OrderDetailsDto dto) {
        final var order = Order.builder()
                .price(dto.getPrice())
                .user(dto.getUserId())
                .build();
        final var newOrder = orderRepository.save(order);
        dto.getDetails().stream().forEach(p ->
                p.setOrderId(order.getId()));
        orderProductInfoRepository.saveAll(dto.getDetails().stream()
                .map(orderProductInfoDtoMapper::map).toList()
        );
        return orderOrderDtoMapper.map(newOrder);
    }
    public List<OrderProductInfoDto> getCompanySales(int id) {
        var details = orderProductInfoRepository.findByCompany(id).stream()
                .map(orderProductInfoMapper::map).toList();
        return details;
    }
}
