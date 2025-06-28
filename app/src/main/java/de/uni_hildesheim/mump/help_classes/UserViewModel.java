package de.uni_hildesheim.mump.help_classes;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class UserViewModel extends ViewModel {
    private final MutableLiveData<String> userName = new MutableLiveData<>();

    public void setUserName(String name) {
        userName.setValue(name);
    }

    public LiveData<String> getUserName() {
        return userName;
    }

    // Optional: Check if user is "logged in"
    public boolean isLoggedIn() {
        return userName.getValue() != null && !userName.getValue().isEmpty();
    }
}