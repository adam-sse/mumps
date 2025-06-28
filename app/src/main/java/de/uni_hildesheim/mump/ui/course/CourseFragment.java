package de.uni_hildesheim.mump.ui.course;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import de.uni_hildesheim.mump.databinding.FragmentCourseBinding;

public class CourseFragment extends Fragment {

    private FragmentCourseBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        CourseViewModel courseViewModel =
                new ViewModelProvider(this).get(CourseViewModel.class);

        binding = FragmentCourseBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        String courseName = getArguments().getString("course_name");
        int points = getArguments().getInt("points");

        TextView textView_name = binding.textViewCourseName;
        TextView textView_score = binding.textViewCoursePoints;



        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}