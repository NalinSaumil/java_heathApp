import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.regex.*;
import java.text.ParseException;

//This is the user class to store user information
class User {

   //user class attributes
   private String username;
   private String password;
   private String name;
   private String gender;
   private double weight;
   private double height;
   private int age;
   private List<DailyData> listOfDailyData;
   
   //User class constructor
   public User(String username, String password, String name, String gender, double weight, int age, double height) {
      this.username = username;
      this.password = password;
      this.name = name;
      this.age = age;
      this.weight = weight;
      this.height = height;
      this.gender = gender;
      this.listOfDailyData = new ArrayList<DailyData>();
   }
   
   //user class get and set methods
   public String getUsername() {
      return username;
   }

   public String getPassword() {
      return password;
   }
   
   public List<DailyData> getListOfDailyData() {
      return listOfDailyData;
   }
   
   public DailyData getDailyDataByDate(Date date) {
      SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
      for(DailyData data : listOfDailyData) {
         if(sdf.format(data.getDate()).equals(sdf.format(date))) {
            return data;
         }
      }
      
      DailyData newData = new DailyData(date);
      listOfDailyData.add(newData);
      return newData;
   }
   
   public void resetDailyData() {
      Calendar currentDate = Calendar.getInstance();
      Calendar dataDate = Calendar.getInstance();
   
      for (DailyData dailyData : listOfDailyData) {
         dataDate.setTime(dailyData.getDate());
      
            // Check if the data date is the same as the current date
         if (dataDate.get(Calendar.YEAR) == currentDate.get(Calendar.YEAR)
                    && dataDate.get(Calendar.MONTH) == currentDate.get(Calendar.MONTH)
                    && dataDate.get(Calendar.DAY_OF_MONTH) == currentDate.get(Calendar.DAY_OF_MONTH)) {
            dailyData.setBCals(0);
            dailyData.setLCals(0);
            dailyData.setDCals(0);
            dailyData.setWaterIntake(0);
            dailyData.setEx(0);
         }
      }
   }
   
   public String getName() {
      return name;
   }
    
   public void setName(String name) {
      this.name = name;
   }
   
   public String getGender() {
      return gender;
   }
    
   public void setGender(String gender) {
      this.gender = gender;
   }
   
   public double getWeight() {
      return weight;
   }
   
   public void setWeight(double weight) {
      this.weight = weight;
   }
   
   public double getHeight() {
      return height;
   }
   
   public void setHeight(double height) {
      this.height = height;
   }
   
   public int getAge() {
      return age;
   }
   
   public void setAge(int age) {
      this.age = age;
   }

   //Method to Calculate BMR
   public int calcBMR() {
      if(gender.equals("Male")) {
         return (int)(88.362 + (13.397 * (this.weight/2.205)) + (4.799 * (this.height * 2.54)) - (5.677 * this.age));
      } else {
         return (int)(447.593 + (9.247 * (this.weight/2.205)) + (3.098 * (this.height * 2.54)) - (4.330 * this.age));
      }
   }
}

class DailyData {
   
   private Date date;
   private int Bcals;
   private int Lcals;
   private int Dcals;
   private int waterIntake;
   private int exerciseCals;
   
   public DailyData(Date date) {
      this.date = date;
      this.Bcals = 0;
      this.Lcals = 0;
      this.Dcals = 0;
      this.waterIntake = 0;
      this.exerciseCals = 0;
   }
   
   public Date getDate() {
      return date;
   }
   
   public int getBCals() {
      return Bcals;
   }
   
   public void addBCals(int Bcals) {
      this.Bcals += Bcals;
   }
   
   public void setBCals(int Bcals) {
      this.Bcals = Bcals;
   }
   
   public int getLCals() {
      return Lcals;
   }
   
   public void addLCals(int Lcals) {
      this.Lcals += Lcals;
   }
   
   public void setLCals(int Lcals) {
      this.Lcals = Lcals;
   }
   
   public int getDCals() {
      return Dcals;
   }
   
   public void addDCals(int Dcals) {
      this.Dcals += Dcals;
   }
   
   public void setDCals(int Dcals) {
      this.Dcals = Dcals;
   }
   
   public int getWaterIntake() {
      return waterIntake;
   }
   
   public void addWaterIntake(int waterIntake) {
      this.waterIntake += waterIntake;
   }
   
   public void setWaterIntake(int waterIntake) {
      this.waterIntake = waterIntake;
   }
   
   public int getEx() {
      return exerciseCals;
   }
   
   public void addEx(int exerciseCals) {
      this.exerciseCals += exerciseCals;
   }
   
   public void setEx(int exerciseCals) {
      this.exerciseCals = exerciseCals;
   }
}

