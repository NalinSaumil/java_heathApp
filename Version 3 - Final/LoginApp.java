import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
      Image logoImage = logoIcon.getImage().getScaledInstance(155, 100, Image.SCALE_DEFAULT);
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
