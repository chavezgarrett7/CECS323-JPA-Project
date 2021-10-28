package csulb.cecs323.model;

import javax.persistence.*;

@Entity
public class Publishers {
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//
//    @Column(nullable = false, length = 13)
//    private int isbn;

    @Id
    @Column(nullable = false, length = 20)
    private String name;

    @Column(nullable = false, length = 30)
    private String phone;

    @Column(nullable = false, length = 64)
    private String email;

    public Publishers() {
    }

    public Publishers(String name, String phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
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
