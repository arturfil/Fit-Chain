package com.arturofilio.fitchain;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.arturofilio.fitchain.Utils.CustomListAdapter;
import com.arturofilio.fitchain.Utils.ExerciseListAdapter;
import com.arturofilio.fitchain.models.Exercise;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Locale;

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
    private CustomListAdapter adapter;
    private ListView exerciseList;
    private EditText mSearchExercises;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_viewexercises, container, false);
        viewExercisesBar = (AppBarLayout) view.findViewById(R.id.viewExercisesToolbar);
        searchBar = (AppBarLayout) view.findViewById(R.id.search_toolbar);
        exerciseList = (ListView) view.findViewById(R.id.exercisesList);
        mSearchExercises = (EditText) view.findViewById(R.id.etSearchExercises);
        Log.d(TAG, "onCreateView: started");

        setAppBarState(STANDARD_APPBAR);

        setupExerciseList();

        ImageView ivSearchExercise = (ImageView) view.findViewById(R.id.ivSearchIcon);
        ivSearchExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: clicked search incon");
                toggleToolBarState();
            }
        });

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            mExerciseListener = (OnExerciseSelectedListener) getActivity();
        } catch (ClassCastException e) {
            Log.e(TAG, "onAttach: ClassCastException" + e.getMessage() );
        }
    }

    private void setupExerciseList() {
        final ArrayList<Exercise> exercises = new ArrayList<>();
        exercises.add(new Exercise("Chest", testImgUrl));
        exercises.add(new Exercise("Chest", testImgUrl));
        exercises.add(new Exercise("Chest", testImgUrl));
        exercises.add(new Exercise("Chest", testImgUrl));
        exercises.add(new Exercise("Chest", testImgUrl));
        exercises.add(new Exercise("Chest", testImgUrl));
        exercises.add(new Exercise("Chest", testImgUrl));
        exercises.add(new Exercise("Chest", testImgUrl));
        exercises.add(new Exercise("Chest", testImgUrl));

        //sort the arrayList based on the contact name
            Collections.sort(exercises, new Comparator<Exercise>() {
            @Override
            public int compare(Exercise o1, Exercise o2) {
                return o1.getName().compareToIgnoreCase(o2.getName());
            }
        });

        adapter = new CustomListAdapter(getActivity(), R.layout.layout_exerciselistitem, exercises, "");

        mSearchExercises.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                String text = mSearchExercises.getText().toString().toLowerCase(Locale.getDefault());
                adapter.filter(text);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        exerciseList.setAdapter(adapter);
    }


    /**
     * Initiates the appbar state toggle
     */
    private void toggleToolBarState() {
        Log.d(TAG, "toggleToolBarState: toggling AppBarState.");
        if(mAppBarState == STANDARD_APPBAR){
            setAppBarState(SEARCH_APPBAR);
        }else{
            setAppBarState(STANDARD_APPBAR);
        }
    }

    /**
     * Sets the appbar state for either the search 'mode' or 'standard' mode
     * @param state
     */
    private void setAppBarState(int state) {
        Log.d(TAG, "setAppBarState: changing app bar state to: " + state);

        mAppBarState = state;

        if(mAppBarState == STANDARD_APPBAR){
            searchBar.setVisibility(View.GONE);
            viewExercisesBar.setVisibility(View.VISIBLE);

            //hide the keyboard
            View view = getView();
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            try{
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }catch (NullPointerException e){
                Log.d(TAG, "setAppBarState: NullPointerException: " + e.getMessage());
            }
        }

        else if(mAppBarState == SEARCH_APPBAR){
            viewExercisesBar.setVisibility(View.GONE);
            searchBar.setVisibility(View.VISIBLE);

            //open the keyboard
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        setAppBarState(STANDARD_APPBAR);
    }



}
