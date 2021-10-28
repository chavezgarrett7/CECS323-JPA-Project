package csulb.cecs323.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Authoring_entities {
    @Id
    @Column(nullable = false, length = 30)
    private String email;

    @Column(length = 31)
    private String authoring_entity_type;

    @Column(nullable = false, length = 80)
    private String name;

    @Column(nullable = false, length = 80)
    private String head_writer;

    @Column()
    private int year_formed;

    @OneToMany(cascade = CascadeType.PERSIST)
    private List<Authoring_entities> authoring_entitiesList = new ArrayList<>();

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAuthoring_entity_type() {
        return authoring_entity_type;
    }

    public void setAuthoring_entity_type(String authoring_entity_type) {
        this.authoring_entity_type = authoring_entity_type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHead_writer() {
        return head_writer;
    }

    public void setHead_writer(String head_writer) {
        this.head_writer = head_writer;
    }

    public int getYear_formed() {
        return year_formed;
    }

    public void setYear_formed(int year_formed) {
        this.year_formed = year_formed;
    }

    public List<Authoring_entities> getAuthoring_entitiesList() {
        return authoring_entitiesList;
    }

    public void setAuthoring_entitiesList(List<Authoring_entities> authoring_entitiesList) {
        this.authoring_entitiesList = authoring_entitiesList;
    }
}
