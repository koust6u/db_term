package edu.pnu.pnumenuselector.web.exception;

public class DuplicateUserIdException extends RuntimeException{

    public DuplicateUserIdException() {
        super("아이디가 중복되었습니다.");
    }
}
