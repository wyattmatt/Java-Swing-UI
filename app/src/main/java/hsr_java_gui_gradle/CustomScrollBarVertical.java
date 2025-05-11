package hsr_java_gui_gradle;

import java.awt.*;
import javax.swing.*;

public class CustomScrollBarVertical extends AbstractCustomScrollBarUI {

    @Override
    public Dimension getPreferredSize(JComponent c) {
        return new Dimension(12, Integer.MAX_VALUE);
    }

    @Override
    protected Dimension getMinimumThumbSize() {
        return new Dimension(8, 40);
    }

    @Override
    protected JButton createDecreaseButton(int orientation) {
        return createArrowButton(JScrollBar.VERTICAL, true);
    }

    @Override
    protected JButton createIncreaseButton(int orientation) {
        return createArrowButton(JScrollBar.VERTICAL, false);
    }
}