//This is the AuthManager Class. 
//It is used to keep a list of users that have accounts on the app.
class AuthManager {
   //AuthManager Attributes
   public List<User> users;
   
   //Authmanager constructor
   public AuthManager() {
      users = new ArrayList<>();
        // Add some sample users
      users.add(new User("admin1", "password1", "One", "Male", 160.2, 24, 75));
      users.add(new User("admin2", "password2", "Two", "Female", 120.5, 23, 68));
      users.add(new User("Saumiln", "Bonzi123", "Saumil Nalin", "Male", 211.6, 21, 68.7));
   }
   
   //used to authenticate a log in. 
   //Learn how to add encryption to this???
   public boolean authenticate(String username, String password) {
      for (User user : users) {
         if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
            return true;
         }
      }
      return false;
   }
   
   //Return user based on username
   public User returnUser(String username) {
      for(User user : users) {
         if(user.getUsername().equals(username)) {
            return user;
         }
      }
      return null;
   }
   
   //Add user during sign up process. 
   //Update this later on for user information expansion.
   public String addUser(String username, String password, String name, String gender, double weight, int age, double height) {
        // Check if the username already exists
      for (User user : users) {
         if (user.getUsername().equals(username)) {
            return "UserN"; // Username already taken
         }
      }
      
      if(height == -1 || weight == -1 || age == -1) {
         return "ValueN";
      }
        // Add the new user
      users.add(new User(username, password, name, gender, weight, age, height));
      return "UserY";
   }
   
   //update user method allows for secure 
   //user updates to the user account information
   public String updateUser(String username, String realName, double height, double weight, int age, String gender) {
      
      if(height == -1 || weight == -1 || age == -1) {
         return "ValueN";
      }
      
      for (User user : users) {
         if (user.getUsername().equals(username)) {
            user.setName(realName);
            user.setHeight(height);
            user.setWeight(weight);
            user.setAge(age);
            user.setGender(gender);
            return "UserY";
         }
      }
      
      return "UserN";
   }
}

//The LoginApp is for the login page. 
//This is where the app starts at all times. 
//Learn how to surpass this by saving logins???
public class LoginApp {
   
   //Login app attributes
   public static AuthManager authManager = new AuthManager();
   
   
   //The APP starts. What the compiler runs.
   public static void main(String[] args) {
      JFrame frame = new JFrame("Login");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setSize(300, 400);
      centerFrame(frame);
   
      JPanel panel = new JPanel();
      frame.add(panel);
      placeComponents(panel);
   
      frame.setVisible(true);
   }
   
   //The building of the login page
   private static void placeComponents(JPanel panel) {
      panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
   
      ImageIcon logoIcon = new ImageIcon("v147_81.png");
      Image logoImage = logoIcon.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
      ImageIcon scaledLogoIcon = new ImageIcon(logoImage);
   
      JLabel logoLabel = new JLabel(scaledLogoIcon);
      logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
      panel.add(logoLabel);
   
      JLabel userLabel = new JLabel("Username:");
      userLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
      panel.add(userLabel);
   
      JTextField userText = new JTextField(20);
      userText.setAlignmentX(Component.CENTER_ALIGNMENT);
      userText.setMaximumSize(new Dimension(150, 25));
      panel.add(userText);
   
      JLabel passwordLabel = new JLabel("Password:");
      passwordLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
      panel.add(passwordLabel);
   
      JPasswordField passwordText = new JPasswordField(20);
      passwordText.setAlignmentX(Component.CENTER_ALIGNMENT);
      passwordText.setMaximumSize(new Dimension(150, 25));
      panel.add(passwordText);
   
      JButton loginButton = new JButton("Login");
      loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
      panel.add(loginButton);
   
      JButton signUpButton = new JButton("Sign Up");
      signUpButton.setAlignmentX(Component.CENTER_ALIGNMENT);
      panel.add(signUpButton);
   
      //Assign Functionality to buttons
      loginButton.addActionListener(
         new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               String username = userText.getText();
               String password = new String(passwordText.getPassword());
            
               if (authManager.authenticate(username, password)) {
                  JOptionPane.showMessageDialog(panel, "Login successful!");
                    // Open dashboard after successful login
                  new Dashboard(authManager.returnUser(username), authManager);
                  userText.setText("");
                  passwordText.setText("");
               } else {
                  JOptionPane.showMessageDialog(panel, "Login failed. Invalid credentials.");
               }
            }
         });
   
      signUpButton.addActionListener(
         new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               new SignUp();
            }
         });
   }

   private static void centerFrame(JFrame frame) {
      Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
      int x = (dim.width - frame.getWidth()) / 2;
      int y = (dim.height - frame.getHeight()) / 2;
      frame.setLocation(x, y);
   }
}

