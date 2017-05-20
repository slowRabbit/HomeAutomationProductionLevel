package com.cyris.homeautomation.majorproject.activities;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.cyris.homeautomation.BluetoothDeviceListDialog;
import com.cyris.homeautomation.BluetoothSerial;
import com.cyris.homeautomation.BluetoothSerialListener;
import com.cyris.homeautomation.majorproject.R;
import com.cyris.homeautomation.majorproject.fragments.FragmentAppliances;
import com.cyris.homeautomation.majorproject.fragments.FragmentHome;
import com.cyris.homeautomation.majorproject.fragments.FragmentInsight;
import com.cyris.homeautomation.majorproject.fragments.FragmentScheduler;
import com.cyris.homeautomation.majorproject.fragments.FragmentSetCurrent;
import com.cyris.homeautomation.majorproject.fragments.FragmentSetVoltage;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MainActivity extends ActionBarActivity
        implements NavigationView.OnNavigationItemSelectedListener, FragmentSetVoltage.FragmentSetVoltageListener, FragmentSetCurrent.FragmentSetCurrentListener, FragmentHome.FragmentHomeSendValue,BluetoothSerialListener, BluetoothDeviceListDialog.OnDeviceSelectedListener{

    int fragmentCheckCounter=0;

    private static final int REQUEST_ENABLE_BLUETOOTH = 1;

    private BluetoothSerial bluetoothSerial;
//
//    private ScrollView svTerminal;
//    private TextView tvTerminal;
//    private EditText etSend;

    private MenuItem actionConnect, actionDisconnect;

    private boolean crlf = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);

        replaceWithHomeFragment();

        //bluetooth part

        // Find UI views and set listeners
//        svTerminal = (ScrollView) findViewById(R.id.terminal);
//        tvTerminal = (TextView) findViewById(R.id.tv_terminal);
//        etSend = (EditText) findViewById(R.id.et_send);
//        etSend.setOnEditorActionListener(new TextView.OnEditorActionListener() {
//            @Override
//            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
//                if (actionId == EditorInfo.IME_ACTION_SEND) {
//                    String send = etSend.getText().toString().trim();
//                    if (send.length() > 0) {
//                        bluetoothSerial.write(send, crlf);
//                        etSend.setText("");
//                    }
//                }
//                return false;
//            }
//        });

        // Create a new instance of BluetoothSerial
        bluetoothSerial = new BluetoothSerial(this, this);

//        Intent i=getIntent();
////        if(i!=null)
////        {
////            int fromScheduler=i.getIntExtra("fromScheduler", 1);
////            if(fromScheduler==1)
////            {
////                sendScheduledMessageToHardware();
////            }
////        }
//
//        boolean fromScheduler = getIntent().getBooleanExtra("fromScheduler", false);
//        if(fromScheduler)
//        {
//            sendScheduledMessageToHardware();
//        }

    }

