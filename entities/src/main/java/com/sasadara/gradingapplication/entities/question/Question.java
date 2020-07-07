package com.sasadara.gradingapplication.entities.question;

public abstract class Question {

    protected long id;
    protected String name;
    protected Results result;
    protected int timeSpentMints;
    protected int numberOfAttempts;
    protected String review;

    public long getId() {
        return id;
    }

    public abstract void updateId(long id);

    public String getName() {
        return name;
    }

    public abstract void updateName(String name);

    public Results getResult() {
        return result;
    }

    public abstract void updateResult(String result);

    public int getTimeSpentMints() {
        return timeSpentMints;
    }

    public abstract void updateTimeSpentMints(int timeSpentMints);

    public int getNumberOfAttempts() {
        return numberOfAttempts;
    }

    public abstract void updateNumberOfAttempts(int numberOfAttempts);

    public String getReview() {
        return review;
    }

    public abstract void updateReview(String review);

}
