package com.example.phisicscatalog.ui.slideshow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.phisicscatalog.R;

public class SlideshowFragment extends Fragment {

    private SlideshowViewModel slideshowViewModel;

    ListView listView;

    String[] testArray = {"Hello", "Hello, world!"};
    TestClassForCheckListView[] testObjectsArray = {new TestClassForCheckListView("Pavel", "Shelogurov"), new TestClassForCheckListView("Alex", "Bolshov")};

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
                new ViewModelProvider(this).get(SlideshowViewModel.class);
        View root = inflater.inflate(R.layout.fragment_slideshow, container, false);


        slideshowViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                /**
                 * Текстовый код для нужного отображения
                 *
                 */
                listView = root.findViewById(R.id.list_item3);

                ArrayAdapter<String> adapter = new ArrayAdapter(inflater.getContext(), android.R.layout.simple_list_item_1, testObjectsArray);

                listView.setAdapter(adapter);


            }
        });
        return root;
    }
}