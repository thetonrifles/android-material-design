package com.thetonrifles.material;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.thetonrifles.material.cards.CardInfo;
import com.thetonrifles.material.cards.CardsAdapter;
import com.thetonrifles.material.cards.SampleCardInfo;
import com.thetonrifles.material.cards.ToolbarCardInfo;
import com.thetonrifles.material.controls.CollapsingTitleLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AbsListView.OnScrollListener {

    // toolbar
    private CollapsingTitleLayout mBackdropToolbar;
    private Toolbar mToolbar;
    private ImageView mToolbarBg;

    // ui elements
    private ListView mCardsList;
    private BaseAdapter mCardsAdapter;

    // business objects
    private List<CardInfo> mCards;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // building ui elements
        mCardsList = (ListView) findViewById(R.id.lst_cards);
        mBackdropToolbar = (CollapsingTitleLayout) findViewById(R.id.backdrop_toolbar);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbarBg = (ImageView) findViewById(R.id.bg_toolbar);

        // using toolbar instead of action bar
        setSupportActionBar(mToolbar);

        // defining toolbar title
        mBackdropToolbar.setTitle(getString(R.string.app_name));

        mCards = buildCards();
        mCardsAdapter = new CardsAdapter(this, mCards);
        mCardsList.setAdapter(mCardsAdapter);

        // defining scroll listener for handling
        // toolbar navigation effect
        mCardsList.setOnScrollListener(this);
    }

    /**
     * Support method for building cards list
     */
    private List<CardInfo> buildCards() {
        List<CardInfo> cards = new ArrayList<>();
        cards.add(new ToolbarCardInfo(this));
        for (int i = 1; i <= 10; i++) {
            cards.add(new SampleCardInfo(this, "card " + i));
        }
        return cards;
    }

    @Override
    public void onScrollStateChanged(AbsListView absListView, int i) {
    }

    @Override
    public void onScroll(AbsListView absListView, int firstVisibleItem,
                         int visibleItemCount, int totalItemCount) {
        // code allowing to handle toolbar collapse feature
        if (visibleItemCount > 0 && firstVisibleItem == 0) {
            final View firstView = absListView.getChildAt(firstVisibleItem);

            final int toolbarHeight = mToolbar.getHeight();
            final int y = -firstView.getTop();
            final float percent = y / (float) (firstView.getHeight() - mToolbar.getHeight());

            if (firstView.getBottom() > toolbarHeight) {
                mBackdropToolbar.setTranslationY(0);
                mBackdropToolbar.setScrollOffset(percent);
                // applying alpha and parallax effects to image
                mToolbarBg.setAlpha(1f - percent);
                mToolbarBg.setTop(absListView.getChildAt(0).getTop() / 2);
            } else {
                mBackdropToolbar.setTranslationY(0);
                mBackdropToolbar.setScrollOffset(1f);
                mToolbarBg.setAlpha(0f);
            }
        } else {
            mBackdropToolbar.setTranslationY(0);
            mBackdropToolbar.setScrollOffset(1f);
        }
    }

}
