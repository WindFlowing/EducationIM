package com.yifan.wzxy.educationim;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;

import java.io.IOException;


public class MainActivity extends Activity {

    private Button button;
    LoginTask task;
    XMPPTCPConnection connection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
    }

    private void login() {

        task = new LoginTask();
        task.execute("dfg");


    }

    class LoginTask extends AsyncTask<String, Integer, Integer> {

        @Override
        protected Integer doInBackground(String... strings) {


            try {


                connection = getConnection();
                connection.connect();
                connection.login("yifan1", "123");
            } catch (SmackException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (XMPPException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }

            return 0;
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            Toast.makeText(MainActivity.this, connection.isAuthenticated() + "-----", Toast.LENGTH_LONG).show();
        }
    }

    private XMPPTCPConnection getConnection() {

        XMPPTCPConnectionConfiguration.Builder builder = XMPPTCPConnectionConfiguration.builder();
        builder.setServiceName("127.0.0.1");
        builder.setHost("192.9.5.199");
        builder.setPort(5222);
        builder.setCompressionEnabled(false);
        builder.setDebuggerEnabled(false);
        builder.setSendPresence(true);
        builder.setSecurityMode(ConnectionConfiguration.SecurityMode.disabled);
        XMPPTCPConnection connection = new XMPPTCPConnection(builder.build());

        return connection;
    }

}
