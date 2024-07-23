import java.util.Arrays;

// Model Class in MVC Design Pattern
public class BoxingCompetitor extends Competitor 
{
    private double[] Scores = new double[5];
    private String Gametype;

    BoxingCompetitor(String Email, String Country, int Age, String F_name, String L_name,
        String Level, String Gender, double[] Scores) 
    {
        // Calling the Constructor of the Superclass (Competitor)
        super(Email, Country, Age, F_name, L_name, Level, Gender);
        this.Scores = Scores;
        this.Gametype = "Boxing";
    }


    // to get all scores as String
    public String getScoreArray() 
    {
        String AllScore = "";
        for (double Score : Scores) 
        {
            AllScore += Score + " , ";
        }
        return AllScore;

    }

    public double[] getScores() 
    {
        return Scores;
    }

    public void setScores(double[] scores) 
    {
        Scores = scores;
    }

    public String getGametype() 
    {
        return Gametype;
    }





    // Abstract Function in competitor class (Super Class)
    @Override
    public double getOverAllScore() 
    {
        Arrays.sort(Scores);
        double average = (Scores[1] + Scores[2] + Scores[3]) / 3;
        return average;
    }

}





// View Class In MVC Design Pattern
class BoxingCompetitorView extends Competitorview {

    // Returning Full Details of the competitor
    public String getFullDetails(int IDnumber, String Email, String Country, int Age, String F_name, String L_name,
            String Level, String Gender, String ScoreArray, double OverAllScore, String Gametype) {
        return "Competitor number : " + IDnumber + " , " + "Name : " + F_name + " " + L_name + "\n" 
                + F_name + " " + L_name + " is a " + Gender
                + " and his Game Type : " + Gametype +
                "\n" + "Email : " + Email + "\n" + "Country : " + Country + "\n" + F_name + " is a " + Level
                + " Aged " + Age + " and received scores : " + "[ " + ScoreArray + "]" + " \n"
                + "Has an OverAllScore : "
                + OverAllScore;
    }
}





// Controller Class in MVC Design
class BoxingCompetitorController extends CompetitorController 
{
    BoxingCompetitor Model;
    BoxingCompetitorView View;

    public BoxingCompetitorController(BoxingCompetitor Model, BoxingCompetitorView View) 
    {
        super(Model, View);
        this.Model = Model;
        this.View = View;
    }



    public double[] getScores() 
    {
        return Model.getScores();
    }
    public void setScores(double[] scores) 
    {
        Model.setScores(scores);
    }


    public String getGametype() 
    {
        return Model.getGametype();
    }


    public String getScoreArray() 
    {
        String AllScore = "";
        for (double Score : Model.getScores()) 
        {

            AllScore += Score + " , ";
        }
        return AllScore;
    }

    
    public double getOverAllScore() 
    {
        return Model.getOverAllScore();
    }



    // Full DetailsView Overriden from competitor class
    public String FullView() 
    {
        return View.getFullDetails(getIDnumber(), getEmail(), getCountry(), getAge(), getFirstName(),
                getSecondName(), getLevel(), getGender(), getScoreArray(), getOverAllScore(), getGametype());

    }

    // Short details inherited from competitor class controller 

}
