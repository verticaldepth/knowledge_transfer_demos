package com.davewarnock.demoapp.groupchat;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.davewarnock.demoapp.R;

import java.util.List;

public class GroupChatActivity extends AppCompatActivity {

    GroupChatController controller;
    ListView messagesView;
    EditText messageEntryView;
    GroupChatMessageAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.groupchat_activity);
        this.controller = new GroupChatController(this);
        messageEntryView = findViewById(R.id.group_chat_enter_message);
        messagesView = findViewById(R.id.group_chat_list);
        adapter = new GroupChatMessageAdapter(this);
        messagesView.setAdapter(adapter);
    }

    public void setSendButtonListener(View.OnClickListener listener) {
        findViewById(R.id.group_chat_send_message).setOnClickListener(listener);
    }

    public String getMessage() {
        return messageEntryView.getText().toString();
    }

    public void clearMessage() {
        messageEntryView.setText("");
    }

    public void setMessages(List<GroupChatMessage> messages) {
        adapter.clear();
        adapter.addAll(messages);
        adapter.notifyDataSetChanged();
    }
}
