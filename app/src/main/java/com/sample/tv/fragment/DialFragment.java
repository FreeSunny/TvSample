package com.sample.tv.fragment;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sample.tv.R;
import com.sample.tv.constant.Extra;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * Use the {@link DialFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DialFragment extends Fragment implements View.OnClickListener {

    public DialFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment DialFragment.
     */
    public static DialFragment newInstance() {
        DialFragment fragment = new DialFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dial, container, false);
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        addFragments();
        //        findViews();
        //        setViewListener();
    }

    private void addFragments() {
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.dial_pan, new DialPanFragment());
        VerticalGridFragment fragment = new VerticalGridFragment();
        Bundle args = new Bundle();
        args.putInt(Extra.COLUMNS, Extra.DIAL_COLUMNS);
        fragment.setArguments(args);
        transaction.replace(R.id.contact_pan, fragment);
        transaction.commit();
    }


    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        }
    }
}
