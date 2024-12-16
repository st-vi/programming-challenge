package de.exxcellent.challenge.analyzer;

import de.exxcellent.challenge.model.FootballDataEntry;
import de.exxcellent.challenge.model.WeatherDataEntry;

import java.util.List;

public class FootballAnalyzer {
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
