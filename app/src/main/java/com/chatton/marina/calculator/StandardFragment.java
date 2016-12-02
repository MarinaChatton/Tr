package com.chatton.marina.calculator;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class StandardFragment extends Fragment {
    private OnStandardButtonClickListener onStandardButtonClickListener;

    public StandardFragment() {
        // Required empty public constructor
    }

    public void setOnStandardButtonClickListener(OnStandardButtonClickListener onStandardButtonClickListener) {
        this.onStandardButtonClickListener = onStandardButtonClickListener;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_standard, container, false);

        initButtons(view);

        return view;
    }

    public void initButtons(View view) {
        int[] idList = {
                R.id.num0,
                R.id.num1,
                R.id.num2,
                R.id.num3,
                R.id.num4,
                R.id.num5,
                R.id.num6,
                R.id.num7,
                R.id.num8,
                R.id.num9,
                R.id.decimal_separator,
                R.id.operator_plus,
                R.id.operator_min,
                R.id.operator_mult,
                R.id.operator_div
        };

        for (int id : idList) {
            final Button button = (Button) view.findViewById(id);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onStandardButtonClickListener.onStandardButtonClick(button);
                }
            });
        }
    }

}
