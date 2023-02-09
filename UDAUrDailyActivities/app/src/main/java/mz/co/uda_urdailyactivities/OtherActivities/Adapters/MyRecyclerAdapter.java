package mz.co.uda_urdailyactivities.OtherActivities.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import mz.co.uda_urdailyactivities.Objects.User_Activity;
import mz.co.uda_urdailyactivities.OtherActivities.My_Fragments.FragmentsClasses.EditActivity_Part2;
import mz.co.uda_urdailyactivities.R;

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.ViewHolder> {

    Context context;
    ArrayList <User_Activity> data;

    public MyRecyclerAdapter(Context context, ArrayList <User_Activity> data){
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public MyRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_custom_cardview, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyRecyclerAdapter.ViewHolder holder, int position) {

        User_Activity activity = data.get(position);
        holder.name.setText(activity.getName());
        holder.description.setText(activity.getDescription());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView description;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tx_name);
            description = itemView.findViewById(R.id.txtDescription);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(itemView.getContext(), EditActivity_Part2.class);
                    intent.putExtra("Nome", data.get(getAdapterPosition()).getName());
                    intent.putExtra("Descricao", data.get(getAdapterPosition()).getDescription());

                    itemView.getContext().startActivity(intent);
                }
            });

        }
    }
}