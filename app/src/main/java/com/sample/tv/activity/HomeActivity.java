/*
 * Copyright (C) 2014 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package com.sample.tv.activity;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.sample.tv.R;
import com.sample.tv.fragment.DialFragment;
import com.sample.tv.fragment.VerticalGridFragment;
import com.sample.tv.fragment.VerticalGridFragment1;


/**
 *
 */
public class HomeActivity extends Activity implements View.OnClickListener {

    public static void start(Context context) {
        Intent intent = new Intent(context, HomeActivity.class);
        context.startActivity(intent);
    }

    private static final String[] TAGS = {"dial", "contact", "my"};

    private FragmentManager manager;

    private int showTabIndex = -1;

    private TextView dialTab;
    private TextView contactTab;
    private TextView myTab;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        findViews();
        setViewsListener();
        init();
        selectTab(0);
    }

    private void findViews() {
        dialTab = (TextView) findViewById(R.id.dial_tab);
        contactTab = (TextView) findViewById(R.id.contact_tab);
        myTab = (TextView) findViewById(R.id.settings_tab);
    }

    private void setViewsListener() {
        dialTab.setOnClickListener(this);
        contactTab.setOnClickListener(this);
        myTab.setOnClickListener(this);
    }

    private void init() {
        manager = getFragmentManager();
    }

    private void selectTab(int index) {
        if (index == showTabIndex) {
            return;
        }
        dialTab.setSelected(index == 0);
        contactTab.setSelected(index == 1);
        myTab.setSelected(index == 2);
        FragmentTransaction transaction = manager.beginTransaction();
        hideFragment(showTabIndex, transaction);
        showTabIndex = index;
        showFragment(showTabIndex, transaction);
        transaction.commit();
    }

    private void hideFragment(int tabIndex, FragmentTransaction transaction) {
        Fragment fragment = getFragmentByIndex(tabIndex);
        if (fragment != null) {
            transaction.hide(fragment);
        }
    }

    private Fragment getFragmentByIndex(int index) {
        if (index >= 0 && index < TAGS.length) {
            return manager.findFragmentByTag(TAGS[index]);
        }
        return null;
    }

    private void showFragment(int tabIndex, FragmentTransaction transaction) {
        Fragment fragment = getFragmentByIndex(tabIndex);
        if (fragment == null) {
            switch (tabIndex) {
                case 0:
                    fragment = new DialFragment();
                    break;
                case 1:
                    fragment = new VerticalGridFragment();
                    break;
                case 2:
                    fragment = new VerticalGridFragment1();
                    break;
            }
            transaction.add(R.id.tab_container, fragment, TAGS[tabIndex]);
            //transaction.addToBackStack(TAGS[tabIndex]);
        } else {
            transaction.show(fragment);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.dial_tab:
                selectTab(0);
                return;
            case R.id.contact_tab:
                selectTab(1);
                return;
            case R.id.settings_tab:
                selectTab(2);
                //                VerticalGridActivity.start(this);
                return;
        }
    }

}
