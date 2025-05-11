package hsr_java_gui_gradle;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.swing.Box;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class SidebarPanel extends JPanel {

    private final Map<String, SidebarButton> buttons = new LinkedHashMap<>();
    private String selected = null;
    private NavigationListener navigationListener;

    public SidebarPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(new Color(45, 45, 45));
        setPreferredSize(new Dimension(150, 0));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        SidebarButton[] buttonInstances = {
            new HomeButton(),
            new CharactersButton(),
            new TierListButton(),
            new MemoryofChaosButton(),
            new LightConesButton(),
            new RelicsButton(),
            new GuidesButton(),
            new ToolsButton()
        };

        for (SidebarButton button : buttonInstances) {
            String name = button.getText();

            button.addActionListener(e -> {
                if (!name.equals(selected) || "Characters".equals(name)) {
                    setSelected(name);
                    if ("Characters".equals(name)) {
                        App.switchContentPanel(new CharacterGridPanel());
                    }
                    if (navigationListener != null) {
                        navigationListener.onNavigate(name);
                    }
                }
            });

            // Hover effect
            button.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    if (!name.equals(selected)) {
                        button.setForeground(new Color(255, 255, 255, 255));
                    }
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    if (!name.equals(selected)) {
                        button.setForeground(Color.LIGHT_GRAY);
                    }
                }
            });

            buttons.put(name, button);
            add(button);
            add(Box.createRigidArea(new Dimension(0, 5)));
        }

        add(Box.createVerticalGlue());
        setSelected("Home");
    }

    public void setSelected(String name) {
        if (selected != null && buttons.containsKey(selected)) {
            buttons.get(selected).setBackground(new Color(60, 60, 60));
            buttons.get(selected).setForeground(Color.LIGHT_GRAY);
            buttons.get(selected).setFont(new Font("SansSerif", Font.PLAIN, 14));
        }

        selected = name;

        if (buttons.containsKey(name)) {
            buttons.get(name).setBackground(new Color(80, 90, 120));
            buttons.get(name).setForeground(new Color(255, 255, 255, 255));
            buttons.get(name).setFont(new Font("SansSerif", Font.BOLD, 14));
        }
    }

    public void setNavigationListener(NavigationListener listener) {
        this.navigationListener = listener;
    }

    public interface NavigationListener {
        void onNavigate(String name);
    }
}
