package csulb.cecs323.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;

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

}
