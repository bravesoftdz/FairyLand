package fairy.easy.test;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import fairy.easy.test.ui.FloatIconPage;
import fairy.easy.test.ui.FloatPageManager;
import fairy.easy.test.ui.PageIntent;
import fairy.easy.test.ui.PermissionUtil;

public class TestHelper {

    private final Object mActivityLifecycleCallbacksLock = new Object();
    private Integer startedActivityCount = 0;
    private Context mContext;
    private boolean sHasRequestPermission;
    private boolean sShowFloatingIcon = true;

    private void requestPermission(Context context) {
        if (!PermissionUtil.canDrawOverlays(context) && !sHasRequestPermission) {
            PermissionUtil.requestDrawOverlays(context);
            sHasRequestPermission = true;
        }
    }

    public void show() {
        if (!isShow()) {
            showFloatIcon();
        }
        sShowFloatingIcon = true;
    }

    public void hide() {
        FloatPageManager.getInstance().removeAll();
        sShowFloatingIcon = false;
    }

    private void showFloatIcon() {
        PageIntent intent = new PageIntent(FloatIconPage.class);
        intent.mode = PageIntent.MODE_SINGLE_INSTANCE;
        FloatPageManager.getInstance().init(mContext);
        FloatPageManager.getInstance().add(intent);
    }

    public boolean isShow() {
        return sShowFloatingIcon;
    }

    private TestHelper(Application application) {
        TestHelperFactory.install(application);
        this.mContext = application;
        application.registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

            }

            @Override
            public void onActivityStarted(Activity activity) {
                synchronized (mActivityLifecycleCallbacksLock) {

                    if (startedActivityCount == 0) {
                        try {
                            appBecomeActive();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    startedActivityCount = startedActivityCount + 1;
                }
            }

            @Override
            public void onActivityResumed(Activity activity) {
                if (PermissionUtil.canDrawOverlays(mContext)) {
                    if (sShowFloatingIcon) {
                        showFloatIcon();
                    }
//                    if (activity instanceof DisplayActivity || activity instanceof DisplayLeakActivity
//                            || activity instanceof LogInfoActivity || activity instanceof CrashInfoActivity
//                            || activity instanceof PreviewActivity) {
//                        hide();
//                    } else {
//                        show();
//                    }
                } else {
                    requestPermission(mContext);

                }
            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

                synchronized (mActivityLifecycleCallbacksLock) {
                    startedActivityCount = startedActivityCount - 1;

                    if (startedActivityCount == 0) {
                        appEnterBackground();
                    }

                }
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {

            }
        });
    }

    private void appEnterBackground() {
        hide();
    }


    private void appBecomeActive() {
        show();
    }

    public static void install(Application application) {
         new TestHelper(application);

    }
}
