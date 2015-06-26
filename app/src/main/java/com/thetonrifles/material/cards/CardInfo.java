package com.thetonrifles.material.cards;

import android.content.Context;
import android.view.View;

/**
 * Wrapper class representing one card to show
 * in CardsAdapter
 */
public abstract class CardInfo {

    public enum Type {

        TOOLBAR,
        SAMPLE

    }

    private Context mContext;

    public CardInfo(Context context) {
        mContext = context;
    }

    protected Context getContext() {
        return mContext;
    }

    public abstract int getLayout();

    public abstract int getType();

    public abstract ViewHolder createViewHolder(View view);

    public abstract void renderView(ViewHolder holder);

    public abstract boolean canBeShown();

    public interface ViewHolder {

    }

}
