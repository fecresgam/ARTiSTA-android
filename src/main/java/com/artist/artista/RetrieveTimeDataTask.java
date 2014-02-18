package com.artist.artista;

import android.os.AsyncTask;
import com.artist.TransportScheduleDAO;

import java.util.List;

class RetrieveTimeDataTask extends AsyncTask<String, Void, List<Long>>
{

    private Exception exception;

    @Override
    protected List<Long> doInBackground(String... params)
    {
        return TransportScheduleDAO.INSTANCE.findNext();
    }

}