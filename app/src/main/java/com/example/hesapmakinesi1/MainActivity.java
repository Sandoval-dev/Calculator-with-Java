package com.example.hesapmakinesi1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText newnumber;
    private TextView operandDisplay;
    private TextView result;

    private String pendingoperation="+";
    private Double operand1=null;
    private Double operand2=null;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        result=(TextView) findViewById(R.id.text_Sonuc);
        newnumber=(EditText) findViewById(R.id.editTextNumberDecimal);
        operandDisplay=(TextView) findViewById(R.id.operandDisplay);

        Button button0=(Button) findViewById(R.id.button_0);
        Button button1=(Button) findViewById(R.id.button_1);
        Button button2=(Button) findViewById(R.id.button_2);
        Button button3=(Button) findViewById(R.id.button_3);
        Button button4=(Button) findViewById(R.id.button_4);
        Button button5=(Button) findViewById(R.id.button_5);
        Button button6=(Button) findViewById(R.id.button_6);
        Button button7=(Button) findViewById(R.id.button_7);
        Button button8=(Button) findViewById(R.id.button_8);
        Button button9=(Button) findViewById(R.id.button_9);
        Button button_esit=(Button) findViewById(R.id.button_esit);
        Button button_nokta=(Button) findViewById(R.id.button_nokta);
        Button button_div=(Button) findViewById(R.id.button_div);
        Button button_plus=(Button) findViewById(R.id.button_plus);
        Button button_minus=(Button) findViewById(R.id.button_minus);
        Button button_carp=(Button) findViewById(R.id.button_carp);
        Button button_delete=(Button) findViewById(R.id.button_delete);

        View.OnClickListener listener=new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button button=(Button) view;
                newnumber.append(button.getText().toString());
            }
        };

        button0.setOnClickListener(listener);
        button1.setOnClickListener(listener);
        button2.setOnClickListener(listener);
        button3.setOnClickListener(listener);
        button4.setOnClickListener(listener);
        button5.setOnClickListener(listener);
        button6.setOnClickListener(listener);
        button7.setOnClickListener(listener);
        button8.setOnClickListener(listener);
        button9.setOnClickListener(listener);
        button_nokta.setOnClickListener(listener);

        View.OnClickListener operationlistener=new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button button=(Button) view;
                String op=button.getText().toString();
                String value=newnumber.getText().toString();
                try {
                    Double dValue=Double.valueOf(value);
                    performOperation(dValue,op);


                }catch(NumberFormatException e){
                    Log.d("--------------","Sayı dönüşüm hatası");
                    newnumber.setText("");

                }


                pendingoperation=op;
                operandDisplay.setText(pendingoperation);

            }
        };

        button_carp.setOnClickListener(operationlistener);
        button_div.setOnClickListener(operationlistener);
        button_esit.setOnClickListener(operationlistener);
        button_minus.setOnClickListener(operationlistener);
        button_plus.setOnClickListener(operationlistener);
        button_delete.setOnClickListener(operationlistener);

    }

    private  void performOperation(Double value,String op){
        if (null==operand1){
            operand1=value;
        }else{
            operand2=value;
            if (pendingoperation.equals("=")){
                pendingoperation=op;
            }

            switch (pendingoperation){
                case "=":
                    operand1=operand2;
                    result.setText(operand1.toString());
                    break;
                case "/":
                    if (operand2==0){
                        operand1=0.0;
                        result.setText(operand1.toString());
                    }else{
                        operand1=operand1/operand2;
                        result.setText(operand1.toString());
                    }
                    break;
                case "+":
                    operand1+=operand2;
                    result.setText(operand1.toString());
                    break;
                case "-":
                    operand1-=operand2;
                    result.setText(operand1.toString());
                    break;
                case "*":
                    operand1*=operand2;
                    result.setText(operand1.toString());
                    break;
                case "CE":
                    result.setText(" ");





            }

        }


        newnumber.setText("");

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putString("PENDINGOPERATION",pendingoperation);
        if (operand1!=null){
            outState.putDouble("VALUE",operand1);
            super.onSaveInstanceState(outState);
        }
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        pendingoperation=savedInstanceState.getString("PENDINGOPERATION");
        operand1=savedInstanceState.getDouble("VALUE");
        result.setText(String.valueOf(operand1));
        operandDisplay.setText(pendingoperation);
    }
}
