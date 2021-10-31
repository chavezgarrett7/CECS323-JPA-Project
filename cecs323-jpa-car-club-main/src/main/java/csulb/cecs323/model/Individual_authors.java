package csulb.cecs323.model;

import javax.persistence.*;
import java.util.List;

@Entity
@DiscriminatorValue("Individual Authors")
public class Individual_authors extends Authoring_entities{
//    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "individual_authorsList")
//    private List<Ad_hoc_teams> ad_hoc_teamsList;
    @ManyToMany(mappedBy = "individual_authorsList", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Ad_hoc_teams> ad_hoc_teamsList;

    @Id
    @Column(nullable = false, length = 30)
    private String individual_authors_email;

    public void setIndividual_authors_email(String individual_authors_email) {
        this.individual_authors_email = individual_authors_email;
    } //end of setIndividual_authors_email

    public String getIndividual_authors_email() {
        return individual_authors_email;
    } //end of getIndividual_authors_email
}
