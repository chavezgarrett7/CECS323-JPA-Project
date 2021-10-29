package csulb.cecs323.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Writing Groups")
public class Writing_groups extends Authoring_entities {
    @Column(nullable = false, length = 80)
    private String name;

    @Column()
    private int year_formed;
}
