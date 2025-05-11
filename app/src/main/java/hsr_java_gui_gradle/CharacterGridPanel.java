package hsr_java_gui_gradle;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

public class CharacterGridPanel extends JScrollPane {
    public static String[] characterImages = {
        "Acheron", "Aglaea", "Anaxa", "Archer",
        "Argenti", "Asta", "Aventurine", "Bailu",
        "Black Swan", "Blade", "Bronya", "Clara",
        "Jade", "Castorice", "Firefly"
    };

    public CharacterGridPanel() {
        JPanel gridPanel = new JPanel(new GridLayout(0, 4, 10, 10));
        gridPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        gridPanel.setBackground(new Color(0x404040));

        String[] characterNames = {
            "Acheron", "Aglaea", "Anaxa", "Archer",
            "Argenti", "Asta", "Aventurine", "Bailu",
            "Black Swan", "Blade", "Bronya", "Clara",
            "Jade", "Castorice", "Firefly"
        };

        for (String characterName : characterNames) {
            String name = characterName;
            CharacterCard card = new CharacterCard(name, () -> {
                if (name.equals("Acheron")) {
                    App.switchContentPanel(new AcheronPanel());
                } else if (name.equals("Aglaea")) {
                    App.switchContentPanel(new AglaeaPanel());
                } else if (name.equals("Anaxa")) {
                    App.switchContentPanel(new AnaxaPanel());
                } else if (name.equals("Archer")) {
                    App.switchContentPanel(new ArcherPanel());
                } else if (name.equals("Argenti")) {
                    App.switchContentPanel(new ArgentiPanel());
                } else if (name.equals("Asta")) {
                    App.switchContentPanel(new AstaPanel());
                } else if (name.equals("Aventurine")) {
                    App.switchContentPanel(new AventurinePanel());
                } else if (name.equals("Bailu")) {
                    App.switchContentPanel(new BailuPanel());
                } else if (name.equals("Black Swan")) {
                    App.switchContentPanel(new BlackSwanPanel());
                } else if (name.equals("Blade")) {
                    App.switchContentPanel(new BladePanel());
                } else if (name.equals("Bronya")) {
                    App.switchContentPanel(new BronyaPanel());
                } else if (name.equals("Clara")) {
                    App.switchContentPanel(new ClaraPanel());
                } else if (name.equals("Jade")) {
                    App.switchContentPanel(new JadePanel());
                } else if (name.equals("Castorice")) {
                    App.switchContentPanel(new CastoricePanel());
                // } else if (name.equals("Firefly")) {
                //     App.switchContentPanel(new FireflyPanel());
                } else {
                    // Placeholder for characters not yet implemented
                    JPanel placeholder = new JPanel(new BorderLayout());
                    placeholder.setBackground(new Color(0x212121));

                    // Large header text: "Coming Soon"
                    JLabel errorTitle = new JLabel(name + " Page - Coming Soon");
                    errorTitle.setFont(new Font("SansSerif", Font.BOLD, 36));
                    errorTitle.setForeground(new Color(0xD3D3D3));
                    errorTitle.setHorizontalAlignment(SwingConstants.CENTER);

                    // Sub message
                    JLabel subtitleError = new JLabel("<html><div style='text-align: center;'>This feature is under construction.<br>Please check back later or explore other sections.</div></html>");
                    subtitleError.setFont(new Font("SansSerif", Font.PLAIN, 18));
                    subtitleError.setForeground(new Color(0xA9A9A9));
                    subtitleError.setHorizontalAlignment(SwingConstants.CENTER);

                    // Organize text in vertical layout
                    JPanel textPanel = new JPanel();
                    textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
                    textPanel.setOpaque(false);

                    textPanel.add(Box.createVerticalGlue());
                    errorTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
                    subtitleError.setAlignmentX(Component.CENTER_ALIGNMENT);
                    textPanel.add(errorTitle);
                    textPanel.add(Box.createRigidArea(new Dimension(0, 20)));
                    textPanel.add(subtitleError);
                    textPanel.add(Box.createVerticalGlue());

                    placeholder.add(textPanel, BorderLayout.CENTER);
                    App.switchContentPanel(placeholder);
                }
            });
            gridPanel.add(card);
        }

        setViewportView(gridPanel);
        getVerticalScrollBar().setUnitIncrement(8);
        getVerticalScrollBar().setUI(new CustomScrollBarVertical());
        getHorizontalScrollBar().setUI(new CustomScrollBarHorizontal());
        getViewport().setOpaque(false);
        setOpaque(false);
        setBorder(null);
    }
}
