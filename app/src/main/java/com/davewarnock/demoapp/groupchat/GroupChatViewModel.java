package com.davewarnock.demoapp.groupchat;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class GroupChatViewModel extends AndroidViewModel {

    private final MutableLiveData<List<GroupChatMessage>> messages = new MutableLiveData<>();
    private final GroupChatModel model;

    public GroupChatViewModel(@NonNull @NotNull Application application) {
        super(application);
        this.model = new GroupChatModel();
        model.addListener(() -> {
            SimpleExecutor.queue(() -> messages.postValue(model.getMessages()));
        });
        SimpleExecutor.queue(() -> messages.postValue(model.getMessages()));
    }

    LiveData<List<GroupChatMessage>> getLiveData() {
        return messages;
    }

    void sendMessage(String message) {
        SimpleExecutor.queue(new AddMessageTask(model, message));
    }
}
