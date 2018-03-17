package ru.health.assistance.presentation.controls;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;

import ru.health.assistance.R;

/**
 * Created by sasha_merkulev on 11.03.2018.
 */

public class PinsView extends LinearLayout {

    private final CircleView[] pinCodes;
    private final int maxCircles;

    public PinsView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        setOrientation(LinearLayout.HORIZONTAL);
        setGravity(Gravity.CENTER);

        TypedArray attributes = context.getTheme().obtainStyledAttributes(attrs, R.styleable.PinsView, 0, 0);
        try {
            maxCircles = attributes.getInt(R.styleable.PinsView_maxCircles, 0);
            pinCodes = new CircleView[maxCircles];
            for(int i =0; i < maxCircles; i++){
                CircleView view = new CircleView(context);
                addView(view);
                pinCodes[i] = view;
            }
        } finally {
            attributes.recycle();
        }
    }

    public void select(int count){
        for(int i=0; i < maxCircles; i++){
            if (i < count){
                pinCodes[i].select();
            } else {
                pinCodes[i].unselect();
            }
        }
    }

    public class CircleView extends View {

        private final Paint paintSelect;
        private final Paint paintUnselect;

        private final int px20dp;
        private final int px12dp;

        private boolean selected;

        public CircleView(Context context) {
            super(context);

            px20dp = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20, context.getResources().getDisplayMetrics());
            px12dp = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 12, context.getResources().getDisplayMetrics());

            paintSelect = new Paint(Paint.ANTI_ALIAS_FLAG);
            paintSelect.setStyle(Paint.Style.FILL_AND_STROKE);
            paintSelect.setColor(ContextCompat.getColor(getContext(), R.color.colorAccent));
            paintSelect.setStrokeWidth(2);

            paintUnselect = new Paint(Paint.ANTI_ALIAS_FLAG);
            paintUnselect.setStyle(Paint.Style.STROKE);
            paintUnselect.setColor(ContextCompat.getColor(getContext(), R.color.colorPrimaryDark));
            paintUnselect.setStrokeWidth(2);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(px20dp, px20dp);
            params.setMargins(px12dp, 0, px12dp, 0);
            setLayoutParams(params);

            selected = false;
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            canvas.drawCircle(px20dp /2, px20dp /2, px20dp /2, selected ? paintSelect : paintUnselect);
        }

        public void select(){
            selected = true;
            invalidate();
        }

        public void unselect(){
            selected = false;
            invalidate();
        }
    }
}
