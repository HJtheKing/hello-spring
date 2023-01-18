package hello.hellospring.domain;

public class Member {

    private Long id;
    private String name;

    // alt + insert : generate getter setter 자동 작성 가능

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
