public class Name {
    private String FirstName;
    private String SecondName;



    Name(String FirstName, String SecondName) {

        this.FirstName = FirstName;
        this.SecondName = SecondName;

    }

       public String getSecondName() {
        return SecondName;
    }

    public void setSecondName(String secondName) {
        SecondName = secondName;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }


}
