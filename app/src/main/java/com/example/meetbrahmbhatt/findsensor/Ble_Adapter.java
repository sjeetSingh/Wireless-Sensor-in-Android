import android.annotation.SuppressLint;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

public class Ble_Adapter extends BaseAdapter {
    private final LayoutInflater inflater;

    public static ArrayList<BluetoothDevice> leDevices;
    private final HashMap<BluetoothDevice, Integer> rssiMap = new HashMap<BluetoothDevice, Integer>();
    private final HashMap<BluetoothDevice, String> urlMap = new HashMap<BluetoothDevice, String>();
    private TreeMap<BluetoothDevice,Integer> sortedRssi = new TreeMap<BluetoothDevice,Integer>();
    private HashMap<String,Integer> urlToIntMap = new HashMap<String, Integer>();
    MainActivity mainObj;

    public Ble_Adapter(Context context,MainActivity tobj) {
        mainObj = tobj;
        leDevices = new ArrayList<BluetoothDevice>();
        inflater = LayoutInflater.from(context);
        urlToIntMap.put("http://server1.com/001",1);
        urlToIntMap.put("http://server1.com/002",2);
        urlToIntMap.put("http://server1.com/003",3);
        urlToIntMap.put("http://server1.com/004",4);
        urlToIntMap.put("http://server1.com/005",5);
        urlToIntMap.put("http://server1.com/006",6);
        urlToIntMap.put("http://server1.com/009",9);
        urlToIntMap.put("http://server1.com/0010",10);
    }

    public void addDevice(BluetoothDevice device, int rssi,String url) {
        if (!leDevices.contains(device)) {
            leDevices.add(device);
            urlMap.put(device,url);
            int tnum = (int) urlToIntMap.get(url);
            mainObj.checkRadio(tnum);
        }
        rssiMap.put(device, rssi);
        sortedRssi = new TreeMap<BluetoothDevice, Integer>(rssiMap);
    }
    void enableRadioFromURL(String turl){
        int radioValue = urlToIntMap.get(turl);
    }
    protected static double calculateDistance(int txPower, double rssi) {
        if (rssi == 0) {
            return -1.0; // if we cannot determine distance, return -1.
        }

        double ratio = rssi*1.0/txPower;
        if (ratio < 1.0) {
            return Math.pow(ratio,10);
        }
        else {
            double accuracy =  (0.89976)*Math.pow(ratio,7.7095) + 0.111;
            return accuracy;
        }
    }

    public BluetoothDevice getDevice(int position) {
        return leDevices.get(position);
    }

    public void clear() {
        leDevices.clear();
    }

    @Override
    public int getCount() {
        return leDevices.size();
    }

    @Override
    public Object getItem(int i) {
        return leDevices.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    public void printMessage(String text){
        System.out.println(text);
    }

    @SuppressLint("InflateParams")
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
//        ViewHolder viewHolder;
//        // General ListView optimization code.
//        if (view == null) {
//            view = inflater.inflate(R.layout.device_list, null);
//            viewHolder = new ViewHolder();
//            viewHolder.deviceName = (TextView) view.findViewById(R.id.device_name);
//            viewHolder.deviceAddress = (TextView) view.findViewById(R.id.device_address);
//            viewHolder.deviceRssi = (TextView) view.findViewById(R.id.device_rssi);
//            view.setTag(viewHolder);
//        } else {
//            viewHolder = (ViewHolder) view.getTag();
//        }
//
//        BluetoothDevice device = leDevices.get(i);
//        final String deviceName = device.getName();
//        if (deviceName != null && deviceName.length() > 0)
//            viewHolder.deviceName.setText(deviceName);
//        else
//            viewHolder.deviceName.setText(R.string.unknown_device);
//        viewHolder.deviceAddress.setText(device.getAddress());
//        viewHolder.deviceRssi.setText(""+rssiMap.get(device)+" dBm");
//
        return view;
    }

    private static class ViewHolder {
        TextView deviceName;
        TextView deviceAddress;
        TextView deviceRssi;
    }
}
