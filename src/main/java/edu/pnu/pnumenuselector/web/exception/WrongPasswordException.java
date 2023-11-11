package edu.pnu.pnumenuselector.web.exception;

public class WrongPasswordException extends RuntimeException{
    public WrongPasswordException() {
        super("잘못된 비밀번호 입니다.");
    }
}
