package ru.noties.jlatexmath;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PixelFormat;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;

import androidx.annotation.ColorInt;
import androidx.annotation.IntDef;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.Px;

import org.scilab.forge.jlatexmath.TeXConstants;
import org.scilab.forge.jlatexmath.TeXFormula;
import org.scilab.forge.jlatexmath.TeXIcon;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import ru.noties.jlatexmath.awt.AndroidGraphics2D;
import ru.noties.jlatexmath.awt.Color;
import ru.noties.jlatexmath.awt.Insets;

public class JLatexMathDrawable extends Drawable {

    public static final int ALIGN_LEFT = 0;
    public static final int ALIGN_CENTER = 1;
    public static final int ALIGN_RIGHT = 2;

    @IntDef({ALIGN_LEFT, ALIGN_CENTER, ALIGN_RIGHT})
    @Retention(RetentionPolicy.CLASS)
    public @interface Align {
    }

    @NonNull
    public static Builder builder(@NonNull String latex) {
        return new Builder(latex);
    }

    private final TeXIcon icon;
    private final int align;
    private final Drawable background;
    private final boolean fitCanvas;

    private final AndroidGraphics2D graphics2D;

    private final int iconWidth;
    private final int iconHeight;

    JLatexMathDrawable(@NonNull Builder builder) {

        this.icon = new TeXFormula(builder.latex)
                .new TeXIconBuilder()
                .setFGColor(new Color(builder.color))
                .setSize(builder.textSize)
                .setStyle(TeXConstants.STYLE_DISPLAY)
                .build();

        if (builder.insets != null) {
            this.icon.setInsets(builder.insets);
        }

        this.align = builder.align;
        this.background = builder.background;
        this.fitCanvas = builder.fitCanvas;

        this.graphics2D = new AndroidGraphics2D();

        this.iconWidth = icon.getIconWidth();
        this.iconHeight = icon.getIconHeight();

        setBounds(0, 0, iconWidth, iconHeight);

        if (background != null) {
            background.setBounds(0, 0, iconWidth, iconHeight);
        }
    }

    @Override
    public void draw(@NonNull Canvas canvas) {

        final int save = canvas.save();
        try {

            if (fitCanvas) {

                // check if we need scaling by checking original bounds against this instance bounds
                final int w = canvas.getWidth();

                // scale down
                if (iconWidth > w) {
                    final float ratio = (float) w / iconWidth;
                    setBounds(0, 0, w, (int) (iconHeight * ratio + .5F));
                    canvas.scale(ratio, ratio);
                } else {
                    // align
                    if (align != ALIGN_LEFT) {
                        final float left;
                        if (ALIGN_CENTER == align) {
                            left = (w - iconWidth) / 2;
                        } else if (ALIGN_RIGHT == align) {
                            left = w - iconWidth;
                        } else {
                            throw new IllegalStateException("Unexpected `align` value: " + align);
                        }
                        canvas.translate(left, 0);
                    }
                }
            }

            if (background != null) {
                background.draw(canvas);
            }

            graphics2D.setCanvas(canvas);

            icon.paintIcon(null, graphics2D, 0, 0);

        } finally {
            canvas.restoreToCount(save);
        }
    }

    @Override
    public void setAlpha(int alpha) {
        // no op
    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {
        // no op
    }

    @Override
    public int getOpacity() {
        return PixelFormat.OPAQUE;
    }

    @Override
    public int getIntrinsicWidth() {
        return iconWidth;
    }

    @Override
    public int getIntrinsicHeight() {
        return iconHeight;
    }

    public static class Builder {

        private final String latex;

        private float textSize;
        private int color = 0xFF000000;
        private int align;
        private Drawable background;
        private Insets insets;
        private boolean fitCanvas = true;

        public Builder(@NonNull String latex) {
            this.latex = latex;
        }

        @NonNull
        public Builder textSize(@Px float textSize) {
            this.textSize = textSize;
            return this;
        }

        @NonNull
        public Builder color(@ColorInt int color) {
            this.color = color;
            return this;
        }

        @NonNull
        public Builder align(@Align int align) {
            this.align = align;
            return this;
        }

        @NonNull
        public Builder background(@NonNull Drawable background) {
            this.background = background;
            return this;
        }

        @NonNull
        public Builder background(@ColorInt int backgroundColor) {
            this.background = new ColorDrawable(backgroundColor);
            return this;
        }

        @NonNull
        public Builder padding(@Px int padding) {
            this.insets = new Insets(padding, padding, padding, padding);
            return this;
        }

        @NonNull
        public Builder padding(@Px int left, @Px int top, @Px int right, @Px int bottom) {
            this.insets = new Insets(top, left, bottom, right);
            return this;
        }

        @NonNull
        public Builder fitCanvas(boolean fitCanvas) {
            this.fitCanvas = fitCanvas;
            return this;
        }

        @NonNull
        public JLatexMathDrawable build() {
            return new JLatexMathDrawable(this);
        }
    }
}
