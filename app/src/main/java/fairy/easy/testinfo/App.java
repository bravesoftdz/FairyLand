package fairy.easy.testinfo;

import android.app.Application;

import fairy.easy.test.TestHelper;


public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        TestHelper.install(this);
    }
}
