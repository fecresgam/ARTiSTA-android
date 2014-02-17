package com.artist.artista.activities;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.artist.TransportScheduleDAO;
import com.artist.artista.R;

import java.util.List;

public class MainActivity extends ActionBarActivity {

    private TextView mTvTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_main);

        mTvTime = (TextView) findViewById(R.id.textviev_time);


        Button buttonRefresh = (Button) findViewById(R.id.button_refresh);
        buttonRefresh.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startCountdown();
            }
        });


        startCountdown();


    }



    private void startCountdown()
    {
        MyCount counter = new MyCount(loadTime() * 1000, 1000);
        counter.start();
    }


    private Long loadTime()
    {
        Long result = null;

        final List<Long> list = TransportScheduleDAO.INSTANCE.findNext();
        if (list.size() > 0)
        {
            result = list.get(0);
        }

        return result;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }


    // countdowntimer is an abstract class, so extend it and fill in methods
    public class MyCount extends CountDownTimer
    {
        public MyCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }


        @Override
        public void onFinish() {
            mTvTime.setText("--");
        }

        @Override
        public void onTick(long millisUntilFinished) {
            mTvTime.setText("Left: " + millisUntilFinished / 1000);
        }
    }

}
