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

/**
 * Interface that defines a set of method to manipulate a display.
 *
 * Rationale: This interface helps to separate the android independent components of this application from the android
 * dependent components.
 */
public interface StatisticsDisplay {

    /**
     * Adds a statistic to the display.
     *
     * @param statistic value to place on display
     */
    void addStatisticsToDisplay(String statistic);

    /**
     * Removes a statistic from the display.
     *
     * @param statistic value to place on display
     */
    void removeStatisticsFromDisplay(String statistic);

    /**
     * Refreshes the display.
     */
    void refreshDisplay();
}
