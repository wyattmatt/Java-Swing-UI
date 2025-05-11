package hsr_java_gui_gradle;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class NavbarPanel extends JPanel {
    public NavbarPanel() {
        setLayout(new BorderLayout());
        setBackground(new Color(0x232323));
        setPreferredSize(new Dimension(0, 60));

        JLabel title = new JLabel("Honkai: Star Rail Characters List");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("SansSerif", Font.BOLD, 20));
        title.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        title.setVerticalAlignment(SwingConstants.CENTER);

        JPanel rightButtons = new JPanel();
        rightButtons.setOpaque(false);
        rightButtons.setLayout(new BoxLayout(rightButtons, BoxLayout.X_AXIS));
        rightButtons.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 20));

        JButton discordButton = createButton("Join Discord", new Color(61, 90, 201));
        JButton kofiButton = createButton("Buy us a Ko-Fi", new Color(227, 60, 90));

        rightButtons.add(discordButton);
        rightButtons.add(Box.createRigidArea(new Dimension(10, 0)));
        rightButtons.add(kofiButton);

        add(title, BorderLayout.WEST);
        add(rightButtons, BorderLayout.EAST);
    }

    private JButton createButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setAlignmentY(Component.CENTER_ALIGNMENT);
        return button;
    }
}
