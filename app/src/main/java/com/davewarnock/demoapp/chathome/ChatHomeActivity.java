package com.davewarnock.demoapp.chathome;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.davewarnock.demoapp.R;

import java.util.List;

public class ChatHomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chathome_activity);
    }

    @Override
    protected void onStart() {
        super.onStart();
        updatePinned();
        updateAdapter();
    }

    void updatePinned() {
        ChatView chatView = findViewById(R.id.chat_pinned_room);
        ChatViewModel viewModel = MockChatFactory.create(this);
        chatView.setAvatar(viewModel.getAvatar());
        chatView.setChatName(viewModel.getChatName());
        chatView.setChatMessage(viewModel.getLastMessage());
        chatView.setLastMessageDate(viewModel.getLastMessageTime());
        chatView.setHasBeenRead(viewModel.isRead());
    }

    void updateAdapter() {
        RecyclerView recyclerView = findViewById(R.id.chat_home_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ChatHomeAdapter adapter = new ChatHomeAdapter();
        recyclerView.setAdapter(adapter);
        List<ChatViewModel> testData = MockChatFactory.create(20, this);
        adapter.bind(testData);
    }
}
