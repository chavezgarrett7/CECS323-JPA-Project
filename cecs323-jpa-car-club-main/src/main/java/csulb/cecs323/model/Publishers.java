package csulb.cecs323.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Publishers {
    /** records books associated with this Publisher. OneToMany with Books.java mapped by publisher's name */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "publisher_name")
    private List<Books> booksList = new ArrayList<>();

    /** records name string of this Publisher. Unique, cannot be NULL, length <= 80 */
    @Id
    @Column(nullable = false, length = 80)
    private String name;

    /** records phone number string of this Publisher. Cannot be NULL, length <= 24 */
    @Column(nullable = false, length = 24)
    private String phone;

    /** records email string of this Publisher. Cannot be NULL, length <= 80 */
    @Column(nullable = false, length = 80)
    private String email;

    /**
     * Default Constructor for Publishers class. Accepts no arguments, and does not
     * instantiate any values within the class. Creates instance of class to be
     * manipulated.
     */
    public Publishers() {
    }

    /**
     * Overloaded Constructor for Publishers class. Accepts name, phone number,
     * and email strings as arguments to instantiate the values within the
     * instance of the class.
     * <p>
     * Instantiates the values for the instance of the class
     *
     * @param  name the name of the publisher instance (UNIQUE TO SPECIFIC PUBLISHER)
     * @param  phone the phone number of the publisher instance
     * @param  phone the phone number of the publisher instance
     */
    public Publishers(String name, String phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    /**
     * This method is used to get the list of books associated for
     * this particular instance of the class.
     * <p>
     * @return booksList Returns the list of books associated with
     *                   this instance of the Publisher class to
     *                   be used for various functions
     */
    public List<Books> getBooksList() {
        return booksList;
    }

    /**
     * Overloaded Constructor for Publishers class. Accepts name, phone number,
     * and email strings as arguments to instantiate the values within the
     * instance of the class
     * <p>
     * Instantiates the values for this instance of the class
     */
    public void setBooksList(List<Books> booksList) {
        this.booksList = booksList;
    }

    /**
     * Returns the name of the Publisher class instance that can be
     * printed on the screen.
     * <p>
     * This method always returns immediately, whether or not the
     * name exists.
     *
     * @return name String name of this Publisher class instance
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the phone number of the Publisher class instance that
     * can be printed on the screen.
     * <p>
     * This method always returns immediately, whether or not the
     * phone number exists.
     *
     * @return phone String phone number of this Publisher class instance
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets this phone number of Publisher class instance to the
     * passed phone number String argument
     * <p>
     * This method always returns immediately, whether or not the
     * phone number exists.
     *
     * @param phone String phone number to set this instances phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Returns the email of the Publisher class instance that
     * can be printed on the screen.
     * <p>
     * This method always returns immediately, whether or not the
     * email exists.
     *
     * @return email String email of this Publisher class instance
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets this email of Publisher class instance to the
     * passed email String argument
     * <p>
     * This method always returns immediately, whether or not the
     * email exists.
     *
     * @param email String email to set this instances email
     */
    public void setEmail(String email) {
        this.email = email;
    }

}
