package com.ggg.hour11application;

import android.animation.ObjectAnimator;
import android.graphics.Matrix;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    /**
     * Vars
     */

    // scale types
    int currentTypePosition = 0;
    String[] scaleTypes;
    ImageView scaleTypeImage;
    TextView textScaleType;

    // rotation
    float currentRotation = 0f;
    ImageView rotateImage;
    TextView textRotation;

    // alpha
    float currentAlpha = 1f;
    ImageView alphaImage;
    TextView textAlpha;
    SeekBar seekAlpha;


    /**
     * onCreate
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // scale types
        scaleTypes = getResources().getStringArray(R.array.scale_types);
        scaleTypeImage = (ImageView) findViewById(R.id.imageScaleType);
        textScaleType = (TextView) findViewById(R.id.textScaleType);

        // rotation
        rotateImage = (ImageView) findViewById(R.id.imageRotation);
        textRotation = (TextView) findViewById(R.id.textRotation);

        // alpha
        alphaImage = (ImageView) findViewById(R.id.imageAlpha);
        textAlpha = (TextView) findViewById(R.id.textAlpha);
        seekAlpha = (SeekBar) findViewById(R.id.seekAlpha);
        initAlphaImage();
    }


    /**
     * scale types - onImageScaleTypeClick
     */
    public void onImageScaleTypeClick(View v) {
        // set default scale type
        ImageView.ScaleType currentType = ImageView.ScaleType.values()[currentTypePosition];

        // update views
        scaleTypeImage.setScaleType(currentType);
        textScaleType.setText("Scale Type: " + currentType.toString());

        // using matrix
        if (scaleTypeImage.getScaleType() == ImageView.ScaleType.MATRIX) {
            // create a matrix for the manipulation
            Matrix matrix = new Matrix();
            // manipulate matrix
            matrix.postScale(0.8f, 0.8f);
            matrix.postTranslate(-400f, -400f);
            matrix.postRotate(60);
            // set matrix to view
            scaleTypeImage.setImageMatrix(matrix);
        }

        // increment type position and reset when reaching end
        currentTypePosition++;
        if (currentTypePosition > scaleTypes.length) {
            currentTypePosition = 0;
        }
    }


    /**
     * Rotation - onImageRotationClick
     */
    public void onImageRotationClick(View v) {
        // get random rotation
        currentRotation = 360 * new Random().nextFloat();

        // clear existing animation on view
        rotateImage.clearAnimation();

        // update view with animation on z-axis
        ObjectAnimator animation = ObjectAnimator.ofFloat(rotateImage, "rotation", currentRotation);
        animation.setDuration(500);
        animation.setInterpolator(new DecelerateInterpolator());
        animation.start();

        // update text view with current rotation
        textRotation.setText("Rotation: " + currentRotation);
    }


    /**
     * Alpha
     */
    public void updateAlpha(float alpha) {
        alphaImage.setAlpha(alpha);
        textAlpha.setText("Alpha: " + alpha);
    }

    public void initAlphaImage() {
        // set default alpha
        updateAlpha((float) seekAlpha.getProgress() / 100);

        // seek bar handler
        seekAlpha.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                updateAlpha((float) progress / 100);
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
