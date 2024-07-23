import java.util.Arrays;
public class AthleticsCompetitor extends Competitor{
    

    private double[] Scores = new double[6];
    private String Gametype;

    AthleticsCompetitor(String Email,String Country, int Age, String F_name, String L_name,
            String Level, String Gender, double[] Scores) 
    {
        // Calling the Constructor of the Superclass (Competitor)
        super(Email,Country, Age, F_name, L_name, Level, Gender);
        this.Scores = Scores;
        this.Gametype = "Athletics";
    }


    // to get all scores as String
    public String getScoreArray() 
    {
        String AllScore = "";
        for (double Score : Scores) {
            AllScore += Score + " ";
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

    @Override
    public double getOverAllScore() 
    {
        Arrays.sort(Scores);
        double average = (Scores[1] + Scores[2] + Scores[3] + Scores[4]) / 4;
        return average;
    }
}





// View Class In MVC Design Pattern
class AthleticsCompetitorView extends Competitorview 
{
    // Returning Full Details of the competitor
    public String getFullDetails(int IDnumber, String Email, String Country, int Age, String F_name, String L_name,
            String Level, String Gender, String ScoreArray, double OverAllScore, String Gametype) 
    {
        return "Competitor number " + IDnumber + " , " + "Name : " + F_name + " " + L_name + "\n"
                + F_name + " " + L_name + " is a " + Gender + " and his Game Type : " + Gametype +"\n" 
                + "Email : " + Email + "\n" + "Country : " + Country + "\n" + F_name + " is a " + Level
                + " Aged " + Age + " and received scores : " + "[ " + ScoreArray +"]" + " \n" + "Has an OverAllScore : "
                + OverAllScore;
    }
}






// Controller Class in MVC Design
class AthleticsCompetitorController extends CompetitorController 
{
    AthleticsCompetitor Model;
    AthleticsCompetitorView View;

    public AthleticsCompetitorController(AthleticsCompetitor Model, AthleticsCompetitorView View) 
    {
        super(Model,View);
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
            AllScore += Score + " ";
        }
        return AllScore;

    }


    public double getOverAllScore()
    {
        return Model.getOverAllScore();
    }

    

    // Full DetailsView
     public String FullView() 
    {
        return View.getFullDetails(getIDnumber(), getEmail(), getCountry(), getAge(), getFirstName(),
                getSecondName(), getLevel(), getGender(), getScoreArray(),getOverAllScore(), getGametype());

    }
}
