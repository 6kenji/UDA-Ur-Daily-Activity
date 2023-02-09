package mz.co.uda_urdailyactivities.OtherActivities.My_Fragments.FragmentsClasses;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import mz.co.uda_urdailyactivities.Objects.User_Activity;
import mz.co.uda_urdailyactivities.OtherActivities.Adapters.DataAccess;
import mz.co.uda_urdailyactivities.OtherActivities.Adapters.MyRecyclerAdapter;
import mz.co.uda_urdailyactivities.R;
import mz.co.uda_urdailyactivities.databinding.ActivitySeeBinding;

public class SeeActivity extends AppCompatActivity {

    private SearchView sv;
    private ActivitySeeBinding binding;
    private RecyclerView recyclerView;
    private MyRecyclerAdapter myRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see);

        binding = ActivitySeeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        recyclerView = binding.list.recyclerList;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        myRecyclerAdapter = new MyRecyclerAdapter(this,DataAccess.actividades);

        binding.list.recyclerList.setAdapter(myRecyclerAdapter);

        sv = findViewById(R.id.txt_search_activity);

        sv.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}