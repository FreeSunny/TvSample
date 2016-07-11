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

package com.sample.tv.fragment;

import android.os.Bundle;
import android.support.v17.leanback.widget.ArrayObjectAdapter;
import android.support.v17.leanback.widget.FocusHighlight;
import android.support.v17.leanback.widget.OnItemViewClickedListener;
import android.support.v17.leanback.widget.OnItemViewSelectedListener;
import android.support.v17.leanback.widget.Presenter;
import android.support.v17.leanback.widget.Row;
import android.support.v17.leanback.widget.RowPresenter;
import android.support.v17.leanback.widget.VerticalGridPresenter;
import android.util.Log;

import com.sample.tv.constant.Extra;
import com.sample.tv.model.Contact;
import com.sample.tv.model.ContactProvider;
import com.sample.tv.presenter.CardPresenter;

import java.util.List;

/*
 * VerticalGridFragment shows a grid of videos
 */
public class VerticalGridFragment1 extends android.support.v17.leanback.app.VerticalGridFragment {
    private static final String TAG = "VerticalGridFragment";

    private static final int DEFAULT_COLUMNS = 4;

    private int numColumns = DEFAULT_COLUMNS;

    private ArrayObjectAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        // setTitle(getString(R.string.vertical_grid_title));
        getParams();
        setupFragment();
    }

    private void setupFragment() {
        VerticalGridPresenter gridPresenter = new VerticalGridPresenter(FocusHighlight.ZOOM_FACTOR_SMALL);
        gridPresenter.setNumberOfColumns(numColumns);
        //gridPresenter.setShadowEnabled(false);
        setGridPresenter(gridPresenter);

        mAdapter = new ArrayObjectAdapter(new CardPresenter());

        List<Contact> contacts = ContactProvider.getContactList();
        mAdapter.addAll(0, contacts);

        setAdapter(mAdapter);

        setOnItemViewClickedListener(new ItemViewClickedListener());
        setOnItemViewSelectedListener(new ItemViewSelectedListener());
    }

    public void getParams() {
        if (getArguments() != null) {
            numColumns = getArguments().getInt(Extra.COLUMNS);
        }
    }

    private final class ItemViewClickedListener implements OnItemViewClickedListener {
        @Override
        public void onItemClicked(Presenter.ViewHolder itemViewHolder, Object item, RowPresenter.ViewHolder
                rowViewHolder, Row row) {

            if (item instanceof Contact) {
                Contact contact = (Contact) item;
                // TODO
            }
        }
    }


    private final class ItemViewSelectedListener implements OnItemViewSelectedListener {
        @Override
        public void onItemSelected(Presenter.ViewHolder itemViewHolder, Object item, RowPresenter.ViewHolder
                rowViewHolder, Row row) {
        }
    }

}
