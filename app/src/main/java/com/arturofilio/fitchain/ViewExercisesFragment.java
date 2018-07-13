package com.arturofilio.fitchain;

import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;

import com.arturofilio.fitchain.models.Exercise;

public class ViewExercisesFragment extends Fragment {

    private static final String TAG = "ViewExercisesFragment";

    private String testImgUrl = "drawable://" + R.drawable.biceps;

    public interface OnExerciseSelectedListener {
        public void OnExerciseSelected(Exercise exer);
    }

    OnExerciseSelectedListener mExerciseListener;

    //variables and widgets
    private static final int STANDARD_APPBAR = 0;
    private static final int SEARCH_APPBAR = 1;
    private int mAppBarState;

    private AppBarLayout viewExercisesBar, searchBar;
    private ExerciseListAdapter adapter;

}
