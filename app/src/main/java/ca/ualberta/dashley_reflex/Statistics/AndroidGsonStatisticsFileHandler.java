// Copyright 2015 Dylan Robert Ashley
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package ca.ualberta.dashley_reflex.Statistics;

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

import ca.ualberta.dashley_reflex.Statistics.StatisticsFileHandler;

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
