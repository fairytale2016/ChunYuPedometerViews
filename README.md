# ChunYuPedometerViews
春雨计步器app 首页圆形刻度进度条自定义View



# 调用方式


```java
      
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cywidget_demo);
        ButterKnife.bind(this);

        int teps = 2100;

        cyNumberSwitcher.setDataToShow(teps);
        cyNumberSwitcher.setShowTime(1500);

        pedometerArcviewStep.a(360);//设置起始位置 
        pedometerArcviewStep.b((teps + 69) * 72 / 5000);//每日目标5000步
        pedometerArcviewStep.setShowTime(1500);//动画展示时长(毫秒)

        cyNumberSwitcher.startAnim();
        pedometerArcviewStep.startAnim();
    }
```

# 运行效果图 

![预览图](https://raw.githubusercontent.com/fairytale2016/ChunYuPedometerViews/master/%E6%98%A5%E9%9B%A8%E8%AE%A1%E6%AD%A5%E5%99%A8%E6%8E%A7%E4%BB%B6.gif)

