package com.davewarnock.demoapp.groupchat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;

import com.davewarnock.demoapp.R;

import org.jetbrains.annotations.NotNull;

public class GroupChatMessageAdapter extends ArrayAdapter<GroupChatMessage> {

    public GroupChatMessageAdapter(@NonNull @NotNull Context context) {
        super(context, 0);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        GroupChatMessage message = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater
                    .from(getContext())
                    .inflate(R.layout.groupchat_post, parent, false);
        }
        return bindData(convertView, message);
    }

    private View bindData(View view, GroupChatMessage message) {
        TextView nameView = view.findViewById(R.id.message_sender_name);
        TextView messageView = view.findViewById(R.id.message_text);
        ImageView imageView = view.findViewById(R.id.message_avatar);
        nameView.setText(message.getSender());
        messageView.setText(message.getMessage());
        imageView.setImageDrawable(
                ResourcesCompat.getDrawable(view.getResources(), message.getAvatar(),
                                            view.getContext().getTheme()));
        return view;
    }
}
