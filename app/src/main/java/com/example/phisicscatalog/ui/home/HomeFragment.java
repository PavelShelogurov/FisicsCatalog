package com.example.phisicscatalog.ui.home;

import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.phisicscatalog.R;
import com.example.phisicscatalog.dialogs.DialogFragmentForShowInfo;
import com.example.phisicscatalog.parserXml.InfoModel;
import com.example.phisicscatalog.parserXml.XMLParser;

import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

public class HomeFragment extends Fragment {

    private static final String NAME_XML_FILE = "data_mehanica.xml";

    private HomeViewModel homeViewModel;

    private ListView listView_mehanica;

    private List<InfoModel> listWithInfo = new LinkedList<>();
    private List<String> listTitle = new LinkedList<>();


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                listView_mehanica = root.findViewById(R.id.list_item_mech);

                //Добавляем обехкт XMLParser для того чтобы считать данные их XML для конкретного фрагмента (для даннной темы)
                XMLParser xmlParser = new XMLParser(NAME_XML_FILE);

                try {
                   listWithInfo = xmlParser.parse(getContext());
                } catch (ParserConfigurationException e) {
                    Log.d("list", "!!!!!!!!!ParserConfigurationException - проглема тут ");
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.d("list", "Парсер не нашёл файл");
                } catch (SAXException e) {
                    Log.d("list", "!!!!!!!SEXExeption - роблема тут");
                    e.printStackTrace();
                }



                //создаем массив из названий тем для отображения в ListView (для ArrayAdapter)
                for(int i =0; i < listWithInfo.size(); i++){
                    listTitle.add(listWithInfo.get(i).getTheme());
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<>(inflater.getContext(), android.R.layout.simple_list_item_1, listTitle);
                listView_mehanica.setAdapter(adapter);

                listView_mehanica.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        AlertDialog dialog = DialogFragmentForShowInfo.getDialog(getActivity(), listTitle.get(position));

                        dialog.show();
                        initViewDialog(dialog, listWithInfo.get(position).getFormula(), listWithInfo.get(position).getDescription());
                    }
                });



            }
        });
        return root;
    }
    //метод редактирующий всплывающее окно (Alert Dialog) для конкретного нажатого элемента списка
    private void initViewDialog(AlertDialog dialog, String formulaValue, String descriptionValue){
        TextView textViewFormula = dialog.findViewById(R.id.text_formula);
        TextView textViewDescription = dialog.findViewById(R.id.text_description);

        textViewFormula.setText(formulaValue);
        textViewDescription.setText(descriptionValue);
    }
}
