package com.arturofilio.fitchain.Utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.arturofilio.fitchain.models.Exercise;

import java.util.ArrayList;
import java.util.List;

public class ExerciseVideosListAdapter extends ArrayAdapter<String> {

    private static final String TAG = "ContactVideosListAdapter";

    private LayoutInflater mInflater;
    private List<String> mVideos = null;
    private ArrayList<Exercise> arrayList;
    private int layoutResource;
    private Context mContext;
    private String mAppend;

    public ExerciseVideosListAdapter(@NonNull Context context, int resource, @NonNull List<String> videos) {
        super(context, resource, videos);
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layoutResource = resource;
        this.mContext = context;
        this.mVideos = videos;
    }


}
