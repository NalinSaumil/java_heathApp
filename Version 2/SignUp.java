import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

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
