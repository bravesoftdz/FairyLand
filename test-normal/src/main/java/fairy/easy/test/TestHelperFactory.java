package fairy.easy.test;

import android.app.Application;

import com.github.moduth.blockcanary.BlockCanary;
import com.github.moduth.blockcanary.BlockCanaryContext;
import com.squareup.leakcanary.LeakCanary;

import fairy.easy.crashcanary.CrashCanary;
import fairy.easy.httpcanary.HttpCanary;
import fairy.easy.logcanary.LogCanary;

public class TestHelperFactory {

    public static void install(Application application) {
        LogCanary.install(application);
        CrashCanary.install(application);
        HttpCanary.install(application);
        BlockCanary.install(application, new BlockCanaryContext()).start();
        if (LeakCanary.isInAnalyzerProcess(application)) {
            return;
        }
        LeakCanary.install(application);
    }
}
