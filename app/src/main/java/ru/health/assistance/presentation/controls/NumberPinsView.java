package ru.health.assistance.presentation.controls;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.health.assistance.R;
import ru.health.assistance.presentation.commons.ItemClickListener;

/**
 * Created by sasha_merkulev on 11.03.2018.
 */

public class NumberPinsView extends RelativeLayout {

    @BindView(R.id.textview_1) TextView button1;
    @BindView(R.id.textview_2) TextView button2;
    @BindView(R.id.textview_3) TextView button3;
    @BindView(R.id.textview_4) TextView button4;
    @BindView(R.id.textview_5) TextView button5;
    @BindView(R.id.textview_6) TextView button6;
    @BindView(R.id.textview_7) TextView button7;
    @BindView(R.id.textview_8) TextView button8;
    @BindView(R.id.textview_9) TextView button9;
    @BindView(R.id.textview_0) TextView button0;

    public NumberPinsView(Context context, AttributeSet attrs) {
        super(context, attrs);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.control_number_pins, this, true);
        ButterKnife.bind(this);

    }

    public void setOnNumberClickListener(final ItemClickListener<String> numberClickListener){

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v instanceof TextView) {
                    TextView text = (TextView)v;
                    numberClickListener.onItemClick(text.getText().toString());
                }
            }
        };

        button1.setOnClickListener(onClickListener);
        button2.setOnClickListener(onClickListener);
        button3.setOnClickListener(onClickListener);
        button4.setOnClickListener(onClickListener);
        button5.setOnClickListener(onClickListener);
        button6.setOnClickListener(onClickListener);
        button7.setOnClickListener(onClickListener);
        button8.setOnClickListener(onClickListener);
        button9.setOnClickListener(onClickListener);
        button0.setOnClickListener(onClickListener);
    }
}
