package com.artist.artista;

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
import com.artist.vo.ArrivalData;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainActivity extends ActionBarActivity {

    private HashMap<String, TextView> textViews = new HashMap<String, TextView>();
    private HashMap<String, MyCount> counters = new HashMap<String, MyCount>();
    private HashMap<String, Button> buttons = new HashMap<String, Button>();

    /*
    private TextView mTvTime;
    private MyCount counter;
*/

    public static final String FAKE_KEY1 = "1";
    public static final String FAKE_KEY2 = "2";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_main);



        // 1
        textViews.put(FAKE_KEY1, (TextView) findViewById(R.id.textviev_time1));
        Button button = (Button) findViewById(R.id.button_refresh1);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startCountdown(FAKE_KEY1);
            }
        });
        buttons.put(FAKE_KEY1, button);


        // 2
        textViews.put(FAKE_KEY2, (TextView) findViewById(R.id.textviev_time2));
        button = (Button) findViewById(R.id.button_refresh2);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startCountdown(FAKE_KEY2);
            }
        });
        buttons.put(FAKE_KEY2, button);





        //Iniciarse al principio
        //startCountdown();


        //TODO fcres: Gestionar cuando el timepo es = 0 �30 seg?
    }



    private void startCountdown(final String key)
    {
        MyCount counter = counters.get(key);

        if (counter != null)
        {
            counter.cancel();
        }
        final Long totalTime = loadTime(key);
        if (totalTime != null)
        {
            counter = new MyCount(key, totalTime);
            counters.put(key, counter);
            counter.start();
        }
    }


    private Long loadTime(final String key)
    {
        Long result = null;


        final List<ArrivalData> list;
        try
        {
            list = new RetrieveTimeDataTask().execute(key).get();

            if (list.size()>0)
            {
                final Integer secondsLeft = list.get(0).getSecondsLeft();
                if (secondsLeft != null)
                {
                    result = secondsLeft.longValue();
                }
            }

        } catch (InterruptedException e)
        {
            e.printStackTrace();  //TODO FCRES: Autogenerated, modify
        } catch (ExecutionException e)
        {
            e.printStackTrace();  //TODO FCRES: Autogenerated, modify
        }

       // result = 200L;

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
        final private String key;

        public MyCount(final String aKey, final long secondsToCount) {
            super(secondsToCount * 1000, 1000);
            this.key = aKey;
        }


        @Override
        public void onFinish() {
            textViews.get(key).setText("--");
        }

        @Override
        public void onTick(long millisUntilFinished) {
  /*
            if (millisUntilFinished < 30000)
            {
                if ((millisUntilFinished / 1000) % 2 == 0)
                {
                    textViews.get(key).setText("> ");
                }
                else
                {
                    textViews.get(key).setText(" >");
                }
                textViews.get(key).setText(">>");

            }
            else
            {
                textViews.get(key).setText("" + millisUntilFinished / 1000);
            }
*/
            //textViews.get(key).setText("" + millisUntilFinished / 60000);
            textViews.get(key).setText("" + millisUntilFinished / 1000);

        }


    }

}
