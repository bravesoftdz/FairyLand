package fairy.easy.test.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.github.moduth.blockcanary.ui.DisplayActivity;
import com.squareup.leakcanary.internal.DisplayLeakActivity;

import fairy.easy.crashcanary.CrashInfoActivity;
import fairy.easy.httpcanary.preview.PreviewActivity;
import fairy.easy.logcanary.LogInfoActivity;
import fairy.easy.test.R;


/**
 * Created by 谷闹年 on 2019/9/3.
 */
public class FloatIconPage extends BaseFloatPage implements TouchProxy.OnTouchEventListener, FloatPageManager.FloatPageManagerListener {
    protected WindowManager mWindowManager;

    private TouchProxy mTouchProxy = new TouchProxy(this);
    private ImageView mIvAll;
    private LinearLayout mll;
    private Button btnLeak;
    private Button btnBlock;
    private Button btnLog;
    private Button btnCrash;
    private Button btnHttp;

    private void startActivity(Class clazz) {
        getContext().startActivity(new Intent(getContext().getApplicationContext(), clazz));
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onViewCreated(View view) {
        mIvAll = findViewById(R.id.easy_test_iv_all);
        btnLeak = findViewById(R.id.easy_test_btn_leak);
        btnBlock = findViewById(R.id.easy_test_btn_block);
        btnLog = findViewById(R.id.easy_test_btn_log);
        btnCrash = findViewById(R.id.easy_test_btn_crash);
        btnHttp = findViewById(R.id.easy_test_btn_http);
        mll = findViewById(R.id.easy_test_ll);

        mIvAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mll.getVisibility() == View.VISIBLE) {
                    mll.setVisibility(View.GONE);
                } else {
                    mll.setVisibility(View.VISIBLE);
                }
            }
        });

        btnLeak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(DisplayLeakActivity.class);

            }
        });

        btnBlock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(DisplayActivity.class);
            }
        });
        btnLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(LogInfoActivity.class);
            }
        });
        btnCrash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(CrashInfoActivity.class);
            }
        });
        btnHttp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(PreviewActivity.class);
            }
        });
        mIvAll.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (mIvAll != null) {
                    return mTouchProxy.onTouchEvent(v, event);
                } else {
                    return false;
                }
            }
        });
    }

    @Override
    protected View onCreateView(Context context, ViewGroup view) {
        return LayoutInflater.from(context).inflate(R.layout.easy_test_float_page_view, view, false);
    }

    @Override
    protected void onLayoutParamsCreated(WindowManager.LayoutParams params) {
        params.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        params.x = FloatIconConfig.getLastPosX(getContext());
        params.y = FloatIconConfig.getLastPosY(getContext());
        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
    }

    @Override
    protected void onCreate(Context context) {
        mWindowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        FloatPageManager.getInstance().addListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        FloatPageManager.getInstance().removeListener(this);
    }

    @Override
    public void onEnterForeground() {
        super.onEnterForeground();
        getRootView().setVisibility(View.VISIBLE);
    }

    @Override
    public void onEnterBackground() {
        super.onEnterBackground();
        getRootView().setVisibility(View.GONE);
    }

    @Override
    public void onMove(int x, int y, int dx, int dy) {
        getLayoutParams().x += dx;
        getLayoutParams().y += dy;
        mWindowManager.updateViewLayout(getRootView(), getLayoutParams());
    }

    @Override
    public void onUp(int x, int y) {
        FloatIconConfig.saveLastPosX(getContext(), getLayoutParams().x);
        FloatIconConfig.saveLastPosY(getContext(), getLayoutParams().y);
    }

    @Override
    public void onDown(int x, int y) {

    }

    @Override
    public void onPageAdd(BaseFloatPage page) {
        if (page == this) {
            return;
        }
        FloatPageManager.getInstance().remove(this);
        PageIntent intent = new PageIntent(FloatIconPage.class);
        intent.mode = PageIntent.MODE_SINGLE_INSTANCE;
        FloatPageManager.getInstance().add(intent);
    }
}
