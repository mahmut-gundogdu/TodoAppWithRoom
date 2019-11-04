package com.mahmutgundogdu.todoapp;

import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {
    private int count = 0;

    public int getCount() {
        return count;
    }

    public void increase() {
        this.count = this.count + 1;
    }

}
