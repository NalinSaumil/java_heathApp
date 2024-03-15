import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
     
        JTextField waterField = new JTextField(20);
        waterField.setBounds(150, 110, 100, 25);
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
                 int enteredCals = Integer.parseInt(waterField.getText());
                  // Update the dashboard with the entered calories
                 dashboard.updateEx(enteredCals);
                 frame.dispose(); // Close the water intake entry page
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
}
