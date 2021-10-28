package csulb.cecs323.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Ad_hoc_teams {
    @Id
    @Column(nullable = false, length = 30)
    private String individual_authors_email;

    @Id
    @Column(nullable = false, length = 30)
    private String ad_hoc_teams_email;

    public Ad_hoc_teams() {

    }//end of default constructor

    public Ad_hoc_teams(String individual_authors_email, String ad_hoc_teams_email) {
        this.individual_authors_email = individual_authors_email;
        this.ad_hoc_teams_email = ad_hoc_teams_email;
    }//end of overloaded constructor

    public String getIndividual_authors_email() {
        return individual_authors_email;
    } //end of getIndividual_authors_email

    public String getAd_hoc_teams_email() {
        return ad_hoc_teams_email;
    } //end of getAd_hoc_teams_email

    public void setIndividual_authors_email(String individual_authors_email) {
        this.individual_authors_email = individual_authors_email;
    } //end of setIndividual_authors_email

    public void setAd_hoc_teams_email(String ad_hoc_teams_email) {
        this.ad_hoc_teams_email = ad_hoc_teams_email;
    } // end of setAd_hoc_teams_email
} //end of Ad_hoc_teams class