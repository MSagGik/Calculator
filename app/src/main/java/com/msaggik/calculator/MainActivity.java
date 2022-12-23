package com.msaggik.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // задание поля ввода/вывода данных в калькулятор
    private TextView textInOut;

    private String numBuffer = "";
    private Double [] numResult = {0.0, 0.0, 0.0, 0.0, 0.0};
    private Double result = 0.0;
    private String [] operatorArray = {"", "", "", "", ""};
    private int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // привязка переменных
        textInOut = findViewById(R.id.textInOut);
        Button btn0 = findViewById(R.id.button0);
        Button btn1 = findViewById(R.id.button1);
        Button btn2 = findViewById(R.id.button2);
        Button btn3 = findViewById(R.id.button3);
        Button btn4 = findViewById(R.id.button4);
        Button btn5 = findViewById(R.id.button5);
        Button btn6 = findViewById(R.id.button6);
        Button btn7 = findViewById(R.id.button7);
        Button btn8 = findViewById(R.id.button8);
        Button btn9 = findViewById(R.id.button9);
        Button btnDub = findViewById(R.id.buttonDub);
        Button btnAdd = findViewById(R.id.buttonAdd);
        Button btnSub = findViewById(R.id.buttonSub);
        Button btnMul = findViewById(R.id.buttonMul);
        Button btnDiv = findViewById(R.id.buttonDiv);
        Button btnEq = findViewById(R.id.buttonEq);

        // нажатие кнопок
        btn0.setOnClickListener(listenerDigit);
        btn1.setOnClickListener(listenerDigit);
        btn2.setOnClickListener(listenerDigit);
        btn3.setOnClickListener(listenerDigit);
        btn4.setOnClickListener(listenerDigit);
        btn5.setOnClickListener(listenerDigit);
        btn6.setOnClickListener(listenerDigit);
        btn7.setOnClickListener(listenerDigit);
        btn8.setOnClickListener(listenerDigit);
        btn9.setOnClickListener(listenerDigit);
        btnDub.setOnClickListener(listenerDigit);
        btnAdd.setOnClickListener(listenerDigit);
        btnSub.setOnClickListener(listenerDigit);
        btnMul.setOnClickListener(listenerDigit);
        btnDiv.setOnClickListener(listenerDigit);
        btnEq.setOnClickListener(listenerDigit);

    }

    // обработка нажатия кнопок
    private View.OnClickListener listenerDigit = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            switch (editDigit(view)) { // ввод числа
                case "operator": // если был введён оператор
                    numResult[counter] = Double.parseDouble(numBuffer); // то имеющееся число до него помещается в массив типа double
                    operatorArray[counter] = editOperator(view); // с помощью метода операторов фиксируется оператор в текстовый массив

                    switch (editOperator(view)) { // вычисления
                        case "result": // если введён оператор "равно"
                            if (counter == 0) { // при вводе первого сисла - выводится само первое число
                                textInOut.setText(numBuffer);
                            } else { // при вводе не первого числа - производятся вычисления

                                switch (operatorArray[counter - 1]) { // просматривается предыдущий оператор
                                    case "+": // если предыдущий оператор равен "плюсу", то высчитывается сумма последних двух чисел
                                        textInOut.setText(Double.toString(numResult[counter - 1] + numResult[counter]));
                                        break;
                                    case "-":
                                        textInOut.setText(Double.toString(numResult[counter - 1] - numResult[counter]));
                                        break;
                                    case "*":
                                        textInOut.setText(Double.toString(numResult[counter - 1] * numResult[counter]));
                                        break;
                                    case "/":
                                        textInOut.setText(Double.toString(numResult[counter - 1] / numResult[counter]));
                                        break;
                                    case "result":
                                        textInOut.setText(Double.toString(numResult[counter]));
                                        break;
                                }

                                // перезаписывается последнее число в массиве чисел double
                                numResult[counter] = Double.parseDouble(textInOut.getText().toString());
                            }
                            break;
                        default: // если введён оператор отличный от знака "равно"
                            textInOut.setText(numBuffer + " " + editOperator(view));
                            //textInOut.setText(numBuffer);
                    }
                    counter++; // повышается счётчик
                    numBuffer = ""; // обнуляется буфер ввода
                    break;
                default:
                    numBuffer = numBuffer + editDigit(view); // посимвольный ввод числа
                    if (counter == 0) { // если число первое
                        textInOut.setText(numBuffer); // то выводится только оно
                    } else { // если число не первое
                        textInOut.setText(Double.toString(numResult[counter - 1]) + " " + operatorArray[counter - 1] + " " + numBuffer); // то дополнительно выводятся и предыдущие число и оператор
                    }
            }
        }
    };

    // метод ввода цифр с фиксацией по умолчанию ввода оператора
    private static String editDigit(View view) {
        // буферная переменная
        String num = "";

        switch (view.getId()) {
            case R.id.button0:
                num = "0";
                break;
            case R.id.button1:
                num = "1";
                break;
            case R.id.button2:
                num = "2";
                break;
            case R.id.button3:
                num = "3";
                break;
            case R.id.button4:
                num = "4";
                break;
            case R.id.button5:
                num = "5";
                break;
            case R.id.button6:
                num = "6";
                break;
            case R.id.button7:
                num = "7";
                break;
            case R.id.button8:
                num = "8";
                break;
            case R.id.button9:
                num = "9";
                break;
            case R.id.buttonDub:
                num = ".";
                break;
            default:
                num = "operator";
                break;
        }

        return num;
    }

    // метод ввода операторов
    private static String editOperator(View view) {
        // буферная переменная
        String operator = "";

        switch (view.getId()) {
            case R.id.buttonAdd:
                operator = "+";
                break;
            case R.id.buttonSub:
                operator = "-";
                break;
            case R.id.buttonMul:
                operator = "*";
                break;
            case R.id.buttonDiv:
                operator = "/";
                break;
            case R.id.buttonEq:
                operator = "result";
                break;
            default:
                break;
        }

        return operator;
    }
}