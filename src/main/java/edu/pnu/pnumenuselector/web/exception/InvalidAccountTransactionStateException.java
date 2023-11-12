package edu.pnu.pnumenuselector.web.exception;

public class InvalidAccountTransactionStateException extends RuntimeException{

    public InvalidAccountTransactionStateException() {
        super("크레딧 거래 중 오류가 발생하였습니다.");
    }
}
