package de.uni_hildesheim.mump.ui.courseList;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import de.uni_hildesheim.mump.R;
import de.uni_hildesheim.mump.api.Api;
import de.uni_hildesheim.mump.databinding.FragmentCourselistBinding;
import de.uni_hildesheim.mump.ui.course.*;

public class CourseListFragment extends Fragment {

    private FragmentCourselistBinding binding;
    private RecyclerView recyclerView;
    private TextAdapter adapter;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentCourselistBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        recyclerView = root.findViewById(R.id.recyclerView_MyCourses);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new TextAdapter(new ArrayList<>(), courseName -> {
            Bundle bundle = new Bundle();
            bundle.putString("courseName", courseName); // Pass course name

            CourseFragment courseFragment = new CourseFragment();
            courseFragment.setArguments(bundle);
//@TODO potentiell falsch!!!!!!
            requireActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.nav_host_fragment_activity_main, courseFragment) // update container ID if needed
                    .addToBackStack(null)
                    .commit();
        });

        recyclerView.setAdapter(adapter);


        Executors.newSingleThreadExecutor().execute(() -> {
            // Add dynamic items
            try {
                List<String> courseNames = Api.INSTANCE.getAllCourses().stream()
                        .map(course -> course.name())
                        .collect(Collectors.toList());
                getActivity().runOnUiThread(() -> {
                    adapter.addItems(courseNames);
                });
            } catch (IOException e) {
                getActivity().runOnUiThread(() -> {
                    adapter.addItems(List.of("IOException: " + e.getMessage()));
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