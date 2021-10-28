package csulb.cecs323.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Publishers {
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "publisher_name")
    private List<Books> booksList = new ArrayList<>();

    @Id
    @Column(nullable = false, length = 80)
    private String name;

    @Column(nullable = false, length = 24)
    private String phone;

    @Column(nullable = false, length = 80)
    private String email;

    public Publishers() {
    }

    public Publishers(String name, String phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    public List<Books> getBooksList() {
        return booksList;
    }

    public void setBooksList(List<Books> booksList) {
        this.booksList = booksList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
