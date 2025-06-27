package de.uni_hildesheim.mump.ui.leaderBoard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import de.uni_hildesheim.mump.databinding.FragmentLeaderboardBinding;

public class LeaderBoardFragment extends Fragment {

    private FragmentLeaderboardBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        LeaderBoardViewModel leaderBoardViewModel =
                new ViewModelProvider(this).get(LeaderBoardViewModel.class);

        binding = FragmentLeaderboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Initialize your leaderboard data here
        // You can access views from your layout like:
        // binding.btnFilter.setOnClickListener(v -> { /* filter logic */ });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}