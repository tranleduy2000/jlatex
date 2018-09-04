package android.awt;

import android.awt.font.FontRenderContext;
import android.awt.geom.Line2D;
import android.awt.geom.Rectangle2D;

import android.awt.geom.AffineTransform;
import android.awt.geom.RoundRectangle2D;

public interface Graphics2D extends Graphics {

    Color getColor();

    void setColor(Color background);

    void fill(Rectangle2D.Float aFloat);

    Stroke getStroke();

    AffineTransform getTransform();

    void draw(Rectangle2D.Float aFloat);

    void setStroke(Stroke st);

    void translate(double x, double y);

    void scale(double v, double v1);

    Font getFont();

    void setFont(Font font);

    void drawChars(char[] arr, int i, int i1, int i2, int i3);

    void setTransform(AffineTransform at);

    void draw(Line2D.Float line);

    void rotate(double v, double v1, double v2);

    void drawArc(int i, int i1, int i2, int i3, int i4, int i5);

    void fillArc(int i, int i1, int i2, int i3, int i4, int i5);

    void draw(RoundRectangle2D.Float aFloat);

    void rotate(double v);

    FontRenderContext getFontRenderContext();

    void fillRect(int i, int i1, int w, int h);

    void dispose();

    RenderingHints getRenderingHints();

    void setRenderingHint(RenderingHints.Key keyAntialiasing, Object valueAntialiasOn);

    void setRenderingHints(RenderingHints oldHints);
}
