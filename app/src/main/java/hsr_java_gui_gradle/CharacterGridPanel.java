package hsr_java_gui_gradle;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

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
                } else if (name.equals("Firefly")) {
                    App.switchContentPanel(new FireflyPanel());
                } else {
                    // Placeholder for characters not yet implemented
                    JPanel placeholder = new JPanel(new BorderLayout());
                    placeholder.setBackground(new Color(45, 45, 45));
                    JLabel label = new JLabel(name + " Page (Coming Soon)", JLabel.CENTER);
                    label.setFont(new Font("SansSerif", Font.BOLD, 24));
                    label.setForeground(Color.WHITE);
                    placeholder.add(label, BorderLayout.CENTER);
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
