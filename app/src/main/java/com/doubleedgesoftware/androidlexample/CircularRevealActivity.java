package com.doubleedgesoftware.androidlexample;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.Button;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by kevintanhongann on 8/9/14.
 */
public class CircularRevealActivity extends Activity {

    @InjectView(R.id.view_test)
    View testView;

    @InjectView(R.id.button_reveal)
    Button revealBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reveal);
        ButterKnife.inject(this);
        revealBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ViewAnimationUtils.createCircularReveal(testView, testView.getRight(), testView.getBottom(), testView.getHeight(), testView.getWidth() / 2).start();
            }
        });
    }
}
