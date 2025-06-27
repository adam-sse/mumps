package de.uni_hildesheim.mump.ui.courseList;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import de.uni_hildesheim.mump.databinding.FragmentCourselistBinding;

public class CourseListFragment extends Fragment {

    private FragmentCourselistBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        CourseListViewModel courseListViewModel =
                new ViewModelProvider(this).get(CourseListViewModel.class);

        binding = FragmentCourselistBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textMyCoursesView = binding.textMyCourses;
        final TextView textAllCoursesView = binding.textAllCourses;
        courseListViewModel.getMyCoursesText().observe(getViewLifecycleOwner(), textMyCoursesView::setText);
        courseListViewModel.getAllCoursesText().observe(getViewLifecycleOwner(), textAllCoursesView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}