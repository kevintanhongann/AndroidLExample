package com.doubleedgesoftware.androidlexample;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by kevintanhongann on 7/26/14.
 */
public class SecondActivity extends Activity {

    @InjectView(R.id.toolbar)
    Toolbar mToolbar;

    @InjectView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    String[] countries = {"Malaysia","Singapore","India","Thailand"};

    RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //this must be called before super
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        ButterKnife.inject(this);

        //testting toolbar API
        mToolbar.setBackgroundColor(getResources().getColor(android.R.color.background_dark));
        mToolbar.setTitle("Test Toolbar");

        //Menu
        mToolbar.inflateMenu(R.menu.toolbar_menu);

        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {

                switch (menuItem.getItemId()) {
                    case R.id.action_share:
                        Toast.makeText(SecondActivity.this, "Share", Toast.LENGTH_SHORT).show();
                        return true;
                }

                return false;
            }
        });

        //Navigation Icon
        mToolbar.setNavigationIcon(R.drawable.ic_launcher);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(SecondActivity.this, "Navigation", Toast.LENGTH_SHORT).show();
            }
        });



        //setActionBar(mToolbar);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mRecyclerView.setAdapter(new MyAdapter(R.layout.layout_row, countries));
    }

    class MyAdapter extends RecyclerView.Adapter<ViewHolder> {

        private int resource;
        private final String[] countries;

        public MyAdapter(int resource, String[] countries) {
            this.countries = countries;
            this.resource = resource;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(getApplicationContext()).inflate(resource, null);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder viewHolder, int i) {
            viewHolder.textView.setText(countries[i]);
        }

        @Override
        public int getItemCount() {
            return countries.length;
        }
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        @InjectView(R.id.imageView)
        ImageView imageView;

        @InjectView(R.id.textView)
        TextView textView;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }

    }
}
