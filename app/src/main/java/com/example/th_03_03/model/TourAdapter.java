package com.example.th_03_03.model;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.th_03_03.MainActivity;
import com.example.th_03_03.R;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class TourAdapter extends RecyclerView.Adapter<TourAdapter.TourViewHolder> {
    private List<Tour> mList = new ArrayList<>();
    private TourItemListener mTourItem;
    private Context context;
    private List<Tour> listBackup; // Phục vụ cho Search, cần 1 list riêng để tìm kiếm ko phụ thuộc vào list kia, nó hay bị thay đổi do người dùng

    public TourAdapter(List<Tour> mList, Context context) {
        this.context = context;
        this.mList = mList;
        listBackup = new ArrayList<>();
    }

    public List<Tour> getBackup(){
        return listBackup;
    }

    public void setTourItemListener(TourItemListener mTourItem) {
        this.mTourItem = mTourItem;
    }

    @NonNull
    @Override
    public TourViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tour, parent, false);
        return new TourViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TourViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Tour Tour = mList.get(position);
        holder.img_icon.setImageResource(Tour.getImg());
        holder.tourName.setText(Tour.getName());
        holder.tourTime.setText(Tour.getTime());

        holder.btnDele.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder buider = new AlertDialog.Builder(context);
                buider.setTitle("Delete item");
                buider.setMessage("Are you sure to delete this tour?");
//                buider.setIcon()
                buider.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        removeAt(position);
                        listBackup.remove(position);
                        notifyDataSetChanged(); // Auto refresh recylerView
                        Toast.makeText(context, "Delete sucsses!", Toast.LENGTH_SHORT).show();
                    }
                });
                buider.setNegativeButton("No", null);
                AlertDialog dialog = buider.create();
                dialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class TourViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView img_icon;
        TextView tourName, tourTime;
        Button btnDele;

        public TourViewHolder(@NonNull View v) {
            super(v);
            img_icon = v.findViewById(R.id.img_icon);
            tourName = v.findViewById(R.id.tourName);
            tourTime = v.findViewById(R.id.tourTime);
            btnDele = v.findViewById(R.id.btnDelete);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mTourItem != null) {
                try {
                    mTourItem.onItemClickCustom(view, getAdapterPosition());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void removeAt(int position) {
        mList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, mList.size());
    }
    public Tour getTourItem(int pos){
        return mList.get(pos);
    }
    public void addTour(Tour tour){
        mList.add(tour);
        listBackup.add(tour);
        notifyDataSetChanged();
    }
    public void updateTour(Tour tour, int pos){
        mList.set(pos, tour);
        listBackup.set(pos, tour);
        notifyDataSetChanged();
    }

    public void filterList(List<Tour> filterList){
        mList = filterList;
        notifyDataSetChanged();
    }

    public interface TourItemListener {
        void onItemClickCustom(View view, int pos) throws ParseException;
    }
}
