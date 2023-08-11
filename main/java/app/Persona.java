package app;

public class Persona {
       // persona ID
   private int ID;

   // Name
   private String Name;

   // Desc
   private String Description;

   // Needs
    private String Needs;
   //Skills
    private String Skills;
   //Pain Points
    private String PainPoints;
   //ImgUrl
    private String ImgURL;
   /**
    * Create a Student and set the fields
    */
   public Persona(int ID, String Name, String Description, String Needs, String Skills, String PainPoints, String ImgURL) {
      this.ID = ID;
      this.Name = Name;
      this.Description = Description;
      this.Needs = Needs;
      this.Skills = Skills;
      this.PainPoints = PainPoints;
      this.ImgURL = ImgURL;

   }

   public int getPID() {
      return ID;
   }

   public String getName() {
      return Name;
   }

   public String getDescription() {
      return Description;
   }

   public String getNeeds() {
    return Needs;
   }

   public String getSkills() {
    return Skills;
   }

   public String getPainPoints() {
    return PainPoints;
   }

   public String getImgURL() {
    return ImgURL;
   }
}
