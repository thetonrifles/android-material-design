package com.thetonrifles.material.cards;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Adapter to be used for rendering cards in list
 */
public class CardsAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private List<CardInfo> mCards;
    private List<CardInfo> mFilteredCards;

    public CardsAdapter(Context context, List<CardInfo> cards) {
        mContext = context;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        mCards = cards;
        mFilteredCards = new ArrayList<>();
        // filter cards
        filterCards();
    }

    /**
     * Support method for including into filtered
     * list only cards that can be shown
     */
    private synchronized void filterCards() {
        mFilteredCards.clear();
        for (CardInfo card : mCards) {
            if (card.canBeShown()) {
                mFilteredCards.add(card);
            }
        }
    }

    @Override
    public void notifyDataSetChanged() {
        filterCards();
        super.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mFilteredCards.size();
    }

    @Override
    public Object getItem(int i) {
        return mFilteredCards.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return CardInfo.Type.values().length;
    }

    @Override
    public int getItemViewType(int position) {
        return ((CardInfo) getItem(position)).getType();
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        CardInfo.ViewHolder holder;

        // getting item to render
        CardInfo item = (CardInfo) getItem(i);

        // building layout
        if (view == null) {
            view = mLayoutInflater.inflate(item.getLayout(), parent, false);
            holder = item.createViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (CardInfo.ViewHolder) view.getTag();
        }

        // rendering view
        item.renderView(holder);

        return view;
    }

}
