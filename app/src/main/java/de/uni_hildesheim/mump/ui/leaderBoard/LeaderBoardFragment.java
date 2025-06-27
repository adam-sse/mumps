package de.uni_hildesheim.mump.ui.leaderBoard;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Executors;

import de.uni_hildesheim.mump.api.Api;
import de.uni_hildesheim.mump.databinding.FragmentLeaderboardBinding;
import de.uni_hildesheim.mump.dto.UserDto;

public class LeaderBoardFragment extends Fragment {

    private FragmentLeaderboardBinding binding;
    private LeaderBoardAdapter adapter;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentLeaderboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Initialize RecyclerView
        binding.recyclerLeaderboard.setLayoutManager(new LinearLayoutManager(getContext()));
        String currentUserId="Marcel";
        adapter = new LeaderBoardAdapter(List.of(), currentUserId); // initially empty
        binding.recyclerLeaderboard.setAdapter(adapter);

        // Load leaderboard data asynchronously
        Executors.newSingleThreadExecutor().execute(() -> {
            try {
                List<UserDto> users = Api.INSTANCE.getLeaderboard();

                // Switch to UI thread to update adapter
                requireActivity().runOnUiThread(() -> adapter.updateItems(users));

            } catch (IOException e) {
                e.printStackTrace();
                requireActivity().runOnUiThread(() -> {
                    Toast.makeText(requireContext(), "Failed to load leaderboard.", Toast.LENGTH_LONG).show();
                });
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
