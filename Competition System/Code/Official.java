import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Official extends Staff
{

    public Official(String FirstName , String SecondName , String Email , String Password)
    {
        super(FirstName ,  SecondName , Email , Password);
        
    }



    public void AddPlayer(String Email,String Country, int Age, String F_name, String L_name,
    String Level, String Gender , String Gametype)
    {
        String csvfile = "";
        int ID = 0;
        double[] Scores = {};
        if (Gametype == "Boxing") 
        {
            Scores = new double[5];
            BoxingCompetitor B = new BoxingCompetitor(Email, Country, Age, F_name, L_name, Level, Gender, Scores);   
            ID = B.getIDnumber();
            csvfile = "Boxing.csv";
        }
        else if(Gametype == "Athletics")
        {
            Scores = new double[6];
            AthleticsCompetitor A = new AthleticsCompetitor(Email, Country, Age, F_name, L_name, Level, Gender, Scores);
            ID = A.getIDnumber();
            csvfile = "Athletics.csv";
        }

        if(Gametype == "Boxing" || Gametype == "Athletics")
        {

        try (PrintWriter writer = new PrintWriter(new FileWriter(csvfile , true))) 
        {
            

            String updatedLine = ID + "," + F_name + "," + L_name + "," + Email + "," 
            + Age + "," + Gender + "," + Country + "," + Gametype + "," + Level ;

            for(double S : Scores )
            {
                updatedLine += "," + Double.toString(S); 
            }

            
            writer.println(updatedLine);
            writer.close();
            System.out.println("CSV file updated successfully!");
        }
            catch (IOException e) 
            {
            e.printStackTrace();
            }
        
        }
        else
        {
            System.out.println("Error, The GameType isnt avaliable !");
        }
   
    }




    public String RemovePlayer(int ID )
    {
        try {
        List<String> lines = new ArrayList<>();
        String csvfile = "";
        AthleticsCompetitor A = getCompetitorList().SearchForAthleticCompetitor(ID );
        BoxingCompetitor B = getCompetitorList().SearchForBoxingCompetitor(ID);
        if(A != null)
        {
            csvfile = "Athletics.csv";
        }
        else if(B != null)
        {
            csvfile = "Boxing.csv";
        } 
        else
        {
            return "This Player Not Found , Invalid ID";
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(csvfile))) 
        {
           
            String line;
            while ((line = reader.readLine()) != null) 
            {
                String[] values = line.split(",");
                if (Integer.parseInt(values[0]) == ID) 
                {
                    continue;    
                }
                lines.add(String.join(",", values));
            }

            reader.close();

        }

        try (PrintWriter writer = new PrintWriter(new FileWriter(csvfile))) 
        {
            for (String updatedLine : lines) 
            {
                writer.println(updatedLine);
            }
            writer.close();
        }
        
    } 
    catch (IOException e) 
    {
        e.printStackTrace();
    }
    return "Player with ID : " + ID + "Has been deleted";
    }




    public String Amendplayer(int ID , String Email, String Country, int Age, String F_name, String L_name,
        String Level, String Gender , String GameType )
    {
        try {
        List<String> lines = new ArrayList<>();
        String csvfile = "";
        AthleticsCompetitor A = getCompetitorList().SearchForAthleticCompetitor(ID );
        BoxingCompetitor B = getCompetitorList().SearchForBoxingCompetitor(ID );
        if(A != null)
        {
            csvfile = "Athletics.csv";
        }
        else if(B != null)
        {
            csvfile = "Boxing.csv";
        } 
        else
        {
            return "This Player Not Found , Invalid ID";
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(csvfile))) 
        {
           
            String line;
            while ((line = reader.readLine()) != null) 
            {
                String[] values = line.split(",");
                if (Integer.parseInt(values[0]) == ID) 
                {
                    values[1] = F_name;
                    values[2] = L_name;
                    values[3] = Email;
                    values[4] = Integer.toString(Age);
                    values[5] = Gender;
                    values[6] = Country;
                    values[7] = GameType;
                    values[8] = Level;

                }
                lines.add(String.join(",", values));
            }

            reader.close();

        }

        try (PrintWriter writer = new PrintWriter(new FileWriter(csvfile))) 
        {
            for (String updatedLine : lines) 
            {
                writer.println(updatedLine);
            }
            writer.close();
        }
        
    } 
    catch (IOException e) 
    {
        e.printStackTrace();
    }
    return "Player with ID : " + ID + " Has been Edited";
}
    
}
    

