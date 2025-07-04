package de.uni_hildesheim.mump.ui.courseList;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import de.uni_hildesheim.mump.help_classes.CourseInfo;
import de.uni_hildesheim.mump.help_classes.UserViewModel;
import de.uni_hildesheim.mump.ui.courseList.CourseInfoAdapter; // Or your actual package

public class CourseListFragment extends Fragment implements CourseInfoAdapter.OnItemClickListener {

    private UserViewModel userViewModel;
    private FragmentCourselistBinding binding;
    private RecyclerView recyclerView;
    private CourseInfoAdapter adapter;
    private ExecutorService executorService;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        userViewModel = new ViewModelProvider(requireActivity()).get(UserViewModel.class);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentCourselistBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        recyclerView = root.findViewById(R.id.recyclerView_MyCourses);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new CourseInfoAdapter(new ArrayList<>(), this);
        recyclerView.setAdapter(adapter);
        System.out.println("printed that fukking statement");  // <-- Add this

        Executors.newSingleThreadExecutor().execute(() -> {
            // Add dynamic items
            try {
                List<CourseInfo> courseDetailsList = Api.INSTANCE.getAllCourses().stream()
                        .map(apiCourse -> new CourseInfo(
                                apiCourse.name(),// Get the owner
                                apiCourse.rewardPerEvent()   // Get the points
                        ))
                        .collect(Collectors.toList());
                System.out.println("hha that fukking statement");
                getActivity().runOnUiThread(() -> {
                    adapter.addItems(courseDetailsList);
                });
            } catch (IOException e) {
                ;
            }
        });

        return root;
    }

    @Override
    public void onItemClick(CourseInfo item) {
        System.out.println("printed that fukking statement");  // <-- Add this

        NavController navController = Navigation.findNavController(requireView()); // requireView() ist sicherer hier
        Bundle bundle = new Bundle();
        bundle.putString("course_name", item.getName());
        bundle.putInt("points", item.getPoints());
        navController.navigate(R.id.action_courseList_to_course, bundle);

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}