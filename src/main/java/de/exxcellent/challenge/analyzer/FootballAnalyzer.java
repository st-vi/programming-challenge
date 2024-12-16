package de.exxcellent.challenge.analyzer;

import de.exxcellent.challenge.model.FootballDataEntry;

import java.util.List;

/**
 * A class responsible for analyzing football match data and providing various statistics (currently just min goal difference).
 */
public class FootballAnalyzer {
    /**
     * Returns the name of the team with the minimum goal difference (i.e., the absolute difference
     * between the goals scored and goals allowed). If there are multiple teams with the same minimum goal
     * difference, the first one encountered in the list will be returned.
     *
     * @param footballDataEntries A list of {@link FootballDataEntry} objects containing match data for each team.
     * @return The name of the team with the minimum goal difference.
     * @throws IllegalArgumentException if the list is empty.
     */
    public String getTeamNameWithMinGoalDifference(List<FootballDataEntry> footballDataEntries){
        if (footballDataEntries.isEmpty()) {
            throw new IllegalArgumentException("At least one FootballDataEntry must be present for analyzing it.");
        }
        int minGoalDifference = Math.abs(footballDataEntries.get(0).numberGoals() - footballDataEntries.get(0).numberGoalsAllowed());
        String teamName = footballDataEntries.get(0).teamName();
        for(int i=1;i< footballDataEntries.size();i++){
            FootballDataEntry footballDataEntry = footballDataEntries.get(i);
            int currentGoalDifference = Math.abs(footballDataEntry.numberGoals() - footballDataEntry.numberGoalsAllowed());
            if(currentGoalDifference < minGoalDifference) {
                minGoalDifference = currentGoalDifference;
                teamName = footballDataEntry.teamName();
            }
        }
        return teamName;
    }
}
