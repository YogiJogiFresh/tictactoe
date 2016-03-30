package alan.kai.tic_tac_toe;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Looper;
import android.os.Message;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.wearable.DataApi;
import com.google.android.gms.wearable.DataEvent;
import com.google.android.gms.wearable.DataEventBuffer;
import com.google.android.gms.wearable.DataItem;
import com.google.android.gms.wearable.DataMap;
import com.google.android.gms.wearable.DataMapItem;
import com.google.android.gms.wearable.PutDataMapRequest;
import com.google.android.gms.wearable.PutDataRequest;
import com.google.android.gms.wearable.Wearable;

/**
 * Created by weiak_000 on 3/21/2016.
 */
public class CommHandler implements
        DataApi.DataListener,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {
    GoogleApiClient mGoogleApiClient;
    MainActivity mainActivity;
    public static final String UPDATE_NUMBER="update number";
    public static final String BUTTON_CLICK = "button click";
    static android.os.Handler UIHandler=null;
    public CommHandler(MainActivity ma) {
        mainActivity = ma;

        mGoogleApiClient = new GoogleApiClient.Builder(mainActivity).addApi(Wearable.API).addConnectionCallbacks(this).addOnConnectionFailedListener(this).build();
        if (!mGoogleApiClient.isConnected()) {
            mGoogleApiClient.connect();
        }
        //Communication thread use this to talk to UI thread
        UIHandler = new android.os.Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                int type = msg.what;
                switch (type) {
                    case 0:
                        mainActivity.watchClick((int) msg.obj);
                        break;
                    default:
                        return;
                }
                super.handleMessage(msg);
            }
        };
    }
    public void sendNumber(int number) {
        new NumberSenderAsync().execute(number);
    }
    private class NumberSenderAsync extends AsyncTask<Integer,Void,Void> {

        @Override
        protected Void doInBackground(Integer... integers) {
            PutDataMapRequest putDataMapReq = PutDataMapRequest.create("/" + UPDATE_NUMBER);
            putDataMapReq.getDataMap().putInt(UPDATE_NUMBER, integers[0]);
            PutDataRequest putDataReq = putDataMapReq.asPutDataRequest();
            putDataReq.setUrgent();
            PendingResult<DataApi.DataItemResult> pendingResult =
                    Wearable.DataApi.putDataItem(mGoogleApiClient, putDataReq);
            return null;
        }
    }
    @Override
    public void onConnected(Bundle bundle) {
        Wearable.DataApi.addListener(mGoogleApiClient, this);
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onDataChanged(DataEventBuffer dataEventBuffer) {
        for (DataEvent event : dataEventBuffer) {
            if (event.getType() == DataEvent.TYPE_CHANGED) {
                // DataItem changed
                DataItem item = event.getDataItem();
                if (item.getUri().getPath().compareTo("/" + BUTTON_CLICK) == 0) {
                    DataMap dataMap = DataMapItem.fromDataItem(item).getDataMap();
                    int buttonID = dataMap.getInt(BUTTON_CLICK);
                    //Create a message and send to the Handler
                    Message msg = UIHandler.obtainMessage(0, buttonID);
                    msg.sendToTarget();
                }
            } else if (event.getType() == DataEvent.TYPE_DELETED) {
                // DataItem deleted
            }
        }
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }
}
