import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
      Image logoImage = logoIcon.getImage().getScaledInstance(155, 100, Image.SCALE_DEFAULT);
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
