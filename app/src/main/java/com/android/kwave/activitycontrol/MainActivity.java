package com.android.kwave.activitycontrol;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button btnStart, btnResult;
    Intent intent;
    EditText number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intent = new Intent(this,SubActivity.class);

        btnStart = (Button) findViewById(R.id.btnStart);
        btnResult = (Button) findViewById(R.id.btnforR);
        number = (EditText) findViewById(R.id.number);

        btnStart.setOnClickListener(this);
        btnResult.setOnClickListener(this);

    }
    public static final int BUTTON_RESULT = 99;
    public static final int BUTTON_START = 98;
    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.btnStart :
                    // 일반적인 Activity Start
                intent.putExtra("key","");
                // 키(변수) ,       값
                startActivityForResult(intent,BUTTON_START);
                break;

            case R.id.btnforR :
                // 값을 돌려받은 Activity Start
                //  startActivityForResult를 실행하게 되면
                // SubActivity가 MainActivity 호출시(finish())
                // MainActivity.onActivityResult에 인자값으로 결과값을 돌려준다.

                // 다른 액티비티에 값을 전달하기 위해서 인텐트를 통해서 해야하는데
                // 키와 값의 형태로 보내주는 것.
                intent.putExtra("key", number.getText().toString());
                                // 키(변수) ,       값
                startActivityForResult(intent,BUTTON_RESULT);
                // start SubActivity > finish() > 결과값을 돌려준다 > MainActivity.onActivityResult(결과값)
                break;
        }
    }


    //  SubActivity가 MainActivity 호출시(finish())  MainActivity.onActivityResult에 인자값으로 결과값을 돌려준다.
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
                                      // BUTTON_RESULT  //                                 // 결과값
//        super.onActivityResult(requestCode, resultCode, data);

        Toast.makeText(this,"Result code = "+ resultCode,Toast.LENGTH_SHORT).show();
        if(resultCode == RESULT_OK){
            switch(requestCode){
                case BUTTON_RESULT :
                    // Intent인 data에서 result 변수로 값을 꺼내는데
                    // 값이 없을 경우 디폴트 값으로 0을 사용한다.
                    int result = data.getIntExtra("result",0);
                                              //public int getIntExtra(String name, int defaultValue) {
                                              //return mExtras == null ? defaultValue : mExtras.getInt(name, defaultValue);
                   // Toast.makeText(this,"결과값 =",Toast.LENGTH_SHORT).show();
                    number.setText("결과값 = "+result);
                    break;
                case BUTTON_START :
                    Toast.makeText(this,"Start 버튼을 눌렀다가 돌아옴",Toast.LENGTH_SHORT).show();
                    break;
            }
        }

    }
}
