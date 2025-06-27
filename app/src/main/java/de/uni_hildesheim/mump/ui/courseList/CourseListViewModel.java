package de.uni_hildesheim.mump.ui.courseList;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CourseListViewModel extends ViewModel {

    private final MutableLiveData<String> myCoursesText;
    private final MutableLiveData<String> allCoursesText;

    public CourseListViewModel() {
        myCoursesText = new MutableLiveData<>();
        allCoursesText = new MutableLiveData<>();
    }

    public LiveData<String> getMyCoursesText() {
        return myCoursesText;
    }

    public LiveData<String> getAllCoursesText() {
        return allCoursesText;
    }
}