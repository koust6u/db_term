package edu.pnu.pnumenuselector.web.mvc.service;

import edu.pnu.pnumenuselector.domain.data.dto.order.OrderDto;
import edu.pnu.pnumenuselector.domain.data.dto.order.StatusDto;
import edu.pnu.pnumenuselector.domain.data.entity.member.Member;
import edu.pnu.pnumenuselector.domain.data.entity.order.Order;
import edu.pnu.pnumenuselector.web.exception.OrderNotFoundException;
import edu.pnu.pnumenuselector.web.mvc.repository.MemberRepository;
import edu.pnu.pnumenuselector.web.mvc.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    private final MemberRepository memberRepository;


    public Order searchOneMyOrder(Member member,String bookTitle){
        List<Order> orderByLender = orderRepository.findOrderByLender(member);
        return orderByLender.stream()
                .filter(e -> e.getBook()
                        .getTitle()
                        .equals(bookTitle))
                .findFirst()
                .orElseThrow(IllegalAccessError::new);
    }

    @Transactional
    public OrderDto updateStatus(Order order, StatusDto dto){
        order.setStatus(dto.getStatus());
        order.setReceipt(dto.getReceipt());

        return order.convertToDto();
    }


    public Order findOne(Long id){
        return orderRepository.findById(id)
                .orElseThrow(IllegalAccessError::new);
    }

}
