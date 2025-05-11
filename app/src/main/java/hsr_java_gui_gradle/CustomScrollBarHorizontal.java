package hsr_java_gui_gradle;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JScrollBar;
import java.awt.Dimension;

public class CustomScrollBarHorizontal extends AbstractCustomScrollBarUI {

    @Override
    public Dimension getPreferredSize(JComponent c) {
        return new Dimension(Integer.MAX_VALUE, 12);
    }

    @Override
    protected Dimension getMinimumThumbSize() {
        return new Dimension(40, 8);
    }

    @Override
    protected JButton createDecreaseButton(int orientation) {
        return createArrowButton(JScrollBar.HORIZONTAL, true);
    }

    @Override
    protected JButton createIncreaseButton(int orientation) {
        return createArrowButton(JScrollBar.HORIZONTAL, false);
    }
}
