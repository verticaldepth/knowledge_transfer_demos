package com.davewarnock.demoapp.contacts;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.davewarnock.demoapp.R;

import java.util.List;

public abstract class AbstractContactsFragment extends Fragment {

    private long timeToBind = -1;
    private int viewCount = -1;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ContactsActivityViewModel viewModel =
                new ViewModelProvider(requireActivity()).get(ContactsActivityViewModel.class);
        viewModel
                .getContactData(requireContext())
                .observe(getViewLifecycleOwner(), this::doBindData);
    }

    private void doBindData(List<ContactListElementViewModel> contactListElementViewModels) {
        timeToBind = -1;
        viewCount = -1;
        updatePerformanceTracker();
        long startTime = System.currentTimeMillis();
        bindData(contactListElementViewModels);
        long endTime = System.currentTimeMillis();
        this.timeToBind = endTime - startTime;
        updatePerformanceTracker();
    }

    protected abstract void bindData(
            List<ContactListElementViewModel> contactListElementViewModels);

    protected void setViewCount(int count) {
        this.viewCount = count;
        updatePerformanceTracker();
    }

    private void updatePerformanceTracker() {
        TextView performanceNote = requireView().findViewById(R.id.performance_tracker);
        if (timeToBind < 0 || viewCount < 0) {
            performanceNote.setText(R.string.performance_string_loading);
        } else {
            performanceNote.setText(
                    getResources().getString(R.string.performance_string, viewCount, timeToBind));
        }
    }
}
