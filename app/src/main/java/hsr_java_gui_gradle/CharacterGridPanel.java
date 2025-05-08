package hsr_java_gui_gradle;

import javax.swing.*;
import java.awt.*;

public class CharacterGridPanel extends JScrollPane {
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

        String[] characterImages = {
            "./app/src/main/resources/images/acheron.png",
            "./app/src/main/resources/images/aglaea.png",
            "./app/src/main/resources/images/anaxa.png",
            "./app/src/main/resources/images/archer.png",
            "./app/src/main/resources/images/argenti.png",
            "./app/src/main/resources/images/asta.png",
            "./app/src/main/resources/images/aventurine.png",
            "./app/src/main/resources/images/bailu.png",
            "./app/src/main/resources/images/blackswan.png",
            "./app/src/main/resources/images/blade.png",
            "./app/src/main/resources/images/bronya.png",
            "./app/src/main/resources/images/clara.png",
            "./app/src/main/resources/images/jade.png",
            "./app/src/main/resources/images/castorice.png",
            "./app/src/main/resources/images/firefly.png",
        };

        for (int i = 1; i <= 15; i++) {
            CharacterCard card = new CharacterCard(characterNames[i - 1], characterImages[i - 1]);
            gridPanel.add(card);
        }

        setViewportView(gridPanel);
        getVerticalScrollBar().setUnitIncrement(16);
        setBorder(null);
    }
}
