package com.example.internmacbook.colorclock;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by internmacbook on 8/11/16.
 */
public class ColorFragment extends Fragment {
    private static final String TAG = "ColorFragment";

    private TextView mTimeView;
    //ImageView mImageView;
    private RelativeLayout mRelativeLayout;

    private String mTime;

    public static ColorFragment newInstance() {
        return new ColorFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main, container, false);
        setRetainInstance(true);

        mTimeView = (TextView) view.findViewById(R.id.time_text_view);
        mRelativeLayout = (RelativeLayout) view.findViewById(R.id.relative_layout);

//        ButterKnife.bind(getActivity());

        mTime = "#";

        Thread t = new Thread() {
            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {
                        Thread.sleep(500); // 0.5 second
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                // update TextView here!
                                updateScreen();
                            }
                        });
                    }
                } catch (InterruptedException e) {

                }
            }
        };
        t.start();

        return view;
    }

    private void updateScreen() {
        CurrentTime time = new CurrentTime();
        mTime = "#";
        String temp = "";
        temp += time.getCurrentTime();
        mTime += temp;
        updateTimeView(mTime);

//        mTime.replaceAll("#", "0xff");
        Log.i(TAG, "temp reads: " + temp);
        Log.i(TAG, "mTime reads: " + mTime);

        int color = Color.parseColor(mTime);
        Log.i(TAG, "Color: " + color);
        int n = hex2Rgb(mTime);
        if(temp.length() > 0){
//            mImageView.setColorFilter(Color.parseColor(mTime));
            mRelativeLayout.setBackgroundColor(n);
        }

        //mImageView.setColorFilter();


    }

    private void updateTimeView(String temp) {
        mTimeView.setText(temp);
    }

    public static int hex2Rgb(String colorStr) {
        Color color = new Color();
        int temp  = color.rgb(Integer.valueOf( colorStr.substring( 1, 3 ), 16 ),
                Integer.valueOf( colorStr.substring( 3, 5 ), 16 ),
                Integer.valueOf( colorStr.substring( 5, 7 ), 16 ) );
        Log.i(TAG, "rgb returns: " + temp);
        return temp;
    }

}
