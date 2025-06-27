package de.uni_hildesheim.mump.ui.courseList;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import de.uni_hildesheim.mump.R;
import de.uni_hildesheim.mump.databinding.FragmentCourselistBinding;

public class CourseListFragment extends Fragment {

    private FragmentCourselistBinding binding;
    private RecyclerView recyclerView;
    private TextAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentCourselistBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        recyclerView = root.findViewById(R.id.recyclerView_MyCourses);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new TextAdapter(new ArrayList<>());
        recyclerView.setAdapter(adapter);

        // Add dynamic items
        List<String> items = Arrays.asList("foo", "bar", "foobar");
        adapter.addItems(items);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}