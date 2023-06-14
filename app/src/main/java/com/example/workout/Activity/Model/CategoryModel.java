package com.example.workout.Activity.Model;

import java.util.ArrayList;
import java.util.List;

public class CategoryModel {
    public List<SubcategoryModel> arraylist2;
    private String routine_id;
    private String routine_name;

    public String getRoutine_id() {
        return routine_id;
    }

    public String getRoutine_name() {
        return routine_name;
    }

    public void setRoutine_id(String routine_id) {
        this.routine_id = routine_id;
    }

    public void setRoutine_name(String routine_name) {
        this.routine_name = routine_name;
    }
}
