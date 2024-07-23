import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;

public class Staff {
    private String FirstName;
    private String SecondName;
    private String Email;
    private String Password;
    private int ID;

    public void setID(int iD) {
        ID = iD;
    }

    private static int IDcounter = 0;
    private CompetitorList competitorlist;

    private String AcsvFile = "Athletics.csv";
    private String BcsvFile = "Boxing.csv";

    // Constructor
    public Staff(String FirstName, String SecondName, String Email, String Password) {

        this.ID = IDcounter;
        this.FirstName = FirstName;
        this.SecondName = SecondName;
        this.Email = Email;
        this.Password = Password;
        IDcounter++;
        competitorlist = new CompetitorList();
        competitorlist.readCompetitorsFromFile(AcsvFile);
        competitorlist.readCompetitorsFromFile(BcsvFile);

    }

    // Getter for competitor list
    public CompetitorList getCompetitorList() {
        return this.competitorlist;
    }

    public String getFirstName() {
        return FirstName;
    }

    public String getSecondName() {
        return SecondName;
    }

    public String getEmail() {
        return Email;
    }

    public String getPassword() {
        return Password;
    }

    public int getID() {
        return ID;
    }

    // Searching for competitor by id to get its full details and to print report if
    // boolean report is equal true
    public String SearchCompetitorFullDetails(int ID, Boolean Report) {
        // Searching for Athletics competitors by id if the id is not for Athletic
        // competitor the Athletic competitor will be null
        AthleticsCompetitor AC = competitorlist.SearchForAthleticCompetitor(ID);
        if (AC != null) {
            AthleticsCompetitorView View = new AthleticsCompetitorView();
            AthleticsCompetitorController ACTRL = new AthleticsCompetitorController(AC, View);
            if (Report == true) {
                PrintReport(AC, null);
            }
            return ACTRL.FullView();
        }

        // Searching for Boxing competitors by id if the id is not for Boxing competitor
        // the Boxing competitor will be null
        BoxingCompetitor BC = competitorlist.SearchForBoxingCompetitor(ID);
        if (BC != null) {
            BoxingCompetitorView View = new BoxingCompetitorView();
            BoxingCompetitorController BCTRL = new BoxingCompetitorController(BC, View);
            if (Report == true) {
                PrintReport(null, BC);
            }
            return BCTRL.FullView();
        }

        // if the code reaches this return this means that the id is not for either
        // Athletics of Boxing competitor
        return "This Competitor Is Not Found !";
    }

    // Searching for competitor by id to get its Short details and to print report
    // if boolean report is equal true
    public String SearchCompetitorShortDetails(int ID, Boolean Report) {

        // Searching for Athletics competitors by id if the id is not for Athletic
        // competitor the Athletic competitor will be null
        AthleticsCompetitor AC = competitorlist.SearchForAthleticCompetitor(ID);
        if (AC != null) {
            AthleticsCompetitorView View = new AthleticsCompetitorView();
            AthleticsCompetitorController ACTRL = new AthleticsCompetitorController(AC, View);
            if (Report == true) {
                PrintReport(AC, null);
            }
            return ACTRL.ShortView();
        }

        // Searching for Boxing competitors by id if the id is not for Boxing competitor
        // the Boxing competitor will be null
        BoxingCompetitor BC = competitorlist.SearchForBoxingCompetitor(ID);
        if (BC != null) {
            BoxingCompetitorView View = new BoxingCompetitorView();
            BoxingCompetitorController BCTRL = new BoxingCompetitorController(BC, View);
            if (Report == true) {
                PrintReport(null, BC);
            }
            return BCTRL.ShortView();
        }

        // if the code reaches this return this means that the id is not for either
        // Athletics of Boxing competitor
        return "This Competitor Is Not Found !";
    }

    // print report for a specific competitor if boolean report from the previous
    // functions is true
    public void PrintReport(AthleticsCompetitor AC, BoxingCompetitor BC) {
        String[] Results;
        String File = "Competitor.txt";

        // if athletic competitor is not null this means that the report that will be
        // printed is for athletic competitor
        if (AC != null) {
            ArrayList<AthleticsCompetitor> AAC = new ArrayList<>();
            AAC.add(AC);
            Results = competitorlist.FinalReport(AAC, null);
        } else // the report that will be printed is for boxing competitor
        {
            ArrayList<BoxingCompetitor> ABC = new ArrayList<>();
            ABC.add(BC);
            Results = competitorlist.FinalReport(null, ABC);
        }

        try {
            // writing the data of the competitor in the text file
            BufferedWriter Writer = new BufferedWriter(new FileWriter(File));
            for (String playerresult : Results) {
                Writer.write(playerresult);
                Writer.newLine();
            }
            Writer.close();
            System.out.println("Data of competitor has been written to the file ");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // for editing or filling the scores of the player with the given id
    public void FillScores(int ID, String GameType, double[] Score) {
        String csvFile = "";
        boolean found = false;
        if (GameType == "Athletics") {
            AthleticsCompetitor AC;
            AC = competitorlist.SearchForAthleticCompetitor(ID);
            csvFile = "Athletics.csv";
            EditCsvFile(csvFile, Score, AC.getIDnumber());
            found = true;
        } else if (GameType == "Boxing") {
            BoxingCompetitor BC;
            BC = competitorlist.SearchForBoxingCompetitor(ID);
            csvFile = "Boxing.csv";
            EditCsvFile(csvFile, Score, BC.getIDnumber());
            found = true;
        }

        if (found == false) {
            System.out.println("The player is not found either the ID or GameType is invalid");
        }
    }

    // for editing or filling the scores of the players with the given id in the csv
    // file
    public void EditCsvFile(String csvfile, double[] Scores, int ID) {
        try {
            /*
             * Getting the lines in the csv file and put it in array list of strings after
             * editing or
             * adding the scores of the player with the given id
             */
            List<String> lines = new ArrayList<>();
            try (BufferedReader reader = new BufferedReader(new FileReader(csvfile))) {

                String line;
                while ((line = reader.readLine()) != null) {
                    String[] values = line.split(",");
                    if (Integer.parseInt(values[0]) == ID) {

                        int count = 9;
                        for (double score : Scores) {
                            values[count] = Double.toString(score);
                            count++;

                        }
                    }
                    lines.add(String.join(",", values));
                }

                reader.close();
            }
            // filling the csv file with the lines saved in the array list
            try (PrintWriter writer = new PrintWriter(new FileWriter(csvfile))) {
                for (String updatedLine : lines) {
                    writer.println(updatedLine);
                }
                writer.close();
            }

            System.out.println("CSV file updated successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
