package gj.cordova.plugin;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import com.alibaba.ha.adapter.AliHaAdapter;
import com.alibaba.ha.adapter.AliHaConfig;
import com.alibaba.ha.adapter.Sampling;
import com.taobao.onlinemonitor.OnLineMonitorApp;

import org.apache.cordova.CallbackContext;
import org.json.JSONArray;
import org.json.JSONObject;

import org.apache.cordova.CordovaPlugin;


public class Crash extends CordovaPlugin {
    private Activity activity;
    private Context con = null;
    private static CallbackContext callbackContext;

    public static String appKey = "";
    public static String appSecret = "";

    @Override
    protected void pluginInitialize() {
        Logger.d("init");
        this.activity = cordova.getActivity();
        con = cordova.getActivity().getApplicationContext();

        try {
            String packageName = con.getPackageName();
            ApplicationInfo appInfo = con.getPackageManager().getApplicationInfo(packageName, PackageManager.GET_META_DATA);
            if (appInfo.metaData != null) {
                appKey = appInfo.metaData.get("CRASH_APP_KEY").toString();
                appSecret = appInfo.metaData.getString("CRASH_APP_SECRET");
                Logger.d(appKey, appSecret);
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        initHa();
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
        AliHaAdapter.getInstance().openPublishEmasHa();//指定启动Activity
        AliHaAdapter.getInstance().telescopeService.setBootPath(new String[]{activity.getLocalClassName()}, System.currentTimeMillis());
        
        //ha init
        AliHaConfig config = new AliHaConfig();
        config.appKey = appKey; //appKey
        config.appVersion = "1.0"; //应版本
        config.channel = "base"; //渠道标记
        config.userNick = null;//意义
        config.application = activity.getApplication();
        config.context = activity.getBaseContext();
        config.isAliyunos = false; //是否yuno
        AliHaAdapter.getInstance().start(config);
        AliHaAdapter.getInstance().utAppMonitor.changeSampling(Sampling.All); //指定数据上报例
        OnLineMonitorApp.sIsDebug = false; //OnLineMonitor模式，线上版本必须为false
        Logger.d("init ha");
    }


    private static class Logger {
        private static String TAG = "Crash";
        private static boolean DEBUG = true;
        public static void d(String str) {
            if (DEBUG) {
                Log.d(TAG, str);
            }
        }

        public static void d(String str1, String str2) {
            if (DEBUG) {
                Log.d(TAG, str1+":"+str2);
            }
        }
    }
}
