package ru.health.assistance.presentation.controls;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.health.assistance.R;

/**
 * Created by sasha_merkulev on 17.03.2018.
 */

public class InfoRowView extends LinearLayout {

    @BindView(R.id.textview_title) TextView title;
    @BindView(R.id.textview_info) TextView info;

    public InfoRowView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.control_info_row, this, true);
        ButterKnife.bind(this);

        TypedArray attributes = context.getTheme().obtainStyledAttributes(attrs, R.styleable.InfoRowView, 0, 0);
        try {
            String tit = attributes.getString(R.styleable.InfoRowView_title);
            String inf = attributes.getString(R.styleable.InfoRowView_info);
            title.setText(tit);
            info.setText(inf);
        } finally {
            attributes.recycle();
        }
    }

    public void setInfo(String inf){
        info.setText(inf);
    }
}