//Signup class to create new accounts.
class SignUp {
   private JFrame frame;
   
   
   //SignUp constructor to intiate the signup page upon being called.
   public SignUp() {
      frame = new JFrame("Sign Up");
      frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      frame.setSize(400, 500);
      centerFrame(frame);
   
      JPanel panel = new JPanel();
      frame.add(panel);
      placeComponents(panel);
   
      frame.setVisible(true);
   }
   
   //Build signup page
   private void placeComponents(JPanel panel) {
      panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
   
      ImageIcon logoIcon = new ImageIcon("v147_81.png");
      Image logoImage = logoIcon.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
      ImageIcon scaledLogoIcon = new ImageIcon(logoImage);
   
      JLabel logoLabel = new JLabel(scaledLogoIcon);
      logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
      panel.add(logoLabel);
   
      JLabel userLabel = new JLabel("Username:");
      userLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
      panel.add(userLabel);
   
      JTextField userText = new JTextField(20);
      userText.setAlignmentX(Component.CENTER_ALIGNMENT);
      userText.setMaximumSize(new Dimension(150, 25));
      panel.add(userText);
      
      JLabel nameLabel = new JLabel("Full Name:");
      nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
      panel.add(nameLabel);
   
      JTextField nameText = new JTextField(20);
      nameText.setAlignmentX(Component.CENTER_ALIGNMENT);
      nameText.setMaximumSize(new Dimension(150, 25));
      panel.add(nameText);
   
      JLabel genLabel = new JLabel("Gender:");
      genLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
      panel.add(genLabel);
   
      String[] genderOptions = {"Male", "Female"};
      JComboBox<String> genderComboBox = new JComboBox<String>(genderOptions);
      genderComboBox.setAlignmentX(Component.CENTER_ALIGNMENT);
      genderComboBox.setMaximumSize(new Dimension(150, 25));
      panel.add(genderComboBox);
   
      JLabel ageLabel = new JLabel("Age:");
      ageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
      panel.add(ageLabel);
   
      JTextField ageText = new JTextField(20);
      ageText.setAlignmentX(Component.CENTER_ALIGNMENT);
      ageText.setMaximumSize(new Dimension(150, 25));
      panel.add(ageText);
   
      JLabel weiLabel = new JLabel("Weight (in pounds):");
      weiLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
      panel.add(weiLabel);
   
      JTextField weiText = new JTextField(20);
      weiText.setAlignmentX(Component.CENTER_ALIGNMENT);
      weiText.setMaximumSize(new Dimension(150, 25));
      panel.add(weiText);
      
      JLabel heiLabel = new JLabel("Height (in inches):");
      heiLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
      panel.add(heiLabel);
   
      JTextField heiText = new JTextField(20);
      heiText.setAlignmentX(Component.CENTER_ALIGNMENT);
      heiText.setMaximumSize(new Dimension(150, 25));
      panel.add(heiText);
   
      JLabel passwordLabel = new JLabel("Password (5-12 Characters; No Special Characters):");
      passwordLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
      panel.add(passwordLabel);
   
      JPasswordField passwordText = new JPasswordField(20);
      passwordText.setAlignmentX(Component.CENTER_ALIGNMENT);
      passwordText.setMaximumSize(new Dimension(150, 25));
      panel.add(passwordText);
   
      JButton submitButton = new JButton("Submit");
      submitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
      panel.add(submitButton);
      
      JButton backButton = new JButton("Back");
      backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
      panel.add(backButton);
      
      //Assign Functionality to buttons
      submitButton.addActionListener(
         new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               String username = userText.getText();
               String name = nameText.getText();
               String gender = (String) genderComboBox.getSelectedItem();
               
               int age;
               double height;
               double weight;
               
               if(isInteger(ageText.getText()) && Integer.parseInt(ageText.getText()) > 0) {
                  age = Integer.parseInt(ageText.getText());
               } else {
                  JOptionPane.showMessageDialog(panel, "Please only enter a positive integer for age.");
                  age = -1;
               }
               
               if(isDouble(weiText.getText()) && Double.parseDouble(weiText.getText()) > 0) {
                  weight = Double.parseDouble(weiText.getText());
               } else {
                  JOptionPane.showMessageDialog(panel, "Please only enter a positive decimal number for weight.");
                  weight = -1;
               }
               
               if(isDouble(heiText.getText()) && Double.parseDouble(heiText.getText()) > 0) {
                  height = Double.parseDouble(heiText.getText());
               } else {
                  JOptionPane.showMessageDialog(panel, "Please only enter a positive decimal number for height.");
                  height = -1;
               }
               
               String password = new String(passwordText.getPassword());
            
               if(!password.matches("^[a-zA-Z0-9]{5,12}$")) { 
                  JOptionPane.showMessageDialog(panel, "Please make sure your password is 5-12 characters and numbers and letters only!");
               } else {            
                  if (LoginApp.authManager.addUser(username, password, name, gender, weight, age, height).equals("UserY")) {
                     JOptionPane.showMessageDialog(panel, "User created successfully!");
                     frame.dispose(); // Close the sign-up window
                  } else if(LoginApp.authManager.addUser(username, password, name, gender, weight, age, height).equals("UserN")) {
                     JOptionPane.showMessageDialog(panel, "Username already taken. Choose another.");
                  } else {
                     JOptionPane.showMessageDialog(panel, "Please re-eneter the incorrect values.");
                  }   
               }
            }
         });
         
      backButton.addActionListener(
         new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               frame.dispose(); // Close the current dashboard
            }
         });
   }

   private void centerFrame(JFrame frame) {
      Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
      int x = (dim.width - frame.getWidth()) / 2;
      int y = (dim.height - frame.getHeight()) / 2;
      frame.setLocation(x, y);
   }
   
   private static boolean isDouble(String str) {
      try {
         Double.parseDouble(str);
         return true;
      } catch (NumberFormatException e) {
         return false;
      }
   }
   
   private static boolean isInteger(String str) {
      try {
         Integer.parseInt(str);
         return true;
      } catch (NumberFormatException e) {
         return false;
      }
   }
}

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

