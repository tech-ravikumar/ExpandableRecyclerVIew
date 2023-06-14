package com.example.workout.Activity.Model;

public class SubcategoryModel {
    private String exercise_id;
    private String exer_name;
    private String exer_image;
    private String exer_description;
    private String routine_id;

    public String getExercise_id() {
        return exercise_id;
    }

    public String getExer_name() {
        return exer_name;
    }

    public String getExer_image() {
        return exer_image;
    }

    public String getExer_description() {
        return exer_description;
    }

    public String getRoutine_id() {
        return routine_id;
    }

    public void setExercise_id(String exercise_id) {
        this.exercise_id = exercise_id;
    }

    public void setExer_name(String exer_name) {
        this.exer_name = exer_name;
    }

    public void setExer_image(String exer_image) {
        this.exer_image = exer_image;
    }

    public void setExer_description(String exer_description) {
        this.exer_description = exer_description;
    }

    public void setRoutine_id(String routine_id) {
        this.routine_id = routine_id;
    }
}
