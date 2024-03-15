import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

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
   private JLabel dateLabel;
   private JButton enterCaloriesButton;
   private JButton logoutButton;
   private JButton waterButton;
   private JButton accountButton;
   private JButton exButton;
   private JButton forwardButton;
   private User user;
   private AuthManager authManager;
   private DailyData dailyData;
   private Date date;
   private Date currentDate;
   private ScheduledExecutorService scheduler;
   boolean past;
   boolean working;
    
    //Dashboard constructor to initiate page. 
    //Called when login is successful.
   public Dashboard(User user, AuthManager authManager) {
      this(user, authManager, new Date());    
   }
   
   public Dashboard(User user, AuthManager authManager, Date date) {
      this.user = user;
      this.authManager = authManager;
      this.date = date;
      this.currentDate = new Date();
      working = false;
      
      frame = new JFrame("Dashboard - " + user.getName());
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setSize(800, 600);
      centerFrame(frame);
   
      JPanel panel = new JPanel();
      frame.add(panel);
      placeComponents(panel);
      
      scheduler = Executors.newScheduledThreadPool(1);
      scheduler.scheduleAtFixedRate(this::checkForNewDay, 0, 15, TimeUnit.SECONDS);
   
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
      
      JButton backwardButton = new JButton("<");
      backwardButton.setBounds(10, 140, 50, 25);
      panel.add(backwardButton);
      
      dailyData = user.getDailyDataByDate(date);
      SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM dd, yyyy");
      dateLabel = new JLabel("Date: " + dateFormat.format(dailyData.getDate()));
      dateLabel.setHorizontalAlignment(SwingConstants.CENTER);
      dateLabel.setBounds(75, 140, 150, 25);
      panel.add(dateLabel);
      
      forwardButton = new JButton(">");
      forwardButton.setBounds(240, 140, 50, 25);
      panel.add(forwardButton);
      
      evalFwdButton();
      
      caloriesLabel = new JLabel("Breakfast Calories: " + dailyData.getBCals() + "     Lunch Calories: " + dailyData.getLCals() + "     Dinner Calories: " + dailyData.getDCals());
      caloriesLabel.setBounds(10, 170, 800, 25);
      panel.add(caloriesLabel);
   
      exLabel = new JLabel("Exercise Calories: " + dailyData.getEx());
      exLabel.setBounds(10, 200, 800, 25);
      panel.add(exLabel);
        
      tcaloriesLabel = new JLabel("Total Calories: " + (dailyData.getBCals() + dailyData.getLCals() + dailyData.getDCals() - dailyData.getEx()) + "/" + user.calcBMR());
      tcaloriesLabel.setBounds(10, 230, 800, 25);
      panel.add(tcaloriesLabel);
        
      waterLabel = new JLabel("Water Intake: " + dailyData.getWaterIntake() + " ounces out of 64 ounces");
      waterLabel.setBounds(10, 260, 800, 25);
      panel.add(waterLabel);
   
      enterCaloriesButton = new JButton("Enter Calories");
      enterCaloriesButton.setBounds(10, 290, 150, 25);
      panel.add(enterCaloriesButton);
   
      logoutButton = new JButton("Logout");
      logoutButton.setBounds(340, 320, 150, 25);
      panel.add(logoutButton);
        
      waterButton = new JButton("Enter Water Intake");
      waterButton.setBounds(175, 290, 150, 25);
      panel.add(waterButton);
      
      accountButton = new JButton("Account");
      accountButton.setBounds(175, 320, 150, 25);
      panel.add(accountButton);
   
      exButton = new JButton("Enter Exercise Calories");
      exButton.setBounds(340, 290, 180, 25);
      panel.add(exButton);
      
      JButton dateButton = new JButton("Change Date");
      dateButton.setBounds(10, 320, 150, 25);
      panel.add(dateButton);
        
        //assign actions to the buttons
      enterCaloriesButton.addActionListener(
         new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               working = true;
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
         
      dateButton.addActionListener(
         new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               working = true;
               frame.dispose(); // Close the current dashboard
               new ChangeDate(Dashboard.this); // Open the calorie entry page
            }
         });
        
      waterButton.addActionListener(
         new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               working = true;
               frame.dispose(); // Close the current dashboard
               new WaterEntryPage(Dashboard.this); // Open the water intake entry page
            }
         });
   
      exButton.addActionListener(
         new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               working = true;
               frame.dispose(); // Close the current dashboard
               new EnterExCals(Dashboard.this); // Open the exercise calorie entry page
            }
         });
       
      accountButton.addActionListener(
         new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               working = true;
               frame.dispose(); // Close the current dashboard
               new AccountPage(Dashboard.this); // Open the account information page
            }
         });
         
      backwardButton.addActionListener(
         new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               past = true;
               setDate(getPreviousDate(date));
               if(isSameDay(currentDate, date)) {
                  forwardButton.setVisible(false);
               } else {
                  forwardButton.setVisible(true);
               }
            
               updateDailyData();
            }
         });
         
      forwardButton.addActionListener(
         new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               if(!isSameDay(date, currentDate)) {
                  past = true;
               } else {
                  past = false;
               }
               setDate(getNextDate(date));
               if(isSameDay(currentDate, date)) {
                  forwardButton.setVisible(false);
               } else {
                  forwardButton.setVisible(true);
               }
            
               updateDailyData();
            }
         });
   }
    
    //Update calories after user input
   public void updateBCalories(int newCalories) {
      dailyData.addBCals(newCalories);
      working = false;
      caloriesLabel.setText("Breakfast Calories: " + dailyData.getBCals() + "     Lunch Calories: " + dailyData.getLCals() + "     Dinner Calories: " + dailyData.getDCals());
      tcaloriesLabel.setText("Total Calories: " + (dailyData.getBCals() + dailyData.getLCals() + dailyData.getDCals() - dailyData.getEx()) + "/" + user.calcBMR());
      frame.setVisible(true);
   }
    
   public void updateLCalories(int newCalories) {
      dailyData.addLCals(newCalories);
      working = false;
      caloriesLabel.setText("Breakfast Calories: " + dailyData.getBCals() + "     Lunch Calories: " + dailyData.getLCals() + "     Dinner Calories: " + dailyData.getDCals());
      tcaloriesLabel.setText("Total Calories: " + (dailyData.getBCals() + dailyData.getLCals() + dailyData.getDCals() - dailyData.getEx()) + "/" + user.calcBMR());
      frame.setVisible(true);
   }

   public void updateDCalories(int newCalories) {
      dailyData.addDCals(newCalories);
      working = false;
      caloriesLabel.setText("Breakfast Calories: " + dailyData.getBCals() + "     Lunch Calories: " + dailyData.getLCals() + "     Dinner Calories: " + dailyData.getDCals());
      tcaloriesLabel.setText("Total Calories: " + (dailyData.getBCals() + dailyData.getLCals() + dailyData.getDCals() - dailyData.getEx()) + "/" + user.calcBMR());
      frame.setVisible(true);
   }
    
   public void updateWaterIntake(int newWaterIntake) {
        // Update user's water intake and update the dashboard
      dailyData.addWaterIntake(newWaterIntake);
      working = false;
      waterLabel.setText("Water Intake: " + dailyData.getWaterIntake() + " ounces out of 64 ounces");
      frame.setVisible(true);
   }

   public void updateEx(int exCals) {
        // Update user's exercise calories and update the dashboard
      dailyData.addEx(exCals);
      working = false;
      exLabel.setText("Exercise Calories: " + dailyData.getEx());
      tcaloriesLabel.setText("Total Calories: " + (dailyData.getBCals() + dailyData.getLCals() + dailyData.getDCals() - dailyData.getEx()) + "/" + user.calcBMR());
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
      updateDailyData();
   }
   
   public void updateDailyData() {
      dailyData = user.getDailyDataByDate(date);
      working = false;
      SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM dd, yyyy");
      dateLabel.setText("Date: " + dateFormat.format(dailyData.getDate()));
      waterLabel.setText("Water Intake: " + dailyData.getWaterIntake() + " ounces out of 64 ounces");
      caloriesLabel.setText("Breakfast Calories: " + dailyData.getBCals() + "     Lunch Calories: " + dailyData.getLCals() + "     Dinner Calories: " + dailyData.getDCals());
      tcaloriesLabel.setText("Total Calories: " + (dailyData.getBCals() + dailyData.getLCals() + dailyData.getDCals() - dailyData.getEx()) + "/" + user.calcBMR());
      exLabel.setText("Exercise Calories: " + dailyData.getEx());
      showFrame();
      frame.revalidate();
      frame.repaint();
   }
   
   public void setDate(Date date) {
      this.date = date;
   }
   
   private void checkForNewDay() {
      currentDate = new Date();
      if (!isSameDay(currentDate, date) && !this.past && !this.working) {
            // New day detected, update the date and refresh the dashboard
         date = currentDate;
         updateDailyData();
      }
   }

   private boolean isSameDay(Date date1, Date date2) {
      SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
      return sdf.format(date1).equals(sdf.format(date2));
   }
   
   private Date getPreviousDate(Date currentDate) {
      Calendar calendar = Calendar.getInstance();
      calendar.setTime(currentDate);
      calendar.add(Calendar.DAY_OF_MONTH, -1); // Subtract one day
      return calendar.getTime();
   }
    
   private Date getNextDate(Date currentDate) {
      Calendar calendar = Calendar.getInstance();
      calendar.setTime(currentDate);
      calendar.add(Calendar.DAY_OF_MONTH, 1); // Subtract one day
      return calendar.getTime();
   }
   
   public void evalFwdButton() {
      if(!past) {
         forwardButton.setVisible(false);
      } else {
         forwardButton.setVisible(true);
      }
   }

   private void centerFrame(JFrame frame) {
      Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
      int x = (dim.width - frame.getWidth()) / 2;
      int y = (dim.height - frame.getHeight()) / 2;
      frame.setLocation(x, y);
   }
}