//ChooseMeal class is for the user to be able 
//to choose which meal they want to add calories for.
class ChooseMeal {
    //ChooseMeal Attributes
   private JFrame frame;
   private Dashboard dashboard;
   private String meal;
    
    //ChooseMeal  Contrustor to initiate Page
   public ChooseMeal(Dashboard dashboard) {
      this.dashboard = dashboard;
      this.meal = "";
   
      frame = new JFrame("Choose Meal");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setSize(800, 600);
      centerFrame(frame);
   
      JPanel panel = new JPanel();
      frame.add(panel);
      placeComponents(panel);
   
      frame.setVisible(true);
   }
    
    //Build Choose Meal page
   private void placeComponents(JPanel panel) {
      panel.setLayout(null);
   
      ImageIcon logoIcon = new ImageIcon("v147_81.png");
      Image logoImage = logoIcon.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
      ImageIcon scaledLogoIcon = new ImageIcon(logoImage);
   
      JLabel logoLabel = new JLabel(scaledLogoIcon);
      logoLabel.setBounds(10, 10, 100, 100);
      panel.add(logoLabel);
        
      JLabel promptLabel = new JLabel("Choose Meal to enter calories:");
      promptLabel.setBounds(10, 110, 200, 25);
      panel.add(promptLabel);
   
      JButton BButton = new JButton("Breakfast");
      BButton.setBounds(10, 140, 100, 25);
      panel.add(BButton);
        
      JButton LButton = new JButton("Lunch");
      LButton.setBounds(115, 140, 80, 25);
      panel.add(LButton);
        
      JButton DButton = new JButton("Dinner");
      DButton.setBounds(200, 140, 80, 25);
      panel.add(DButton);
      
      JButton backButton = new JButton("Back");
      backButton.setBounds(10, 170, 80, 25);
      panel.add(backButton);
        
        //Assign action to buttons
      BButton.addActionListener(
         new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               meal = "Breakfast";
               frame.dispose(); // Close the current dashboard
               new CalorieEntryPage(dashboard, ChooseMeal.this, meal); // Open the calorie entry page
            }
         });
        
      LButton.addActionListener(
         new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               meal = "Lunch";
               frame.dispose(); // Close the current dashboard
               new CalorieEntryPage(dashboard, ChooseMeal.this, meal); // Open the calorie entry page
            }
         });
        
      DButton.addActionListener(
         new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               meal = "Dinner";
               frame.dispose(); // Close the current dashboard
               new CalorieEntryPage(dashboard, ChooseMeal.this, meal); // Open the calorie entry page
            }
         });
         
      backButton.addActionListener(
         new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               dashboard.working = false;
               dashboard.showFrame();
               frame.dispose(); // Close the current dashboard
            }
         });
   }

   public void showFrame() {
      frame.setVisible(true);
   }

   private void centerFrame(JFrame frame) {
      Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
      int x = (dim.width - frame.getWidth()) / 2;
      int y = (dim.height - frame.getHeight()) / 2;
      frame.setLocation(x, y);
   }
}

//CalorieEntryPage class to add calories
class CalorieEntryPage {
    //CalorieEntryPage Attributes
   private JFrame frame;
   private Dashboard dashboard;
   private ChooseMeal chooseMealWin;
   private String meal;
    
