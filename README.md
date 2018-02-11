使用高德地图进行定位
==========================================

★提供的功能有：定位、导航、语音合成播报、记录移动距离、计算两坐标的距离
------------------------------------------
<br>

>使用方法
>>- start(onceFlag, interval, callBack) 开始定位
>>- stop() 结束定位
>>- showRoute(ttsAppId, startObj, endObj) 执行导航
>>- stopRoute(stopReason) 停止导航
>>- startSpeak(ttsAppId, speekContent) 语音合成播报
>>- startRecord(interval, minDis, maxDis) 开始记录移动距离
>>- stopRecord() 停止记录移动距离
>>- calcNavInfo(oneLatLng, twoLatLng) 计算两坐标的距离

#### 插件安装
```cmd
$ npm install cordova-plugin-gdlocation
$ cordova plugin add cordova-plugin-gdlocation --variable API_KEY=高德地图中应用的API_KEY
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
<br><br><br>
>**☆ showRoute(ttsAppId, startObj, endObj)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;执行导航**
<br><br>
● `ttsAppId: String`<br>
字符串参数<br>
导航使用的讯飞语音AppId
<br>
● `startObj: JSONObject`<br>
JSON对象参数<br>
导航的起点数据，格式为：{ startName: string, startLat: double, startLng: double }
<br>
● `endObj: JSONObject`<br>
JSON对象参数<br>
导航的终点数据，格式为：{ endName: string, endLat: double, endLng: double }
<br><br><br>
>**☆ stopRoute(stopReason, callBack)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;停止导航**
<br><br>
● `stopReason: String`<br>
字符串参数<br>
停止导航的原因<br>
● `callBack: Function`<br>
回调方法<br>
当导航停止后，回调该方法，无信息返回
<br><br><br>
>**☆ startSpeak(ttsAppId, speekContent)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;语音合成播报**
<br><br>
● `ttsAppId: String`<br>
字符串参数<br>
语音播报使用的讯飞语音AppId<br>
● `speekContent: String`<br>
字符串参数<br>
语音播报的文字内容
<br><br><br>
>**☆ startRecord(interval, minDis, maxDis)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;开始记录移动距离**
<br><br>
● `interval: Number`<br>
数值参数<br>
连续返回移动距离记录的时间间隔，单位 毫秒，默认值 2000<br>
● `minDis: Float`<br>
数值参数<br>
允许忽略掉每次移动距离的最小值<br>
● `maxDis: Float`<br>
数值参数<br>
允许忽略掉每次移动距离的最大值
<br><br><br>
>**☆ stopRecord()&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;停止记录移动距离**
<br><br><br>
>**☆ calcNavInfo(oneLatLng, twoLatLng)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;计算两坐标的距离**
<br><br>
● `oneLatLng: JSONObject`<br>
JSON对象参数<br>
第一个坐标点，格式为：{ lat: double, lng: double }
<br>
● `twoLatLng: JSONObject`<br>
JSON对象参数<br>
第二个坐标点，格式为：{ lat: double, lng: double }

联系作者
-----------------------------------------------
- ``Email``&nbsp;&nbsp;&nbsp;2559635030@qq.com
<br><br><br>
![](https://pandao.github.io/editor.md/examples/images/4.jpg)