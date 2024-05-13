package gui;

import javax.swing.*;
import java.awt.*;

public class ZombieList extends JPanel {
    private JLabel detailLabel; // Displays the detail in the large rectangle
    private JFrame parentFrame;

    public ZombieList(JFrame parentFrame) {
        this.parentFrame = parentFrame;
        initializeUI();
        setPreferredSize(new Dimension(1280, 720)); // Preferred size for the panel
        parentFrame.add(this); // Adds this panel to the parent frame
        parentFrame.pack();
        parentFrame.setVisible(true);
    }
    
    private void initializeUI() {
        this.setLayout(new BorderLayout(5, 5));

        // Top bar with image
        ImageIcon topBarIcon = new ImageIcon(getClass().getResource("/resources/images/description/headerzombie.png")); // Adjust path
        JLabel topBarLabel = new JLabel(topBarIcon);
        this.add(topBarLabel, BorderLayout.NORTH);

        // Grid of clickable items
        JPanel gridPanel = new JPanel(new GridLayout(3, 3, 5, 5)); // Adjust grid layout as needed
        for (int i = 0; i < 10; i++) { // Adjust to 9 if that's your intent (previously 10 causing out of grid)
            final int index = i;
            JButton button = new JButton(new ImageIcon(getClass().getResource("/resources/images/description/zombie" + i + ".png")));
            button.addActionListener(e -> updateDetailDisplay("/resources/images/description/desczombie" + index + ".png"));
            gridPanel.add(button);
        }

        // Large rectangle for details
        detailLabel = new JLabel("", JLabel.CENTER);

        // Adding components to the layout
        this.add(gridPanel, BorderLayout.WEST);
        this.add(detailLabel, BorderLayout.CENTER);
    }

    private void updateDetailDisplay(String detailText) {
        detailLabel.setIcon(new ImageIcon(getClass().getResource(detailText)));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Zombie List");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            frame.getContentPane().add(new ZombieList(frame));
            frame.pack();
            frame.setVisible(true);
        });
    }
}
