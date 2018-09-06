package ru.noties.jlatexmath.awt;

import android.annotation.SuppressLint;
import android.graphics.Typeface;
import android.support.annotation.NonNull;

import java.io.InputStream;

public class Font {

    public static final int PLAIN = 0;

    public static final int BOLD = 1;

    public static final int ITALIC = 2;

    public static final int TRUETYPE_FONT = 0;

    @Deprecated
    public static Font createFont(int truetypeFont, InputStream fontIn) {
        return null;
    }

    @NonNull
    public static Font createFont(@NonNull Typeface typeface, float size) {
        return new Font(typeface, 0, size);
    }

//    private final String name;
    private final Typeface typeface;
    private int style;
    private float size;

    public Font(String name, int style, int size) {
        throw new RuntimeException("TODO");
    }

//    @NonNull
//    private static Typeface fromName(@NonNull String name) {
//        switch (name) {
//
//        }
//    }

    private Font(@NonNull Typeface typeface, int style, float size) {
        this.typeface = applyStyle(typeface, style);
        this.style = style;
        this.size = size;
    }

    @NonNull
    private static Typeface applyStyle(@NonNull Typeface typeface, int style) {

        // todo: I have a feeling that BOLD_ITALIC flag might be needed here to be used

        final int current = (typeface.isBold() ? BOLD : 0) | (typeface.isItalic() ? ITALIC : 0);
        if (current != style) {
            // both will be 3 (BOLD_ITALIC)
            @SuppressLint("WrongConstant")
            final int out = ((style & BOLD) != 0 ? BOLD : 0) | ((style & ITALIC) != 0 ? ITALIC : 0);
            typeface = Typeface.create(typeface, out);
        }

        return typeface;
    }

    public Font deriveFont(float size) {
        return new Font(typeface, style, (int) size);
    }

    public Typeface typeface() {
        return typeface;
    }

    public int style() {
        return style;
    }

    public float size() {
        return size;
    }

    public boolean isBold() {
        return (style & BOLD) != 0;
    }

    public boolean isItalic() {
        return (style & ITALIC) != 0;
    }
}
