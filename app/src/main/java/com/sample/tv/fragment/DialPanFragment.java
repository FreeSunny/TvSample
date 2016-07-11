package com.sample.tv.fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sample.tv.R;
import com.sample.tv.model.ContactProvider;
import com.sample.tv.util.ToastUtil;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * Use the {@link DialPanFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DialPanFragment extends Fragment implements View.OnClickListener {

    private TextView showPhone;

    private ImageView dialBnt;

    public DialPanFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment DialFragment.
     */
    public static DialPanFragment newInstance() {
        DialPanFragment fragment = new DialPanFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dial_pan, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        findViews();
    }

    private void findViews() {
        showPhone = (TextView) getView().findViewById(R.id.show_phone);
        dialBnt = (ImageView) getView().findViewById(R.id.dial_icon);
        dialBnt.setOnClickListener(this);
        dialBnt.setTag(-2);
        dialBnt.setEnabled(false);
        View view0 = getView().findViewById(R.id.input_key_number_0);
        view0.setTag(0);
        view0.setOnClickListener(this);
        View view1 = getView().findViewById(R.id.input_key_number_1);
        view1.setTag(1);
        view1.setOnClickListener(this);
        view1.setNextFocusUpId(R.id.dial_tab);
        View view2 = getView().findViewById(R.id.input_key_number_2);
        view2.setTag(2);
        view2.setOnClickListener(this);
        view2.setNextFocusUpId(R.id.dial_tab);
        View view3 = getView().findViewById(R.id.input_key_number_3);
        view3.setTag(3);
        view3.setOnClickListener(this);
        view3.setNextFocusUpId(R.id.dial_tab);
        View view4 = getView().findViewById(R.id.input_key_number_4);
        view4.setTag(4);
        view4.setOnClickListener(this);
        View view5 = getView().findViewById(R.id.input_key_number_5);
        view5.setTag(5);
        view5.setOnClickListener(this);
        View view6 = getView().findViewById(R.id.input_key_number_6);
        view6.setTag(6);
        view6.setOnClickListener(this);
        View view7 = getView().findViewById(R.id.input_key_number_7);
        view7.setTag(7);
        view7.setOnClickListener(this);
        View view8 = getView().findViewById(R.id.input_key_number_8);
        view8.setTag(8);
        view8.setOnClickListener(this);
        View view9 = getView().findViewById(R.id.input_key_number_9);
        view9.setTag(9);
        view9.setOnClickListener(this);
        View viewDel = getView().findViewById(R.id.input_key_number_del);
        viewDel.setTag(-1);
        viewDel.setOnClickListener(this);
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
        int tag = (int) v.getTag();
        if (tag == -2) {
            dial();
        } else if (tag == -1) {// DEL
            delNumber();
        } else {
            inputNumber(tag);
        }
    }

    private void delNumber() {
        String text = showPhone.getText().toString();
        if (text != null && text.length() > 0) {
            text = text.substring(0, text.length() - 1);
            showPhone.setText(text);
        }
        dialBtnState(text);
    }

    private void inputNumber(int tag) {
        String text = showPhone.getText().toString();
        if (text == null) {
            text = new String(String.valueOf(tag));
        } else {
            text = text + tag;
        }
        dialBtnState(text);
        showPhone.setText(text);
    }

    private void dial() {
        String text = showPhone.getText().toString();
        int len = TextUtils.isEmpty(text) ? 0 : text.length();
        if (len != 11) {
            ToastUtil.showToast("你输入的账号不合法!", getActivity());
            showPhone.setText("");
        } else {
            String uid = ContactProvider.getUidByPhone(text);
            if (TextUtils.isEmpty(uid)) {
                ToastUtil.showToast("该账号不存在!", getActivity());
            } else {
                // TODO
            }
        }
    }

    private void dialBtnState(String text) {
        dialBnt.setEnabled(!TextUtils.isEmpty(text));
    }
}
