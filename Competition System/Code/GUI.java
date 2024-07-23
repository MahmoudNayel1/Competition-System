import javax.swing.border.Border;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.*;

public class GUI {


    //Constructor (Main Window) have 2 buttons Manger and user
    public GUI() {
        JFrame frame1 = new JFrame("Window 1");
        JButton MButton = new JButton("Manager");
        JButton UButton = new JButton("User");
        JLabel FirstLable = new JLabel("Welcome to the Competition System GUI");
        JPanel panel = new JPanel();

        panel.setBorder(BorderFactory.createEmptyBorder(150, 150, 150, 150));
        panel.setLayout(new GridLayout(0, 1));
        panel.add(FirstLable);
        panel.add(MButton);
        panel.add(UButton);

        MButton.addActionListener(new ActionListener() 
        {

            @Override
            public void actionPerformed(ActionEvent e) 
            {
                frame1.dispose();
                ManagerLogin();
            }

        });

        UButton.addActionListener(new ActionListener() 
        {

            @Override
            public void actionPerformed(ActionEvent e) 
            {

                frame1.dispose();
                UserSearchWindow();
            }

        });

        frame1.add(panel, BorderLayout.CENTER);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.setTitle("Competetion GUI");
        frame1.pack();
        frame1.setVisible(true);
    }



   // callable function (Main Window) have 2 buttons Manger and user
    private static void MainWindow()
    {
        JFrame frame0 = new JFrame("Main Window");
        JButton MButton = new JButton("Manager");
        JButton UButton = new JButton("User");
        JLabel FirstLable = new JLabel("Welcome to the Competition System GUI");
        JPanel panel = new JPanel();

        panel.setBorder(BorderFactory.createEmptyBorder(150, 150, 150, 150));
        panel.setLayout(new GridLayout(0, 1));
        panel.add(FirstLable);
        panel.add(MButton);
        panel.add(UButton);

        MButton.addActionListener(new ActionListener() 
        {

            @Override
            public void actionPerformed(ActionEvent e) 
            {
                frame0.dispose();
                ManagerLogin();
            }

        });

        UButton.addActionListener(new ActionListener() 
        {

            @Override
            public void actionPerformed(ActionEvent e) 
            {

                frame0.dispose();
                OfficialSearch(null);
            }

        });

        frame0.add(panel, BorderLayout.CENTER);
        frame0.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame0.setTitle("Competetion GUI");
        frame0.pack();
        frame0.setVisible(true);
    }




