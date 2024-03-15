import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
      Image logoImage = logoIcon.getImage().getScaledInstance(155, 100, Image.SCALE_DEFAULT);
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
