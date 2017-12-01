package com.sieudaochichcode.com.qrcodedemo;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.github.sumimakito.awesomeqr.AwesomeQRCode;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    Button button;
    EditText editText;
    String EditTextValue;

    public final static int QRcodeWidth = 500;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView) findViewById(R.id.imageView);
        editText = (EditText) findViewById(R.id.editText);
        button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditTextValue = editText.getText().toString();

                new AwesomeQRCode.Renderer()
                        .contents(EditTextValue)
                        .size(QRcodeWidth).margin(0).dotScale(1).logoScale(0.1F)
                        .renderAsync(new AwesomeQRCode.Callback() {
                            @Override
                            public void onRendered(AwesomeQRCode.Renderer renderer, final Bitmap bitmap) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        // Tip: here we use runOnUiThread(...) to avoid the problems caused by operating UI elements from a non-UI thread.
                                        imageView.setImageBitmap(bitmap);
                                    }
                                });
                            }

                            @Override
                            public void onError(AwesomeQRCode.Renderer renderer, Exception e) {
                                e.printStackTrace();
                            }
                        });

            }
        });
    }
}