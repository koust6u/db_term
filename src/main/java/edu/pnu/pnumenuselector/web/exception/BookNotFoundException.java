package edu.pnu.pnumenuselector.web.exception;

public class BookNotFoundException extends RuntimeException{
    public BookNotFoundException() {
        super("해당 책은 등록 되어 있지 않습니다.");
    }
}
