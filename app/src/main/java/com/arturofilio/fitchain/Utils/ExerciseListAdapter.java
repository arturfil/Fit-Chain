package com.arturofilio.fitchain.Utils;

import android.content.Context;
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

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ExerciseListAdapter extends ArrayAdapter<Exercise> {

    private LayoutInflater mInflater;
    private List<Exercise> mExercises = null;
    private ArrayList<Exercise> arrayList;
    private int layoutResource;
    private Context mContext;
    private String mAppend;

    public ExerciseListAdapter(@NonNull Context context, int resource, @NonNull List<Exercise> exercises, String append) {
        super(context, resource, exercises);
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layoutResource = resource;
        this.mContext = context;
        arrayList = new ArrayList<>();
        this.arrayList.addAll(mExercises);
    }

    private static class ViewHolder{
        TextView name;
        CircleImageView imgURL;
        ProgressBar mProgressBar;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return super.getView(position, convertView, parent);

        //----------------------ViewHolder Pattern Start
        final ViewHolder holder;

        if(convertView == null) {
            convertView = mInflater.inflate(layoutResource, parent, false);
            holder = new ViewHolder();

            //-------------------------------------Stuff to change---------------------------
            holder.name = (TextView) convertView.findViewById(R.id.exerciseName);
            holder.imgURL = (ImageView) convertView.findViewById(R.id.exerciseImage);

            holder.mProgressBar = (ProgressBar) convertView.findViewById(R.id.exerciseProgressbar);
        }
    }
}
