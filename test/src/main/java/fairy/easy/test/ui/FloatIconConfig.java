package fairy.easy.test.ui;

import android.content.Context;

/**
 * Created by 谷闹年 on 2019/9/3.
 */
public class FloatIconConfig {public static int getLastPosX(Context context) {
    return SharedPrefsUtil.getInt(context, SharedPrefsKey.FLOAT_ICON_POS_X, 0);
}

    public static int getLastPosY(Context context) {
        return SharedPrefsUtil.getInt(context, SharedPrefsKey.FLOAT_ICON_POS_Y, 0);
    }

    public static void saveLastPosY(Context context, int val) {
        SharedPrefsUtil.putInt(context, SharedPrefsKey.FLOAT_ICON_POS_Y, val);
    }

    public static void saveLastPosX(Context context, int val) {
        SharedPrefsUtil.putInt(context, SharedPrefsKey.FLOAT_ICON_POS_X, val);
    }

}
