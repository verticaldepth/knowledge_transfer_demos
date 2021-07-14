package com.davewarnock.demoapp.groupchat;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.davewarnock.demoapp.R;

import java.util.List;

public class GroupChatActivity extends AppCompatActivity {

    ListView messagesView;
    EditText messageEntryView;
    GroupChatMessageAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.groupchat_activity);
        messageEntryView = findViewById(R.id.group_chat_enter_message);
        messagesView = findViewById(R.id.group_chat_list);
        adapter = new GroupChatMessageAdapter(this);
        messagesView.setAdapter(adapter);
        final GroupChatViewModel viewModel =
                new ViewModelProvider(this).get(GroupChatViewModel.class);
        viewModel.getLiveData().observe(this, this::setMessages);
        setSendButtonListener((btn) -> {
            viewModel.sendMessage(getMessage());
            clearMessage();
        });
    }

    public void setSendButtonListener(View.OnClickListener listener) {
        findViewById(R.id.group_chat_send_message).setOnClickListener(listener);
    }

    private String getMessage() {
        return messageEntryView.getText().toString();
    }

    private void clearMessage() {
        messageEntryView.setText("");
    }

    private void setMessages(List<GroupChatMessage> messages) {
        adapter.clear();
        adapter.addAll(messages);
        adapter.notifyDataSetChanged();
    }
}
