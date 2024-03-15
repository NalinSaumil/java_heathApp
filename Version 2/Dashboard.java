import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

//The dashboard class as the homepage for 
//the class. This is where all the user info is 
//displayed and is only accessible after login is 
//successful. Different for every user.
class Dashboard {
    //Dashboard attributes
   private JFrame frame;
   private JLabel caloriesLabel;
   private JLabel userLabel;
   private JLabel tcaloriesLabel;
   private JLabel waterLabel;
   private JLabel exLabel;
   private JButton enterCaloriesButton;
   private JButton logoutButton;
   private JButton waterButton;
   private JButton accountButton;
   private JButton exButton;
   private User user;
   private AuthManager authManager;
    
    //Dashboard constructor to initiate page. 
    //Called when login is successful.
   public Dashboard(User user, AuthManager authManager) {
      this.user = user;
      this.authManager = authManager;
      
      frame = new JFrame("Dashboard - " + user.getName());
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setSize(800, 600);
      centerFrame(frame);
   
      JPanel panel = new JPanel();
      frame.add(panel);
      placeComponents(panel);
   
      frame.setVisible(true);
   }
    
    //Build dashboard page
   private void placeComponents(JPanel panel) {
      panel.setLayout(null);
        
      ImageIcon logoIcon = new ImageIcon("v147_81.png");
      Image logoImage = logoIcon.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
      ImageIcon scaledLogoIcon = new ImageIcon(logoImage);
   
      JLabel logoLabel = new JLabel(scaledLogoIcon);
      logoLabel.setBounds(10, 10, 100, 100);
      panel.add(logoLabel);
   
      userLabel = new JLabel("Logged in as: " + user.getName());
      userLabel.setBounds(10, 110, 200, 25);
      panel.add(userLabel);
   
      caloriesLabel = new JLabel("Breakfast Calories: " + user.getBCals() + "     Lunch Calories: " + user.getLCals() + "     Dinner Calories: " + user.getDCals());
      caloriesLabel.setBounds(10, 140, 800, 25);
      panel.add(caloriesLabel);
   
      exLabel = new JLabel("Exercise Calories: " + user.getEx());
      exLabel.setBounds(10, 170, 800, 25);
      panel.add(exLabel);
        
      tcaloriesLabel = new JLabel("Total Calories: " + (user.getBCals() + user.getLCals() + user.getDCals() - user.getEx()) + "/" + user.calcBMR());
      tcaloriesLabel.setBounds(10, 200, 800, 25);
      panel.add(tcaloriesLabel);
        
      waterLabel = new JLabel("Water Intake: " + user.getWaterIntake() + " ounces out of 64 ounces");
      waterLabel.setBounds(10, 230, 800, 25);
      panel.add(waterLabel);
   
      enterCaloriesButton = new JButton("Enter Calories");
      enterCaloriesButton.setBounds(10, 260, 150, 25);
      panel.add(enterCaloriesButton);
   
      logoutButton = new JButton("Logout");
      logoutButton.setBounds(175, 290, 150, 25);
      panel.add(logoutButton);
        
      waterButton = new JButton("Enter Water Intake");
      waterButton.setBounds(175, 260, 150, 25);
      panel.add(waterButton);
      
      accountButton = new JButton("Account");
      accountButton.setBounds(10, 290, 150, 25);
      panel.add(accountButton);
   
      exButton = new JButton("Enter Exercise Calories");
      exButton.setBounds(340, 260, 180, 25);
      panel.add(exButton);
        
        //assign actions to the buttons
      enterCaloriesButton.addActionListener(
         new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               frame.dispose(); // Close the current dashboard
               new ChooseMeal(Dashboard.this); // Open the calorie entry page
            }
         });
        
      logoutButton.addActionListener(
         new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               frame.dispose(); // Close the current dashboard
            }
         });
        
      waterButton.addActionListener(
         new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               frame.dispose(); // Close the current dashboard
               new WaterEntryPage(Dashboard.this); // Open the water intake entry page
            }
         });
   
      exButton.addActionListener(
         new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               frame.dispose(); // Close the current dashboard
               new EnterExCals(Dashboard.this); // Open the water intake entry page
            }
         });
       
      accountButton.addActionListener(
         new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               frame.dispose(); // Close the current dashboard
               new AccountPage(Dashboard.this); // Open the account information page
            }
         });
   }
    
    //Update calories after user input
   public void updateBCalories(int newCalories) {
      user.addBCals(newCalories);
      caloriesLabel.setText("Breakfast Calories: " + user.getBCals() + "     Lunch Calories: " + user.getLCals() + "     Dinner Calories: " + user.getDCals());
      tcaloriesLabel.setText("Total Calories: " + (user.getBCals() + user.getLCals() + user.getDCals() - user.getEx()) + "/" + user.calcBMR());
      frame.setVisible(true);
   }
    
   public void updateLCalories(int newCalories) {
      user.addLCals(newCalories);
      caloriesLabel.setText("Breakfast Calories: " + user.getBCals() + "     Lunch Calories: " + user.getLCals() + "     Dinner Calories: " + user.getDCals());
      tcaloriesLabel.setText("Total Calories: " + (user.getBCals() + user.getLCals() + user.getDCals() - user.getEx()) + "/" + user.calcBMR());
      frame.setVisible(true);
   }

   public void updateDCalories(int newCalories) {
      user.addDCals(newCalories);
      caloriesLabel.setText("Breakfast Calories: " + user.getBCals() + "     Lunch Calories: " + user.getLCals() + "     Dinner Calories: " + user.getDCals());
      tcaloriesLabel.setText("Total Calories: " + (user.getBCals() + user.getLCals() + user.getDCals() - user.getEx()) + "/" + user.calcBMR());
      frame.setVisible(true);
   }
    
   public void updateWaterIntake(int newWaterIntake) {
        // Update user's water intake and update the dashboard
      user.addWaterIntake(newWaterIntake);
      waterLabel.setText("Water Intake: " + user.getWaterIntake() + " ounces out of 64 ounces");
      frame.setVisible(true);
   }

   public void updateEx(int exCals) {
        // Update user's exercise calories and update the dashboard
      user.addEx(exCals);
      exLabel.setText("Exercise Calories: " + user.getEx());
      tcaloriesLabel.setText("Total Calories: " + (user.getBCals() + user.getLCals() + user.getDCals() - user.getEx()) + "/" + user.calcBMR());
      frame.setVisible(true);
   }
   
   public void showFrame() {
      frame.setVisible(true);
   }
   
   public User getUser() {
      return user;
   }
   
   public AuthManager getAuthManager() {
      return authManager;
   }
   
   public void updateUserInfo() {
      userLabel.setText("Logged in as: " + user.getName());
      tcaloriesLabel.setText("Total Calories: " + (user.getBCals() + user.getLCals() + user.getDCals() - user.getEx()) + "/" + user.calcBMR());
      frame.setVisible(true);
   }

   private void centerFrame(JFrame frame) {
      Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
      int x = (dim.width - frame.getWidth()) / 2;
      int y = (dim.height - frame.getHeight()) / 2;
      frame.setLocation(x, y);
   }
}
