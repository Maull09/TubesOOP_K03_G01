package gui;

import javax.swing.*;
import java.awt.*;

public class PlantList extends JPanel {
    private JLabel detailLabel; // Displays the detail in the large rectangle
    private JFrame parentFrame;

    public PlantList(JFrame parentFrame) {
        this.parentFrame = parentFrame;
        initializeUI();
        setPreferredSize(new Dimension(600, 400)); // Preferred size for the panel
        parentFrame.add(this); // Adds this panel to the parent frame
        parentFrame.pack();
        parentFrame.setVisible(true);
    }

    private void initializeUI() {
        this.setLayout(new BorderLayout(5, 5));

        // Top bar with image
        ImageIcon topBarIcon = new ImageIcon(getClass().getResource("/resources/images/background/StartButton.png")); // Adjust path
        JLabel topBarLabel = new JLabel(topBarIcon);
        this.add(topBarLabel, BorderLayout.NORTH);

        // Grid of clickable items
        JPanel gridPanel = new JPanel(new GridLayout(3, 3, 5, 5)); // Adjust grid layout as needed
        for (int i = 0; i < 10; i++) { // Adjust to 9 if that's your intent (previously 10 causing out of grid)
            final int index = i;
            JButton button = new JButton("Plant " + (i + 1));
            button.addActionListener(e -> updateDetailDisplay("Detail for Plant " + (index + 1)));
            gridPanel.add(button);
        }

        // Large rectangle for details
        detailLabel = new JLabel("Click a Plant for details", JLabel.CENTER);
        detailLabel.setBackground(Color.GREEN);
        detailLabel.setOpaque(true);
        detailLabel.setPreferredSize(new Dimension(200, 200));

        // Adding components to the layout
        this.add(gridPanel, BorderLayout.WEST);
        this.add(detailLabel, BorderLayout.CENTER);
    }

    private void updateDetailDisplay(String detailText) {
        detailLabel.setText(detailText);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Plant List");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            frame.getContentPane().add(new PlantList(frame));
            frame.pack();
            frame.setVisible(true);
        });
    }
}
