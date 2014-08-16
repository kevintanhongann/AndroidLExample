package com.doubleedgesoftware.androidlexample;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by kevintanhongann on 7/28/14.
 */
public class PaletteActivity extends Activity {

    @InjectView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_palette);
        ButterKnife.inject(this);

        //generate color palette from a photo
        Palette.generateAsync(BitmapFactory.decodeResource(getResources(), R.drawable.snowman), new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                //set adapter here
                mRecyclerView.setAdapter(new PaletteAdapter(R.layout.palette_item_row, palette));
            }
        });

    }

    class PaletteViewHolder extends RecyclerView.ViewHolder {

        @InjectView(R.id.paletteItem)
        View paletteView;

        public PaletteViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }
    }

    class PaletteAdapter extends RecyclerView.Adapter<PaletteViewHolder> {


        private final Palette palette;
        private int resource;

        public PaletteAdapter(int resource, Palette palette) {
            this.palette = palette;
            this. resource = resource;
        }

        @Override
        public PaletteViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View paletteView = LayoutInflater.from(getApplicationContext()).inflate(resource, null);
            return new PaletteViewHolder(paletteView);
        }

        @Override
        public void onBindViewHolder(PaletteViewHolder paletteViewHolder, int position) {
            paletteViewHolder.paletteView.setBackgroundColor(palette.getPallete().get(position).getRgb());
        }

        @Override
        public int getItemCount() {
            return palette.getPallete().size();
        }
    }
}
