package com.doubleedgesoftware.androidlexample;

import android.animation.Animator;
import android.animation.ValueAnimator;
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
        /*revealBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });*/

        revealBtn.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                ValueAnimator revealAnimator = ViewAnimationUtils.createCircularReveal(testView,
                        testView.getRight(),
                        testView.getBottom(), testView.getHeight() * 2, testView.getWidth() / 2);
                revealAnimator.start();
                revealAnimator.addListener( new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animator) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animator) {
                        testView.setVisibility(View.INVISIBLE);
                    }

                    @Override
                    public void onAnimationCancel(Animator animator) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animator) {

                    }
                });
                return true;
            }
        });
    }
}
