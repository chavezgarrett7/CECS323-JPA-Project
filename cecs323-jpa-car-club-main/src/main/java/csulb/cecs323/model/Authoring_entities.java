package csulb.cecs323.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NamedNativeQueries({
        @NamedNativeQuery(name = "Authoring_entities.count",
                query = "SELECT count(*) " + "FROM AUTHORING_ENTITIES " + "WHERE email = ?"),
        @NamedNativeQuery(name = "Authoring_entities.all",
                query = "SELECT * " + "FROM AUTHORING_ENTITIES " + "ORDER BY NAME",
                resultClass = Authoring_entities.class)
})
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "authoring_entity_type", discriminatorType = DiscriminatorType.STRING)

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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "authoring_entity_name")
    private List<Books> booksList = new ArrayList<>();

    public Authoring_entities() {

    }

    public Authoring_entities(String email, String authoring_entity_type, String name, String head_writer, int year_formed) {
        this.email = email;
        this.authoring_entity_type = authoring_entity_type;
        this.name = name;
        this.head_writer = head_writer;
        this.year_formed = year_formed;
    }

    public Authoring_entities(String name, String email) {
        this.name = name;
        this.email = email;
    }

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

    public List<Books> getBooksList() {
        return booksList;
    }

    public void setBooksList(List<Books> booksList) {
        this.booksList = booksList;
    }

    @Override
    public String toString() {
        return "Authoring_entities{" +
                "email='" + email + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    // TODO: Figure out how to Implement
    protected void addAd_hoc_teams(Ad_hoc_teams ad_hoc_teams) {
    }

    // TODO: Figure out how to Implement
    protected void removeAd_hoc_teams(Ad_hoc_teams ad_hoc_teams) {
    }
}
