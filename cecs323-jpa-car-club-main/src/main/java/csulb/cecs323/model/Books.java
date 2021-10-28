package csulb.cecs323.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Books {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(nullable = false, length = 20)
    private String ISBN;

    @Column(nullable = false, length = 30)
    private String authoring_entity_name;

    @Column(nullable = false, length = 64)
    private String title;

    @Column(nullable = false, length = 80)
    private String publisher_name;

    @Column(nullable = false, length = 4)
    private int year_published;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="name", referencedColumnName = "name")
    private Publishers publisher;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="authoring_entity_name", referencedColumnName = "authoring_entity_type")
    private Authoring_entities authoring_entity;

    public Books() {

    }//end of default constructor

    public Books(String title, int year_published, Publishers publisher, Authoring_entities authoring_entity) {
        this.title = title;
        this.year_published = year_published;
        this.publisher = publisher;
        this.authoring_entity = authoring_entity;
        publisher_name = publisher.getName();
        authoring_entity_name = authoring_entity.getName();
    }//end of overloaded constructor

    public String getTitle() {
        return title;
    } //end of title

    public void setTitle(String title) {
        this.title = title;
    } //end of setTitle

    public String getISBN() {
        return ISBN;
    } //end of getISBN

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    } //end of setISBN

    public String getAuthoring_entity_name() {
        return authoring_entity_name;
    } //end of getAuthoringEntityName

    public void setAuthoring_entity_name(String authoring_entity_name) {
        this.authoring_entity_name = authoring_entity_name;
    } //end of setAuthoring_entity_name

    public String getPublisher_name() {
        return publisher_name;
    }

    public void setPublisher_name(String publisher_name) {
        this.publisher_name = publisher_name;
    } //end of setPublisher_name

    public int getYear_published() {
        return year_published;
    } //end of getYear_published

    public void setYear_published(int year_published) {
        this.year_published = year_published;
    } //end of setYear_published

    public Publishers getPublisher() {
        return publisher;
    }

    public void setPublisher(Publishers publisher) {
        this.publisher = publisher;
    }

    public Authoring_entities getAuthoring_entity() {
        return authoring_entity;
    }

    public void setAuthoring_entity(Authoring_entities authoring_entity) {
        this.authoring_entity = authoring_entity;
    }
}