package com.lennoo.rno;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.CellInfo;
import android.telephony.CellInfoGsm;
import android.telephony.CellInfoLte;
import android.telephony.CellSignalStrengthGsm;
import android.telephony.PhoneStateListener;
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
        TextView textTac = (TextView)findViewById(R.id.tac);
        TextView textPci = (TextView)findViewById(R.id.pci);
        TextView textRsrp = (TextView)findViewById(R.id.rsrp);

        textPhoneModel.setText(Build.MODEL);
        textSystemVer.setText("系统:"+Build.VERSION.RELEASE);
        textImei.setText("IMEI:"+telephonyInfo.getDeviceId());
        textImsi.setText("IMSI:"+telephonyInfo.getSubscriberId());
        textNetworkType.setText("网络类型:"+ telephonyInfo.getNetworkType());
        textPhoneType.setText("手机类型:"+telephonyInfo.getPhoneType());
        textDataState.setText("数据状态:"+telephonyInfo.getDataState());
        textDataActivity.setText("数据活动:"+telephonyInfo.getDataActivity());
        SignalPhoneStateListener ps = new SignalPhoneStateListener(this);
        TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        tm.listen(ps, PhoneStateListener.LISTEN_SIGNAL_STRENGTHS);
/*
        TelephonyManager tm = (TelephonyManager)this.getSystemService(Context.TELEPHONY_SERVICE);
        CellInfo cellinfo = tm.getAllCellInfo().get(0);
        int sign = 0;
        int ta = 0;
        int tac = 0;
        if (cellinfo instanceof CellInfoGsm) {
            sign = ((CellInfoGsm)cellinfo).getCellSignalStrength().getDbm();
        } else  if (cellinfo instanceof CellInfoLte) {
            CellInfoLte cellInfoLte = (CellInfoLte)cellinfo;
            sign = cellInfoLte.getCellSignalStrength().getDbm();
            ta = cellInfoLte.getCellSignalStrength().getTimingAdvance();
            tac = cellInfoLte.getCellIdentity().getTac();

        }
        textRsrp.setText("Sign"+ Integer.toString(sign));
        textPci.setText("TA:"+Integer.toString(ta));
        textTac.setText("TAC:"+ Integer.toString(tac));
        */
    }
}
