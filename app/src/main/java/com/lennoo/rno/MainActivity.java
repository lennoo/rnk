package com.lennoo.rno;

import android.content.Context;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.CellInfoGsm;
import android.telephony.CellSignalStrengthGsm;
import android.telephony.TelephonyManager;
import android.view.ViewDebug;
import android.widget.TextView;

import com.lennoo.rno.util.TelephonyInfo;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TelephonyInfo telephonyInfo = TelephonyInfo.get(getApplicationContext());
        TextView textPhoneModel = (TextView)findViewById(R.id.phonemodel);
        TextView textSystemVer = (TextView)findViewById(R.id.systemver);
        TextView textImei = (TextView)findViewById(R.id.imei);
        TextView textImsi = (TextView)findViewById(R.id.imsi);
        TextView textNetworkType = (TextView)findViewById(R.id.networktype);
        TextView textPhoneType = (TextView)findViewById(R.id.phonetype);
        TextView textDataState = (TextView)findViewById(R.id.datastate);
        TextView textDataActivity = (TextView)findViewById(R.id.dataactivity);
        TextView textGsmSign = (TextView)findViewById(R.id.gsmsign);

        textPhoneModel.setText("手机:"+Build.MODEL);
        textSystemVer.setText("系统:"+Build.VERSION.RELEASE);
        textImei.setText("IMEI:"+telephonyInfo.getDeviceId());
        textImsi.setText("IMSI:"+telephonyInfo.getSubscriberId());
        textNetworkType.setText("网络类型:"+ telephonyInfo.getNetworkType());
        textPhoneType.setText("手机类型:"+telephonyInfo.getPhoneType());
        textDataState.setText("数据状态:"+telephonyInfo.getDataState());
        textDataActivity.setText("数据活动:"+telephonyInfo.getDataActivity());

        TelephonyManager telephonyManager = (TelephonyManager)this.getSystemService(Context.TELEPHONY_SERVICE);
        CellInfoGsm cellinfogsm = (CellInfoGsm)telephonyManager.getAllCellInfo().get(0);
        CellSignalStrengthGsm cellSignalStrengthGsm = cellinfogsm.getCellSignalStrength();
        int gsmsign = cellSignalStrengthGsm.getDbm();
        textGsmSign.setText("Sign"+ Integer.toString(gsmsign));
    }
}
