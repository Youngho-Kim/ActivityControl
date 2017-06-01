# ActivityControl
StartActivity &lt;--> SubActivity 값 주고 받기

## StartActivityForResult
[소스코드 전체보기](https://github.com/Youngho-Kim/ActivityControl/blob/master/app/src/main/java/com/android/kwave/activitycontrol/MainActivity.java)

이 함수로 Activity를 실행하면 실행된 Activity가 종료되면서 onActivityResult 함수를 호출해준다.


```java

//액티비티를 실행하는 버튼을 구분하기 위한 플래그

final int BUTTON_START = 99;

Intent intent = new Intent(this, 서브.class);

StartActivityForResult(intent, BUTTON_START);
```


## setResult
호출되는 서비.class에 작성되는 코드

```java

Intent intent = new Intent(); // 이미 생성되어 있는 Activity를 사용하기 때문에 필요로 하지 않는다.
intent.putExtra("result","결과값");
  
//RESULT_OK는 부모Activity에 이미 정의되어 있는 플래그 값으로 처리가 성공적이라는 것을 의미한다.
//setResult함수는 현재 Activity에 intent를 저장하기 때문에 18번 줄에서 언급한바와 같이 Context를 필요로 하지 않는다. 
  
 setResult(RESULT_OK, intent);
```

## onActivityResult

[소스코드 전체보기](https://github.com/Youngho-Kim/ActivityControl/blob/master/app/src/main/java/com/android/kwave/activitycontrol/SubActivity.java)



// requestCode : StartActivityForResult를 실행한 주체를 구분하기 위한 플래그

// resultCode : 결과처리의 성공여부 | RESULT_OK = 성공

// data = 돌려받은 값이 담겨있는 Intent

//  SubActivity가 MainActivity 호출시(finish())  MainActivity.onActivityResult에 인자값으로 결과값을 돌려준다.


```java
@Override

protected void onActivityResult(int requestCode, int resultCode, Intent data) {
                              // BUTTON_RESULT  /                 // 결과값
//        super.onActivityResult(requestCode, resultCode, data);
  
    Toast.makeText(this,"Result code = "+ resultCode,Toast.LENGTH_SHORT).show();  
    
    if(resultCode == RESULT_OK){
        switch(requestCode){  
        
            case BUTTON_RESULT :
                // Intent인 data에서 result 변수로 값을 꺼내는데
                // 값이 없을 경우 디폴트 값으로 0을 사용한다.
                int result = data.getIntExtra("result",0);
                                      // getIntExtra의 원본은 아래와 같다.
                                      //public int getIntExtra(String name, int defaultValue) {
                                        //return mExtras == null ? defaultValue :mExtras.getInt(name, defaultValue);
                                        }
               // Toast.makeText(this,"결과값 =",Toast.LENGTH_SHORT).show();
                number.setText("결과값 = "+result);
                break;  
                
            case BUTTON_START :
                Toast.makeText(this,"Start 버튼을 눌렀다가 돌아옴",Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
```