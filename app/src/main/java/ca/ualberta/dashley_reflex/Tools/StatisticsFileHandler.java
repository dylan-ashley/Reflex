package ca.ualberta.dashley_reflex.Tools;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * Created by dashley on 2015-10-03.
 */
public interface StatisticsFileHandler {

    HashMap<String, LinkedList<Long>> loadFile(String filename) throws FileNotFoundException;

    void saveInFile(String filename, HashMap<String, LinkedList<Long>> hashMap) throws IOException;
}
