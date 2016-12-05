package com.chatton.marina.calculator;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class ScientificFragment extends Fragment {
    private OnScientificButtonClickListener onScientificButtonClickListener;

    public ScientificFragment() {
        // Required empty public constructor
    }

    public void setOnScientificButtonClickListener(OnScientificButtonClickListener onScientificButtonClickListener) {
        this.onScientificButtonClickListener = onScientificButtonClickListener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_scientific, container, false);
        initButtons(view);
        return view;
    }

    public void initButtons(View view) {
        int[] idList = {
                R.id.func_sin,
                R.id.func_cos,
                R.id.func_tan,
                R.id.func_pow2,
                R.id.func_asin,
                R.id.func_acos,
                R.id.func_atan,
                R.id.func_pow3,
                R.id.func_exp,
                R.id.func_ten_pow,
                R.id.func_inv,
                R.id.operator_pow,
                R.id.func_ln,
                R.id.func_log,
                R.id.func_sqrt,
                R.id.func_sign
        };

        for (int id : idList) {
            final Button button = (Button) view.findViewById(id);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onScientificButtonClickListener.onScientificButtonClick(button);
                }
            });
        }
    }
}