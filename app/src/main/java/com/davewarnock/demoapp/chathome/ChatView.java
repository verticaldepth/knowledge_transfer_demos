package com.davewarnock.demoapp.chathome;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.davewarnock.demoapp.R;

public class ChatView extends ConstraintLayout {

    public ChatView(@NonNull Context context) {
        super(context);
    }

    public ChatView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ChatView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    void setData(ChatViewModel model) {
        ImageView avatar = findViewById(R.id.image_thumbnail);
        avatar.setImageDrawable(
                getResources().getDrawable(model.getAvatar(), getContext().getTheme()));

        TextView chatName = findViewById(R.id.chat_name);
        chatName.setText(model.getChatName());

        TextView lastMessageTime = findViewById(R.id.last_message_time);
        lastMessageTime.setText(model.getLastMessageTime().toString());

        TextView lastMessage = findViewById(R.id.last_message);
        lastMessage.setText(model.getLastMessage());

        @ColorInt
        int tintColor = getResources().getColor(model.isRead()
                                                ? R.color.design_default_color_primary
                                                : R.color.material_on_background_disabled,
                                                getContext().getTheme());
        ImageView readMark = findViewById(R.id.read_mark);
        readMark.setImageTintMode(PorterDuff.Mode.DST_ATOP);
        readMark.setImageTintList(ColorStateList.valueOf(tintColor));
    }
}
