package com.example.cbluser29.threadhandler;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView tvUpdate;
    private static final int completed300000=3;
    private static final int completed100000=1;
    private static final int completed200000=2;
    private Button btnClick;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        listener();

        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                // background processing


                for (int i = 0; i < 20000000; i++) {
                    Log.e("value",i+"");

                    if(i==100000)
                    {
                        // UI Update
                        Message message=new Message();

                        Bundle bundle=new Bundle();
                        bundle.putInt("value",completed100000);
                        message.setData(bundle);


                        handler.sendMessage(message);

                    }
                    else    if(i==200000)
                    {
                        // UI Update
                        Message message=new Message();

                        Bundle bundle=new Bundle();
                        bundle.putInt("value",completed200000);
                        message.setData(bundle);
                        handler.sendMessage(message);

                    }
                    else    if(i==300000)
                    {

                        // UI Update
                        Message message=new Message();

                        Bundle bundle=new Bundle();
                        bundle.putInt("value",completed300000);
                        message.setData(bundle);
                        handler.sendMessage(message);

                    }

                }






            }
        });

        thread.start();
    }


    /**
     * listeners
     */
    private void listener() {
        btnClick.setOnClickListener(this);
    }


    /**
     * initialise the views
     */
    private void init() {
        tvUpdate=(TextView)findViewById(R.id.tv_main);
        btnClick=(Button)findViewById(R.id.btn_main);
    }


    Handler handler=new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {

            switch (msg.getData().getInt("value"))
            {
                case completed100000:
                    tvUpdate.setText("100000");
                    break;

                case completed200000:
                    tvUpdate.setText("200000");
                    break;

                case  completed300000:
                    tvUpdate.setText("300000");
                    break;
            }



            return true;
        }
    });

    @Override
    public void onClick(View v) {
        Toast.makeText(this, "CLICKEDDDDDDDD", Toast.LENGTH_SHORT).show();
    }
}
