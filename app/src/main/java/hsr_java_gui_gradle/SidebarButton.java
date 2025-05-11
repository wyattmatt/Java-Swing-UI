package hsr_java_gui_gradle;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import javax.swing.JButton;

public abstract class SidebarButton extends JButton {
    public SidebarButton(String text) {
        super(text);
        setFocusPainted(false);
        setBorderPainted(false);
        setContentAreaFilled(true);
        setBackground(new Color(60, 60, 60));
        setForeground(Color.WHITE);
        setFont(new Font("SansSerif", Font.PLAIN, 14));
        setAlignmentX(Component.CENTER_ALIGNMENT);
        setPreferredSize(new Dimension(140, 35));
        setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        setMinimumSize(new Dimension(140, 35));
        setMargin(new Insets(5, 10, 5, 10));
    }
}