    // Have username and passwrord to login as official or staff upon the usernames and passwords saved in manger csv file 
    private static void ManagerLogin() 
    {
        JFrame frame2 = new JFrame("Manager Login");
        JPanel panel2 = new JPanel();

        frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel usernameLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");
        JButton Login = new JButton("Login");
        JTextField EmailField = new JTextField(20);
        EmailField.setToolTipText("Enter Email");

        JPasswordField PasswordField = new JPasswordField(20);
        PasswordField.setToolTipText("Enter Password");

        Login.setPreferredSize(new Dimension(150, 20));

        panel2.setLayout(new GridLayout(3, 2));
        panel2.add(usernameLabel);
        panel2.add(EmailField);
        panel2.add(passwordLabel);
        panel2.add(PasswordField);
        panel2.add(new JLabel()); // Empty label for spacing
        panel2.add(Login);


        Login.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                String username = EmailField.getText();
                char[] passwordChars = PasswordField.getPassword();
                String password = new String(passwordChars);

                Manager M = new Manager();
                M.ReadManagersFromFile("Managers.csv");
                boolean successfully = false;

                for(Official O : M.officials)
                {
                    // Perform login validation (you can replace this with your actual validation
                    // logic)
                    if (O.getEmail().equals(username) && O.getPassword().equals(password)) 
                    {
                        Official Off = new Official(O.getFirstName(), O.getSecondName(), O.getEmail() , O.getPassword());
                        JOptionPane.showMessageDialog(null, "Login Successful as an Official!");
                        frame2.dispose();
                        successfully = true;
                        OfficialMainWindow(Off);
                    }
                }

                for(Staff S : M.staff)
                {
                    if (S.getEmail().equals(username) && S.getPassword().equals(password)) 
                    {

                        Staff Stf = new Staff(S.getFirstName(), S.getSecondName() , S.getEmail() ,  S.getPassword());
                        JOptionPane.showMessageDialog(null, "Login Successful as an Staff!");
                        frame2.dispose();
                        successfully = true;
                        StaffMainWindow(Stf);
                    }
                }
                if(successfully == false)
                {
                    JOptionPane.showMessageDialog(null, "Invalid username or password", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }

                // Clear the password field after login attempt
                PasswordField.setText("");
            }
        });

        frame2.add(panel2, BorderLayout.CENTER);
        frame2.setSize(300, 150);
        frame2.setLocationRelativeTo(null);
        frame2.setVisible(true);
    }





    /*Main window for officials after login it have 4 buttons 
      (manage scores) for filling scores 
      (manage players) for adding and removing and edeting player details 
      (Search for player) for searching on specific player by id 
      (Print Reports) for printing the reports of athletics players or boxing players or both of them 
     */ 
    private static void OfficialMainWindow(Official O) 
    {
        
        JFrame frame3 = new JFrame("Official Main Window");
        JPanel panel3 = new JPanel(new GridLayout(0, 1));
        frame3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        JButton Manage_Scores = new JButton("Manage Scores");
        JButton Manage_Players = new JButton("Manage_Players");
        JButton Search = new JButton("Search For Player");
        JButton Reports = new JButton("Print Reports");
        panel3.add(Manage_Players);
        panel3.add(Manage_Scores);
        panel3.add(Search);
        panel3.add(Reports);

         Manage_Players.addActionListener(new ActionListener() 
         {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame3.dispose();
                OfficialManageWindow(O);
            }
        });

        Reports.addActionListener(new ActionListener() 
         {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame3.dispose();
                PlayerReportWindow(O,null);
            }
        });


        Manage_Scores.addActionListener(new ActionListener() 
         {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame3.dispose();
                ChooseGameType(O , null);
            }
        });

        Search.addActionListener(new ActionListener() 
         {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame3.dispose();
                OfficialSearch(O);
            }
        });


        frame3.add(panel3, BorderLayout.CENTER);
        frame3.setSize(300, 150);
        frame3.setLocationRelativeTo(null);
        frame3.setVisible(true);



    }





   // (manage players) for adding and removing and edeting player details 
    private static void OfficialManageWindow(Official O) 
    {
        JFrame frame4 = new JFrame("Official  Manage Player Window");
        JPanel panel4 = new JPanel(new GridLayout(11, 2));
        frame4.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        JLabel Label_ID = new JLabel("ID");
        JLabel Label_F_name = new JLabel("F_name");
        JLabel Label_L_name = new JLabel("L_name");
        JLabel Label_Email = new JLabel("Email");
        JLabel Label_Age = new JLabel("Age");
        JLabel Label_Country = new JLabel("Country");
        JLabel Label_Level = new JLabel("Level");
        JLabel Label_Gender = new JLabel("Gender");
        JLabel Label_Gametype = new JLabel("Game Type");


        JTextField ID_Field = new JTextField(20);
        JTextField F_name_Field = new JTextField(20);
        JTextField L_name_Field = new JTextField(20);
        JTextField Email_Field = new JTextField(20);
        JTextField Age_Field = new JTextField(20);
        JTextField Country_Field = new JTextField(20);
        JTextField Level_Field = new JTextField(20);
        JTextField Gender_Field = new JTextField(20);

        String[] games = {"Athletics" , "Boxing"};
        JComboBox GameType = new JComboBox<>(games);

        
        JButton Add_Player = new JButton("Add Player");
        JButton Remove_Player = new JButton("Remove Player");
        JButton Amend_Player = new JButton("Amend Player");
      

        panel4.add(Label_ID);
        panel4.add(ID_Field);

        panel4.add(Label_F_name);
        panel4.add(F_name_Field);

        panel4.add(Label_L_name);
        panel4.add(L_name_Field);

        panel4.add(Label_Email);
        panel4.add(Email_Field);

        panel4.add(Label_Age);
        panel4.add(Age_Field);
        
        panel4.add(Label_Country);
        panel4.add(Country_Field);

        panel4.add(Label_Level);
        panel4.add(Level_Field);

        panel4.add(Label_Gender);
        panel4.add(Gender_Field);

        panel4.add(Label_Gametype);
        panel4.add(GameType);


        panel4.add(Add_Player);
        panel4.add(Remove_Player);
        panel4.add(Amend_Player);

         Add_Player.addActionListener(new ActionListener() 
         {
            @Override
            public void actionPerformed(ActionEvent e) {
                O.AddPlayer(Email_Field.getText(), Country_Field.getText(), Integer.parseInt(Age_Field.getText()), F_name_Field.getText(), L_name_Field.getText(), Level_Field.getText(), Gender_Field.getText(), GameType.getSelectedItem().toString());
                frame4.dispose();
                OfficialMainWindow(O);
            }
        });


        Remove_Player.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                O.RemovePlayer(Integer.parseInt(ID_Field.getText()));
                frame4.dispose();
                OfficialMainWindow(O);

            }
        });


        Amend_Player.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                O.Amendplayer( Integer.parseInt(ID_Field.getText()),Email_Field.getText(), Country_Field.getText(), Integer.parseInt(Age_Field.getText()), F_name_Field.getText(), L_name_Field.getText(), Level_Field.getText(), Gender_Field.getText(), GameType.getSelectedItem().toString());
                frame4.dispose();
                OfficialMainWindow(O);

            }
        });






        frame4.add(panel4, BorderLayout.CENTER);
        frame4.setSize(800, 400);
        frame4.setLocationRelativeTo(null);
        frame4.setVisible(true);
    }





    // have 2 buttons either athletic player or boxing player to search on 
    public static void ChooseGameType(Official O , Staff S)
    {
        JFrame frame5 = new JFrame("Player Gametype choice");
        JPanel panel5 = new JPanel(new GridLayout(0,1));
        JButton Athletics_Player = new JButton("Athletics Player");
        JButton Boxing_Player = new JButton("Boxing Player");
        panel5.add(Boxing_Player);
        panel5.add(Athletics_Player);

         Athletics_Player.addActionListener(new ActionListener() 
         {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame5.dispose();
                AthleticsCompetitorSearch(O , S);
            }
        });

        Boxing_Player.addActionListener(new ActionListener() 
         {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame5.dispose();
                BoxingCompetitorSearch(O , S);
            }
        });


        frame5.add(panel5 , BorderLayout.CENTER);
        frame5.setSize(300 , 150);
        frame5.setLocationRelativeTo(null);
        frame5.setVisible(true);


    }





    // adding or editing scores of athletics players
    public static void AthleticsCompetitorSearch(Official O , Staff  S){
        JFrame frame6 = new JFrame("Athletic competitor search");
        JPanel panel6 = new JPanel(new GridLayout(8 ,2));
        
        JLabel ID = new JLabel("ID");
        JLabel score_1 = new JLabel("Score 1");
        JLabel score_2 = new JLabel("Score 2");
        JLabel score_3 = new JLabel("Score 3");
        JLabel score_4 = new JLabel("Score 4");
        JLabel score_5 = new JLabel("Score 5");
        JLabel score_6 = new JLabel("Score 6");

        String[] IDs ;
        if(O != null)
        {
            IDs = new String[O.getCompetitorList().APlayers.size()];
            int count = 0;
            for(AthleticsCompetitor A : O.getCompetitorList().APlayers) 
            {

                IDs[count] = Integer.toString(A.getIDnumber());
                count++;
            }
        }
        else 
        {
            IDs = new String[S.getCompetitorList().APlayers.size()];
            int count = 0;
            for(AthleticsCompetitor A : S.getCompetitorList().APlayers) 
            {

                IDs[count] = Integer.toString(A.getIDnumber());
                count++;
            }
        }
        
        
        
        JComboBox ID_Field = new JComboBox<>(IDs);
        JTextField Fieldscore_1 = new JTextField(1);
        JTextField Fieldscore_2 = new JTextField(1);
        JTextField Fieldscore_3 = new JTextField(1);
        JTextField Fieldscore_4 = new JTextField(1);
        JTextField Fieldscore_5 = new JTextField(1);
        JTextField Fieldscore_6 = new JTextField(1);
        JButton submit = new JButton("Submit");
        
        

        panel6.add(ID);
        panel6.add(ID_Field);

        panel6.add(score_1);
        panel6.add(Fieldscore_1);

        panel6.add(score_2);
        panel6.add(Fieldscore_2);

        panel6.add(score_3);
        panel6.add(Fieldscore_3);

        panel6.add(score_4);
        panel6.add(Fieldscore_4);
        
        panel6.add(score_5);
        panel6.add(Fieldscore_5);

        panel6.add(score_6);
        panel6.add(Fieldscore_6);

        panel6.add(new JLabel());
        panel6.add(submit);

         submit.addActionListener(new ActionListener() 
         {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                if( Double.parseDouble(Fieldscore_1.getText()) < 6 && Double.parseDouble(Fieldscore_2.getText()) < 6 && Double.parseDouble(Fieldscore_3.getText()) < 6 && Double.parseDouble(Fieldscore_4.getText()) < 6 && Double.parseDouble(Fieldscore_5.getText()) < 6 
                   && Double.parseDouble(Fieldscore_6.getText()) < 6 && Double.parseDouble(Fieldscore_1.getText()) >= 0 && Double.parseDouble(Fieldscore_2.getText())>= 0 && Double.parseDouble(Fieldscore_3.getText())>= 0 && Double.parseDouble(Fieldscore_4.getText()) >= 0 && Double.parseDouble(Fieldscore_5.getText()) >= 0  && Double.parseDouble(Fieldscore_6.getText()) >= 0)
                    {
                         double [] Score = {Double.parseDouble(Fieldscore_1.getText()) , Double.parseDouble(Fieldscore_2.getText())  , Double.parseDouble(Fieldscore_3.getText()) , Double.parseDouble(Fieldscore_4.getText()) , Double.parseDouble(Fieldscore_5.getText()) , Double.parseDouble(Fieldscore_6.getText())};
                        if( O != null)
                        {
                            O.FillScores(Integer.parseInt(ID_Field.getSelectedItem().toString()), "Athletics", Score);
                            frame6.dispose();
                            OfficialMainWindow(O);
                        }
                        else 
                        {
                            S.FillScores(Integer.parseInt(ID_Field.getSelectedItem().toString()), "Athletics", Score);
                            frame6.dispose();
                            StaffMainWindow(S);
                        }   
                        
                    }

                    else
                    {
                        JOptionPane.showMessageDialog(null, "Invalid Scores", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    }
                    
                
            }
        });


        frame6.add(panel6 , BorderLayout.CENTER);
        frame6.setSize(800 , 400);
        frame6.setLocationRelativeTo(null);
        frame6.setVisible(true);



        
    }





     // adding or editing scores of Boxing players
     public static void BoxingCompetitorSearch(Official O , Staff S)
     {
        JFrame frame7 = new JFrame("Boxing Competitor Scores");
        JPanel panel7 = new JPanel(new GridLayout(7 ,2));

        
        JLabel ID = new JLabel("ID");
        JLabel score_1 = new JLabel("Score 1");
        JLabel score_2 = new JLabel("Score 2");
        JLabel score_3 = new JLabel("Score 3");
        JLabel score_4 = new JLabel("Score 4");
        JLabel score_5 = new JLabel("Score 5");

        
        String[] IDs ;
        if(O != null)
        {
            IDs = new String[O.getCompetitorList().BPlayers.size()];
            int count = 0;
            for(BoxingCompetitor B : O.getCompetitorList().BPlayers) 
            {

                IDs[count] = Integer.toString(B.getIDnumber());
                count++;
            }
        }
        else 
        {
            IDs = new String[S.getCompetitorList().BPlayers.size()];
            int count = 0;
            for(BoxingCompetitor B : S.getCompetitorList().BPlayers) 
            {

                IDs[count] = Integer.toString(B.getIDnumber());
                count++;
            }
        }

        
        JComboBox ID_Field = new JComboBox<>(IDs);
        JTextField Fieldscore_1 = new JTextField(1);
        JTextField Fieldscore_2 = new JTextField(1);
        JTextField Fieldscore_3 = new JTextField(1);
        JTextField Fieldscore_4 = new JTextField(1);
        JTextField Fieldscore_5 = new JTextField(1);

        JButton submit = new JButton("Submit");


        panel7.add(ID);
        panel7.add(ID_Field);

        panel7.add(score_1);
        panel7.add(Fieldscore_1);

        panel7.add(score_2);
        panel7.add(Fieldscore_2);

        panel7.add(score_3);
        panel7.add(Fieldscore_3);

        panel7.add(score_4);
        panel7.add(Fieldscore_4);
        
        panel7.add(score_5);
        panel7.add(Fieldscore_5);

        panel7.add(new JLabel());
        panel7.add(submit);
        submit.addActionListener(new ActionListener() 
         {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                if( Double.parseDouble(Fieldscore_1.getText()) < 6 && Double.parseDouble(Fieldscore_2.getText()) < 6 && Double.parseDouble(Fieldscore_3.getText()) < 6 && Double.parseDouble(Fieldscore_4.getText()) < 6 && Double.parseDouble(Fieldscore_5.getText()) < 6 
                    && Double.parseDouble(Fieldscore_1.getText()) >= 0 && Double.parseDouble(Fieldscore_2.getText())>= 0 && Double.parseDouble(Fieldscore_3.getText())>= 0 && Double.parseDouble(Fieldscore_4.getText()) >= 0 && Double.parseDouble(Fieldscore_5.getText()) >= 0 )
                    {
                        double [] Score = {Double.parseDouble(Fieldscore_1.getText()) , Double.parseDouble(Fieldscore_2.getText())  , Double.parseDouble(Fieldscore_3.getText()) , Double.parseDouble(Fieldscore_4.getText()) , Double.parseDouble(Fieldscore_5.getText())};
                        if( O != null)
                        {
                            O.FillScores(Integer.parseInt(ID_Field.getSelectedItem().toString()), "Boxing", Score);
                            frame7.dispose();
                            OfficialMainWindow(O);
                        }
                        else 
                        {
                            S.FillScores(Integer.parseInt(ID_Field.getSelectedItem().toString()), "Boxing", Score);
                            frame7.dispose();
                            StaffMainWindow(S);
                        }   
                    }
                else
                {
                    JOptionPane.showMessageDialog(null, "Invalid Scores", "Error",
                        JOptionPane.ERROR_MESSAGE);
                }
            }
        });



        frame7.add(panel7 , BorderLayout.CENTER);
        frame7.setSize(800 , 400);
        frame7.setLocationRelativeTo(null);
        frame7.setVisible(true);
    }





    // Official search for player full or short details by ID and given a choice to print report or not 
     public static void OfficialSearch(Official O)
     {

        JFrame frame8 = new JFrame("Official Search");
        JPanel panel8 = new JPanel( new GridLayout(3, 2));

        JLabel IDLabel = new JLabel("ID");
        JTextField ID = new JTextField(20);
        JButton SearchFull = new JButton("Search Full details");
        JButton SearchShort = new JButton("Search Short details");
        JCheckBox Report = new JCheckBox("Print Report");

        panel8.add(IDLabel);
        panel8.add(ID);
        panel8.add(SearchShort);
        panel8.add(SearchFull);
        panel8.add(Report);

        

        SearchFull.addActionListener(new ActionListener() 
         {
            @Override
            public void actionPerformed(ActionEvent e) {
                AthleticsCompetitor A = O.getCompetitorList().SearchForAthleticCompetitor(Integer.parseInt(ID.getText()));
                BoxingCompetitor B = O.getCompetitorList().SearchForBoxingCompetitor(Integer.parseInt(ID.getText()));

                boolean isSelected = Report.isSelected();

                if(A != null)
                {
                    
                    JOptionPane.showMessageDialog(null, O.SearchCompetitorFullDetails(A.getIDnumber(), isSelected) );
                    if(isSelected)
                    {
                         JOptionPane.showMessageDialog(null, "Report Has been printed in Competitor Text file : Competitor.txt" );
                    }
                    frame8.dispose();
                    OfficialMainWindow(O);
                }
                else if (B != null)
                {
                    
                    
                    JOptionPane.showMessageDialog(null, O.SearchCompetitorFullDetails(B.getIDnumber(), isSelected) );
                    if(isSelected)
                    {
                         JOptionPane.showMessageDialog(null, "Report Has been printed in Competitor Text file : Competitor.txt" );
                    }
                    frame8.dispose();
                    OfficialMainWindow(O);
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Player with this ID is not Found", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
                
            }
        });


        SearchShort.addActionListener(new ActionListener() 
         {
            @Override
            public void actionPerformed(ActionEvent e) {
                AthleticsCompetitor A = O.getCompetitorList().SearchForAthleticCompetitor(Integer.parseInt(ID.getText()));
                BoxingCompetitor B = O.getCompetitorList().SearchForBoxingCompetitor(Integer.parseInt(ID.getText()));

                boolean isSelected = Report.isSelected();

                if(A != null)
                {
                    
                    JOptionPane.showMessageDialog(null, O.SearchCompetitorShortDetails(A.getIDnumber(), isSelected) );
                    if(isSelected)
                    {
                         JOptionPane.showMessageDialog(null, "Report Has been printed in Competitor Text file : Competitor.txt" );
                    }
                    frame8.dispose();
                    OfficialMainWindow(O);
                }
                else if (B != null)
                {
                    
                    
                    JOptionPane.showMessageDialog(null, O.SearchCompetitorShortDetails(B.getIDnumber(), isSelected) );
                    if(isSelected)
                    {
                         JOptionPane.showMessageDialog(null, "Report Has been printed in Competitor Text file : Competitor.txt" );
                    }
                    frame8.dispose();
                    OfficialMainWindow(O);
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Player with this ID is not Found", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
                
            }
        });

        frame8.add(panel8 , BorderLayout.CENTER);
        frame8.setSize(300 , 150);
        frame8.setLocationRelativeTo(null);
        frame8.setVisible(true);
     }





     /*Staff main window after login it have 3 buttons either search for player
       by ID or managing the scores of players by ID or printing report of a player*/
     public static void StaffMainWindow(Staff S)
     {
        JFrame frame9 = new JFrame("Staff Window");
        JPanel panel9 = new JPanel(new GridLayout(0 ,1));
        JButton Search = new JButton("Search For Player");
        JButton Manage = new JButton("Manage Scores");
        JButton Reports = new JButton("Print Reports");

        panel9.add(Search);
        panel9.add(Manage);
        panel9.add(Reports);

        Search.addActionListener(new ActionListener() 
         {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame9.dispose();
                StaffSearchWindow(S);
            }
        });

        Reports.addActionListener(new ActionListener() 
         {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame9.dispose();
                PlayerReportWindow(null,S);
            }
        });

        Manage.addActionListener(new ActionListener() 
         {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                frame9.dispose();
                ChooseGameType(null, S);
            }
        });


        frame9.add(panel9 , BorderLayout.CENTER);
        frame9.setSize(300 , 150);
        frame9.setLocationRelativeTo(null);
        frame9.setVisible(true);

     }





     // Staff search for player full or short details by ID and given a choice to print report or not 
     public static void StaffSearchWindow(Staff S)
     {
        JFrame frame10 = new JFrame("Staff Search");
        JPanel panel10 = new JPanel( new GridLayout(3, 2));

        JLabel IDLabel = new JLabel("ID");
        JTextField ID = new JTextField(20);
        JButton SearchFull = new JButton("Search Full details");
        JButton SearchShort = new JButton("Search Short details");
        JCheckBox Report = new JCheckBox("Print Report");

        panel10.add(IDLabel);
        panel10.add(ID);
        panel10.add(SearchShort);
        panel10.add(SearchFull);
        panel10.add(Report);

        

        SearchFull.addActionListener(new ActionListener() 
         {
            @Override
            public void actionPerformed(ActionEvent e) {
                AthleticsCompetitor A = S.getCompetitorList().SearchForAthleticCompetitor(Integer.parseInt(ID.getText()));
                BoxingCompetitor B = S.getCompetitorList().SearchForBoxingCompetitor(Integer.parseInt(ID.getText()));

                boolean isSelected = Report.isSelected();

                if(A != null)
                {
                    
                    JOptionPane.showMessageDialog(null, S.SearchCompetitorFullDetails(A.getIDnumber(), isSelected) );
                    if(isSelected)
                    {
                         JOptionPane.showMessageDialog(null, "Report Has been printed in Competitor Text file : Competitor.txt" );
                    }
                    frame10.dispose();
                    StaffMainWindow(S);
                }
                else if (B != null)
                {
                    
                    
                    JOptionPane.showMessageDialog(null, S.SearchCompetitorFullDetails(B.getIDnumber(), isSelected) );
                    if(isSelected)
                    {
                         JOptionPane.showMessageDialog(null, "Report Has been printed in Competitor Text file : Competitor.txt" );
                    }
                    frame10.dispose();
                    StaffMainWindow(S);
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Player with this ID is not Found", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
                
            }
        });


        SearchShort.addActionListener(new ActionListener() 
         {
            @Override
            public void actionPerformed(ActionEvent e) {
                AthleticsCompetitor A = S.getCompetitorList().SearchForAthleticCompetitor(Integer.parseInt(ID.getText()));
                BoxingCompetitor B = S.getCompetitorList().SearchForBoxingCompetitor(Integer.parseInt(ID.getText()));

                boolean isSelected = Report.isSelected();

                if(A != null)
                {
                    
                    JOptionPane.showMessageDialog(null, S.SearchCompetitorShortDetails(A.getIDnumber(), isSelected) );
                    if(isSelected)
                    {
                         JOptionPane.showMessageDialog(null, "Report Has been printed in Competitor Text file : Competitor.txt" );
                    }
                    frame10.dispose();
                    StaffMainWindow(S);
                }
                else if (B != null)
                {
                    
                    
                    JOptionPane.showMessageDialog(null, S.SearchCompetitorShortDetails(B.getIDnumber(), isSelected) );
                    if(isSelected)
                    {
                         JOptionPane.showMessageDialog(null, "Report Has been printed in Competitor Text file : Competitor.txt" );
                    }
                    frame10.dispose();
                    StaffMainWindow(S);
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Player with this ID is not Found", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
                
            }
        });

        frame10.add(panel10 , BorderLayout.CENTER);
        frame10.setSize(300 , 150);
        frame10.setLocationRelativeTo(null);
        frame10.setVisible(true);
     }




     
     // userwindow for searching for his details and scores and printing reports 
     public static void UserSearchWindow()
     {
        Staff S = new Staff("a", "b", "a@b", "11");

        JFrame frame11 = new JFrame("User Search");
        JPanel panel11 = new JPanel( new GridLayout(3, 2));

        JLabel IDLabel = new JLabel("ID");
        JTextField ID = new JTextField(20);
        JButton SearchFull = new JButton("Search Full details");
        JButton SearchShort = new JButton("Search Short details");
        JCheckBox Report = new JCheckBox("Print Report");

        panel11.add(IDLabel);
        panel11.add(ID);
        panel11.add(SearchShort);
        panel11.add(SearchFull);
        panel11.add(Report);

        

        SearchFull.addActionListener(new ActionListener() 
         {
            @Override
            public void actionPerformed(ActionEvent e) {
                AthleticsCompetitor A = S.getCompetitorList().SearchForAthleticCompetitor(Integer.parseInt(ID.getText()));
                BoxingCompetitor B = S.getCompetitorList().SearchForBoxingCompetitor(Integer.parseInt(ID.getText()));

                boolean isSelected = Report.isSelected();

                if(A != null)
                {
                    
                    JOptionPane.showMessageDialog(null, S.SearchCompetitorFullDetails(A.getIDnumber(), isSelected) );
                    if(isSelected)
                    {
                         JOptionPane.showMessageDialog(null, "Report Has been printed in Competitor Text file : Competitor.txt" );
                    }
                }
                else if (B != null)
                {
                    
                    
                    JOptionPane.showMessageDialog(null, S.SearchCompetitorFullDetails(B.getIDnumber(), isSelected) );
                    if(isSelected)
                    {
                         JOptionPane.showMessageDialog(null, "Report Has been printed in Competitor Text file : Competitor.txt" );
                    }
                    frame11.dispose();
                    MainWindow();
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Player with this ID is not Found", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
                
            }
        });


        SearchShort.addActionListener(new ActionListener() 
         {
            @Override
            public void actionPerformed(ActionEvent e) {
                AthleticsCompetitor A = S.getCompetitorList().SearchForAthleticCompetitor(Integer.parseInt(ID.getText()));
                BoxingCompetitor B = S.getCompetitorList().SearchForBoxingCompetitor(Integer.parseInt(ID.getText()));

                boolean isSelected = Report.isSelected();

                if(A != null)
                {
                    
                    JOptionPane.showMessageDialog(null, S.SearchCompetitorShortDetails(A.getIDnumber(), isSelected) );
                    if(isSelected)
                    {
                         JOptionPane.showMessageDialog(null, "Report Has been printed in Competitor Text file : Competitor.txt" );
                    }
                    frame11.dispose();
                    MainWindow();
                }
                else if (B != null)
                {
                    
                    
                    JOptionPane.showMessageDialog(null, S.SearchCompetitorShortDetails(B.getIDnumber(), isSelected) );
                    if(isSelected)
                    {
                         JOptionPane.showMessageDialog(null, "Report Has been printed in Competitor Text file : Competitor.txt" );
                    }
                    frame11.dispose();
                    MainWindow();
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Player with this ID is not Found", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
                
            }
        });

        frame11.add(panel11 , BorderLayout.CENTER);
        frame11.setSize(300 , 200);
        frame11.setLocationRelativeTo(null);
        frame11.setVisible(true);
     }




     /*3 choices is given here to the staff or official to print either 
      the reports of all athletics player or boxing players or both of them on the same report */  
     private static void PlayerReportWindow (Official O , Staff S)
     {
        JFrame frame12 = new JFrame("Players Report");
        JPanel panel12 = new JPanel(new GridLayout(0,1));

        JButton Athletics = new JButton("All Athletics Report");
        JButton Boxing = new JButton("All Boxing Reports");
        JButton AllPlayers = new JButton("All Players Report");

        panel12.add(Athletics);
        panel12.add(Boxing);
        panel12.add(AllPlayers);
        Athletics.addActionListener(new ActionListener() 
         {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(O!=null)
                {
                    O.getCompetitorList().PrintAthleticsFinalReport(O.getCompetitorList().APlayers);
                    JOptionPane.showMessageDialog(null, "Reports of all Athletic Players Has been printed in Text file : (AthleticsFinalReport.txt)" );
                    frame12.dispose();
                    OfficialMainWindow(O);
                }
                else
                {
                    S.getCompetitorList().PrintAthleticsFinalReport(S.getCompetitorList().APlayers);
                    JOptionPane.showMessageDialog(null, "Reports of all Athletic Players Has been printed in Text file : (AthleticsFinalReport.txt)" );
                    frame12.dispose();
                    StaffMainWindow(S);
                }
               

            }
        });

        Boxing.addActionListener(new ActionListener() 
         {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(O != null)
                {
                    O.getCompetitorList().PrintBoxingFinalReport(O.getCompetitorList().BPlayers);
                    JOptionPane.showMessageDialog(null, "Reports of all Athletic Players Has been printed in Text file : (BoxingFinalReport.txt)" );
                    frame12.dispose();
                    OfficialMainWindow(O);
                }
                else
                {
                    S.getCompetitorList().PrintBoxingFinalReport(S.getCompetitorList().BPlayers);
                    JOptionPane.showMessageDialog(null, "Reports of all Athletic Players Has been printed in Text file : (BoxingFinalReport.txt)" );
                    frame12.dispose();
                    StaffMainWindow(S);
                }

                

            }
        });

        AllPlayers.addActionListener(new ActionListener() 
         {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(O != null)
                {
                    O.getCompetitorList().PrintFinalReport(O.getCompetitorList().APlayers, O.getCompetitorList().BPlayers);
                    JOptionPane.showMessageDialog(null, "Reports of all Players Has been printed in Text file : (FinalReport.txt)" );
                    frame12.dispose();
                    OfficialMainWindow(O);
                }
                else
                {
                    S.getCompetitorList().PrintFinalReport(S.getCompetitorList().APlayers , S.getCompetitorList().BPlayers);
                    JOptionPane.showMessageDialog(null, "Reports of all Players Has been printed in Text file : (FinalReport.txt)" );
                    frame12.dispose();
                    StaffMainWindow(S);
                }
                

            }
        });

        frame12.add(panel12 , BorderLayout.CENTER);
        frame12.setSize(300 , 200);
        frame12.setLocationRelativeTo(null);
        frame12.setVisible(true);
     }



    public static void main(String[] args) {
        new GUI();
    }
}

