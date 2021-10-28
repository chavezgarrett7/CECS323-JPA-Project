/*
 * Licensed under the Academic Free License (AFL 3.0).
 *     http://opensource.org/licenses/AFL-3.0
 *
 *  This code is distributed to CSULB students in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE, other than educational.
 *
 *  2018 Alvaro Monge <alvaro.monge@csulb.edu>
 *
 */

package csulb.cecs323.app;

// Import all of the entity classes that we have written for this application.
import csulb.cecs323.model.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

/**
 * A simple application to demonstrate how to persist an object in JPA.
 * <p>
 * This is for demonstration and educational purposes only.
 * </p>
 * <p>
 *     Originally provided by Dr. Alvaro Monge of CSULB, and subsequently modified by Dave Brown.
 * </p>
 */
public class BookDriver {
   /**
    * You will likely need the entityManager in a great many functions throughout your application.
    * Rather than make this a global variable, we will make it an instance variable within the CarClub
    * class, and create an instance of CarClub in the main.
    */
   private EntityManager entityManager;

   /**
    * The Logger can easily be configured to log to a file, rather than, or in addition to, the console.
    * We use it because it is easy to control how much or how little logging gets done without having to
    * go through the application and comment out/uncomment code and run the risk of introducing a bug.
    * Here also, we want to make sure that the one Logger instance is readily available throughout the
    * application, without resorting to creating a global variable.
    */
   private static final Logger LOGGER = Logger.getLogger(BookDriver.class.getName());

   private static Scanner scan = new Scanner(System.in);

   /**
    * The constructor for the CarClub class.  All that it does is stash the provided EntityManager
    * for use later in the application.
    * @param manager    The EntityManager that we will use.
    */
   public BookDriver(EntityManager manager) {
      this.entityManager = manager;
   }

   public static void main(String[] args) {
      LOGGER.fine("Creating EntityManagerFactory and EntityManager");
      EntityManagerFactory factory = Persistence.createEntityManagerFactory("BookDriver");
      EntityManager manager = factory.createEntityManager();
      // Create an instance of CarClub and store our new EntityManager as an instance variable.
      BookDriver bookdriver = new BookDriver(manager);


      // Any changes to the database need to be done within a transaction.
      // See: https://en.wikibooks.org/wiki/Java_Persistence/Transactions

      LOGGER.fine("Begin of Transaction");
      EntityTransaction tx = manager.getTransaction();

      tx.begin();

      List<Publishers> publishersList = new ArrayList<>();

      publishersList.add(newPublisher());
      bookdriver.createEntity(publishersList);

      // Commit the changes so that the new data persists and is visible to other users.
      tx.commit();
      LOGGER.fine("End of Transaction");

   } // End of the main method

   /**
    * Create and persist a list of objects to the database.
    * @param entities   The list of entities to persist.  These can be any object that has been
    *                   properly annotated in JPA and marked as "persistable."  I specifically
    *                   used a Java generic so that I did not have to write this over and over.
    */
   public <E> void createEntity(List <E> entities) {
      for (E next : entities) {
         LOGGER.info("Persisting: " + next);
         // Use the CarClub entityManager instance variable to get our EntityManager.
         this.entityManager.persist(next);
      }

      // The auto generated ID (if present) is not passed in to the constructor since JPA will
      // generate a value.  So the previous for loop will not show a value for the ID.  But
      // now that the Entity has been persisted, JPA has generated the ID and filled that in.
      for (E next : entities) {
         LOGGER.info("Persisted object after flush (non-null id): " + next);
      }
   } // End of createEntity member method

   public static Publishers newPublisher(){
      Publishers publisher = new Publishers();
      System.out.println("Enter a name for the new publisher (Less than 30 Characters): ");
      String name = scan.nextLine();

      while(!isValidName(name)){
         System.out.println("ERROR: Invalid input. Try again...");
         name = scan.nextLine();
      }

      publisher.setName(name);

      System.out.println("Enter a phone number for the publisher (At least 10 digits): ");
      String phone = scan.next();

      // Regular expression to see if string contains integers
      while(!isValidPhone(phone)){
         System.out.println("ERROR: Invalid input. Try again...");
         phone = scan.next();
      }

      publisher.setPhone(phone);

      System.out.println("Enter an email for the publisher (At most 64 characters): ");
      String email = scan.next();

      // Regular expression to see if string contains integers
      while(!isValidEmail(email)){
         System.out.println("ERROR: Invalid input. Try again...");
         email = scan.next();
      }
      publisher.setEmail(email);
      return publisher;
   }

   private static boolean isValidName(String name) {
      if(name.length() >= 30 || name.isEmpty()) {
         return false;
      }
      return true;
   }

   public static boolean isValidPhone(String phone) {
      boolean isValid = true;

      int hyphenCount = 0;
      int leftParenthCount = 0;
      int rightParenthCount = 0;

      if (phone != null) {
         if(phone.length() < 10 || phone.length() > 30){
            System.out.println("A phone number includes at least 10 digits or you entered one that is too long");
            return false;
         }
         for (char c : phone.toCharArray()) {
            if (!Character.isDigit(c) && Character.isAlphabetic(c) && c != '-' && c != '(' && c != ')') {
               return false;
            }

            if(c == '-'){
               hyphenCount++;
               if(hyphenCount > 2){
                  System.out.println("Only two hyphens are permitted for a phone number");
                  return false;
               }
            }

            if(c == '('){
               leftParenthCount++;
               if(leftParenthCount > 1){
                  System.out.println("Only one left parenthesis is permitted for a phone number");
                  return false;
               }
            }

            if(c == ')'){
               rightParenthCount++;
               if(rightParenthCount > 1){
                  System.out.println("Only one right parenthesis is permitted for a phone number");
                  return false;
               }
            }
         }
      }

      return true;
   }

   private static boolean isValidEmail(String email) {
      if(email == null){
         System.out.println("Email can not be empty");
         return false;
      }

      if(email.length() > 64){
         System.out.println("Email must be less than 64 characters");
         return false;
      }

      if(!email.contains("@")){
         System.out.println("Email must include @domain at the end of username");
         return false;
      }

      if(!(email.contains(".com") || email.contains(".org") || email.contains(".net") || email.contains(".gov"))){
         System.out.println("Email must include @domain at the end of username and then either .com, .org, .net, or .gov at the end of the domain");
         return false;
      }

      return true;
   }

} // End of CarClub class
