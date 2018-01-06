﻿使用高德地图进行定位
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
>☆ **start(onceFlag, interval, callBack)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;开始进行定位**
<br><br>
● `onceFlag: Boolean`<br>
布尔值参数<br>
表示是否为一次定位逻辑，设置为 true 表示进行一次定位请求，设置为 false 表示进行连续的定位请求<br>
● `interval: Number`<br>
数值参数<br>
连续定位的时间间隔，单位 毫秒，默认值 2000, 如果参数 `onceFlag` 设置为 true，该参数无效<br>
● `callBack: Function`<br>
回调方法<br>
当获取到位置信息后，回调该方法，返回位置信息
<br><br><br>
>**☆ stop()&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;结束定位**

联系作者
-----------------------------------------------
- ``Email``&nbsp;&nbsp;&nbsp;2559635030@qq.com
<br><br><br>
![](https://pandao.github.io/editor.md/examples/images/4.jpg)