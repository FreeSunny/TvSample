package com.sample.tv.activity;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.sample.tv.R;
import com.sample.tv.fragment.VerticalGridFragment;
import com.sample.tv.fragment.VerticalGridFragment1;



/**
 * {@link VerticalGridActivity} loads {@link VerticalGridFragment}
 */
public class VerticalGridActivity extends Activity {

    public static void start(Context context) {
        Intent intent = new Intent(context, VerticalGridActivity.class);
        context.startActivity(intent);
    }

    private static final String TAG = VerticalGridActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vertical_grid);
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.root, new VerticalGridFragment1());
        transaction.commit();
    }
}