    //CEP Constructor to initiate page
   public CalorieEntryPage(Dashboard dashboard, ChooseMeal chooseMealWin, String meal) {
      this.dashboard = dashboard;
      this.chooseMealWin = chooseMealWin;
      this.meal = meal;
   
      frame = new JFrame("Calorie Entry");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setSize(800, 600);
      centerFrame(frame);
   
      JPanel panel = new JPanel();
      frame.add(panel);
      placeComponents(panel);
   
      frame.setVisible(true);
   }
    
    //build the CEP page
   private void placeComponents(JPanel panel) {
      panel.setLayout(null);
   
      ImageIcon logoIcon = new ImageIcon("v147_81.png");
      Image logoImage = logoIcon.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
      ImageIcon scaledLogoIcon = new ImageIcon(logoImage);
   
      JLabel logoLabel = new JLabel(scaledLogoIcon);
      logoLabel.setBounds(10, 10, 100, 100);
      panel.add(logoLabel);
        
        //Choose Label based on meal choice
      if(meal.equals("Breakfast")) {
         JLabel promptLabel = new JLabel("Enter Breakfast Calories:");
         promptLabel.setBounds(10, 110, 150, 25);
         panel.add(promptLabel);
      }
        
      if(meal.equals("Lunch")) {
         JLabel promptLabel = new JLabel("Enter Lunch Calories:");
         promptLabel.setBounds(10, 110, 150, 25);
         panel.add(promptLabel);
      }
        
      if(meal.equals("Dinner")) {
         JLabel promptLabel = new JLabel("Enter Dinner Calories:");
         promptLabel.setBounds(10, 110, 150, 25);
         panel.add(promptLabel);
      }
   
      JTextField caloriesField = new JTextField(20);
      caloriesField.setBounds(160, 110, 100, 25);
      panel.add(caloriesField);
   
      JButton saveButton = new JButton("Save");
      saveButton.setBounds(10, 140, 80, 25);
      panel.add(saveButton);
      
      JButton backButton = new JButton("Back");
      backButton.setBounds(110, 140, 80, 25);
      panel.add(backButton);
        
      //assign actions to a button
      saveButton.addActionListener(
         new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               if(isInteger(caloriesField.getText()) && Integer.parseInt(caloriesField.getText()) > 0) {
                  int enteredCalories = Integer.parseInt(caloriesField.getText());
                // Update the dashboard with the entered calories based on a meal
                  if(meal.equals("Breakfast")) {
                     dashboard.updateBCalories(enteredCalories);
                  }
                
                  if(meal.equals("Lunch")) {
                     dashboard.updateLCalories(enteredCalories);
                  }
                
                  if(meal.equals("Dinner")) {
                     dashboard.updateDCalories(enteredCalories);
                  }
                
                  frame.dispose(); // Close the calorie entry page
               
               } else {
                  JOptionPane.showMessageDialog(panel, "Please only enter a positive integer for calories.");
               }
            }
         });
         
      backButton.addActionListener(
         new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               chooseMealWin.showFrame();
               frame.dispose(); // Close the current dashboard
            }
         });
   }

   private void centerFrame(JFrame frame) {
      Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
      int x = (dim.width - frame.getWidth()) / 2;
      int y = (dim.height - frame.getHeight()) / 2;
      frame.setLocation(x, y);
   }
   
   private static boolean isInteger(String str) {
      try {
         Integer.parseInt(str);
         return true;
      } catch (NumberFormatException e) {
         return false;
      }
   }
}

//Water Entry Page to enter water intake
class WaterEntryPage {
   //WEP Attributes
   private JFrame frame;
   private Dashboard dashboard;
   
   //WEP Constructor initiates page
   public WaterEntryPage(Dashboard dashboard) {
      this.dashboard = dashboard;
   
      frame = new JFrame("Water Intake Entry");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setSize(800, 600);
      centerFrame(frame);
   
      JPanel panel = new JPanel();
      frame.add(panel);
      placeComponents(panel);
   
      frame.setVisible(true);
   }
   
