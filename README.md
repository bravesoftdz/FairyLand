<div align="center">

## FairyLand

**`Android`简易测试输出**

[![Download](https://api.bintray.com/packages/guxiaonian/FairyLand/FairyLandNormal/images/download.svg) ](https://bintray.com/guxiaonian/FairyLand/FairyLandNormal/_latestVersion)
[![GitHub issues](https://img.shields.io/github/issues/guxiaonian/FairyLand.svg)](https://github.com/guxiaonian/FairyLand/issues)
[![GitHub forks](https://img.shields.io/github/forks/guxiaonian/FairyLand.svg)](https://github.com/guxiaonian/FairyLand/network)
[![GitHub stars](https://img.shields.io/github/stars/guxiaonian/FairyLand.svg)](https://github.com/guxiaonian/FairyLand/stargazers)
[![GitHub license](https://img.shields.io/github/license/guxiaonian/FairyLand.svg)](http://www.apache.org/licenses/LICENSE-2.0)

</div>
<br>

# 效果展示

![fairy_logo](./img/img.png)


# 依赖

```gradle
debugImplementation  'fairy.easy.land:test-normal:{latestVersion}'
releaseImplementation  'fairy.easy.land:test-no-op:{latestVersion}'
//androidX使用
//debugImplementation  'fairy.easy.land:test-androidx:{latestVersion}'

```
      
# 调用方式

```java
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        TestHelper.install(this);
    }
}

```

# 注意事项

1. targetSdkVersion设置为28以下
2. 添加1.8版本

```gradle
 compileOptions {
        targetCompatibility 1.8
        sourceCompatibility 1.8
    }
```
