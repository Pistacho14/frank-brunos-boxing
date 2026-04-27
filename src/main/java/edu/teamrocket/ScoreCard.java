package edu.teamrocket;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ScoreCard {

    private final String color;
    private String redCorner;
    private String blueCorner;
    private List<Round> rounds = new ArrayList<>();

    public ScoreCard(String color) {
        this.color = color;
    }

    public void setRCorner(String boxerName) {
        this.redCorner = boxerName;
    }

    public void setBCorner(String boxerName) {
        this.blueCorner = boxerName;
    }

    public int getRedBoxerFinalScore() {
        int score = 0;
        for (Round round : rounds) {
            score += round.getRedBoxerScore();
        }
        return score;
    }

    public int getBlueBoxerFinalScore() {
        int score = 0;
        for (Round round : rounds) {
            score += round.getBlueBoxerScore();
        }
        return score;
    }

    public List<Round> getRounds() {
        return rounds;
    }

    public byte getNumRounds() {
        return (byte) rounds.size();
    }

    public void addRound(Round round) {
        this.rounds.add(round);
    }

    public void loadJudgeScoreCard(String[] judgeScoreCard) {


        Optional<Round> roundsScore = Optional.empty();
        
            for (String round : judgeScoreCard) {
                roundsScore = Optional.ofNullable(RoundFactory.getRound(round));
                roundsScore.ifPresent(this::addRound);
            }
    }

    private String viewRounds() {
        StringBuilder score = new StringBuilder("\n");
        byte roundCounter = 1;
        int redBoxerScore = 0;
        int blueBoxerScore = 0;

        score.append("Round\tScore\tRound\tScore\tRound\n");
        score.append("Score\tTotal\t     \tTotal\tScore\n");

        for (Round round : rounds) {
            redBoxerScore += round.getRedBoxerScore();
            blueBoxerScore += round.getBlueBoxerScore();
            score.append(round.getRedBoxerScore())
                    .append("\t")
                    .append(redBoxerScore)
                    .append("\t")
                    .append(roundCounter++)
                    .append("\t")
                    .append(blueBoxerScore)
                    .append("\t")
                    .append(round.getBlueBoxerScore())
                    .append("\n");
        }
        return score.toString();
    }

    @Override
    public String toString() {
        return viewRounds();
    }
}