   //build WEP page
   private void placeComponents(JPanel panel) {
      panel.setLayout(null);
   
      ImageIcon logoIcon = new ImageIcon("v147_81.png");
      Image logoImage = logoIcon.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
      ImageIcon scaledLogoIcon = new ImageIcon(logoImage);
   
      JLabel logoLabel = new JLabel(scaledLogoIcon);
      logoLabel.setBounds(10, 10, 100, 100);
      panel.add(logoLabel);
   
      JLabel promptLabel = new JLabel("Enter Water Intake (in ounces):");
      promptLabel.setBounds(10, 110, 200, 25);
      panel.add(promptLabel);
   
      JTextField waterField = new JTextField(20);
      waterField.setBounds(220, 110, 100, 25);
      panel.add(waterField);
   
      JButton saveButton = new JButton("Save");
      saveButton.setBounds(10, 140, 80, 25);
      panel.add(saveButton);
      
      JButton backButton = new JButton("Back");
      backButton.setBounds(110, 140, 80, 25);
      panel.add(backButton);
      
      //assign actions to button
      saveButton.addActionListener(
         new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               if(isInteger(waterField.getText()) && Integer.parseInt(waterField.getText()) > 0) {
                  int enteredWater = Integer.parseInt(waterField.getText());
                // Update the dashboard with the entered water intake
                  dashboard.updateWaterIntake(enteredWater);
                  frame.dispose(); // Close the water intake entry page
               } else {
                  JOptionPane.showMessageDialog(panel, "Please only enter a positive integer for ounces.");
               }
            }
         });
         
      backButton.addActionListener(
         new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               dashboard.working = false;
               dashboard.showFrame();
               frame.dispose(); // Close the current dashboard
            }
         });
   }

   private void centerFrame(JFrame frame) {
      Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
      int x = (dim.width - frame.getWidth()) / 2;
      int y = (dim.height - frame.getHeight()) / 2;
      frame.setLocation(x, y);
   }
   
   private static boolean isInteger(String str) {
      try {
         Integer.parseInt(str);
         return true;
      } catch (NumberFormatException e) {
         return false;
      }
   }
}

//Class to enter exercise calories
class EnterExCals {
    //Class atrributes
   private JFrame frame;
   private Dashboard dashboard;

    //class constructor to initiate page
   public EnterExCals(Dashboard dashboard) {
      this.dashboard = dashboard;
     
      frame = new JFrame("Exercise Calories Entry");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setSize(800, 600);
      centerFrame(frame);
     
      JPanel panel = new JPanel();
      frame.add(panel);
      placeComponents(panel);
     
      frame.setVisible(true);
   }
     
     //Build excals page
   private void placeComponents(JPanel panel) {
      panel.setLayout(null);
     
      ImageIcon logoIcon = new ImageIcon("v147_81.png");
      Image logoImage = logoIcon.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
      ImageIcon scaledLogoIcon = new ImageIcon(logoImage);
     
      JLabel logoLabel = new JLabel(scaledLogoIcon);
      logoLabel.setBounds(10, 10, 100, 100);
      panel.add(logoLabel);
     
      JLabel promptLabel = new JLabel("Enter Calories Burnt:");
      promptLabel.setBounds(10, 110, 200, 25);
      panel.add(promptLabel);
     
      JTextField exField = new JTextField(20);
      exField.setBounds(150, 110, 100, 25);
      panel.add(exField);
     
      JButton saveButton = new JButton("Save");
      saveButton.setBounds(10, 140, 80, 25);
      panel.add(saveButton);
        
      JButton backButton = new JButton("Back");
      backButton.setBounds(110, 140, 80, 25);
      panel.add(backButton);
        
        //assign actions to button
      saveButton.addActionListener(
           new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent e) {
                 if(isInteger(exField.getText()) && Integer.parseInt(exField.getText()) > 0) {
                    int enteredCals = Integer.parseInt(exField.getText());
                  // Update the dashboard with the entered calories
                    dashboard.updateEx(enteredCals);
                    frame.dispose(); // Close the exercise cals entry page
                 } else {
                    JOptionPane.showMessageDialog(panel, "Please only enter a positive integer for calories.");
                 }
              }
           });
           
      backButton.addActionListener(
           new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent e) {
                 dashboard.working = false;
                 dashboard.showFrame();
                 frame.dispose(); // Close the current dashboard
              }
           });
   }
     
   private void centerFrame(JFrame frame) {
      Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
      int x = (dim.width - frame.getWidth()) / 2;
      int y = (dim.height - frame.getHeight()) / 2;
      frame.setLocation(x, y);
   }
   
   private static boolean isInteger(String str) {
      try {
         Integer.parseInt(str);
         return true;
      } catch (NumberFormatException e) {
         return false;
      }
   }
}

//account page for the user to view and update account information
class AccountPage {
   //account page attributws
   private JFrame frame;
   private Dashboard dashboard;
   private JTextField realNameField;
   private JTextField heightField;
   private JTextField weightField;
   private JTextField ageField;
   
   //Accounts constructor initiates page.
   public AccountPage(Dashboard dashboard) {
      this.dashboard = dashboard;
   
      frame = new JFrame("Account Information");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setSize(800, 600);
      centerFrame(frame);
   
      JPanel panel = new JPanel();
      frame.add(panel);
      placeComponents(panel);
   
      frame.setVisible(true);
   }
   
