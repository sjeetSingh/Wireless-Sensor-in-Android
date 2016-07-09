package com.example.meetbrahmbhatt.findsensor;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;


public class MainActivity extends AppCompatActivity implements Error_Report.ErrorDialogListener,Enable_Bluetooth.EnableBluetoothDialogListener {
    private Button searchBtn;
    private static final int REQUEST_ENABLE_BT = 1;
    private static final long SCAN_PERIOD = 30;

    private Ble_Adapter leDeviceListAdapter;
    private BluetoothAdapter bluetoothAdapter;
    private BleScanner scanner;
    private RadioButton bt1;
    private RadioButton bt2;
    private RadioButton bt3;
    private RadioButton bt4;
    private RadioButton bt5;
    private RadioButton bt6;
    private RadioButton bt9;
    private RadioButton bt10;

    public static int rssi_value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        while (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this,Manifest.permission.BLUETOOTH)!= PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this,Manifest.permission.BLUETOOTH_ADMIN)!= PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            System.out.println("Something is wrong:...");
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.BLUETOOTH,Manifest.permission.BLUETOOTH_ADMIN},27);
        }
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        searchBtn = (Button)findViewById(R.id.searchbtn);
        searchBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                searchBtn.setVisibility(View.GONE);
                searchForBLEBeacons();
                displayAllRadio();
//                checkRadio(1);
            }
        });
        bt1 = (RadioButton)findViewById(R.id.radio1);
        bt1.setVisibility(View.GONE);
        bt2 = (RadioButton)findViewById(R.id.radio2);
        bt2.setVisibility(View.GONE);
        bt3 = (RadioButton)findViewById(R.id.radio3);
        bt3.setVisibility(View.GONE);
        bt4 = (RadioButton)findViewById(R.id.radio4);
        bt4.setVisibility(View.GONE);
        bt5 = (RadioButton)findViewById(R.id.radio5);
        bt5.setVisibility(View.GONE);
        bt6 = (RadioButton)findViewById(R.id.radio6);
        bt6.setVisibility(View.GONE);
        bt9 = (RadioButton)findViewById(R.id.radio9);
        bt9.setVisibility(View.GONE);
        bt10 = (RadioButton)findViewById(R.id.radio10);
        bt10.setVisibility(View.GONE);


        printMessage("Check bluetooth service available...");
        final int bleStatus = Ble_Utils.getBleStatus(getBaseContext());
        switch (bleStatus) {
            case Ble_Utils.STATUS_BLE_NOT_AVAILABLE:
                printMessage("Khabar Nai");
                Error_Report.newInstance(R.string.dialog_error_no_ble).show(
                        getFragmentManager(), Error_Report.TAG);
                return;
            case Ble_Utils.STATUS_BLUETOOTH_NOT_AVAILABLE:
                printMessage("Khabar Nai padtila....");
                Error_Report.newInstance(R.string.dialog_error_no_bluetooth).show(
                        getFragmentManager(), Error_Report.TAG);
                return;
            default:
                bluetoothAdapter = Ble_Utils.getBluetoothAdapter(getBaseContext());
        }
        printMessage("Bluetooth services checked....");

        if (bluetoothAdapter == null)
            return;

        printMessage("bluetooth adapter initialized");

        scanner = new BleScanner(bluetoothAdapter,
                new BluetoothAdapter.LeScanCallback() {
                    @Override
                    public void onLeScan(final BluetoothDevice device,
                                         final int rssi, byte[] scanRecord) {

                        UriBeacon uriurl = UriBeacon.parseFromBytes(scanRecord);
                        String urlStr = uriurl.toString();
                        leDeviceListAdapter.addDevice(device, rssi,urlStr);
                        leDeviceListAdapter.notifyDataSetChanged();

                        printMessage("Device Found...");


                        printMessage("Found device url:"+uriurl.toString()+" rssi value"+rssi);
//                        rssi_value=rssi;
                    }
                });
        scanner.setScanPeriod(SCAN_PERIOD);
    }
    public void displayAllRadio(){
        bt1.setVisibility(View.VISIBLE);
        bt1.setEnabled(false);
        bt2.setVisibility(View.VISIBLE);
        bt2.setEnabled(false);
        bt3.setVisibility(View.VISIBLE);
        bt3.setEnabled(false);
        bt4.setVisibility(View.VISIBLE);
        bt4.setEnabled(false);
        bt5.setVisibility(View.VISIBLE);
        bt5.setEnabled(false);
        bt6.setVisibility(View.VISIBLE);
        bt6.setEnabled(false);
        bt9.setVisibility(View.VISIBLE);
        bt9.setEnabled(false);
        bt10.setVisibility(View.VISIBLE);
        bt10.setEnabled(false);
    }
    public void checkRadio(int num){
        switch (num){
            case 1:
                bt1.setEnabled(true);
                bt1.setChecked(true);
                break;
            case 2:
                bt2.setEnabled(true);
                bt2.setChecked(true);
                break;
            case 3:
                bt3.setEnabled(true);
                bt3.setChecked(true);
                break;
            case 4:
                bt4.setEnabled(true);
                bt4.setChecked(true);
                break;
            case 5:
                bt5.setEnabled(true);
                bt5.setChecked(true);
                break;
            case 6:
                bt6.setEnabled(true);
                bt6.setChecked(true);
                break;
            case 9:
                bt9.setEnabled(true);
                bt9.setChecked(true);
                break;
            case 10:
                bt10.setEnabled(true);
                bt10.setChecked(true);
                break;


        }

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
    public void showSearchButton(){

    }
    public void hideSearchButton(){

    }
    public void showSearchingIcon(){

    }
    public void hideSearchingIcon(){

    }
//    private BluetoothAdapter.LeScanCallback mLeScanCallback =
//            new BluetoothAdapter.LeScanCallback() {
//
//                @Override
//                public void onLeScan(final BluetoothDevice device, int rssi, byte[] scanRecord) {
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            Toast.makeText(MainActivity.this, device.getName(), Toast.LENGTH_SHORT)
//                                    .show();
//                        }
//                    });
//                }
//            };

    public void searchForBLEBeacons(){
        printMessage("Start searching....");
        if (leDeviceListAdapter == null) {
            leDeviceListAdapter = new Ble_Adapter(getBaseContext(),MainActivity.this);
        }

        scanner.start();

        printMessage("Search Started");
    }
    public void printMessage(String text){
        System.out.println(text);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onDismiss(Error_Report f) {
        finish();
    }
    @Override
    public void onEnableBluetooth(Enable_Bluetooth f) {
        bluetoothAdapter.enable();
    }

    @Override
    public void onCancel(Enable_Bluetooth f) {
        finish();
    }
}
