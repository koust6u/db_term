package edu.pnu.pnumenuselector.web.mvc.controller;

import edu.pnu.pnumenuselector.domain.data.dto.order.OrderDto;
import edu.pnu.pnumenuselector.domain.data.dto.order.StatusDto;
import edu.pnu.pnumenuselector.domain.data.entity.member.Member;
import edu.pnu.pnumenuselector.domain.data.entity.order.Order;
import edu.pnu.pnumenuselector.web.WebConstant;
import edu.pnu.pnumenuselector.web.mvc.service.MemberService;
import edu.pnu.pnumenuselector.web.mvc.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import static edu.pnu.pnumenuselector.web.WebConstant.SESSION_ID;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    private final MemberService memberService;
    @PostMapping("/status")
    public ResponseEntity<?> makeOrder(@SessionAttribute(name = SESSION_ID) Member member,
                                       @RequestBody StatusDto statusDto){
        Member user = memberService.searchMemberByUserId(member.getUserId());

        Order order = orderService.searchOneMyOrder(user, statusDto.getTitle());
        OrderDto orderDto = orderService.updateStatus(order, statusDto);
        return ResponseEntity.ok(orderDto);
    }

    @GetMapping("/status")
    public ResponseEntity<?> getStatus(@SessionAttribute(name = SESSION_ID) Member member,
                                       @RequestParam(name = "title")String title){
        Member user = memberService.searchMemberByUserId(member.getUserId());

        log.info("title: {}", title);
        Order order = orderService.searchOneMyOrder(user, title);
        return ResponseEntity.ok(order.convertToDto());
    }
}
