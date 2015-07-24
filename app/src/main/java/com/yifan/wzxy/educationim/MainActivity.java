package com.yifan.wzxy.educationim;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.jivesoftware.smack.chat.Chat;
import org.jivesoftware.smack.chat.ChatManager;
import org.jivesoftware.smack.chat.ChatMessageListener;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smack.roster.Roster;
import org.jivesoftware.smack.roster.RosterEntry;
import org.jivesoftware.smack.roster.RosterListener;

import java.util.Collection;


public class MainActivity extends Activity implements View.OnClickListener {

    private EditText mSender;
    private EditText mMessage;
    private Button mSend;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Roster roster = Roster.getInstanceFor(LoginActivity.connection);
        Collection<RosterEntry> entries = roster.getEntries();

        System.out.println("-----------------------" + entries.size());
        for (RosterEntry entry : entries) {
            System.out.println("-----------------------" + entry);
        }
        registerRosterUpdate();
        mMessage = (EditText) findViewById(R.id.message);
        mSender = (EditText) findViewById(R.id.sender);
        mSend = (Button) findViewById(R.id.send_message);
        mSend.setOnClickListener(this);
    }

    private void registerRosterUpdate() {
        Roster roster = Roster.getInstanceFor(LoginActivity.connection);
        roster.addRosterListener(new RosterListener() {
            // Ignored events public void entriesAdded(Collection<String> addresses) {}
            public void entriesDeleted(Collection<String> addresses) {
                System.out.println("----------------entriesDeleted------------------" + addresses.toString());
            }

            @Override
            public void entriesAdded(Collection<String> addresses) {
                System.out.println("----------------entriesAdded------------------" + addresses.toString());
            }

            public void entriesUpdated(Collection<String> addresses) {
                System.out.println("----------------entriesUpdated------------------=" + addresses.toString());
            }

            public void presenceChanged(Presence presence) {
                System.out.println("Presence changed: " + presence.getFrom() + " " + presence);
            }
        });
    }

    private void sendChat() {
        ChatManager chatmanager = ChatManager.getInstanceFor(LoginActivity.connection);
        Chat newChat = chatmanager.createChat(mSender.getText().toString().trim(), new ChatMessageListener() {
            @Override
            public void processMessage(Chat chat, Message message) {
                System.out.println("Received message: " + message);
            }
        });
//        Chat newChat = chatmanager.createChat("jsmith@jivesoftware.com", new MessageListener() {
//            @Override
//            public void processMessage(Message message) {
//
//            }

//            public void processMessage(Chat chat, Message message) {
//                System.out.println("Received message: " + message);
//            }
//        });

        try {
            newChat.sendMessage(mMessage.getText().toString().trim());
        }
        catch (Exception e) {
            System.out.println("Error Delivering block");
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.send_message:
                sendChat();
                break;
        }
    }
}
