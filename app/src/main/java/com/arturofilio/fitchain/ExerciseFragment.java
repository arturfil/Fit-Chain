package com.arturofilio.fitchain;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


import com.arturofilio.fitchain.Utils.UniversalImageLoader;
import com.arturofilio.fitchain.models.Exercise;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class ExerciseFragment extends Fragment {

    private static final String TAG = "ExerciseFragment";

    // This is an attempt to avoid null pointer exception when adding to a new bundle from MainActivity
    public ExerciseFragment() {
        super();
        setArguments(new Bundle());
    }

    private Toolbar toolbar;
    private Exercise mExercise;
    private TextView mExerciseName;
    private ImageView mExerciseImage;
    private ListView mListView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_exercise, container, false);
        toolbar = (Toolbar) view.findViewById(R.id.exerciseToolbar);
        mExerciseName = (TextView) view.findViewById(R.id.exerciseName);
        mExerciseImage = (ImageView) view.findViewById(R.id.exerciseImage);
        mListView = (ListView) view.findViewById(R.id.lvContactProperties);
        Log.d(TAG, "onCreateView: started");
        mExercise = getExerciseFromBundle();

        // setting up the toolbar
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);

        init();

        //navigation for the back-arrow
//        ImageView ivBackArrow = (ImageView) view.findViewById(R.id.ivBackArrow);
//        ivBackArrow.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.d(TAG, "onClick: clicked back arrow.");
//                //remove previous fragment form the back-stack (therefore navigating back)
//                getActivity().getSupportFragmentManager().popBackStack();
//            }
//        });


        return view;
    }

    private void init() {
        mExerciseName.setText(mExercise.getName());

        UniversalImageLoader.setImage(mExercise.getImgURL(), mExerciseImage, null, "");

        ArrayList<String> properties = new ArrayList<>();
    }

    /**
     * Retrieves the selected contact from the bundle (coming from MainActivity)
     * @return
     */
    private Exercise getExerciseFromBundle(){
        Log.d(TAG, "getContactFromBundle: argumetns" + getArguments());

        Bundle bundle = this.getArguments();
        if(bundle != null){
            return bundle.getParcelable(getString(R.string.exercise));
        } else {
            return null;
        }
    }
}
