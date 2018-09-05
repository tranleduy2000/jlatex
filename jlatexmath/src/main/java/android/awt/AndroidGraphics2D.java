package android.awt;

import android.awt.font.FontRenderContext;
import android.awt.geom.AffineTransform;
import android.awt.geom.Line2D;
import android.awt.geom.Rectangle2D;
import android.awt.geom.RoundRectangle2D;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;

import java.util.Arrays;

import ru.noties.debug.Debug;

public class AndroidGraphics2D implements Graphics2D {

    private final RectF rectF = new RectF();

    private final Paint paint;

    {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStrokeCap(Paint.Cap.BUTT);
        paint.setStrokeJoin(Paint.Join.MITER);
    }

    private Canvas canvas;
    private Color color;
    private Stroke stroke;
    private Font font;
    private AffineTransform transform;

    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
        this.transform = AffineTransform.create(canvas);
    }

    @Override
    public Color getColor() {
        if (color == null) {
            color = new Color(paint.getColor());
        }
        return color;
    }

    @Override
    public void setColor(Color color) {
        this.color = color;
        paint.setColor(color.getColorInt());
    }

    @Override
    public void fill(Rectangle2D.Float aFloat) {
//        Debug.i("fill(Rectangle2D.Float: %s", aFloat);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRect(
                aFloat.x, aFloat.y,
                aFloat.x + aFloat.w,
                aFloat.y + aFloat.h,
                paint
        );
    }

    @Override
    public Stroke getStroke() {
        if (stroke == null) {
            stroke = new BasicStroke(
                    paint.getStrokeWidth(),
                    BasicStroke.CAP_BUTT,
                    BasicStroke.JOIN_MITER,
                    paint.getStrokeMiter()
            );
        }
        return stroke;
    }

    @Override
    public void setStroke(Stroke stroke) {
//        Debug.i("stroke: %s", stroke);
        this.stroke = stroke;
        this.paint.setStrokeWidth(stroke.width());

        // todo: find out about this, if added, bad draws..
//        this.paint.setStrokeMiter(stroke.miterLimit());
    }

    @Override
    public AffineTransform getTransform() {
//        if (scale < 3) {
//            Debug.i("scale: {%s %s}", transform.getScaleX(), transform.getScaleY());
//            Debug.trace(10);
//        }
        transform = transform.save();
        return transform;
    }

    @Override
    public void setTransform(AffineTransform at) {
        if (canvas != at.getCanvas()) {
            throw new IllegalStateException("Supplied transform has different Canvas attached");
        }
        this.transform = at.restore();
//        Debug.i("scale: {%s %s}, prev: {%s %s}", transform.getScaleX(), transform.getScaleY(), at.getScaleX(), at.getScaleY());
    }

    @Override
    public void draw(Rectangle2D.Float aFloat) {
//        Debug.i("rectangle: %s, scale{%s %s}, translate: {%s %s}", aFloat, transform.getScaleX(), transform.getScaleY(), transform.translateX(), transform.translateY());
//        Debug.trace(7);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(
                aFloat.x,
                aFloat.y,
                aFloat.x + aFloat.w,
                aFloat.y + aFloat.h,
                paint
        );
    }

    @Override
    public void translate(double x, double y) {
//        Debug.i("x: %s, y: %s", x, y);
//        canvas.translate((float) x, (float) y);
        transform.translate((float) x, (float) y);
    }

    @Override
    public void scale(double v, double v1) {
//        Debug.i("v: %s, v1: %s", v, v1);
//        if (scale++ < 3) {
//            Debug.trace(10);
//        }
        transform.scale(v, v1);
    }

    @Override
    public Font getFont() {
        return font;
    }

    @Override
    public void setFont(Font font) {
        this.font = font;
    }

    @Override
    public void drawChars(char chars[], int offset, int length, int x, int y) {
//        Debug.i("chars: %s, offset: %s, length: %s, x: %s, y: %s", Arrays.toString(chars), offset, length, x, y);
        if (font != null) {
            paint.setTypeface(font.typeface());
            paint.setTextSize(font.size());
        }
        canvas.drawText(chars, offset, length, x, y, paint);
    }

    @Override
    public void draw(Line2D.Float line) {
//        Debug.i("line: %s", line);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawLine(
                (float) line.x1,
                (float) line.y1,
                (float) line.x2,
                (float) line.y2,
                paint
        );
    }

    @Override
    public void rotate(double v, double v1, double v2) {
//        Debug.i("v: %s, v1: %s, v2: %s", v, v1, v2);
        canvas.rotate((float) v, (float) v1, (float) v2);
    }

    @Override
    public void drawArc(int i, int i1, int i2, int i3, int i4, int i5) {
//        Debug.i("i: %s, i1: %s, i2: %s, i3: %s, i4: %s, i5: %s", i, i1, i2, i3, i4, i5);
        // todo: useCenter?
        paint.setStyle(Paint.Style.STROKE);
        rectF.set(i, i1, i2, i3);
        canvas.drawArc(rectF, i4, i5, false, paint);
    }

    @Override
    public void fillArc(int i, int i1, int i2, int i3, int i4, int i5) {
//        Debug.i("i: %s, i1: %s, i2: %s, i3: %s, i4: %s, i5: %s", i, i1, i2, i3, i4, i5);
        // todo: useCenter?
        paint.setStyle(Paint.Style.FILL);
        rectF.set(i, i1, i2, i3);
        canvas.drawArc(rectF, i4, i5, false, paint);
    }

    @Override
    public void draw(RoundRectangle2D.Float aFloat) {
        paint.setStyle(Paint.Style.STROKE);
        rectF.set(
                aFloat.x,
                aFloat.y,
                aFloat.x + aFloat.width,
                aFloat.y + aFloat.height
        );
        canvas.drawRoundRect(rectF, aFloat.arcwidth, aFloat.archeight, paint);
    }

    @Override
    public void rotate(double v) {
        // radians here?
//        Debug.i("v: %s", v);
        canvas.rotate((float) Math.toDegrees(v));
    }

    @Override
    public FontRenderContext getFontRenderContext() {
        return null;
    }

    @Override
    public void fillRect(int i, int i1, int w, int h) {
//        Debug.i("fillRect, i: %s, i1: %s, w: %s, h: %s", i, i1, w, h);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRect(
                i, i1,
                i + w, i1 + h,
                paint
        );
    }

//    @Override
//    public void dispose() {
//
//    }

    @Override
    public RenderingHints getRenderingHints() {
        return null;
    }

    @Override
    public void setRenderingHint(RenderingHints.Key keyAntialiasing, Object valueAntialiasOn) {

    }

    @Override
    public void setRenderingHints(RenderingHints oldHints) {

    }
}
