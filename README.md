使用高德地图进行定位
==========================================

★该插件仅有定位功能
------------------------------------------
<br>

>使用方法
>>提供两个方法
>>>- start(onceFlag, interval, callBack) 开始定位
>>>- stop() 结束定位

#### 插件安装
```cmd
$ npm install gdlocation
$ cordova plugin add gdlocation
```

#### 方法说明
>**start(onceFlag, interval, callBack)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;开始进行定位**
<br><br>
● `onceFlag: Boolean`<br>
布尔值参数<br>
表示是否为一次定位逻辑，设置为 true 表示进行一次定位请求，设置为 false 表示进行连续的定位请求<br>
● `interval: Number`<br>
数值参数<br>
连续定位的时间间隔，单位 秒，默认值 2000, 如果参数 `onceFlag` 设置为 true，该参数无效

>**stop()&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;结束定位**

![](https://pandao.github.io/editor.md/examples/images/4.jpg)