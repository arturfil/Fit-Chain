package com.arturofilio.fitchain.Utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.arturofilio.fitchain.R;
import com.arturofilio.fitchain.models.Exercise;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;

public class CustomListAdapter extends ArrayAdapter<Exercise> {

    private LayoutInflater mInflater;
    private List<Exercise> mExercices = null;
    private ArrayList<Exercise> arrayList; //used for search bar
    private int layoutResource;
    private Context mContext;
    private String mAppend;

    public CustomListAdapter(@NonNull Context context, int resource, @NonNull List<Exercise> exercises, String append) {
        super(context, resource, exercises);
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layoutResource = resource;
        this.mContext = context;
        mAppend = append;
        this.mExercices = exercises;
        arrayList = new ArrayList<>();
        this.arrayList.addAll(mExercices);

    }

    private static class ViewHolder{
        TextView name;
        ImageView exerciseImage;
        ProgressBar mProgressBar;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        //----------------------- ViewHolder Pattern Start
        final ViewHolder holder;

        if(convertView == null){
            convertView = mInflater.inflate(layoutResource, parent, false);
            holder = new ViewHolder();

            //------------------------------------Stuff to change------------------------------
            holder.name = (TextView) convertView.findViewById(R.id.exerciseName);
            holder.exerciseImage = (ImageView) convertView.findViewById(R.id.exerciseImage);

            holder.mProgressBar = (ProgressBar) convertView.findViewById(R.id.exerciseProgressbar);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        //stuff to change
        String name = getItem(position).getName();
        String imagePath = getItem(position).getImgURL();
        holder.name.setText(name);

        ImageLoader imageLoader = ImageLoader.getInstance();

        imageLoader.displayImage(mAppend + imagePath, holder.exerciseImage, new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String imageUri, View view) {
//                holder.mProgressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                holder.mProgressBar.setVisibility(View.GONE);
            }

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                holder.mProgressBar.setVisibility(View.GONE);
            }

            @Override
            public void onLoadingCancelled(String imageUri, View view) {
                holder.mProgressBar.setVisibility(View.GONE);
            }
        });

        return  convertView;
    }

    //Filter class
    public void filter(String characterText) {
        characterText = characterText.toLowerCase(Locale.getDefault());
        mExercices.clear();
        if(characterText.length() == 0) {
            mExercices.addAll(arrayList);
        } else {
            mExercices.clear();
            for(Exercise contact: arrayList){
                if(contact.getName().toLowerCase(Locale.getDefault()).contains(characterText)) {
                    mExercices.add(contact);
                }
            }
        }
        notifyDataSetChanged();
    }
}
