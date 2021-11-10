package com.davewarnock.demoapp.chathome;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.davewarnock.demoapp.R;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

public class ChatView extends ConstraintLayout {

    public ChatView(@NonNull Context context) {
        super(context);
        inflate(context, R.layout.chathome_view, this);
        setPadding(24, 24, 24, 24);
    }

    public ChatView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        inflate(context, R.layout.chathome_view, this);
        setPadding(24, 24, 24, 24);
        processAttrs(context, attrs);
    }

    public ChatView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflate(context, R.layout.chathome_view, this);
        setPadding(24, 24, 24, 24);
        processAttrs(context, attrs);
    }

    public void setAvatar(@DrawableRes int avatarId) {
        ImageView avatar = findViewById(R.id.image_thumbnail);
        avatar.setImageDrawable(getResources().getDrawable(avatarId, getContext().getTheme()));
    }

    public void setChatName(String name) {
        TextView chatName = findViewById(R.id.chat_name);
        chatName.setText(name);
    }

    public void setChatMessage(String message) {
        TextView lastMessageTime = findViewById(R.id.last_message);
        lastMessageTime.setText(message);
    }

    public void setLastMessageDate(OffsetDateTime lastMessageDate) {
        TextView lastMessage = findViewById(R.id.last_message_time);
        lastMessage.setText(lastMessageDate.format(DateTimeFormatter.ISO_LOCAL_TIME));
    }

    public void setHasBeenRead(boolean isRead) {
        @ColorInt
        int tintColor = getResources().getColor(isRead
                                                ? R.color.design_default_color_primary
                                                : R.color.material_on_background_disabled,
                                                getContext().getTheme());
        ImageView readMark = findViewById(R.id.read_mark);
        readMark.setImageTintList(ColorStateList.valueOf(tintColor));
    }

    private void processAttrs(Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.ChatView, 0, 0);
        try {
            setChatName(ta.getString(R.styleable.ChatView_chatName));
            setHasBeenRead(ta.getBoolean(R.styleable.ChatView_chatName, false));
        } finally {
            ta.recycle();
        }
    }
}
