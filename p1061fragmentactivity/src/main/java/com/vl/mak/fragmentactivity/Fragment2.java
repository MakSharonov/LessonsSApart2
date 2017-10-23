package com.vl.mak.fragmentactivity;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment2 extends Fragment {

    final String LOG_TAG = "myLogs";

    SomeEventListener someEventListener;

    public interface SomeEventListener {
        public void someEvent(String s);
    }

    public Fragment2() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            someEventListener = (SomeEventListener) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString() + " must implement interface SomeEventListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_fragment2, container, false);
        Button button = v.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(LOG_TAG, "Fragment two button click");
                someEventListener.someEvent("Test string from fragment two");
            }
        });
        return v;
    }

}
