package com.example.th_03_03;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class ImgSpinnerAdapter extends BaseAdapter {
    List<Integer> lstImg = new ArrayList<>();
    private Context context;

    public ImgSpinnerAdapter(Context context) {
        this.context = context;
        lstImg.add(R.drawable.ic_airplanemode);
        lstImg.add(R.drawable.ic_car);
        lstImg.add(R.drawable.ic_electric_bike);
    }

    @Override
    public int getCount() {
        return lstImg.size();
    }

    @Override
    public Object getItem(int pos) {
        return pos;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

//    Mỗi view ở đây tương ứng với một item
    @Override
    public View getView(int pos, View view, ViewGroup viewGroup) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_img_spinner, viewGroup, false);
        ImageView imgview = v.findViewById((R.id.item_img_spinner));
//        ImageView imgview = v.findViewById((R.id.imgs_spinner)); // Chạy cái này là bị lỗi nhé (mình chưa biết vì sao)
        imgview.setImageResource(lstImg.get(pos));
        return v;
    }
}
