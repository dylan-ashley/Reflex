package ca.ualberta.dashley_reflex.StatisticsActivity;

import android.app.Activity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import ca.ualberta.dashley_reflex.R;

/**
 * Created by dashley on 2015-09-28.
 */
public class AndroidStatisticsDisplay implements StatisticsDisplay {

    private final ArrayList<String> statisticsOnDisplay = new ArrayList<>();
    private final ArrayAdapter<String> arrayAdapter;

    public AndroidStatisticsDisplay(ListView list, Activity activity) {
        this.arrayAdapter = new ArrayAdapter<>(activity, R.layout.list_item, statisticsOnDisplay);
        list.setAdapter(arrayAdapter);
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
