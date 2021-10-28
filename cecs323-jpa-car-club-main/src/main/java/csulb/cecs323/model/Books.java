package csulb.cecs323.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Books {
    @Id
    @Column(nullable = false, length = 20)
    private String ISBN;

    @Column(nullable = false, length = 30)
    private String authoring_entity_name;

    @Column(nullable = false, length = 64)
    private String title;

    @ManyToOne
    @JoinColumn(name="name", referencedColumnName = "name")
    private Publishers publisher;

    @Column(nullable = false, length = 80)
    private String publisher_name;

    @Column(nullable = false, length = 4)
    private int year_published;

    public Books() {

    }//end of default constructor

    public Books(String ISBN, String authoring_entity_name, String title, int year_published, Publishers publisher) {
        this.ISBN = ISBN;
        this.authoring_entity_name = authoring_entity_name;
        this.title = title;
        this.year_published = year_published;
        this.publisher = publisher;
        publisher_name = publisher.getName();
    }//end of overloaded constructor

    public Publishers getPublisher() {
        return publisher;
    }

    public void setPublisher(Publishers publisher) {
        this.publisher = publisher;
    }

    public String getISBN() {
        return ISBN;
    } //end of getISBN

    public String getAuthoring_entity_name() {
        return authoring_entity_name;
    } //end of getAuthoringEntityName

    public String getTitle() {
        return title;
    } //end of title

    public int getYear_published() {
        return year_published;
    } //end of getYear_published

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    } //end of setISBN

    public void setAuthoring_entity_name(String authoring_entity_name) {
        this.authoring_entity_name = authoring_entity_name;
    } //end of setAuthoring_entity_name

    public void setTitle(String title) {
        this.title = title;
    } //end of setTitle

    public void setPublisher_name(String publisher_name) {
        this.publisher_name = publisher_name;
    } //end of setPublisher_name

    public void setYear_published(int year_published) {
        this.year_published = year_published;
    } //end of setYear_published
}