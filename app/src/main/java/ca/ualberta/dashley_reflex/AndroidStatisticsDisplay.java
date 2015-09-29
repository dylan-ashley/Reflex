package ca.ualberta.dashley_reflex;

import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by dashley on 2015-09-28.
 */
public class AndroidStatisticsDisplay implements StatisticsDisplay {

    private final ListView list;
    private final ArrayList<String> statisticsOnDisplay = new ArrayList<>();
    private final ArrayAdapter<String> arrayAdapter;

    public AndroidStatisticsDisplay(ListView list) {
        this.list = list;
        this.arrayAdapter = new ArrayAdapter<String>(this, R.layout.list_item, statisticsOnDisplay);
        this.list.setAdapter(arrayAdapter);
        arrayAdapter.notifyDataSetChanged();
    }

    @Override
    public void addStatisticsToDisplay(String statistic) {
        statisticsOnDisplay.add(statistic);
    }

    @Override
    public void removeStatisticsFromDisplay(String statistic) {
        statisticsOnDisplay.remove(statistic);
    }

    @Override
    public void refreshDisplay() {
        arrayAdapter.notifyDataSetChanged();
    }
}
