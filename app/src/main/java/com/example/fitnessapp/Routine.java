package com.example.fitnessapp;

import android.content.Context;
import android.util.Log;

import java.util.List;

public class Routine {

    private String name;
    private List<Workout> workouts;
    private DatabaseHelper databaseHelper;

    // EFFECTS: constructs a routine that has a name, a list of workouts, and sets the week of
    //          the routine to 1
    public Routine(String name, List<Workout> workouts, Context context) {
        this.name = name;
        this.workouts = workouts;
        databaseHelper = new DatabaseHelper(context);
    }

    public String getName() {
        return name;
    }

    public List<Workout> getWorkouts() {
        return workouts;
    }

    public Workout getCurrentWorkout() {
        int currentWorkoutNum = getCurrentWorkoutNum();
        return workouts.get(currentWorkoutNum);
    }

    private int getCurrentWorkoutNum() {
        String tableName = this.name + "Table";
        int lastWorkout = databaseHelper.getLatestWorkout(tableName);
        Log.d("TAG", "Lastworkout " + lastWorkout);
        int currentWorkout = (lastWorkout + 1) % this.workouts.size();
        Log.d("TAG", "currentworkout " + currentWorkout);
        return currentWorkout;
    }
}
