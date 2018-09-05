package ru.noties.jlatexmath;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;

import java.io.IOException;
import java.io.InputStream;

public abstract class JLatexMathAndroid {

    private static final String BASE = "org/scilab/forge/jlatexmath/";

    private static Context sContext = null;

    public static void init(Context context) {
        sContext = context.getApplicationContext();
    }

    public static InputStream getResourceAsStream(String path) {
        try {
            return sContext.getAssets().open(BASE + path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @NonNull
    public static Typeface loadTypeface(@NonNull String path) {
        return Typeface.createFromAsset(sContext.getAssets(), BASE + path);
    }

    private JLatexMathAndroid() {
    }
}
