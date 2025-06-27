package de.uni_hildesheim.mump.ui.leaderBoard;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LeaderBoardViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public LeaderBoardViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Leaderboard");
    }

    public LiveData<String> getText() {
        return mText;
    }
}