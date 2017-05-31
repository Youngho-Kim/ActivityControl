package com.android.kwave.activitycontrol;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static com.android.kwave.activitycontrol.R.id.btnReturn;

public class SubActivity extends AppCompatActivity {

    TextView textView;
    EditText editText;
    Button button;
    String value = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        textView = (TextView) findViewById(R.id.textView);
        editText = (EditText) findViewById(R.id.name);
        button = (Button) findViewById(btnReturn);



/**
 *  개발자가 처리할 수 있는 것은 조건문 처리를 하고
 *  개발자가 처리할 수 없는 것은 try~catch로 처리한다.
 */





        // 1. 이전 Activity에서 넘어온 intent 객체
         Intent intent = getIntent();        // 여기는 Null이 될 수 없음

        // 2. 값의 묶음을 꺼낸다.
        Bundle bundle = intent.getExtras(); // 여기는 전달된 값이 없으면 null이 된다.

        // 3. 단일 값을 꺼낸다. - 번들에 전달된 값을 호출한다. 이때 bundle이 null이면 nullPointException이 발생된다.
        // 꺼내기 전에 null체크해줘야한다.
        if(bundle != null){
            value = bundle.getString("key");
            // 3.1 값이 있으면 textView에 출력한다.
            textView.setText(value);    // TextView에 MainActivity에서 넘어온 값으로 세팅
        }

        // * 위의 두줄(2,3번)을 합쳐놓은 method
        // String value = intent.getStringExtra("key");
        //  - 자체적으로 bundle에 대한 null 처리 로직을 포함하고 있다.
               // public String getStringExtra(String name) {
               // return mExtras == null ? null : mExtras.getString(name);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // MainActivity에서 넘겨받은 값을 int로 변환
                int num1 = Integer.parseInt(value);
                // 현재 Activity에 입력된 값을 받아서
                String temp = editText.getText().toString();
                //int로 변환
                int num2 = Integer.parseInt(temp);
                int result = num1 + num2;

                /** 값 반환하기 */

                // 1. 결과값을 intent에 담아서
                Intent intent1 = new Intent();
                intent1.putExtra("result", result);

                // 2. setResult 함수에 넘겨준다/
                setResult(RESULT_OK,intent1);       // setResult는 resultCode와 intent를 인자값으로 한다.
                // 이때 resultCode는

                // 3. 현재 activity를 종료한다.
                finish();
            }
        });

    }
}
