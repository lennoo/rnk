package com.lennoo.rno.util;

import android.content.Context;
import android.graphics.Matrix;
import android.telephony.TelephonyManager;

public final class TelephonyInfo {

    private static TelephonyInfo sTelephonyInfo;
    private TelephonyManager mTelephonyManager;

    private TelephonyInfo(Context context) {
        mTelephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
    }
    public static TelephonyInfo get(Context context) {
        if (sTelephonyInfo == null) {
            sTelephonyInfo = new TelephonyInfo(context);
        }
        return sTelephonyInfo;
    }
    public String getDeviceId() {
        return mTelephonyManager.getDeviceId();
    }
    public String getSubscriberId() {
        return mTelephonyManager.getSubscriberId();
    }
    public String getPhoneType() {
        int pt = mTelephonyManager.getPhoneType();
        switch (pt) {
            case TelephonyManager.PHONE_TYPE_CDMA:
                return "CDMA";
            case TelephonyManager.PHONE_TYPE_GSM:
                return "GSM";
            case TelephonyManager.PHONE_TYPE_SIP:
                return "SIP";
            default:
                return "NONE";
        }
    }
    public String getDataState(){
        int ds = mTelephonyManager.getDataState();
        switch (ds)
        {
            default:
                return "NONE";
            case TelephonyManager.DATA_CONNECTED:
                return "CONNECTED";
            case TelephonyManager.DATA_CONNECTING:
                return "CONNECTING";
        }
    }
    public String getDataActivity() {
        int da = mTelephonyManager.getDataActivity();
        switch (da) {
            case TelephonyManager.DATA_ACTIVITY_DORMANT:
                return "DORMANT";
            case TelephonyManager.DATA_ACTIVITY_IN:
                return "IN";
            case TelephonyManager.DATA_ACTIVITY_OUT:
                return "OUT";
            case TelephonyManager.DATA_ACTIVITY_INOUT:
                return "INOUT";
            default:
                return "NONE";
        }
    }
    public String getNetworkType() {
        int nt = mTelephonyManager.getNetworkType();
        switch (nt) {
            case TelephonyManager.NETWORK_TYPE_CDMA:
                return "CDMA";
            case TelephonyManager.NETWORK_TYPE_EDGE:
                return "EDGE";
            case TelephonyManager.NETWORK_TYPE_LTE:
                return "LTE";
            default:
                return "NONE";
        }
    }

}
