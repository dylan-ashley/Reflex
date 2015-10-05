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

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * Interface that defines a set of method that provide saving and loading of files.
 *
 * Rationale: This interface helps to separate the android independent components of this application from the android
 * dependent components.
 */
public interface StatisticsFileHandler {

    /**
     * Loads a JSON structure from a file.
     *
     * @param filename name of the file
     * @return value loaded from the file
     * @throws FileNotFoundException
     */
    HashMap<String, LinkedList<Long>> loadFile(String filename) throws FileNotFoundException;

    /**
     * Saves a JSON structure into a file.
     *
     * @param filename name of the file
     * @param hashMap value to save
     * @throws IOException
     */
    void saveInFile(String filename, HashMap<String, LinkedList<Long>> hashMap) throws IOException;
}
