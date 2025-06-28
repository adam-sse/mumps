package de.uni_hildesheim.mump.ui.home;

import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import de.uni_hildesheim.mump.api.Api;
import de.uni_hildesheim.mump.databinding.FragmentHomeBinding;
import de.uni_hildesheim.mump.help_classes.UserViewModel;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private UserViewModel userViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        userViewModel = new ViewModelProvider(requireActivity()).get(UserViewModel.class);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        userViewModel = new ViewModelProvider(requireActivity()).get(UserViewModel.class);

        Button myButton = binding.button;
        if (userViewModel.isLoggedIn()) {
            myButton.setText("Logout");
        } else {
            myButton.setText("Login");
        }

        TextInputEditText input1 = binding.input1;

        // Setze einen OnClickListener für den Button
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!userViewModel.isLoggedIn()) {
                    Editable editableName = binding.input1.getText(); // input1 is your TextInputEditText via binding
                    String name = "";
                    if (editableName != null) {
                        name = editableName.toString().trim(); // Convert Editable to String and trim whitespace
                    }

                    boolean exists = false;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        try {
                            List<String> userIds = Api.INSTANCE.getAllUsers().stream().map(apiuser ->
                                    apiuser.userID()).collect(Collectors.toList());
                            exists = userIds.contains(name);
                            System.out.println(userIds);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    if (!name.isEmpty() && exists ) {
                        // Now you have the name string, you can use it
                        userViewModel.setUserName(name);
                        myButton.setText("Logout");
                        ;}
                } else {
                        myButton.setText("Login");
                        userViewModel.setUserName("");
                }
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