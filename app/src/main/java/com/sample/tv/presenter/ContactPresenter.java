/*
 * Copyright (C) 2015 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.sample.tv.presenter;

import android.support.v17.leanback.widget.Presenter;
import android.util.Log;
import android.view.ViewGroup;

import com.sample.tv.R;
import com.sample.tv.model.Contact;
import com.sample.tv.widget.ContactView;

/*
 * A CardPresenter is used to generate Views and bind Objects to them on demand. 
 * It contains an Image CardView
 */
public class ContactPresenter extends Presenter {
    private static final String TAG = "CardPresenter";
    private static int sSelectedBackgroundColor;
    private static int sDefaultBackgroundColor;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent) {
        Log.d(TAG, "onCreateViewHolder");

        sDefaultBackgroundColor = parent.getResources().getColor(R.color.white_35_transparent);
        sSelectedBackgroundColor = parent.getResources().getColor(R.color.white_60_transparent);


        ContactView contactView = new ContactView(parent.getContext()) {
            @Override
            public void setSelected(boolean selected) {
                updateCardBackgroundColor(this, selected);
                super.setSelected(selected);
            }
        };

        contactView.setFocusable(true);
        contactView.setFocusableInTouchMode(true);
        updateCardBackgroundColor(contactView, false);
        return new ViewHolder(contactView);
    }

    private static void updateCardBackgroundColor(ContactView view, boolean selected) {
        int color = selected ? sSelectedBackgroundColor : sDefaultBackgroundColor;
        view.setBackgroundColor(color);
        //view.findViewById(R.id.info_field).setBackgroundColor(color);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, Object item) {
        Contact contact = (Contact) item;
        ContactView contactView = (ContactView) viewHolder.view;
        Log.d(TAG, "onBindViewHolder");
        contactView.setHead(contact.getHeadResId());
        contactView.setName(contact.getName());
        contactView.setPhone(contact.getPhone());

    }

    @Override
    public void onUnbindViewHolder(ViewHolder viewHolder) {
        Log.d(TAG, "onUnbindViewHolder");
        ContactView contactView = (ContactView) viewHolder.view;
        // Remove references to images so that the garbage collector can free up memory
        contactView.setHead(0);
    }
}
