package android.awt;

import java.io.InputStream;

public class Font {

    public static final int PLAIN = 0;

    public static final int BOLD = 1;

    public static final int ITALIC = 2;

    public static final int TRUETYPE_FONT = 0;

    public static Font createFont(int truetypeFont, InputStream fontIn) {
        return null;
    }

    public Font(String name, int style, int size) {

    }

    public Font deriveFont(float v) {
        return null;
    }

    public String getFontName() {
        return null;
    }
}
