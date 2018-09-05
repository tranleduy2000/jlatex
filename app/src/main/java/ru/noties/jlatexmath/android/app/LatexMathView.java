package ru.noties.jlatexmath.android.app;

import android.awt.AndroidGraphics2D;
import android.awt.Insets;
import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.textservice.TextInfo;

import org.scilab.forge.jlatexmath.TeXConstants;
import org.scilab.forge.jlatexmath.TeXFormula;
import org.scilab.forge.jlatexmath.TeXIcon;

import ru.noties.debug.Debug;

public class LatexMathView extends View {

    private TeXIcon teXIcon;
    private String latexMath;
    private AndroidGraphics2D graphics2D;

    public LatexMathView(Context context) {
        super(context);
        init(context, null);
    }

    public LatexMathView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, @Nullable AttributeSet attrs) {
        setWillNotDraw(false);
    }

    public void setLatexMath(@NonNull String latexMath) {
        this.latexMath = latexMath;
        this.teXIcon = null;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Debug.i(latexMath);

        if (latexMath == null) {
            return;
        }

        if (teXIcon == null) {
            teXIcon = create(canvas.getWidth());
        }

        Debug.i(teXIcon);

        if (graphics2D == null) {
            graphics2D = new AndroidGraphics2D();
        }

        Debug.i(graphics2D);

//        final int save = canvas.save();
//        try {
//            graphics2D.setCanvas(canvas);
//            teXIcon.paintIcon(null, graphics2D, 0, 0);
//        } finally {
//            canvas.restoreToCount(save);
//        }

        final int save = canvas.save();
        try {

            final int w = teXIcon.getIconWidth();
//            final int h = teXIcon.getIconHeight();

            if (w > canvas.getWidth()) {
                final float ratio = (float) canvas.getWidth() / w;
                canvas.scale(ratio, ratio);
            }
            // todo: else we can align it (by setting)

            graphics2D.setCanvas(canvas);
            teXIcon.paintIcon(null, graphics2D, 0, 0);
        } finally {
            canvas.restoreToCount(save);
        }
    }

    @NonNull
    private TeXIcon create(int width) {

//        String latex = "\\begin{array}{cc}";
//        latex += "\\fbox{\\text{A framed box with \\textdbend}}&\\shadowbox{\\text{A shadowed box}}\\cr";
//        latex += "\\doublebox{\\text{A double framed box}}&\\ovalbox{\\text{An oval framed box}}\\cr";
//        latex += "\\end{array}";

        TeXFormula formula = new TeXFormula(latexMath);
        // Note: Old interface for creating icons:
        //TeXIcon icon = formula.createTeXIcon(TeXConstants.STYLE_DISPLAY, 30);
        // Note: New interface using builder pattern (inner class):
        TeXIcon icon = formula.new TeXIconBuilder()
                .setStyle(TeXConstants.STYLE_DISPLAY)
                .setSize(30)
//                .setWidth(TeXConstants.UNIT_PIXEL, width, 0)
//                .setIsMaxWidth(true)
                .build();
        icon.setInsets(new Insets(5, 5, 5, 5));
//        Debug.i("width: %d, icon: {%d %d}", width, icon.getIconWidth(), icon.getIconHeight());
        return icon;
    }
}
