package com.example.codex.Slider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.codex.R;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;
import java.util.Objects;

public class SliderImageAdapter extends RecyclerView.Adapter<SliderImageAdapter.ImageViewHolder> {

    private final List<SliderImage> imageList;

    public  SliderImageAdapter(List<SliderImage> imageList){
        this.imageList = imageList;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.slider_image_container, parent, false);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        Objects.requireNonNull(holder).imageView.setImageResource(imageList.get(position).getSliderImage());

//        if (position == imageList.size() - 1){
//            viewPager2.post(runnable);
//        }

        holder.imageView.setOnClickListener(view -> {
            if (((int) position) == 0) {
                Toast.makeText(view.getContext(), "clicked on image " + ((int) position + 1), Toast.LENGTH_SHORT).show();
            } else if (((int) position) == 1) {
                Toast.makeText(view.getContext(), "clicked on image " + ((int) position + 1), Toast.LENGTH_SHORT).show();
            } else if (((int) position) == 2) {
                Toast.makeText(view.getContext(), "clicked on image " + ((int) position + 1), Toast.LENGTH_SHORT).show();
            } else if (((int) position) == 3) {
                Toast.makeText(view.getContext(), "clicked on image " + ((int) position + 1), Toast.LENGTH_SHORT).show();
            } else if (((int) position) == 4) {
                Toast.makeText(view.getContext(), "clicked on image " + ((int) position + 1), Toast.LENGTH_SHORT).show();
            } else if (((int) position) == 5) {
                Toast.makeText(view.getContext(), "clicked on image " + ((int) position + 1), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }

    public static class ImageViewHolder extends RecyclerView.ViewHolder{
        RoundedImageView imageView;
        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.slider_con_iv);

        }
    }

//    private Runnable runnable = new Runnable() {
//        @Override
//        public void run() {
//            imageList.addAll(imageList);
//            notifyDataSetChanged();
//        }
//    };
    
}
