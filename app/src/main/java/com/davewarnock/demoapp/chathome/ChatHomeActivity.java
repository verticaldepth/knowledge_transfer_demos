package com.davewarnock.demoapp.chathome;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.davewarnock.demoapp.R;

import java.util.List;

public class ChatHomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chathome_activity);
        ChatHomeAdapter adapter = new ChatHomeAdapter();
        RecyclerView recyclerView = findViewById(R.id.chat_home_view);
        recyclerView.setAdapter(adapter);
        List<ChatViewModel> testData = MockChatFactory.create(20, this);
        adapter.bind(testData);
    }
}