   //build accounts page
   private void placeComponents(JPanel panel) {
      panel.setLayout(null);
   
      ImageIcon logoIcon = new ImageIcon("v147_81.png");
      Image logoImage = logoIcon.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
      ImageIcon scaledLogoIcon = new ImageIcon(logoImage);
   
      JLabel logoLabel = new JLabel(scaledLogoIcon);
      logoLabel.setBounds(10, 10, 100, 100);
      panel.add(logoLabel);
   
      JLabel realNameLabel = new JLabel("Full Name:");
      realNameLabel.setBounds(10, 120, 80, 25);
      panel.add(realNameLabel);
   
      realNameField = new JTextField(dashboard.getUser().getName());
      realNameField.setBounds(150, 120, 200, 25);
      panel.add(realNameField);
   
      JLabel heightLabel = new JLabel("Height (in inches):");
      heightLabel.setBounds(10, 150, 110, 25);
      panel.add(heightLabel);
   
      heightField = new JTextField(String.valueOf(dashboard.getUser().getHeight()));
      heightField.setBounds(150, 150, 200, 25);
      panel.add(heightField);
   
      JLabel weightLabel = new JLabel("Weight (in pounds):");
      weightLabel.setBounds(10, 180, 130, 25);
      panel.add(weightLabel);
   
      weightField = new JTextField(String.valueOf(dashboard.getUser().getWeight()));
      weightField.setBounds(150, 180, 200, 25);
      panel.add(weightField);
   
      JLabel ageLabel = new JLabel("Age:");
      ageLabel.setBounds(10, 210, 80, 25);
      panel.add(ageLabel);
   
      ageField = new JTextField(String.valueOf(dashboard.getUser().getAge()));
      ageField.setBounds(150, 210, 200, 25);
      panel.add(ageField);
   
      JLabel genderLabel = new JLabel("Gender:");
      genderLabel.setBounds(10, 240, 80, 25);
      panel.add(genderLabel);
   
      String[] genderOptions = {"Male", "Female"};
      JComboBox<String> genderComboBox = new JComboBox<String>(genderOptions);
      genderComboBox.setSelectedItem(dashboard.getUser().getGender());
      genderComboBox.setBounds(150, 240, 200, 25);
      panel.add(genderComboBox);
   
      JButton saveButton = new JButton("Save");
      saveButton.setBounds(10, 290, 80, 25);
      panel.add(saveButton);
      
      JButton backButton = new JButton("Back");
      backButton.setBounds(110, 290, 80, 25);
      panel.add(backButton);
      
      //assign button actions
      saveButton.addActionListener(
         new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Update user information and update the dashboard
               String realName = realNameField.getText();
               int age;
               double height;
               double weight;
               
               if(isInteger(ageField.getText()) && Integer.parseInt(ageField.getText()) > 0) {
                  age = Integer.parseInt(ageField.getText());
               } else {
                  JOptionPane.showMessageDialog(panel, "Please only enter a positive integer for age.");
                  age = -1;
               }
               
               if(isDouble(weightField.getText()) && Double.parseDouble(weightField.getText()) > 0) {
                  weight = Double.parseDouble(weightField.getText());
               } else {
                  JOptionPane.showMessageDialog(panel, "Please only enter a positive decimal number for weight.");
                  weight = -1;
               }
               
               if(isDouble(heightField.getText()) && Double.parseDouble(heightField.getText()) > 0) {
                  height = Double.parseDouble(heightField.getText());
               } else {
                  JOptionPane.showMessageDialog(panel, "Please only enter a positive decimal number for height.");
                  height = -1;
               }
            
               String gender = (String) genderComboBox.getSelectedItem();
            
               if (dashboard.getAuthManager().updateUser(dashboard.getUser().getUsername(), realName, height, weight, age, gender).equals("UserY")) {
                  dashboard.updateUserInfo();
                  frame.dispose(); // Close the account information page
               } else if(dashboard.getAuthManager().updateUser(dashboard.getUser().getUsername(), realName, height, weight, age, gender).equals("UserN")) {
                  JOptionPane.showMessageDialog(panel, "Failed to update user information.");
               } else {
                  JOptionPane.showMessageDialog(panel, "Please re-enter all incorrect values.");
               }
            }
         });
         
      backButton.addActionListener(
         new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               dashboard.working = false;
               dashboard.showFrame();
               frame.dispose(); // Close the current dashboard
            }
         });
   }

   private void centerFrame(JFrame frame) {
      Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
      int x = (dim.width - frame.getWidth()) / 2;
      int y = (dim.height - frame.getHeight()) / 2;
      frame.setLocation(x, y);
   }
   
   private static boolean isDouble(String str) {
      try {
         Double.parseDouble(str);
         return true;
      } catch (NumberFormatException e) {
         return false;
      }
   }
   
   private static boolean isInteger(String str) {
      try {
         Integer.parseInt(str);
         return true;
      } catch (NumberFormatException e) {
         return false;
      }
   }
}

