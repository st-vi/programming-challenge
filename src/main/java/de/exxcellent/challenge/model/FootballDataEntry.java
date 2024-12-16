package de.exxcellent.challenge.model;

/**
 * A record representing football (soccer) match data for a specific team.
 * This record contains information about the team's name, the number of goals scored,
 * and the number of goals allowed in a match.
 */
public record FootballDataEntry(String teamName, int numberGoals, int numberGoalsAllowed) {
}
