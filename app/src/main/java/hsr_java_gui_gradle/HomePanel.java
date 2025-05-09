package hsr_java_gui_gradle;

import javax.swing.*;
import java.awt.*;

public class HomePanel extends JPanel {
    public HomePanel() {
        setLayout(new BorderLayout());
        setBackground(new Color(245, 245, 245));
        JLabel label = new JLabel("Welcome to Honkai: Star Rail UI!");
        label.setFont(new Font("SansSerif", Font.BOLD, 24));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        add(label, BorderLayout.CENTER);
    }
}
