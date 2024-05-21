package gui;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;

public class LoseScreen extends JPanel {
    private Image backgroundImage;
    private GameGUI gameGUI;

    public LoseScreen(GameGUI gameGUI) {
        this.gameGUI = gameGUI;
        initializeGUI();
        try {
            backgroundImage = new ImageIcon(getClass().getResource("/resources/images/background/39.png")).getImage();
        } catch (Exception e) {
            e.printStackTrace(); // Handle potential errors gracefully
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this); // Draw the background image to fit the panel size
        }
    }

    private void initializeGUI() {
        setLayout(null); // Use null layout to manually position buttons

        // Create and place buttons
        add(createButton("MainMenu", 535, 535, "/resources/images/background/mainMenu.png"));

        revalidate();
        repaint();
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
            case "MainMenu":
                gameGUI.showMainMenu(); 
                System.out.println("Back to Main Menu");
                break;
            default:
                System.out.println("Unknown command: " + command);
                break;
        }
    }
    public static void main(String[] args) {
        JFrame frame = new JFrame("Michael vs. Lalapan");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1280, 720);
        frame.setLocationRelativeTo(null);

        GameGUI gameGUI = new GameGUI();
        LoseScreen menu = new LoseScreen(gameGUI);
        frame.setContentPane(menu);
        frame.setVisible(true);
    }
}
