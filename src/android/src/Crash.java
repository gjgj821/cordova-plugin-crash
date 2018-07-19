package gj.cordova.plugin;

import android.app.Activity;
import android.util.Log;

import org.apache.cordova.CallbackContext;
import org.json.JSONArray;
import org.json.JSONObject;

import org.apache.cordova.CordovaPlugin;


public class Crash extends CordovaPlugin {
    private Activity activity;
    private static CallbackContext callbackContext;

    public static String appKey = "";
    public static String appSecret = "";

    @Override
    protected void pluginInitialize() {
        Logger.d("init");
        this.activity = cordova.getActivity();

        appKey = preferences.getString("appKeyAndroid", "");
        appSecret = preferences.getString("appSecretAndroid", "");

        initManService();
        // initFeedbackService();
        // initHttpDnsService();
        // initHotfix();
        // initPushService(this);
    }

    @Override
    public void onPause(boolean multitasking) {

    }
    
    @Override
    public void onResume(boolean multitasking) {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) {
        Logger.d("execute");
        JSONObject obj = args.optJSONObject(0);
        if (action.equals("login")) {
            
        } else {
            return false;
        }
        return true;
    }

    private void initHa() {//这必须启动，否则服务端收不到数据
        AliHaAdapter.getInstance().openPublishEmasHa();//指定启动
        ActivityAliHaAdapter.getInstance().telescopeService.setBootPath(new String[]{activity}, System.currentTimeMillis());
        
        //ha init
        AliHaConfig config = new AliHaConfig();
        config.appKey = appKey; //appKey
        config.appVersion = "1.0"; //应版本
        config.channel = "base"; //渠道标记
        config.userNick = null;//意义
        config.application = this;
        config.context = getApplicationContext();
        config.isAliyunos = false; //是否yuno
        sAliHaAdapter.getInstance().start(config);
        AliHaAdapter.getInstance().utAppMonitor.changeSampling(Sampling.All); //指定数据上报例
        OnLineMonitorApp.sIsDebug = false; //OnLineMonitor模式，线上版本必须为false
        Log.e("ha", "init");}
}
