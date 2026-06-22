package GUI;

import javax.swing.border.AbstractBorder;
import java.awt.*;

public class RoundBorder extends AbstractBorder {
    private int radius;
    private int thickness;
    private Color color;

    public RoundBorder(int radius) {
        this(radius, 3, null); // default thickness 3, color will use button's foreground
    }

    public RoundBorder(int radius, int thickness, Color color) {
        this.radius = radius;
        this.thickness = thickness;
        this.color = color;
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Use specified color or button's foreground color
        if (color != null) {
            g2.setColor(color);
        } else {
            g2.setColor(c.getForeground());
        }

        g2.setStroke(new BasicStroke(thickness));
        g2.drawRoundRect(x + thickness/2, y + thickness/2,
                width - thickness, height - thickness,
                radius, radius);
    }
}