package edu.pnu.pnumenuselector.domain.data.entity.member;

public enum Role {
    ANONYMOUS(1), NORMAL(2), MANAGER(3), ADMIN(4);

    private final Integer grade;

    Role(Integer grade) {
        this.grade = grade;
    }

    public Integer getGrade() {
        return grade;
    }
}
