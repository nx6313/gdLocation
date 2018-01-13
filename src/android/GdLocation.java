package com.hmj.nx6313.gdlocation;

import android.util.Log;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Poi;
import com.amap.api.navi.AmapNaviPage;
import com.amap.api.navi.AmapNaviParams;
import com.amap.api.navi.AmapNaviType;
import com.amap.api.navi.INaviInfoCallback;
import com.amap.api.navi.model.AMapNaviLocation;

import com.iflytek.cloud.InitListener;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechSynthesizer;
import com.iflytek.cloud.SpeechUtility;
import com.iflytek.cloud.SynthesizerListener;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by nx6313 on 2018/1/2.
 */

public class GdLocation extends CordovaPlugin {
    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;
    //声明AMapLocationClientOption对象
    public AMapLocationClientOption mLocationOption = null;
    //语音合成Tts对象
    public SpeechSynthesizer mTts = null;

    @Override
    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);
    }

    @Override
    public boolean execute(String action, JSONArray args, final CallbackContext callbackContext) throws JSONException {
        if(action.equals("start")) {
            final boolean onceFlag = args.getBoolean(0);
            //初始化定位
            mLocationClient = new AMapLocationClient(cordova.getActivity().getApplicationContext());
            //设置定位回调监听
            mLocationClient.setLocationListener(new AMapLocationListener() {
                @Override
                public void onLocationChanged(AMapLocation aMapLocation) {
                    if (aMapLocation != null) {
                        if (aMapLocation.getErrorCode() == 0) {
                            Log.d("获取到定位信息", "" + aMapLocation);
                            JSONObject location = new JSONObject();
                            try {
                                location.put("locationType", aMapLocation.getLocationType()); // 当前定位结果来源
                                location.put("latitude", aMapLocation.getLatitude()); // 纬度
                                location.put("longitude", aMapLocation.getLongitude()); // 经度
                                location.put("accuracy", aMapLocation.getAccuracy()); // 精度信息
                                location.put("address", aMapLocation.getAddress()); // 地址
                                location.put("country", aMapLocation.getCountry()); // 国家信息
                                location.put("province", aMapLocation.getProvince()); // 省信息
                                location.put("city", aMapLocation.getCity()); // 城市信息
                                location.put("district", aMapLocation.getDistrict()); // 城区信息
                                location.put("street", aMapLocation.getStreet()); // 街道信息
                                location.put("streetNum", aMapLocation.getStreetNum()); // 街道门牌号信息
                                location.put("cityCode", aMapLocation.getCityCode()); // 城市编码
                                location.put("adCode", aMapLocation.getAdCode()); // 地区编码
                                location.put("aoiName", aMapLocation.getAoiName()); // 当前定位点的AOI信息
                                location.put("buildingId", aMapLocation.getBuildingId()); // 当前室内定位的建筑物Id
                                location.put("floor", aMapLocation.getFloor()); // 当前室内定位的楼层
                                location.put("gpsStatus", aMapLocation.getGpsAccuracyStatus()); // GPS的当前状态
                                location.put("time", aMapLocation.getTime()); // 定位时间
                            } catch (JSONException e) {
                            }
                            if(onceFlag) {
                                callbackContext.success(location);
                            } else {
                                PluginResult r = new PluginResult(PluginResult.Status.OK, location);
                                r.setKeepCallback(true);
                                callbackContext.sendPluginResult(r);
                            }
                        } else {
                            Log.e("AmapError", "location Error, ErrCode:"
                                    + aMapLocation.getErrorCode() + ", errInfo:"
                                    + aMapLocation.getErrorInfo());
                            JSONObject locationError = new JSONObject();
                            try {
                                locationError.put("errCode", aMapLocation.getErrorCode());
                                locationError.put("errInfo", aMapLocation.getErrorInfo());
                            } catch (JSONException e) {
                            }
                            if(onceFlag) {
                                callbackContext.error(locationError);
                            } else {
                                PluginResult r = new PluginResult(PluginResult.Status.ERROR, aMapLocation.getErrorCode());
                                r.setKeepCallback(true);
                                callbackContext.sendPluginResult(r);
                            }
                        }
                    }
                }
            });

            //初始化AMapLocationClientOption对象
            mLocationOption = new AMapLocationClientOption();
            mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy); // 设置定位模式，高精度定位
            // 设置是否返回定位信息
            mLocationOption.setNeedAddress(true);
            // 设置是否允许模拟位置
            mLocationOption.setMockEnable(true);
            // 设置定位超时时间
            mLocationOption.setHttpTimeOut(20000);
            if (onceFlag) {
                // 设置获取一次定位结果
                mLocationOption.setOnceLocation(true);
                // 获取最近3s内精度最高的一次定位结果
                mLocationOption.setOnceLocationLatest(true);
            } else {
                long interval = 2000L;
                if(!args.isNull(1)) {
                    interval = args.getLong(1);
                }
                if (interval < 1000L) {
                    interval = 1000L;
                }
                // 设置连续定位时间间隔 ms
                mLocationOption.setInterval(interval);
            }
            // 启动定位
            mLocationClient.setLocationOption(mLocationOption);
            mLocationClient.startLocation();
            return true;
        } else if(action.equals("stop")) {
            if (mLocationClient != null) {
                mLocationClient.stopLocation();
                mLocationClient.onDestroy();
                return true;
            }
        } else if(action.equals("showRoute")) {
            SpeechUtility.createUtility(cordova.getActivity().getApplicationContext(), SpeechConstant.APPID + "=5a597d0a");
            initTTs();
            JSONObject startObj = args.getJSONObject(0);
            JSONObject endObj = args.getJSONObject(1);
            showRoute(startObj, endObj);
            return true;
        }
        return super.execute(action, args, callbackContext);
    }

    private void initTTs() {
        mTts = SpeechSynthesizer.createSynthesizer(cordova.getActivity().getApplicationContext(), new InitListener() {
            @Override
            public void onInit(int i) {
            }
        });

        mTts.setParameter(SpeechConstant.ENGINE_TYPE, SpeechConstant.TYPE_CLOUD);
        mTts.setParameter(SpeechConstant.ENGINE_MODE, SpeechConstant.MODE_AUTO);
        mTts.setParameter(SpeechConstant.VOICE_NAME, "xiaoping");
        //设置合成语速
        mTts.setParameter(SpeechConstant.SPEED, "40");
        //设置合成音调
        mTts.setParameter(SpeechConstant.PITCH, "50");
        //设置合成音量
        mTts.setParameter(SpeechConstant.VOLUME, "100");
    }

    private void showRoute(JSONObject startObj, JSONObject endObj) {
        String startLocationName = "";
        String endLocationName = "";
        Double startLat = 0.0;
        Double startLng = 0.0;
        Double endLat = 0.0;
        Double endLng = 0.0;
        try {
            startLocationName = startObj.getString("startName");
            startLat = startObj.getDouble("startLat");
            startLng = startObj.getDouble("startLng");
            
            endLocationName = endObj.getString("endName");
            endLat = endObj.getDouble("endLat");
            endLng = endObj.getDouble("endLng");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Poi start = new Poi(startLocationName, new LatLng(startLat, startLng), "");
        Poi end = new Poi(endLocationName, new LatLng(endLat, endLng), "");
        AmapNaviPage.getInstance().showRouteActivity(cordova.getActivity().getApplicationContext(), new AmapNaviParams(start, null, end, AmapNaviType.DRIVER), new INaviInfoCallback() {
            @Override
            public void onInitNaviFailure() {
                // 导航初始化失败时的回调函数
            }

            @Override
            public void onGetNavigationText(String s) {
                // 导航播报信息回调函数
                startSpeek(s);
            }

            @Override
            public void onLocationChange(AMapNaviLocation aMapNaviLocation) {
                // 当GPS位置有更新时的回调函数
            }

            @Override
            public void onArriveDestination(boolean b) {
                // 到达目的地后回调函数
            }

            @Override
            public void onStartNavi(int i) {
                // 启动导航后的回调函数
            }

            @Override
            public void onCalculateRouteSuccess(int[] ints) {
                // 算路成功回调
            }

            @Override
            public void onCalculateRouteFailure(int i) {
                // 步行或者驾车路径规划失败后的回调函数
            }

            @Override
            public void onStopSpeaking() {
                // 停止语音回调，收到此回调后用户可以停止播放语音
            }
        });
    }

    private void startSpeek(String speekStr) {
        if(!speekStr.equals("")) {
            mTts.startSpeaking(speekStr, new SynthesizerListener() {
                @Override
                public void onSpeakBegin() {

                }

                @Override
                public void onBufferProgress(int i, int i1, int i2, String s) {

                }

                @Override
                public void onSpeakPaused() {

                }

                @Override
                public void onSpeakResumed() {

                }

                @Override
                public void onSpeakProgress(int i, int i1, int i2) {

                }

                @Override
                public void onCompleted(SpeechError speechError) {

                }

                @Override
                public void onEvent(int i, int i1, int i2, Bundle bundle) {

                }
            });
        }
    }

}
