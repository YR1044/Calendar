package rygel.cn.uilibrary.utils;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.orhanobut.logger.Logger;

import java.lang.reflect.Field;

public class LeakClearUtils {

    public static void fixInputMethodManagerLeak(Context context) {
        if (context == null) {
            return;
        }

        InputMethodManager im = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (im == null) {
            return;
        }

        String[] fieldArray = {"mCurRootView", "mServedView", "mNextServedView"};
        Field field;
        Object obj;

        for (String f : fieldArray) {
            try {
                field = im.getClass().getField(f);

                if (!field.isAccessible()) {
                    field.setAccessible(true);
                }
                obj = field.get(im);
                if (obj instanceof View) {
                    View v = (View) obj;
                    if (v.getContext() == context) {
                        field.set(im, null);
                    } else {
                        break;
                    }
                }
            } catch (NoSuchFieldException ex) {
                Logger.e(ex.getMessage());
            } catch (IllegalAccessException ex) {
                Logger.e(ex.getMessage());
            }
        }

    }

}
