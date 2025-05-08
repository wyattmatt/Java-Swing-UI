package hsr_java_gui_gradle;

import javax.swing.*;
import java.awt.*;

public class CharacterCard extends JPanel {
    public CharacterCard(String name, String imageUrl) {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(150, 200));

        ImageIcon imageIcon = new ImageIcon(new ImageIcon(imageUrl).getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH));
        JLabel imageLabel = new JLabel(imageIcon);
        JLabel nameLabel = new JLabel(name, SwingConstants.CENTER);
        nameLabel.setFont(new Font("SansSerif", Font.BOLD, 14));

        add(imageLabel, BorderLayout.CENTER);
        add(nameLabel, BorderLayout.SOUTH);
    }
}
