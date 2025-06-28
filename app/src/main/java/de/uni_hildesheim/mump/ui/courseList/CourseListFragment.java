package de.uni_hildesheim.mump.ui.courseList;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.navigation.NavController;


import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import de.uni_hildesheim.mump.R;
import de.uni_hildesheim.mump.api.Api;
import de.uni_hildesheim.mump.databinding.FragmentCourselistBinding;

public class CourseListFragment extends Fragment implements CourseInfoAdapter.OnItemClickListener {

    private FragmentCourselistBinding binding;
    private RecyclerView recyclerView;
    private CourseInfoAdapter adapter;
    private ExecutorService executorService;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentCourselistBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        recyclerView = root.findViewById(R.id.recyclerView_MyCourses);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new CourseInfoAdapter(new ArrayList<>(), this);
        recyclerView.setAdapter(adapter);


        Executors.newSingleThreadExecutor().execute(() -> {
            // Add dynamic items
            try {
                List<CourseInfo> courseDetailsList = Api.INSTANCE.getAllCourses().stream()
                        .map(apiCourse -> new CourseInfo(
                                apiCourse.name(),// Get the owner
                                apiCourse.rewardPerEvent()   // Get the points
                        ))
                        .collect(Collectors.toList());
                getActivity().runOnUiThread(() -> {
                    adapter.addItems(courseDetailsList);
                });
            } catch (IOException e) {
                Toast.makeText(getContext(), "Error loading courses. Please check connection.", Toast.LENGTH_LONG).show();
            }
        });

        Button myButton = binding.button1;

        // Setze einen OnClickListener für den Button
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myButton.setText("Button wurde geklickt!");;
            }
        });


        return root;
    }

    @Override
    public void onItemClick(CourseInfo item) {

        Button myButton = binding.button1;

        NavController navController = Navigation.findNavController(requireView()); // requireView() ist sicherer hier
        Bundle bundle = new Bundle();
        bundle.putString("course_name", item.getName());
        bundle.putInt("points", item.getPoints());
        navController.navigate(R.id.action_courseList_to_course, bundle);

        myButton.setText(String.valueOf(item.getPoints()));
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}