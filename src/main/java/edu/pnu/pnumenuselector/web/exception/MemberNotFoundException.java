package edu.pnu.pnumenuselector.web.exception;

public class MemberNotFoundException extends RuntimeException{
    public MemberNotFoundException(String userId) {
        super(String.format("[%s]는 존재하지 않는 사용자입니다.",userId));
    }
}
