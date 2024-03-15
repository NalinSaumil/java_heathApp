import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

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