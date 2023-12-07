package edu.pnu.pnumenuselector.web.mvc.controller;

import edu.pnu.pnumenuselector.domain.data.dto.account.TransferForm;
import edu.pnu.pnumenuselector.domain.data.dto.order.OrderDto;
import edu.pnu.pnumenuselector.domain.data.dto.order.OrderRequest;
import edu.pnu.pnumenuselector.domain.data.dto.order.StatusDto;
import edu.pnu.pnumenuselector.domain.data.entity.member.Member;
import edu.pnu.pnumenuselector.domain.data.entity.order.Order;
import edu.pnu.pnumenuselector.web.WebConstant;
import edu.pnu.pnumenuselector.web.mvc.service.AccountService;
import edu.pnu.pnumenuselector.web.mvc.service.MemberService;
import edu.pnu.pnumenuselector.web.mvc.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static edu.pnu.pnumenuselector.web.WebConstant.SESSION_ID;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    private final MemberService memberService;

    private final AccountService accountService;
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

        Order order = orderService.searchOneMyOrder(user, title);
        return ResponseEntity.ok(order.convertToDto());
    }

    @PostMapping("/request")
    @Transactional
    public ResponseEntity<?> borrowRequest(@SessionAttribute(name = SESSION_ID) Member member,
                                           @RequestBody OrderRequest request){

        if (member.getUserId().equals(request.getOwnerId())){
            throw new IllegalArgumentException();
        }
        Member borrower = memberService.searchMemberByUserId(member.getUserId());
        Member owner =  memberService.searchMemberByUserId(request.getOwnerId());
        Order order = orderService.searchOneMyOrder(owner, request.getBookName());

        if (order.getBorrower() != null ){
            throw  new IllegalArgumentException();
        }
        accountService.transfer(borrower, owner, new TransferForm(request.getPrice()));
        orderService.borrow(order,borrower);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/borrow")
    public ResponseEntity<?> borrowList(@SessionAttribute(name = SESSION_ID)Member member){
        List<OrderDto> orderDtos = orderService.searchMyBorrowBook(member)
                .stream()
                .map(Order::convertToDto)
                .toList();

        return ResponseEntity.ok(orderDtos);
    }
}
