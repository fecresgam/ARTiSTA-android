package com.artist.artista;

import android.os.AsyncTask;
import com.artist.Country;
import com.artist.TransportScheduleDAO;
import com.artist.vo.ArrivalData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class RetrieveTimeDataTask extends AsyncTask<String, Void, List<ArrivalData>>
{

    private Exception exception;

    @Override
    protected List<ArrivalData> doInBackground(String... params)
    {
        final String param = params[0];

        List<ArrivalData> result = Collections.emptyList();

        if (MainActivity.FAKE_KEY1.equals(param))
        {
            final List<String> lines = new ArrayList<String>();
            lines.add("14");

            result = TransportScheduleDAO.INSTANCE.findNext(Country.SPAIN,
                                                                                    "EMT_MADRID",
                                                                                    "5443",
                                                                                    null,
                                                                                    lines);

        }
        else if (MainActivity.FAKE_KEY2.equals(param))
        {
            final List<String> lines = new ArrayList<String>();
            lines.add("27");

            result = TransportScheduleDAO.INSTANCE.findNext(Country.SPAIN,
                                                            "EMT_MADRID",
                                                            "5443",
                                                            null,
                                                            lines);


        }

        return result;
    }

}