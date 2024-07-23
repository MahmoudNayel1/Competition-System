import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class CompetitorList {

    ArrayList<AthleticsCompetitor> APlayers ;
    ArrayList<BoxingCompetitor> BPlayers ;

    
    CompetitorList()
    {
        APlayers = new ArrayList<>();
        BPlayers = new ArrayList<>();
    }




    public void readCompetitorsFromFile(String csvFile) 
    {

        String line; // reading single line
        BufferedReader Br = null; // reading line by line from the csv file

        try { // try and catch for any error happening when reading csv file

            // Reading the csv file
            Br = new BufferedReader(new FileReader(csvFile));
            // Reading line by line until getting null (Lines Finished)
            while ((line = Br.readLine()) != null) 
            {
                String[] data = line.split(","); // Splitting each row into column

                if (data[7].equals("Athletics")) // data[7] is the game type index in the file
                {
                    // Reading data of one athletics player then adding it to array of list of
                    // athletics players
                    double[] Scores = new double[6];
                    int count = 0;
                    for (int i = 9; i <= 14; i++) 
                    {
                        Scores[count] = Double.parseDouble(data[i]);
                        count++;
                    }
                    
                    AthleticsCompetitor Acompatitor = new AthleticsCompetitor(data[3], data[6],
                            Integer.parseInt(data[4]), data[1], data[2], data[8], data[5], Scores);
                    Acompatitor.setIDnumber(Integer.parseInt(data[0]));
                    APlayers.add(Acompatitor);
                } 

                else if (data[7].equals("Boxing")) 
                {
                    // Reading data of one Boxing player then adding it to array of list of Boxing
                    // players
                    double[] Scores = new double[5];
                    int count = 0;
                    for (int i = 9; i <= 13; i++) 
                    {
                        Scores[count] = Double.parseDouble(data[i]);
                        count++;
                    }
                    BoxingCompetitor Bcompetitor = new BoxingCompetitor(data[3], data[6], Integer.parseInt(data[4]),
                            data[1], data[2], data[8], data[5], Scores);
                    Bcompetitor.setIDnumber(Integer.parseInt(data[0]));
                    BPlayers.add(Bcompetitor);
                }
            }

            Br.close();
        } 

        catch (IOException e) 
        {
            e.printStackTrace();
        } 

    }





    // this function get Athletics competitor return a string of its full details
    public String PrintAthleiticPlayerDetails(AthleticsCompetitor Acompetitor) 
    {
        AthleticsCompetitorView AView = new AthleticsCompetitorView();
        AthleticsCompetitorController AController = new AthleticsCompetitorController(Acompetitor, AView);
        String Fresults = AController.FullView();
        return Fresults;

    }






    // this function get Boxing competitor and return a string of its full details
    public String PrintBoxingPlayerDetails(BoxingCompetitor Bcompetitor) {
        BoxingCompetitorView BView = new BoxingCompetitorView();
        BoxingCompetitorController BController = new BoxingCompetitorController(Bcompetitor, BView);
        String Fresults = BController.FullView();
        return Fresults;

    }





    // printing the player summary statistics ( total, average, max, min)
    public String PlayerSummaryStatictics(String name, double[] scores) 
    {
        double total = 0;
        for (double score : scores) 
        {
            total += score;
        }

        double average = total / scores.length;
        Arrays.sort(scores);
        double max = scores[scores.length - 1];
        double min = scores[0];

        String result = "Total Score = " + total + "\n" + "Average = " + average + "\n" + "Maximum Score = " + max
                + "\n" + "Minimum Score = " + min;
        return result;

    }




    // Printing player Frequency Statistics scores how many times 0 and 1 .....
    public String PlayerFrequencyStatistics(String name, double[] scores) 
    {

        String result = "";
        int[] ArrayofFrequencies = new int[6];
        for (double score : scores) 
        {
            int index = (int) score;
            ArrayofFrequencies[index]++;
        }
        for (int i = 0; i <= 5; i++) 
        {
            result += "Frequency of Score " + i + " = " + ArrayofFrequencies[i] + "\n";
        }
        return result;

    }





    // printing all players full details and summary statistics and Frequency Statistics
    public String[] FinalReport(ArrayList<AthleticsCompetitor> APlayers, ArrayList<BoxingCompetitor> BPlayers) 
    {
        int count ; // calculating counts of players 
        if(APlayers != null && BPlayers != null) // printing Athletics and boxing players if both are not null 
        {
            count = APlayers.size() + BPlayers.size();
        }
        else if (APlayers == null)  // if one sport type players = null then it will print the other sport players 
        {
            count = BPlayers.size();
        }
        else 
        {
            count = APlayers.size();  
        }
        String[] Results = new String[count]; // each index in results represents one player details 

        CompetitorList competitorList = new CompetitorList();
       

        int counter = 0; // counter of players for filling each player in the for loop

        if(APlayers!=null)  // filling Athletics players full details if not null
        {
            for (AthleticsCompetitor A : APlayers) 
            {

                Results[counter] = A.getFirstName() + "'s Final Report" + "\n";
                String Fresults = competitorList.PrintAthleiticPlayerDetails(A);
                Results[counter] += Fresults + "\n";

                Results[counter] += "Printing " + A.getFirstName() + " Summary Statistics : " + "\n";
                String PlayerSummaryStatictics = competitorList.PlayerSummaryStatictics(A.getFirstName(), A.getScores());
                Results[counter] += PlayerSummaryStatictics + "\n";

                Results[counter] += "Printing " + A.getFirstName() + " Frequency Statistics : " + "\n";
                String PlayerFrequencyStatistics = competitorList.PlayerFrequencyStatistics(A.getFirstName(),
                        A.getScores());
                Results[counter] += PlayerFrequencyStatistics + "\n";
                counter++;

            }
         }

        if(BPlayers != null)  // filling Boxing players full details if not null
        {
            for (BoxingCompetitor B : BPlayers) 
            {

                Results[counter] = B.getFirstName() + "'s Final Report" + "\n";
                String Fresults = competitorList.PrintBoxingPlayerDetails(B);
                Results[counter] += Fresults + "\n";

                Results[counter] += "Printing " + B.getFirstName() + " Summary Statistics : " + "\n";
                String PlayerSummaryStatictics = competitorList.PlayerSummaryStatictics(B.getFirstName(), B.getScores());
                Results[counter] += PlayerSummaryStatictics + "\n";

                Results[counter] += "Printing " + B.getFirstName() + " Frequency Statistics : " + "\n";
                String PlayerFrequencyStatistics = competitorList.PlayerFrequencyStatistics(B.getFirstName(),
                        B.getScores());
                Results[counter] += PlayerFrequencyStatistics + "\n";
                counter++;
            }
        }

        return Results;

    }




    // filling text file with players full details 
    public void PrintFinalReport(ArrayList<AthleticsCompetitor> APlayers, ArrayList<BoxingCompetitor> BPlayers) 
    {
        String AllFileLocation = "FinalReport.txt";
        

        try { 
            BufferedWriter Writer = new BufferedWriter(new FileWriter(AllFileLocation));
            String[] Results = FinalReport(APlayers, BPlayers);
            for (String playerresult : Results) 
            {
                Writer.write(playerresult);
                Writer.newLine();
            }
            Writer.close();
            System.out.println("Data has been written to the file ");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }




    // filling athletics text file with athletics players final report 
    public void PrintAthleticsFinalReport(ArrayList<AthleticsCompetitor> APlayers)
    {
        String AthleticsFinalLocation = "AthleticsFinalReport.txt";
         try {
            BufferedWriter Writer = new BufferedWriter(new FileWriter(AthleticsFinalLocation));
            String[] Results = FinalReport(APlayers, null);
            for (String playerresult : Results) 
            {
                Writer.write(playerresult);
                Writer.newLine();
            }
            Writer.close();
            System.out.println("Athletics Data has been written to the file ");
        } catch (IOException e) {
            e.printStackTrace();
        }


    }



       // filling Boxing text file with athletics players final report 
     public void PrintBoxingFinalReport(ArrayList<BoxingCompetitor> BPlayers)
    {
        String BoxingFinalLocation = "BoxingFinalReport.txt";

         try {
            BufferedWriter Writer = new BufferedWriter(new FileWriter(BoxingFinalLocation));
            String[] Results = FinalReport(null , BPlayers);
            for (String playerresult : Results) 
            {
                Writer.write(playerresult);
                Writer.newLine();
            }
            Writer.close();
            System.out.println("Boxing Data has been written to the file ");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }




    // Searching for Athletic competitor by ID
    public AthleticsCompetitor SearchForAthleticCompetitor(int ID )
    {
        AthleticsCompetitor A = null;
        for(AthleticsCompetitor AC : APlayers )
        {
            if(AC.getIDnumber() == ID )
            {
                A = AC;
            }
        }
        return A;
    }




    // Searching For Boxing competitor by ID
     public BoxingCompetitor SearchForBoxingCompetitor(int ID )
    {
        BoxingCompetitor B = null;
        for(BoxingCompetitor BC : BPlayers )
        {
            System.out.println(BC.getIDnumber());
            if(BC.getIDnumber() == ID )
            {
                B = BC;
            }
        }
        return B;

    }
}

