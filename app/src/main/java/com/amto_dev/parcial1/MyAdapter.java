package com.amto_dev.parcial1;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import org.jetbrains.annotations.NotNull;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    String data1[], data2[];
    int images[];
    Context context;

    public MyAdapter(Context ct, String s1[], String s2[], int img[]){
        context = ct;
        data1 = s1;
        data2 = s2;
        images = img;
    }

    @NonNull
    @NotNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.tvBand.setText(data1[position]);
        holder.tvTitleMusic.setText(data2[position]);
        holder.imgCover.setImageResource(images[position]);

        int number[] = {0,1,2,3,4,5};

        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, play_music.class);
                intent.putExtra("data1", data1[position]);
                intent.putExtra("data2", data2[position]);
                intent.putExtra("myImage", images[position]).toString();
                intent.putExtra("number", number[position]);

                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return images.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvBand, tvTitleMusic;
        ImageView imgCover;
        LinearLayout mainLayout;
        public MyViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            tvBand = itemView.findViewById(R.id.tvBand);
            tvTitleMusic = itemView.findViewById(R.id.tvTitleMusic);
            imgCover = itemView.findViewById(R.id.imgCover);
            mainLayout = itemView.findViewById(R.id.mainLayouts);
        }
    }
}
