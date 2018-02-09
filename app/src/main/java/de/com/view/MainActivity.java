package de.com.view;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import de.com.R;
import de.com.controller.Controller;
import de.com.model.adapter.DisplayListAdapter;
import de.com.model.pojo.CountryAct;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener, Controller.CallbackListener {

    private static String TAG = MainActivity.class.getSimpleName();

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;

    public RecyclerView.LayoutManager getmLayoutManager() {
        return mLayoutManager;
    }

    private RecyclerView.LayoutManager mLayoutManager;
    private SwipeRefreshLayout swipeLayout;
    private Controller mController;
    private TextView emptyView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        emptyView = (TextView) findViewById(R.id.empty_view);
        Toolbar topToolBar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(topToolBar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(null);
        }
        swipeLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);
        if (swipeLayout != null) {
            swipeLayout.setOnRefreshListener(this);
        }

        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mController = new Controller(MainActivity.this, MainActivity.this);

        sendRequest();


    }


    // Initiating Menu XML file (menu.xml)
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }


    /**
     * Event Handling for Individual menu item selected
     * Identify single menu item by it's id
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.refresh:
                dispatchRefresh(true);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void sendRequest() {
        mController.startFetching();


    }

    private void dispatchRefresh(boolean refresh) {
        swipeLayout.setRefreshing(refresh);
        sendRequest();
        swipeLayout.setRefreshing(!refresh);
    }


    @Override
    public void onRefresh() {
        dispatchRefresh(true);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void onFetchComplete(CountryAct countryAct) {
        swipeLayout.setVisibility(View.VISIBLE);
        mRecyclerView.setVisibility(View.VISIBLE);
        emptyView.setVisibility(View.GONE);

        Log.d(TAG, countryAct.toString());
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(countryAct.getTitle());
        }
        mAdapter = new DisplayListAdapter(getApplicationContext(), countryAct.getRows());
        mRecyclerView.setAdapter(mAdapter);

    }

    @Override
    public void onFetchFailed() {
        swipeLayout.setVisibility(View.GONE);
        mRecyclerView.setVisibility(View.GONE);
        emptyView.setVisibility(View.VISIBLE);
    }
}
