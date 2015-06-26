package com.thetonrifles.material.cards;

import android.content.Context;
import android.view.View;

import com.thetonrifles.material.R;

public class ToolbarCardInfo extends CardInfo {

    public ToolbarCardInfo(Context context) {
        super(context);
    }

    @Override
    public int getLayout() {
        return R.layout.view_card_toolbar;
    }

    @Override
    public int getType() {
        return Type.TOOLBAR.ordinal();
    }

    @Override
    public ViewHolder createViewHolder(View view) {
        return new ViewHolder() { };
    }

    @Override
    public void renderView(ViewHolder holder) {
    }

    @Override
    public boolean canBeShown() {
        return true;
    }

}
