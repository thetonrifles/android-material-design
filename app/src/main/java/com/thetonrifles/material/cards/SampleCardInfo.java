package com.thetonrifles.material.cards;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.thetonrifles.material.R;

public class SampleCardInfo extends CardInfo {

    private String mTitle;

    public SampleCardInfo(Context context, String title) {
        super(context);
        mTitle = title;
    }

    @Override
    public int getLayout() {
        return R.layout.view_card_sample;
    }

    @Override
    public int getType() {
        return Type.SAMPLE.ordinal();
    }

    @Override
    public ViewHolder createViewHolder(View view) {
        SampleViewHolder holder = new SampleViewHolder();
        holder.title = (TextView) view.findViewById(R.id.txt_title);
        return holder;
    }

    @Override
    public void renderView(ViewHolder holder) {
        SampleViewHolder view = (SampleViewHolder) holder;
        view.title.setText(mTitle);
    }

    @Override
    public boolean canBeShown() {
        return true;
    }

    public static class SampleViewHolder implements ViewHolder {

        TextView title;

    }

}
