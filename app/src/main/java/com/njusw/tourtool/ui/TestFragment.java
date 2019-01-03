package com.njusw.tourtool.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.njusw.tourtool.R;

public class TestFragment extends Fragment {

    private TextView tv;

    /**
     *
     * @param name
     * @return 一个Fragment
     */
    public static TestFragment newInstance(String name) {
        Bundle args = new Bundle();
        args.putString("FragmentName", name);
        TestFragment fragment = new TestFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_test, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tv = view.findViewById(R.id.fragment_test_tv);
        Bundle bundle = getArguments();
        if (bundle != null) {
            String name = bundle.get("FragmentName").toString();
            tv.setText(name);
        }

    }
}
