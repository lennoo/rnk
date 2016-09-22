package com.lennoo.rno;

import android.app.Activity;
import android.content.Context;
import android.telephony.CellInfo;
import android.telephony.CellInfoGsm;
import android.telephony.CellInfoLte;
import android.telephony.PhoneStateListener;
import android.telephony.SignalStrength;
import android.telephony.TelephonyManager;
import android.widget.TextView;

import java.util.List;

/**
 * Created by len on 9/22/16.
 */
public class SignalPhoneStateListener extends PhoneStateListener {
    private TelephonyManager mTelephonyManager;
    private Activity mActivity;

    public SignalPhoneStateListener(Activity activity) {
        mActivity = activity;
        mTelephonyManager = (TelephonyManager) activity.getApplicationContext().getSystemService(Context.TELEPHONY_SERVICE);
    }

    @Override
    public void onSignalStrengthsChanged(SignalStrength signalStrength) {
        super.onSignalStrengthsChanged(signalStrength);
        List<CellInfo> cellInfoList = mTelephonyManager.getAllCellInfo();
        CellInfo cellinfo = mTelephonyManager.getAllCellInfo().get(0);
        if (cellinfo instanceof CellInfoGsm) {
            int sign = ((CellInfoGsm) cellinfo).getCellSignalStrength().getDbm();
        } else if (cellinfo instanceof CellInfoLte) {
            CellInfoLte cellInfoLte = (CellInfoLte) cellinfo;
            int tac = cellInfoLte.getCellIdentity().getTac();
            int pci = cellInfoLte.getCellIdentity().getPci();
            int ci = cellInfoLte.getCellIdentity().getCi();
            int rsrp = cellInfoLte.getCellSignalStrength().getDbm();
            String rsrq = cellInfoLte.getCellSignalStrength().toString();


            TextView textTac = (TextView) mActivity.findViewById(R.id.tac);
            TextView textPci = (TextView) mActivity.findViewById(R.id.pci);
            TextView textCi = (TextView) mActivity.findViewById(R.id.ci);
            TextView textRsrp = (TextView) mActivity.findViewById(R.id.rsrp);
            TextView textTest = (TextView) mActivity.findViewById(R.id.test);
            textPci.setText(String.format("%d", pci));
            textTac.setText(String.format("%d", tac));
            textCi.setText(String.format("%d", ci));
            textRsrp.setText(String.format("%d", rsrp));
            textTest.setText(rsrq);
        }

    }
}
