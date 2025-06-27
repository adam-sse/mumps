package de.uni_hildesheim.mump.ui.courseList;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CourseListViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public CourseListViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is courseList fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}