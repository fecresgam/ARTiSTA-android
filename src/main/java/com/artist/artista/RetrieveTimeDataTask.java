package com.artist.artista;

import android.os.AsyncTask;
import com.artist.Country;
import com.artist.TransportScheduleDAO;
import com.artist.vo.ArrivalData;

import java.util.ArrayList;
import java.util.List;

class RetrieveTimeDataTask extends AsyncTask<String, Void, List<ArrivalData>>
{

    private Exception exception;

    @Override
    protected List<ArrivalData> doInBackground(String... params)
    {
        final List<String> lines = new ArrayList<String>();
        lines.add("10");

        return TransportScheduleDAO.INSTANCE.findNext(Country.SPAIN,
                                                      "EMT_MADRID",
                                                      "5442",
                                                      null,
                                                      lines);
    }

}