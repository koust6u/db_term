package edu.pnu.pnumenuselector.web.exception;

public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException() {
        super("해당 주문을 찾을 수 없습니다.");
    }
}
