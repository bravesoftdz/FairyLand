package fairy.easy.test;

import com.github.moduth.blockcanary.BlockCanaryContext;

public class HttpBlockCanary extends BlockCanaryContext {
    @Override
    public int provideBlockThreshold() {
        return 5 * 1000;
    }
}