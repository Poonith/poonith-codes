package app;

public class Student {

   // Student ID
   private String ID;

   // first name Name
   private String firstname;

   // last name
   private String lastname;

   /**
    * Create a Student and set the fields
    */
   public Student(String ID, String firstname, String lastname) {
      this.ID = ID;
      this.firstname = firstname;
      this.lastname = lastname;
   }

   public String getID() {
      return ID;
   }

   public String getFirstName() {
      return firstname;
   }

   public String getLastName() {
      return lastname;
   }
}
