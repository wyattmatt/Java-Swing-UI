package hsr_java_gui_gradle;

import javax.swing.*;
import java.awt.*;

public class SidebarPanel extends JPanel {
    public SidebarPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(new Color(45, 45, 45));
        setPreferredSize(new Dimension(150, 0));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        String[] menuItems = {
            "Home", "Characters", "Tier List", "Memory of Chaos",
            "Light Cones", "Relics", "Guides", "Tools"
        };

        for (String item : menuItems) {
            JButton button = new JButton(item);
            button.setFocusPainted(false);
            button.setBorderPainted(false);
            button.setBackground(new Color(60, 60, 60));
            button.setForeground(Color.WHITE);
            button.setAlignmentX(Component.CENTER_ALIGNMENT);
            button.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
            button.setPreferredSize(new Dimension(140, 45)); 
            button.setMargin(new Insets(5, 10, 5, 10));
            add(button);
            add(Box.createRigidArea(new Dimension(0, 5)));
        }
    }
}
