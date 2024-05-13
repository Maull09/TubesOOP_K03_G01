/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package gui;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.event.ActionEvent;
/**
 *
 * @author USER
 */
public class MainMenu extends javax.swing.JFrame {
    private JFrame parentFrame;

    /**
     * Creates new form MainMenu1
     */
    
    public MainMenu(JFrame parentFrame) {
        this.parentFrame = parentFrame;
        initializeGUI();
    }

    private void initializeGUI() {
        // Set up the main window
        setTitle("Plants vs. Zombies");
        setSize(1280, 720);  // Set size according to your background image
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Set the background
        setContentPane(new JLabel(new ImageIcon(getClass().getResource("/resources/images/background/Menu.png"))));

        // Use null layout to manually position buttons
        setLayout(null);

        // Create and place buttons
        add(createButton("Start", 535, 435, "/resources/images/background/StartButton.png"));
        add(createButton("Plants", 400, 540, "/resources/images/background/PlantsButton.png"));
        add(createButton("Zombies", 650, 540, "/resources/images/background/ZombiesButton.png"));
        add(createButton("Help", 50, 33, "/resources/images/background/HelpButton.png"));
        add(createButton("Exit", 1050, 33, "/resources/images/background/ExitButton.png"));

        // Ensure the JFrame is visible
        setVisible(true);
    }

    private JButton createButton(String actionCommand, int x, int y, String iconPath) {
        ImageIcon icon = new ImageIcon(getClass().getResource(iconPath));
        JButton button = new JButton(icon);
        button.setActionCommand(actionCommand); 
        button.setBounds(x, y, icon.getIconWidth(), icon.getIconHeight());
        button.addActionListener(this::buttonAction);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        return button;
    }
    
    private void buttonAction(ActionEvent e) {
        String command = e.getActionCommand();  
        switch (command) {
            case "Start":
                System.out.println("Start Game");
                break;
            case "Plants":
                System.out.println("View Plants");
                break;
            case "Zombies":
                System.out.println("View Zombies");
                openzombielist();
                break;
            case "Help":
                System.out.println("Show Help");
                break;
            case "Exit":
                System.exit(0);
                break;
            default:
                System.out.println("Unknown command: " + command);
                break;
        }
    }

    private void openzombielist() {
        parentFrame.setContentPane(new ZombieList(parentFrame));
        parentFrame.revalidate();
        parentFrame.repaint();
    }
    
    public static void main(String[] args) {
        JFrame parentFrame = new JFrame();
        new MainMenu(parentFrame); // Create and display the main menu
    }             
}
