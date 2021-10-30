package csulb.cecs323.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("Ad Hoc Teams")
public class Ad_hoc_teams extends Authoring_entities{
//    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
//    @JoinTable(
//            name = "Ad_hoc_teams_member",
//            joinColumns = @JoinColumn(name = "ad_hoc_teams_email"),
//            inverseJoinColumns = @JoinColumn(name = "individual_authors_email")
//    )
//    private List<Individual_authors> individual_authorsList;
//
//    @Id
//    @Column(nullable = false, length = 30)
//    private String individual_authors_email;
//
//    @Id
//    @Column(nullable = false, length = 30)
//    private String ad_hoc_teams_email;
//
//    public Ad_hoc_teams() {
//
//    }//end of default constructor
//
//    public Ad_hoc_teams(String name, String email) {
//        super(name, email);
//        this.individual_authorsList = new ArrayList<Individual_authors>();
//    }//end of overloaded constructor
//
//    public List<Individual_authors> getIndividual_authorsList() {
//        return individual_authorsList;
//    }
//
//    public void addIndividual_authors(Individual_authors individual_authors) {
//        if(!this.individual_authorsList.contains(individual_authors)){
//            this.individual_authorsList.add(individual_authors);
//            individual_authors.addAd_hoc_teams(this);
//        }
//    }
//
//    public void removeIndividualAuthors(Individual_authors individual_authors){
//        if(this.individual_authorsList.contains(individual_authors)){
//            this.individual_authorsList.remove(individual_authors);
//            individual_authors.removeAd_hoc_teams(this);
//        }
//    }
//
//    public String getIndividual_authors_email() {
//        return individual_authors_email;
//    } //end of getIndividual_authors_email
//
//    public String getAd_hoc_teams_email() {
//        return ad_hoc_teams_email;
//    } //end of getAd_hoc_teams_email
//
//    public void setIndividual_authors_email(String individual_authors_email) {
//        this.individual_authors_email = individual_authors_email;
//    } //end of setIndividual_authors_email
//
//    public void setAd_hoc_teams_email(String ad_hoc_teams_email) {
//        this.ad_hoc_teams_email = ad_hoc_teams_email;
//    } // end of setAd_hoc_teams_email
} //end of Ad_hoc_teams class