package com.davewarnock.demoapp.contacts;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.davewarnock.demoapp.R;

public class ContactSummaryView extends ConstraintLayout {

    public ContactSummaryView(@NonNull Context context) {
        super(context);
        inflate(context, R.layout.contact_summary_view, this);
    }

    public ContactSummaryView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        inflate(context, R.layout.contact_summary_view, this);
    }

    public ContactSummaryView(@NonNull Context context, @Nullable AttributeSet attrs,
                              int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflate(context, R.layout.contact_summary_view, this);
    }

    public void setAvatar(@DrawableRes int id) {
        ((ImageView) findViewById(R.id.image_thumbnail)).setImageResource(id);
    }

    public void setName(String name) {
        ((TextView) findViewById(R.id.person_name)).setText(name);
    }

    public void setPhoneNumber(String phoneNumber) {
        ((TextView) findViewById(R.id.phone_number)).setText(phoneNumber);
    }

    public void setEmailAddress(String emailAddress) {
        ((TextView) findViewById(R.id.email_address)).setText(emailAddress);
    }
}
