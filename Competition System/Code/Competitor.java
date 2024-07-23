import java.util.*;

// Model Class in Design Pattern MVC
abstract class Competitor extends Name { // abstract Class competitor inherit class name as each competitor has first
                                         // name and second name

    private static int IDcounter = 0; // Class Variable not For specific object
    private int IDnumber;
    private String Email;
    private String Country;
    private int Age;
    private String Level;
    private String Gender;

    // Constructor For initializing object
    Competitor(String Email , String Country, int Age, String F_name, String L_name,
            String Level, String Gender) {

        super(F_name, L_name); // initializing constructor of the super class (Name)
        this.IDnumber = IDcounter; // to set the id of user automatically
        this.Email = Email;
        this.Country = Country;
        this.Age = Age;
        this.Level = Level;
        this.Gender = Gender;
        IDcounter += 1; // For counting The Number of compatetors

    }

    public int getIDnumber() {
        return IDnumber;
    }

    public void setIDnumber(int iDnumber) {
        IDnumber = iDnumber;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }


    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }

    public String getLevel() {
        return Level;
    }

    public void setLevel(String level) {
        Level = level;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public abstract double getOverAllScore(); // abstract method as each sub class inherit from competitor should
                                              // override it.

}

// View Class in Design Pattern MVC
class Competitorview {

    // Returning Full Details of the competitor
    public String getFullDetails(int IDnumber, String Email, String Country, int Age, String F_name, String L_name,
            String Level, String Gender, float OverAllScore) {
        return " Competitor number " + IDnumber + "\n" + "Name : " + F_name + " " + L_name + " is " + Gender +
                "\n" + "Email : " + Email + "\n" + "Country : " + Country + "\n" + F_name + " is a " + Level
                + " Aged " + Age + "\n" + "Has an OverAllScore : " + OverAllScore;
    }

    // Returning short Details of the competitor
    public String getShortDetails(String F_name, String L_name, int IDnumber, double OverAllScore) {
        return "CN " + IDnumber + " (" + F_name.charAt(0) + L_name.charAt(0) + ")" + " has overall score "
                + OverAllScore;
    }
}

// Competitor Controller Class in Design Pattern MVC
class CompetitorController {
    private Competitor Model;
    private Competitorview View;

    public CompetitorController() {

    }

    public CompetitorController(Competitor Model, Competitorview View) {
        this.Model = Model;
        this.View = View;
    }

    public int getIDnumber() {
        return Model.getIDnumber();
    }

    public String getEmail() {
        return Model.getEmail();
    }

    public void setEmail(String email) {
        Model.setEmail(email);
    }


    public String getCountry() {
        return Model.getCountry();
    }

    public void setCountry(String country) {
        Model.setCountry(country);
    }

    public int getAge() {
        return Model.getAge();
    }

    public void setAge(int age) {
        Model.setAge(age);
    }

    public String getLevel() {
        return Model.getLevel();
    }

    public void setLevel(String level) {
        Model.setLevel(level);
    }

    public String getGender() {
        return Model.getGender();
    }

    public void setGender(String gender) {
        Model.setGender(gender);
    }

    public String getFirstName() {
        return Model.getFirstName();
    }

    public void setFirstName(String FirstName) {
        Model.setFirstName(FirstName);
    }

    public String getSecondName() {
        return Model.getSecondName();
    }

    public void setSecondName(String SecondName) {
        Model.setSecondName(SecondName);
    }

    public double getOverAllScore() {
        return Model.getOverAllScore();
    }

    // Printing Full details of competitor
    public String FullView() {
        return View.getFullDetails(getIDnumber(), getEmail(), getCountry(), getAge(), getFirstName(),
                getSecondName(), getLevel(), getGender(), 0);

    }

    // Printing short details of competitor
    public String ShortView() {
        return View.getShortDetails(getFirstName(), getSecondName(), getIDnumber(), getOverAllScore());
    }

}
