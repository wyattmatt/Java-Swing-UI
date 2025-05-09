package hsr_java_gui_gradle;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedHashMap;
import java.util.Map;

public class SidebarPanel extends JPanel {

    private final Map<String, JButton> buttons = new LinkedHashMap<>();
    private String selected = null;
    private NavigationListener navigationListener;

    public SidebarPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(new Color(45, 45, 45));
        setPreferredSize(new Dimension(150, 0));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        String[] items = {"Home", "Characters", "Tier List", "Memory of Chaos", "Light Cones", "Relics", "Guides", "Tools"};

        for (String name : items) {
            JButton button = new JButton(name);
            button.setFocusPainted(false);
            button.setBorderPainted(false);
            button.setContentAreaFilled(true);
            button.setBackground(new Color(60, 60, 60));
            button.setForeground(Color.WHITE);
            button.setFont(new Font("SansSerif", Font.PLAIN, 14));
            button.setAlignmentX(Component.CENTER_ALIGNMENT);

            // ✅ Consistent sizing
            button.setPreferredSize(new Dimension(140, 35));
            button.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
            button.setMinimumSize(new Dimension(140, 35));
            button.setMargin(new Insets(5, 10, 5, 10));

            // ✅ On click: highlight + navigate
            button.addActionListener(e -> {
                if (!name.equals(selected)) {
                    setSelected(name);
                    if (navigationListener != null) {
                        navigationListener.onNavigate(name);
                    }
                }
            });

            buttons.put(name, button);
            add(button);
            add(Box.createRigidArea(new Dimension(0, 5))); // spacing
        }

        add(Box.createVerticalGlue()); // push content to top
        setSelected("Home"); // default selected
    }

    public void setSelected(String name) {
        if (selected != null && buttons.containsKey(selected)) {
            buttons.get(selected).setBackground(new Color(60, 60, 60)); // unselected
            buttons.get(selected).setFont(new Font("SansSerif", Font.PLAIN, 14));
        }

        selected = name;

        if (buttons.containsKey(name)) {
            buttons.get(name).setBackground(new Color(70, 130, 180)); // blue highlight
            buttons.get(name).setFont(new Font("SansSerif", Font.BOLD, 14));
        }
    }

    public void setNavigationListener(NavigationListener listener) {
        this.navigationListener = listener;
    }

    // Simple interface to notify App
    public interface NavigationListener {
        void onNavigate(String name);
    }
}
