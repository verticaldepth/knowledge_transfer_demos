package com.davewarnock.demoapp.contacts;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ContactsActivityViewModel extends ViewModel {

    private static final int MOCK_DATA_ENTRIES = 50;

    private static final ExecutorService THREAD_EXECUTOR =
            Executors.newSingleThreadScheduledExecutor();

    private MutableLiveData<List<ContactListElementViewModel>> liveData;

    public LiveData<List<ContactListElementViewModel>> getContactData(Context context) {
        if (liveData == null) {
            liveData = new MutableLiveData<>();
            regenerateData(context);
        }
        return liveData;
    }

    public void refreshData() {
        if (liveData != null) {
            liveData.postValue(liveData.getValue());
        }
    }

    public void regenerateData(Context context) {
        if (liveData != null) {
            THREAD_EXECUTOR.submit(() -> doRegenerateData(context));
        }
    }

    private void doRegenerateData(Context context) {
        if (liveData != null) {
            Collection<ContactModel> rawData =
                    MockContactFactory.create(MOCK_DATA_ENTRIES, context);
            Set<String> headers = new HashSet<>();

            List<ContactListElementViewModel> data = new ArrayList<>();
            for (ContactModel rawItem : rawData) {
                data.add(new ContactListElementViewModel(rawItem));
                if (rawItem.getName().length() > 0) {
                    headers.add(rawItem.getName().substring(0, 1).toUpperCase());
                }
            }
            for (String header : headers) {
                data.add(new ContactListElementViewModel(header));
            }
            data.sort(new ContactsListComparator());
            liveData.postValue(data);
        }
    }
}
