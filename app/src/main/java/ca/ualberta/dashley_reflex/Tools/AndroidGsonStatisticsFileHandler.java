package ca.ualberta.dashley_reflex.Tools;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * Created by dashley on 2015-10-03.
 */
public class AndroidGsonStatisticsFileHandler implements StatisticsFileHandler {

    private Context context;

    public AndroidGsonStatisticsFileHandler(Context context) {
        this.context = context;
    }

    @Override
    public HashMap<String, LinkedList<Long>> loadFile(String filename) throws FileNotFoundException {
        FileInputStream fis = context.openFileInput(filename);
        BufferedReader in = new BufferedReader(new InputStreamReader(fis));
        Gson gson = new Gson();
        // https://google-gson.googlecode.com/svn/trunk/gson/docs/javadocs/com/google/gson/Gson.html; 2015-09-23
        Type type = new TypeToken<HashMap<String, LinkedList<Long>>>() {}.getType();
        return gson.fromJson(in, type);
    }

    @Override
    public void saveInFile(String filename, HashMap<String, LinkedList<Long>> hashMap) throws IOException {
        FileOutputStream fos = context.openFileOutput(filename, Context.MODE_PRIVATE);
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));
        Gson gson = new Gson();
        gson.toJson(hashMap, out);
        out.flush();
        fos.close();
    }
}
