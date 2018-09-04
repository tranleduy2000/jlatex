package androidx.swing;

import android.awt.Graphics;
import android.awt.Component;


public interface Icon {
    void paintIcon(Component c, Graphics g, int x, int y);

    int getIconWidth();

    int getIconHeight();
}

