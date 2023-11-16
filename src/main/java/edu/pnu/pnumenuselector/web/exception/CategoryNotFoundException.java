package edu.pnu.pnumenuselector.web.exception;

public class CategoryNotFoundException extends RuntimeException {

    public CategoryNotFoundException(String categoryName) {
        super(String.format("%s은(는) 없는 카테고리 입니다. 다시 입력해주세요.", categoryName));
    }
}
