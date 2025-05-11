package hsr_java_gui_gradle;

import java.awt.*;
import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;

public class CustomScrollBarUI extends BasicScrollBarUI {

    private static final int ARROW_SIZE = 8;
    private static final int ARROW_SPACING = 3;
    private static final int THUMB_MARGIN = 3;

    @Override
    protected void configureScrollBarColors() {
        thumbColor = new Color(100, 100, 100, 210);
        trackColor = new Color(0, 0, 0, 0);
    }

    @Override
    protected Dimension getMinimumThumbSize() {
        return new Dimension(8, 40);
    }

    @Override
    public Dimension getPreferredSize(JComponent c) {
        JScrollBar sb = (JScrollBar) c;
        return sb.getOrientation() == JScrollBar.VERTICAL
            ? new Dimension(12, Integer.MAX_VALUE)
            : new Dimension(Integer.MAX_VALUE, 12);
    }

    @Override
    protected JButton createDecreaseButton(int orientation) {
        return createArrowButton(orientation, true);
    }

    @Override
    protected JButton createIncreaseButton(int orientation) {
        return createArrowButton(orientation, false);
    }

    private JButton createArrowButton(int orientation, boolean isDecrease) {
        final int finalOrientation = orientation; // <- keep orientation safe inside the anonymous class
        return new JButton() {
            {
                setOpaque(false);
                setContentAreaFilled(false);
                setBorderPainted(false);
                setFocusPainted(false);
                setFocusable(false);
            }

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                int w = getWidth();
                int h = getHeight();
                int s = ARROW_SIZE;

                g2.setColor(new Color(100, 100, 100));
                Polygon arrow = new Polygon();

                if (finalOrientation == JScrollBar.VERTICAL) {
                    if (isDecrease) {
                        // ↑
                        arrow.addPoint(w / 2, ARROW_SPACING);
                        arrow.addPoint(w / 2 - s / 2, ARROW_SPACING + s);
                        arrow.addPoint(w / 2 + s / 2, ARROW_SPACING + s);
                    } else {
                        // ↓
                        arrow.addPoint(w / 2 - s / 2, h - ARROW_SPACING - s);
                        arrow.addPoint(w / 2 + s / 2, h - ARROW_SPACING - s);
                        arrow.addPoint(w / 2, h - ARROW_SPACING);
                    }
                } else {
                    if (isDecrease) {
                        // ←
                        arrow.addPoint(ARROW_SPACING, h / 2);
                        arrow.addPoint(ARROW_SPACING + s, h / 2 - s / 2);
                        arrow.addPoint(ARROW_SPACING + s, h / 2 + s / 2);
                    } else {
                        // →
                        arrow.addPoint(w - ARROW_SPACING, h / 2);
                        arrow.addPoint(w - ARROW_SPACING - s, h / 2 - s / 2);
                        arrow.addPoint(w - ARROW_SPACING - s, h / 2 + s / 2);
                    }
                }

                g2.fill(arrow);
                g2.dispose();
            }

            @Override
            public Dimension getPreferredSize() {
                return (finalOrientation == JScrollBar.VERTICAL)
                    ? new Dimension(12, ARROW_SIZE + ARROW_SPACING + 2)
                    : new Dimension(ARROW_SIZE + ARROW_SPACING + 2, 12);
            }
        };
    }

    @Override
    protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setColor(trackColor);
        g2.fillRect(trackBounds.x, trackBounds.y, trackBounds.width, trackBounds.height);
        g2.dispose();
    }

    @Override
    protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int x = thumbBounds.x + THUMB_MARGIN;
        int y = thumbBounds.y + THUMB_MARGIN;
        int width = thumbBounds.width - 2 * THUMB_MARGIN;
        int height = thumbBounds.height - 2 * THUMB_MARGIN;

        if (scrollbar.getOrientation() == JScrollBar.VERTICAL) {
            height = Math.max(height, getMinimumThumbSize().height);
        } else {
            width = Math.max(width, getMinimumThumbSize().width);
        }

        g2.setColor(thumbColor);
        g2.fillRoundRect(x, y, width, height, 10, 10);
        g2.dispose();
    }
}
