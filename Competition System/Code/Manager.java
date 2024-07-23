import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Manager {

    ArrayList<Official> officials;
    ArrayList<Staff> staff;
    Manager()
    {
        officials = new ArrayList<>();
        staff = new ArrayList<>();
    }



    public void ReadManagersFromFile(String csvfile)
    {
        String line; // reading single line
        BufferedReader Br = null; // reading line by line from the csv file

        try { // try and catch for any error happening when reading csv file

            // Reading the csv file
            Br = new BufferedReader(new FileReader(csvfile));
            // Reading line by line until getting null (Lines Finished)
            while ((line = Br.readLine()) != null) 
            {

                String[] data = line.split(","); // Splitting each row into column

                if (data[5].equals("Staff")) // data[5] is the Manager type index in the file
                {
                    // Reading data of one Staff then adding it to array of list of Staff
                    Staff S = new Staff(data[1], data[2], data[3], data[4]);
                    S.setID(S.getID());
                    staff.add(S);
                } else if (data[5].equals("Official")) 
                {
                    // Reading data of one Official then adding it to array of list of Officials
                    Official O = new Official(data[1], data[2], data[3], data[4]);
                    O.setID(O.getID());
                    officials.add(O);
                }

               
            }
            Br.close();
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        } 
    }


    
}
