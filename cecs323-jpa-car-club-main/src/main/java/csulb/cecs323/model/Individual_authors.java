package csulb.cecs323.model;

import javax.persistence.*;
import java.util.List;

@Entity
@DiscriminatorValue("Individual Authors")
public class Individual_authors extends Authoring_entities{
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "Ad_hoc_teams_member",
            joinColumns = @JoinColumn(name = "ad_hoc_teams_email"),
            inverseJoinColumns = @JoinColumn(name = "individual_authors_email")
    )
    private List<Individual_authors> individual_authorsList;
}
