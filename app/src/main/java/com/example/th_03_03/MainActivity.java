package com.example.th_03_03;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.th_03_03.model.Tour;
import com.example.th_03_03.model.TourAdapter;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements TourAdapter.TourItemListener, SearchView.OnQueryTextListener {
    private List<Tour> mList = new ArrayList<>();
    private RecyclerView rview;
    private TourAdapter adapter;
    private Button btnAdd, btnUpdate;
    private EditText edt_tourName, edt_tourTime;
    private int imgChoice = R.drawable.ic_electric_bike;
    private Spinner spinner;
    private int[] imgs = {R.drawable.ic_airplanemode, R.drawable.ic_car, R.drawable.ic_electric_bike};
    private int current_pos;

    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        try {
            adapter = new TourAdapter(getListTour(), MainActivity.this);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        adapter.setTourItemListener(this);

        LinearLayoutManager manager = new LinearLayoutManager(
                this, LinearLayoutManager.VERTICAL, false);
        rview.setLayoutManager(manager);
        rview.setAdapter(adapter);

        searchView.setOnQueryTextListener(this);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edt_tourName.getText().toString();
                String time = edt_tourTime.getText().toString();
                String i = spinner.getSelectedItem().toString();

                imgChoice = imgs[Integer.parseInt(i)];
                Tour c = new Tour(imgChoice, name, time);
                adapter.addTour(c);
                Toast.makeText(MainActivity.this, "Add sucsses!", Toast.LENGTH_SHORT).show();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edt_tourName.getText().toString();
                String time = edt_tourTime.getText().toString();
                String i = spinner.getSelectedItem().toString();

                imgChoice = imgs[Integer.parseInt(i)];
                Tour c = new Tour(imgChoice, name, time);
                adapter.updateTour(c, current_pos);
                btnAdd.setEnabled(true);
                btnUpdate.setEnabled(false);
                Toast.makeText(MainActivity.this, "Update sucsses!", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void initView() {
        rview = findViewById(R.id.rview);
        edt_tourName = findViewById(R.id.edt_tourName);
        edt_tourTime = findViewById(R.id.edt_tourTime);
        btnAdd = findViewById(R.id.btnAdd);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnUpdate.setEnabled(false);
        spinner = findViewById(R.id.imgs_spinner);
        ImgSpinnerAdapter spinnerAdapter = new ImgSpinnerAdapter(this);
        spinner.setAdapter(spinnerAdapter);
        searchView = findViewById(R.id.search);

    }

    private List<Tour> getListTour() throws ParseException {
//        mList.add(new Tour(R.drawable.ic_electric_bike, "Hà Nội - Sâp", "3 ngày 2 đêm"));
        return mList;
    }

    @Override
    public void onItemClickCustom(View view, int pos){
        btnAdd.setEnabled(false);
        btnUpdate.setEnabled(true);
        current_pos = pos;
        Tour tour = adapter.getTourItem(current_pos);
        int img = tour.getImg();
        int p = 0;
        for (int i = 0; i < imgs.length; i++) {
            if (img == imgs[i]) {
                p = i;
                break;
            }
        }
        spinner.setSelection(p);
        edt_tourName.setText(tour.getName());
        edt_tourTime.setText(tour.getTime());
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        filter(s);
        return false;
    }

    private void filter(String s) {
        List<Tour> filterList = new ArrayList<>();
        for(Tour i:adapter.getBackup()){
            if(i.getName().toLowerCase().contains(s.toLowerCase())){
                filterList.add(i);
            }
        }
        if(filterList.isEmpty()){
            Toast.makeText(this, "Opps! No data exits", Toast.LENGTH_SHORT).show();
        }else{
            adapter.filterList(filterList);
        }
    }
}