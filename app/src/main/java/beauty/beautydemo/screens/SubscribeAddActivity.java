package beauty.beautydemo.screens;

import android.inputmethodservice.Keyboard;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;

import java.util.ArrayList;

import beauty.beautydemo.R;
import beauty.beautydemo.adapter.SubscribeAdapter;
import beauty.beautydemo.adapter.SubscribeAllAdapter;
import beauty.beautydemo.adapter.SubscribeContentAdapter;
import beauty.beautydemo.base.BeautyBaseActivity;
import beauty.beautydemo.entity.SubcribeContentListItem;
import beauty.beautydemo.entity.SubscribeListItem;
import beauty.beautydemo.tools.Constants;
import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by LJW on 15/7/12.
 */
public class SubscribeAddActivity extends BeautyBaseActivity {

    @InjectView(R.id.rv_sub_content)
    RecyclerView mList;
    @InjectView(R.id.toolbar)
    Toolbar mToolbar;
    private SubscribeAllAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscribe_add);

        ButterKnife.inject(this);

        setupToolBar();
        setupList();
    }

    private void setupToolBar() {
        mToolbar.setTitle("订阅");
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.drawable.ic_keyboard_arrow_left_white_24dp);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exit();
            }
        });
    }

    private void setupList() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        mList.setLayoutManager(manager);
        mList.setHasFixedSize(true);

        ArrayList<SubscribeListItem> list = Constants.getSubListAll();
        adapter = new SubscribeAllAdapter(this, list);
        adapter.setDelay(getResources().getInteger(R.integer.activity_change_duration));
        mList.setAdapter(adapter);
        mList.setOverScrollMode(View.OVER_SCROLL_NEVER);
        mList.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_DRAGGING) {
                    adapter.setAnimationsLocked(true);
                }
            }
        });

    }

    private void exit() {
        finish();
        overridePendingTransition(R.anim.nothing, R.anim.slide_out_bottom);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
        }
        return super.onKeyDown(keyCode, event);
    }
}