//Class to enter a new date for data viewing/editing
class ChangeDate {
    //Class atrributes
   private JFrame frame;
   private Dashboard dashboard;

    //class constructor to initiate page
   public ChangeDate(Dashboard dashboard) {
      this.dashboard = dashboard;
     
      frame = new JFrame("Change Date");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setSize(800, 600);
      centerFrame(frame);
     
      JPanel panel = new JPanel();
      frame.add(panel);
      placeComponents(panel);
     
      frame.setVisible(true);
   }
     
     //Build change date page
   private void placeComponents(JPanel panel) {
      panel.setLayout(null);
     
      ImageIcon logoIcon = new ImageIcon("v147_81.png");
      Image logoImage = logoIcon.getImage().getScaledInstance(155, 100, Image.SCALE_DEFAULT);
      ImageIcon scaledLogoIcon = new ImageIcon(logoImage);
     
      JLabel logoLabel = new JLabel(scaledLogoIcon);
      logoLabel.setBounds(10, 10, 100, 100);
      panel.add(logoLabel);
     
      JLabel promptLabel = new JLabel("Enter Date [mm/dd/yyyy]: ");
      promptLabel.setBounds(10, 110, 150, 25);
      panel.add(promptLabel);
     
      JTextField dateField = new JTextField(20);
      dateField.setBounds(175, 110, 100, 25);
      panel.add(dateField);
      
      JLabel msgLabel = new JLabel("You can also type \"CD\" to return dashboard to current date.");
      msgLabel.setBounds(10, 140, 800, 25);
      panel.add(msgLabel);
     
      JButton saveButton = new JButton("Save");
      saveButton.setBounds(10, 170, 80, 25);
      panel.add(saveButton);
        
      JButton backButton = new JButton("Back");
      backButton.setBounds(110, 170, 80, 25);
      panel.add(backButton);
        
        //assign actions to button
      saveButton.addActionListener(
           new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent e) {
                 SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
                 Date currentDate = new Date();
                 if(isValidDate(dateField.getText())) {
                    try {
                       Date newDate = dateFormat.parse(dateField.getText());
                       if(currentDate.after(newDate) && !isCurrentDate(newDate)){
                          dashboard.showFrame();
                          dashboard.setDate(newDate);
                          dashboard.past = true;
                          dashboard.evalFwdButton();
                          dashboard.updateDailyData();
                          frame.dispose(); // Close the date change page
                       } else {
                          JOptionPane.showMessageDialog(panel, "You can only change to dates in the past.");
                       }
                    } catch (ParseException exception) {
                       JOptionPane.showMessageDialog(panel, "Please only enter correct dates in given format or CD.");
                    }
                 } else if (dateField.getText().toLowerCase().equals("cd")) {
                    Date nowDate = new Date();
                    dashboard.setDate(nowDate);
                    dashboard.past = false;
                    dashboard.evalFwdButton();
                    dashboard.updateDailyData();
                    frame.dispose(); // Close the date change page
                 } else {
                    JOptionPane.showMessageDialog(panel, "Please only enter correct dates in given format or CD.");
                 }
              }
           });
           
      backButton.addActionListener(
           new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent e) {
                 dashboard.working = false;
                 dashboard.showFrame();
                 frame.dispose(); // Close the current dashboard.
              }
           });
   }
   
   public static boolean isValidDate(String date) {
      String datePattern = "^(0[1-9]|1[0-2])/(0[1-9]|[12][0-9]|3[01])/\\d{4}$";
   
      if (!date.matches(datePattern)) {
         return false; // Invalid format
      }
      
      String[] parts = date.split("/");
   
        // Extract month, day, and year
      int month = Integer.parseInt(parts[0]);
      int day = Integer.parseInt(parts[1]);
      int year = Integer.parseInt(parts[2]);
   
        // Validate days in each month
      if (day > 0 && day <= getDaysInMonth(month, year)) {
         return true; // Valid date
      } else {
         return false; // Invalid day for the given month
      }
   }

   private static int getDaysInMonth(int month, int year) {
      switch (month) {
         case 4:
            case 6:
            case 9:
            case 11:
            return 30;
         case 2:
            return (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0)) ? 29 : 28;
         default:
            return 31;
      }
   }
   
   private boolean isCurrentDate(Date date) {
      SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
      if(sdf.format(date).equals(sdf.format(new Date()))) {
         return true;
      }
      return false;
   }
     
   private void centerFrame(JFrame frame) {
      Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
      int x = (dim.width - frame.getWidth()) / 2;
      int y = (dim.height - frame.getHeight()) / 2;
      frame.setLocation(x, y);
   }
}