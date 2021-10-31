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
   private static EntityManager entityManager;

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

      List<Books> booksList = new ArrayList<>();
      List<Publishers> publishersList = new ArrayList<>();
      List<Writing_groups> writingGroupsList = new ArrayList<>();



      // Any changes to the database need to be done within a transaction.
      // See: https://en.wikibooks.org/wiki/Java_Persistence/Transactions

      LOGGER.fine("Begin of Transaction");
      EntityTransaction tx = manager.getTransaction();

      int choice = 0;

      while(choice != 9){
         choice = menu();

         switch(choice){
            case 1:
               System.out.println("\nPlease fill out the following prompts to add a new Book to the database");
               booksList.add(addNewBook());
               bookdriver.createEntity(booksList);
               break;
            case 2:
               System.out.println("\nPlease fill out the following prompts to add a new Publisher to the database");
               publishersList.add(addNewPublisher());
               bookdriver.createEntity(publishersList);
               break;
            case 3:
               System.out.println("What type of authoring instance would you like to add?");
               System.out.println("1. Writing group \n2. Individual author \n3. Ad Hoc Team");
               int userInput = scan.nextInt();
               switch(userInput) {
                  case 1:
                     writingGroupsList.add(addNewWritingGroup());
                     bookdriver.createEntity(writingGroupsList);
                     break;
                  case 2:
                     //addIndividualAuthor();
                     break;
                  case 3:
                     //addAdHocTeam();
                     break;
               }
               //TODO :: Add new authoring instance where we will give the user the options to which instance to add them to

               displayBookInfo();
               break;
            case 4:
               displayPublisherInfo();
               break;
            case 5:
               displayWritingGroupInfo();
               break;
            case 6:
               deleteBook();
               break;
            case 7:
               updateBook();
               break;
            case 8:
               displayAllPrimaryKeys();
               break;
            case 9:
               break;
         }
      }

      tx.begin();

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

   // =========================================================
   // MENU FUNCTIONS FOR INPUTS BELOW
   // =========================================================

   private static int menu() {
      System.out.println("Welcome to the Books database");
      System.out.println("What would you like to do? (Choose an option)");
      System.out.println("\t1. Add a new book \n\t2. Add a new publisher \n\t3. Add a new Authoring instance");
      System.out.println("\t4. Add an individual author to an existing Ad Hoc Team \n\t5. List information about a book");
      System.out.println("\t6. List information about a publisher \n\t7. List information about a writing group");
      System.out.println("\t8. Delete a book \n\t9. Update a book \n\t10. List all primary keys within the database \n\t11. Quit");
      System.out.print("Your selection: ");
      String choiceString = scan.nextLine();
      int choice;
      // = acquireChoice()
      try{
         choice = Integer.parseInt(choiceString);
      }catch (NumberFormatException e){
         System.out.println("\nERROR: Incorrect input. Please enter a valid integer value for your choice (1-9)");
         return menu();
      }

      if(!isValidChoice(choice)){
         System.out.println("\nERROR: Incorrect input. Please enter a valid integer value for your choice (1-9)");
         return menu();
      }

      return choice;
   }

   // TODO: Implement all these functions
   // TODO: Implement relationship between new book and authoring_entity, and new book and Publisher
   private static Books addNewBook() {
      Books book = new Books();

      System.out.print("Please enter the title of this new book: ");
      String title = scan.nextLine();
      while(!isValidTitle(title)){
         System.out.println("ERROR: Invalid title. Title must be less than 64 characters.");
         System.out.print("Please enter the title of this new book: ");
         title = scan.nextLine();
      }

      book.setTitle(title);

      System.out.print("Please enter the publication year (YYYY): ");
      int year = acquireYear();
      book.setYear_published(year);

      return book;
   }


   /* TODO: Make sure name is not used twice (primary key constraint) and add code to show relationship
      TODO: between a publisher and their book(s). Change error outputs to reflect what the error is */
   private static Publishers addNewPublisher(){
      Publishers publisher = new Publishers();
      System.out.println("Enter a name for the new publisher (Less than 30 Characters):");
      String name = scan.nextLine();

      while(!isValidName(name)){
         System.out.println("ERROR: Invalid input. Try again...");
         System.out.print("Enter a name for the new publisher (Less than 30 Characters): ");
         name = scan.nextLine();
      }

      publisher.setName(name);

      System.out.print("Enter a phone number for the publisher (At least 10 digits):");
      String phone = scan.next();

      // Regular expression to see if string contains integers
      while(!isValidPhone(phone)){
         System.out.println("\nERROR: Invalid input. Try again...");
         System.out.print("Enter a phone number for the publisher (At least 10 digits): ");
         phone = scan.next();
      }

      publisher.setPhone(phone);

      System.out.print("Enter an email for the publisher (At most 64 characters): ");
      String email = scan.next();

      // Regular expression to see if string contains integers
      while(!isValidEmail(email)){
         System.out.println("\nERROR: Invalid input. Try again...");
         System.out.print("Enter an email for the publisher (At most 64 characters): ");
         email = scan.next();
      }
      publisher.setEmail(email);
      return publisher;
   }

   private static Writing_groups addNewWritingGroup(){
      Scanner scan = new Scanner(System.in);
      Writing_groups writing_group = new Writing_groups();
      System.out.println("Please enter an email for the writing group");
      String email = scan.next();
      while(!isValidEmail(email)) {
         System.out.println("That is not a valid email.");
         email = scan.next();
      }
      writing_group.setEmail(email);
      writing_group.setAuthoring_entity_type("Writing Groups");
      System.out.println("Please enter a name:");
      String name = scan.next();
      while(!isValidName(name)) {
         System.out.println("That is not a valid name.");
         name = scan.next();
      }
      writing_group.setName(name);
      System.out.println("Please enter a head writer:");
      String headWriter = scan.next();
      while(!isValidName(headWriter)) {
         System.out.println("That is not a valid name.");
         headWriter = scan.next();
      }
      writing_group.setHead_writer(headWriter);
      System.out.println("Please enter a year formed:");
      int yearFormed = scan.nextInt();
      while(!isValidYear(yearFormed)) {
         System.out.println("That is not a valid year.");
         yearFormed = scan.nextInt();
      }
      writing_group.setYear_formed(yearFormed);

      return writing_group;
   }

   private static void displayBookInfo() {
      System.out.println("Enter the title of the book: ");
      String userInput = scan.next();

      List<Books> books = entityManager.createNativeQuery("SELECT * FROM BOOKS b", Books.class).getResultList();
      for(int i = 0; i < books.size(); i++) {
         Books temp = books.get(i);
         if (temp.getTitle().toLowerCase().equals(userInput.toLowerCase())) {
            System.out.println("Here is all the information about that book:");
            System.out.println(temp.toString() + "\n");
            return;
         }
      }
      System.out.println("Sorry, that title did not match any names in our database...\n");
   }

   private static void displayPublisherInfo() {
      System.out.println("Enter the name of the publisher: ");
      String userInput = scan.next();

      List<Publishers> publishers = entityManager.createNativeQuery("SELECT * FROM PUBLISHERS p", Publishers.class).getResultList();
      for(int i = 0; i < publishers.size(); i++) {
         Publishers temp = publishers.get(i);
         if (temp.getName().toLowerCase().equals(userInput.toLowerCase())) {
            System.out.println("Here is all the information about that publisher:");
            System.out.println(temp.toString() + "\n");
            return;
         }
      }
      System.out.println("Sorry, that name did not match any names in our database...\n");
   }

   private static void displayWritingGroupInfo() {

   }

   private static void deleteBook() {
      System.out.println("Enter the title of the book you would like to delete from the database:");
      String userInput = scan.next();

      List<Books> books = entityManager.createNativeQuery("SELECT * FROM BOOKS b", Books.class).getResultList();
      for(int i = 0; i < books.size(); i++) {
         Books temp = books.get(i);
         if (temp.getTitle().toLowerCase().equals(userInput.toLowerCase())) {
            System.out.println("Deleting " + temp.getTitle() + "...");
            entityManager.remove(temp);
            System.out.println("Book deleted...\n");
            return;
         }
      }
      System.out.println("Sorry, that title did not match any names in our database...\n");
   }

   private static void updateBook() {
      System.out.println("Enter the title of the book you wish to update:");
      String userInput = scan.next();

      List<Books> books = entityManager.createNativeQuery("SELECT * FROM BOOKS b", Books.class).getResultList();
      for(int i = 0; i < books.size(); i++) {
         Books bookToUpdate = books.get(i);
         if (bookToUpdate.getTitle().toLowerCase().equals(userInput.toLowerCase())) {
            System.out.println("What do you wish to update about that book?");
            System.out.println("1.Title \n2.ISBN \n3. Year published");
            System.out.println("4. Authoring entity name \n5. Publisher name");
            int updateBookMenuOption = scan.nextInt(); //TODO :: set up isnextInt() for this
            switch(updateBookMenuOption) {
               case 1:
                  System.out.println("Enter a new title for the book:");
                  String newBookName = scan.next();
                  bookToUpdate.setTitle(newBookName);
                  break;
               case 2:
                  System.out.println("Enter a new ISBN for the book:");
                  String newISBN = scan.next();
                  bookToUpdate.setISBN(newISBN); //TODO :: check for valid ISBN
                  break;
               case 3:
                  System.out.println("Enter a new year published for the book:");
                  int newYearPublished = scan.nextInt();
                  while(!isValidYear(newYearPublished)) {
                     System.out.println("Sorry, that is not a valid year.");
                     newYearPublished = scan.nextInt();
                  }
                  bookToUpdate.setYear_published(newYearPublished);
                  break;
               case 4:
                  System.out.println("enter a new authoring entity name:");
                  String newAuthoringEntityName = scan.next();
                  while(!isValidEmail(newAuthoringEntityName)){
                     System.out.println("Sorry, that is not a valid email.");
                     newAuthoringEntityName = scan.next();
                  }
                  bookToUpdate.setAuthoring_entity_name(newAuthoringEntityName); //TODO :: make sure that setting new AE doesnt conflict with foreign keys
               case 5:
                  System.out.println("Enter a new publisher name:");
                  String newPublisher = scan.next();
                  while(!isValidName(newPublisher)) {
                     System.out.println("Sorry, that is not a valid name.");
                  }
                  bookToUpdate.setPublisher_name(newPublisher); //TODO :: make sure that setting new publisher doesnt conflict with foreign keys
                  break;
            }
            System.out.println("Book updated...\n");
            return;
         }
      }
      System.out.println("Sorry, that title did not match any names in our database...\n");
   }

   private static void displayAllPrimaryKeys() {
      List<Publishers> publishersPK = entityManager.createNativeQuery("SELECT p.name FROM PUBLISHERS p", Publishers.class).getResultList();
      List<Books> booksISBN = entityManager.createNativeQuery("SELECT b.ISBN FROM BOOKS b", Books.class).getResultList();
      List<Books> booksTitle = entityManager.createNativeQuery("SELECT b.title FROM BOOKS b", Books.class).getResultList();

      System.out.println("Publishers primary keys:");
      for(int i = 0; i < publishersPK.size(); i++) {
         System.out.println(publishersPK.get(i).getName());
      }
      System.out.println("Books primary keys:");
      for(int i = 0; i < booksISBN.size(); i++) {
         System.out.println(booksTitle.get(i).getTitle() + " " + booksISBN.get(i).getISBN());
      }

      //TODO :: finish mapping out how the authoring entities work and then we can show their PK and type of AE it is
      System.out.println();
   }

   // =========================================================
   // VALIDATION FUNCTIONS FOR INPUTS BELOW
   // =========================================================

   private static Boolean isValidChoice(int choice){
      if(choice >= 10 || choice <= 0){
         return false;
      }
      return true;
   }

   private static boolean isValidTitle(String title) {
      if(title.length() >= 64 || title.isEmpty()) {
         return false;
      }
      return true;
   }

   private static boolean isValidYear(int year){
      if(String.valueOf(year).length() != 4 || year < 1454 || year > 2021) {
         return false;
      }
      return true;
   }

   private static int acquireYear() {
      String yearString = scan.nextLine();
      int year;

      try{
         year = Integer.parseInt(yearString);
      }catch (NumberFormatException e){
         System.out.println("\nERROR: Invalid year. Year must be of the form YYYY, where the Y represents a digit");
         System.out.print("Please enter the publication year (YYYY): ");
         return acquireYear();
      }

      int length = String.valueOf(year).length();
      if(length != 4){
         System.out.println("\nERROR: Invalid year. Year must be of the form YYYY");
         System.out.print("Please enter the publication year (YYYY): ");
         return acquireYear();
      }

      return year;
   }

   private static boolean isValidName(String name) {
      if(name.length() >= 80 || name.isEmpty()) {
         return false;
      }
      return true;
   }

   private static boolean isValidPhone(String phone) {
      boolean isValid = true;

      int hyphenCount = 0;
      int leftParenthCount = 0;
      int rightParenthCount = 0;

      if (phone != null) {
         if(phone.length() < 10 || phone.length() > 24){
            System.out.println("A phone number includes at least 10 digits or you have entered one that is too long");
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

      if(email.length() > 80){
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

} // End of BookDriver class