//    public void sendScheduledMessageToHardware()
//    {
//
//        Toast.makeText(this, "Message Sending...", Toast.LENGTH_SHORT).show();
//
//        if(bluetoothSerial.getState()==BluetoothSerial.STATE_CONNECTED)
//        {
//            bluetoothSerial.write("empty string", crlf);
//            Toast.makeText(this, "Message Sent", Toast.LENGTH_SHORT).show();
//       }
//
//    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {

            if(fragmentCheckCounter==1)
            {
                super.onBackPressed();
            }
            else
            {
                replaceWithHomeFragment();
            }
        }
    }

    public void replaceWithHomeFragment()
    {
        fragmentCheckCounter=1;
        FragmentHome fragmentHome=new FragmentHome();
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_content_menu, fragmentHome, "FragmentHome").commit();
        setTitle("Home");
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home_fragment) {
            fragmentCheckCounter=1;
            replaceWithHomeFragment();
        }
        else if(id==R.id.nav_select_rooms)
        {
            fragmentCheckCounter=2;
            showDeviceListDialog();
        }
        else if(id==R.id.nav_insights)
        {
            //set voltage fragment
            fragmentCheckCounter=3;
            FragmentInsight fragmentInsight=new FragmentInsight();
            getSupportFragmentManager().beginTransaction().replace(R.id.fl_content_menu, fragmentInsight, "FragmentInsight").commit();
            setTitle("Insights");
        }
        else if(id==R.id.nav_appliances)
        {
            //set current fragment
            fragmentCheckCounter=4;
            FragmentAppliances fragmentAppliances=new FragmentAppliances();
            getSupportFragmentManager().beginTransaction().replace(R.id.fl_content_menu, fragmentAppliances, "FragmentAppliances").commit();
            setTitle("Appliances");
        }
        else if(id==R.id.nav_scheduler)
        {
            fragmentCheckCounter=5;
            FragmentScheduler fragmentScheduler=new FragmentScheduler();
            getSupportFragmentManager().beginTransaction().replace(R.id.fl_content_menu, fragmentScheduler, "FragmentScheduler").commit();
            setTitle("Scheduler");
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    protected void onStart() {
        super.onStart();

        // Check Bluetooth availability on the device and set up the Bluetooth adapter
        bluetoothSerial.setup();
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Open a Bluetooth serial port and get ready to establish a connection
        if (bluetoothSerial.checkBluetooth() && bluetoothSerial.isBluetoothEnabled()) {
            if (!bluetoothSerial.isConnected()) {
                bluetoothSerial.start();

            }
        }
        updateBluetoothState();


    }

    @Override
    protected void onStop() {
        super.onStop();

        // Disconnect from the remote device and close the serial port
       // bluetoothSerial.stop();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_terminal, menu);

        actionConnect = menu.findItem(R.id.action_connect);
        actionDisconnect = menu.findItem(R.id.action_disconnect);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_connect) {
            showDeviceListDialog();
            return true;
        } else if (id == R.id.action_disconnect) {
            bluetoothSerial.stop();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void invalidateOptionsMenu() {
        if (bluetoothSerial == null)
            return;

        // Show or hide the "Connect" and "Disconnect" buttons on the app bar
        if (bluetoothSerial.isConnected()) {
            if (actionConnect != null)
                actionConnect.setVisible(false);
            if (actionDisconnect != null)
                actionDisconnect.setVisible(true);
        } else {
            if (actionConnect != null)
                actionConnect.setVisible(true);
            if (actionDisconnect != null)
                actionDisconnect.setVisible(false);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQUEST_ENABLE_BLUETOOTH:
                // Set up Bluetooth serial port when Bluetooth adapter is turned on
                if (resultCode == Activity.RESULT_OK) {
                    bluetoothSerial.setup();
                }
                break;
        }
    }

    private void updateBluetoothState() {
        // Get the current Bluetooth state
        final int state;
        if (bluetoothSerial != null)
            state = bluetoothSerial.getState();
        else
            state = BluetoothSerial.STATE_DISCONNECTED;

        // Display the current state on the app bar as the subtitle
        String subtitle;
        switch (state) {
            case BluetoothSerial.STATE_CONNECTING:
                subtitle = getString(R.string.status_connecting);
                break;
            case BluetoothSerial.STATE_CONNECTED:
                subtitle = getString(R.string.status_connected, bluetoothSerial.getConnectedDeviceName());
                break;
            default:
                subtitle = getString(R.string.status_disconnected);
                break;
        }
        sentRoomConnectionDetailToHomeFragment(subtitle);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setSubtitle(subtitle);
        }
    }

    private void showDeviceListDialog() {
        // Display dialog for selecting a remote Bluetooth device
        BluetoothDeviceListDialog dialog = new BluetoothDeviceListDialog(this);
        dialog.setOnDeviceSelectedListener(this);
        //dialog.setTitle(R.string.paired_devices);
        dialog.setTitle("Choose a room :");
       // dialog.setDevices(bluetoothSerial.getPairedDevices());

        Set<BluetoothDevice> pairedDevices = bluetoothSerial.getPairedDevices();
        String btDeviceName;

        Set<BluetoothDevice> finalDevices = new HashSet<BluetoothDevice>();

        List<String> s = new ArrayList<String>();
        for(BluetoothDevice bt : pairedDevices) {
            //s.add(bt.getName());

            btDeviceName=bt.getName();
            if(btDeviceName.contains("Room")||(btDeviceName.contains("Room")))
            {
               // BluetoothDevice btDevice=bt;
             //   btDeviceName.replace("Room", "");
              //  btDeviceName.replace("room", "");
               // s.add(btDeviceName);
//                finalDevices.add(bt); // use this line enabled if you want to use this if condition block
            }
            finalDevices.add(bt);
            //Log.i("Device", "Device Name :"+bt.getName());
        }


        dialog.setDevices(finalDevices);
        dialog.showAddress(false);
        dialog.show();
    }

    /* Implementation of BluetoothSerialListener */

    @Override
    public void onBluetoothNotSupported() {
        new AlertDialog.Builder(this)
                .setMessage(R.string.no_bluetooth)
                .setPositiveButton(R.string.action_quit, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setCancelable(false)
                .show();
    }

    @Override
    public void onBluetoothDisabled() {
        Intent enableBluetooth = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
        startActivityForResult(enableBluetooth, REQUEST_ENABLE_BLUETOOTH);
    }

    @Override
    public void onBluetoothDeviceDisconnected() {
        invalidateOptionsMenu();
        updateBluetoothState();
    }

    @Override
    public void onConnectingBluetoothDevice() {
        updateBluetoothState();
    }

    @Override
    public void onBluetoothDeviceConnected(String name, String address) {
        invalidateOptionsMenu();
        updateBluetoothState();
    }

    String singleMessage=null;

    @Override
    public void onBluetoothSerialRead(String message) {
        // Print the incoming message on the terminal screen


        Log.i("rcvd", "Message recieved is :"+message);

        //we will decide whether string is starting or not
        if(message.contains("!"))
        {
            //means its the start of message
            singleMessage=null;
            singleMessage=message;
        }
        else if(message.contains("@"))
        {
            //means its the end of message
            singleMessage=singleMessage+message;

            //now we will remove special symbols from the final string
            //1. !
            //2. ^

            singleMessage=singleMessage.replaceAll("!", "");
            singleMessage=singleMessage.replaceAll("@", "");

//            tvTerminal.append(getString(R.string.terminal_message_template,
//                    bluetoothSerial.getConnectedDeviceName(),
//                    singleMessage));

            Log.i("recievedMessage", "Message :"+singleMessage);
            sendParamsToFragment(singleMessage);
        }
        else
        {
            //part of message is being sent
            singleMessage=singleMessage+message;
        }

        //svTerminal.post(scrollTerminalToBottom);
    }


    public void sentRoomConnectionDetailToHomeFragment(String connectionStatus)
    {
        FragmentHome fragmentHome=(FragmentHome)getSupportFragmentManager().findFragmentByTag("FragmentHome");
        if(fragmentHome!=null)
        {
            fragmentHome.updateConnectionStatus(connectionStatus);
        }
    }

    public void sendParamsToFragment(String param)
    {
        Calendar c=Calendar.getInstance();
        SimpleDateFormat df=new SimpleDateFormat("HH:mm:ss dd-MM-yyyy");
        String currentDateTime=df.format(c.getTime());
        Log.i("currentdate", currentDateTime);

        FragmentHome fragmentHome=(FragmentHome)getSupportFragmentManager().findFragmentByTag("FragmentHome");
        if(fragmentHome!=null)
        {
            Log.i("fragmentt", "already created");
            fragmentHome.updateViews(param, currentDateTime);
        }
        else
        {
            Log.i("fragmentt", "need to create");
//            Bundle args=new Bundle();
//            args.putString("params", param);
//            args.putString("datetime", currentDateTime);
//            fragmentSystemParameters.setArguments(args);
        }

    }

    @Override
    public void onBluetoothSerialWrite(String message) {
        // Print the outgoing message on the terminal screen
//        tvTerminal.append(getString(R.string.terminal_message_template,
//                bluetoothSerial.getLocalAdapterName(),
//                message));
//        svTerminal.post(scrollTerminalToBottom);
    }

    /* Implementation of BluetoothDeviceListDialog.OnDeviceSelectedListener */

    @Override
    public void onBluetoothDeviceSelected(BluetoothDevice device) {
        // Connect to the selected remote Bluetooth device
        bluetoothSerial.connect(device);
    }

    /* End of the implementation of listeners */

    private final Runnable scrollTerminalToBottom = new Runnable() {
        @Override
        public void run() {
            // Scroll the terminal screen to the bottom
            //svTerminal.fullScroll(ScrollView.FOCUS_DOWN);
        }
    };

    @Override
    public void setVoltage(int voltage, int time) {

        String sVoltage=getStringForInteger(voltage, 3);
        String sTime=getStringForInteger(time, 4);

        String finalSetVoltageString="V"+sVoltage+"T"+sTime;
        bluetoothSerial.write(finalSetVoltageString, crlf);
        Log.i("strnng", "Final str :"+finalSetVoltageString);
        Toast.makeText(getApplicationContext(), "Voltage Set !", Toast.LENGTH_SHORT).show();
    }

    public String getStringForInteger(int value, int len)
    {
        String numberAsString=String.valueOf(value);
        StringBuilder sb=new StringBuilder();
        while (sb.length()+numberAsString.length()<len)
        {
            sb.append('0');
        }
        sb.append(numberAsString);
        String paddedNumberAsString=sb.toString();
        return paddedNumberAsString;

    }

    @Override
    public void setCurrent(int current, int time) {

        String sCurrent=getStringForInteger(current, 4);
        String sTime=getStringForInteger(time, 4);

        String finalSetCurrentString="I"+sCurrent+"T"+sTime;
        bluetoothSerial.write(finalSetCurrentString, crlf);
        Log.i("strnng", "Final str :"+finalSetCurrentString);
        Toast.makeText(getApplicationContext(), "Current Set !", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void sendValueForTesting(int voltage, int time) {

        bluetoothSerial.write(voltage+"-"+time, crlf);
        Log.i("sending", "message sent from main activity to hardware");
    }
}
