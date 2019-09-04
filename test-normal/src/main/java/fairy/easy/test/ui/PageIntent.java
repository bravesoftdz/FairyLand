package fairy.easy.test.ui;

import android.os.Bundle;

/**
 * Created by 谷闹年 on 2019/9/3.
 */
public class PageIntent {
    public static final int MODE_NORMAL = 0;

    public static final int MODE_SINGLE_INSTANCE = 1;

    public Class<? extends BaseFloatPage> targetClass;

    public Bundle bundle;

    public String tag;

    public int mode = MODE_NORMAL;

    public PageIntent() {
    }

    public PageIntent(Class<? extends BaseFloatPage> targetClass) {
        this.targetClass = targetClass;
    }
}
