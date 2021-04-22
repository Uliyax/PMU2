package com.example.lab3;

import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class Calculator extends AppCompatActivity {
    Button btn_1, btn_2, btn_3, btn_4, btn_5, btn_6, btn_7, btn_8, btn_9, btn_0, btn_dot,
            btn_plus, btn_minus, btn_multiply, btn_divide, btn_equals,
            btn_memMinus, btn_memPlus, btn_memRead,
            btn_openBracket, btn_closeBracket,
            btn_sin, btn_cos;

    TextView expression, answer;

    ImageView btn_clear;

    StringBuilder str;

    float memValue = 0;

    int bracketsCount = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        str = new StringBuilder();

        btn_0 = (Button) findViewById(R.id.n0);
        btn_1 = (Button) findViewById(R.id.n1);
        btn_2 = (Button) findViewById(R.id.n2);
        btn_3 = (Button) findViewById(R.id.n3);
        btn_4 = (Button) findViewById(R.id.n4);
        btn_5 = (Button) findViewById(R.id.n5);
        btn_6 = (Button) findViewById(R.id.n6);
        btn_7 = (Button) findViewById(R.id.n7);
        btn_8 = (Button) findViewById(R.id.n8);
        btn_9 = (Button) findViewById(R.id.n9);
        btn_dot = (Button) findViewById(R.id.dot);


        btn_plus = (Button) findViewById(R.id.plus);
        btn_minus = (Button) findViewById(R.id.minus);
        btn_multiply = (Button) findViewById(R.id.multiplication);
        btn_divide = (Button) findViewById(R.id.division);
        btn_equals = (Button) findViewById(R.id.equals);
        btn_clear = (ImageView) findViewById(R.id.del);


        btn_memMinus = (Button) findViewById(R.id.mMinus);
        btn_memPlus = (Button) findViewById(R.id.mPlus);
        btn_memRead = (Button) findViewById(R.id.mr);


        btn_openBracket = (Button) findViewById(R.id.firstSkobka);
        btn_closeBracket = (Button) findViewById(R.id.secondSkobka);

        btn_sin = (Button) findViewById(R.id.sin);
        btn_cos = (Button) findViewById(R.id.cos);


        expression = (TextView) findViewById(R.id.input);
        answer = (TextView) findViewById(R.id.result);


        btn_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str = new StringBuilder();
                expression.setText("");
                answer.setText("");
                bracketsCount = 0;
            }
        });


        btn_0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {appendNumber("0");
            }
        });
        btn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendNumber("1");
            }
        });
        btn_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendNumber("2");
            }
        });
        btn_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendNumber("3");
            }
        });
        btn_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendNumber("4");
            }
        });
        btn_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendNumber("5");
            }
        });
        btn_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendNumber("6");
            }
        });
        btn_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendNumber("7");
            }
        });
        btn_8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendNumber("8");
            }
        });
        btn_9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendNumber("9");
            }
        });

        btn_dot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (expression.length() == 0) return;
                if (isLastSymbolDot() || isLastSymbolOperation()) return;
                if (isDotAllowed())
                    expression.setText(str.append("."));
            }
        });

        btn_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendOperation("+");
            }
        });

        btn_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendOperation("-");
            }
        });

        btn_multiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendOperation("*");
            }
        });

        btn_divide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendOperation("/");
            }
        });


        btn_equals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bracketsCount = 0;
                if (isExpressionNotEmpty()) {
                    if (isLastSymbolOperation() || isLastSymbolDot())
                        return;
                    answer.setText(calculateExpression(expression.getText().toString()));
                    str = new StringBuilder();
                    expression.setText("");
                }
            }
        });

        btn_openBracket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (str.length() == 0 || isAnyOperation(str.toString(), str.length() - 1)
                        || str.charAt(str.length() - 1) == '(') {
                    expression.setText(str.append("("));
                    bracketsCount++;
                }
            }
        });

        btn_closeBracket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bracketsCount > 0 && str.charAt(str.length() - 1) != '('
                        && !isAnyOperation(str.toString(), str.length() - 1)) {
                    expression.setText(str.append(")"));
                    bracketsCount--;
                }
            }
        });

        btn_memRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //str = new StringBuilder(memValue + "");
                if (str.length() == 0 || isLastSymbolOperation()) str.append(memValue + "");
                expression.setText(str);

            }
        });

        btn_sin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (str.length() == 0 || isAnyOperation(str.toString(), str.length() - 1)
                        || str.charAt(str.length() - 1) == '(') {
                    str.append("sin(");
                    bracketsCount++;
                    expression.setText(str);
                }
            }
        });


        btn_cos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (str.length() == 0 || isAnyOperation(str.toString(), str.length() - 1)
                        || str.charAt(str.length() - 1) == '(') {
                    str.append("cos(");
                    bracketsCount++;
                    expression.setText(str);
                }
            }
        });

        btn_memPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    memValue += Float.parseFloat(answer.getText().toString());
                }
                catch (Exception ignored) {}
            }
        });

        btn_memMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    memValue -= Float.parseFloat(answer.getText().toString());
                }
                catch (Exception ignored) {}
            }
        });
    }

    String calculateExpression(String expressionStr) {
        String exp = expressionStr;
        String result = "";
        if (exp.contains("(") || exp.contains(")"))
            expressionStr = replaceAllBrackets(exp);
        expressionStr = expressionStr.replaceAll("--", "+");
        try {
            if (exp.contains("sin") || exp.contains("cos"))
                expressionStr = calculateSinCos(expressionStr);
        } catch (NumberFormatException e) {
            result = "Division by zero!";
            answer.setText(result);
            return result;
        }
        result = calculateMultiplyDivide(expressionStr);
        if (result.equals("Division by zero!")) {
            answer.setText(result);
            return result;
        }
        else if (result.equals("Can't calculate")) {
            answer.setText(result);
            return result;
        }
        else if (expressionStr.contains("+") || expressionStr.contains("-"))
            result = calculatePlusMinus(result);
        return result;
    }


    String calculateMultiplyDivide(String expressionStr) {
        String exp = expressionStr;
        String value1 = "1", value2 = "1", result;
        for (int i = 0; i < exp.length(); i++) {
            if (isMultiplyOrDivide(exp, i)) {
                int j = getStartOfNumber(exp, i);
                int k = getEndOfNumber(exp, i);
                value1 = exp.substring(j, i);
                if (exp.charAt(i + 1) == '-')
                    k = getEndOfNumber(exp, i + 1);
                value2 = exp.substring(i + 1, k);
                result = calculateTwoNumbers(value1, value2, exp.charAt(i));
                if (result.equals("Division by zero!")) {
                    answer.setText(result);
                    return result;
                }
                else if (result.equals("Can't calculate")) {
                    answer.setText(result);
                    return result;
                }
                exp = exp.replace(value1 + exp.charAt(i) + value2, result);
                Log.d("res", exp);

                i = j + result.length() - 2;
            }
        }
        return exp;
    }



    String calculatePlusMinus(String expressionStr) {
        String exp = expressionStr;
        String value1, value2, result;
        int startOfNumber = 0;
        for (int i = 0; i < exp.length(); i++) {
            if (isPlusOrMinus(exp, i)) {
                int k = getEndOfNumber(exp, i);
                value1 = exp.substring(startOfNumber, i);
                value2 = exp.substring(i + 1, k);
                result = calculateTwoNumbers(value1, value2, exp.charAt(i));
                exp = exp.replace(value1 + exp.charAt(i) + value2, result);
                Log.d("res", exp);

                i = startOfNumber + result.length() - 1;
                startOfNumber = i - result.length() + 1;
            }
        }
        return exp;
    }

    String calculateTwoNumbers(String v1, String v2, char operation) {
        String resultStr = "";
        float result = 0;
        switch (operation) {
            case '+':
                if (v1.equals(""))
                    v1 = "0";
                else if (v2.equals(""))
                    v2 = "0";
                result = Float.parseFloat(v1) + Float.parseFloat(v2);
                break;

            case '-':
                if (v1.equals(""))
                    v1 = "0";
                else if (v2.equals(""))
                    v2 = "0";
                result = (Float.parseFloat(v1)) - Float.parseFloat(v2);
                break;

            case '*':
                result = Float.parseFloat(v1) * Float.parseFloat(v2);
                break;

            case '/':
                if (v2.equals("0") || v2.equals("0.0") || v2.equals(".0")) {
                    expression.setText("");
                    str = new StringBuilder();
                    resultStr = "Division by zero!";

                }
                result = Float.parseFloat(v1) / Float.parseFloat(v2);

        }
        result = (float)Math.round(result * 10000f) / 10000f;
        if (resultStr.isEmpty()) resultStr = result + "";
        return resultStr;
    }

    boolean isLastSymbolOperation() {
        String expressionStr = expression.getText().toString();
        int lastIndex = expressionStr.length() - 1;
        return isAnyOperation(expressionStr, lastIndex);
    }


    boolean isLastSymbolDot() {
        return expression.getText().toString().charAt(expression.length() - 1) == '.';
    }
    void replaceLastOperation(String operation) {
        expression.setText(str.replace(str.length() - 1, str.length(), operation));
    }

    boolean isExpressionNotEmpty() {
        return expression.length() != 0;
    }
    void appendOperation(String operation) {
        if (!isExpressionNotEmpty() && isAnswerNumber()) {
            str = new StringBuilder(answer.getText().toString());
            expression.setText(str);
        }
        if (isExpressionNotEmpty() && expression.getText().charAt(expression.length() - 1) == '(') {
            if (!operation.equals("-"))
                return;
        }
        else if (expression.getText().length() > 1 && isLastSymbolOperation() && expression.getText().charAt(expression.length() - 2) == '(') {
            if (expression.getText().charAt(expression.length() - 1) == '-')
                return;
        }
        if (isExpressionNotEmpty()) {
            if (isLastSymbolOperation())
                replaceLastOperation(operation);
            else
                expression.setText(str.append(operation));
        }
    }


    void appendNumber(String number) {
        if (expression.length() > 0 && expression.getText().charAt(expression.length() - 1) == ')') {
            return;
        }
        expression.setText(str.append(number));
    }

    boolean isPlusOrMinus (String expressionStr, int index) {
        return (expressionStr.charAt(index) == '+' || expressionStr.charAt(index) == '-');
    }

    boolean isMultiplyOrDivide (String expressionStr, int index) {
        return (expressionStr.charAt(index) == '*' || expressionStr.charAt(index) == '/');
    }

    boolean isAnyOperation (String expressionStr, int index) {
        return (isMultiplyOrDivide(expressionStr, index) || isPlusOrMinus(expressionStr, index));
    }

    int getEndOfNumber (String expressionStr, int operationIndex) {
        int k = operationIndex + 1;
        while (k < expressionStr.length()) {
            if (isAnyOperation(expressionStr, k)) {
                k--;
                break;
            }
            k++;
        }
        if (k + 1 < expressionStr.length())
            k++;
        return k;
    }

    int getStartOfNumber(String expressionStr, int operationIndex) {
        int j = operationIndex - 1;
        while (j > 0) {
            if (isAnyOperation(expressionStr, j)) {
                j++;
                break;
            }
            j--;
        }
        return j;
    }

    boolean isDotAllowed() {
        String expressionStr = expression.getText().toString();
        char lastChar = expression.getText().charAt(expression.length() - 1);
        if ("1234567890".contains(lastChar + "")) {
            int startOfNumber = getStartOfNumber(expressionStr, expressionStr.length());
            return !(expressionStr.substring(startOfNumber, expressionStr.length() - 1).contains("."));
        }
        return false;
    }


    void clear() {
        expression.setText("");
        answer.setText("");
    }

    boolean isAnswerNumber() {
        try {
            Float.parseFloat(answer.getText().toString());
        }
        catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    String replaceAllBrackets(String expressionStr) {
        String exp = expressionStr;
        int openingBrackets = exp.length() - exp.replace("(", "").length();
        int closingBrackets = exp.length() - exp.replace(")", "").length();
        if (openingBrackets != closingBrackets) {
            return "Can't calculate";
        }
        String result;
        while(exp.contains("(") || exp.contains(")")) {
            int lastClosingBracketIndex = exp.indexOf(")");
            for (int i = lastClosingBracketIndex; i > -1; i--) {
                if (exp.charAt(i) == '(') {
                    if (isExpressionNegativeNumber(exp.substring(i + 1, lastClosingBracketIndex))) {
                        result = exp.substring(i + 1, lastClosingBracketIndex);
                    }
                    else
                        result = calculateExpression(exp.substring(i + 1, lastClosingBracketIndex));
                    exp = exp.replace(exp.substring(i, lastClosingBracketIndex + 1), result);
                    Log.d("res", exp);
                    break;
                }
            }
        }
        return exp;
    }


    String calculateSinCos(String expressionStr) {
        String exp = expressionStr;
        String value, result;
        while (exp.contains("sin") || exp.contains("cos")) {
            int sinIndex, cosIndex, index, code;
            sinIndex = exp.lastIndexOf("sin");
            cosIndex = exp.lastIndexOf("cos");
            if (sinIndex > cosIndex) {
                index = sinIndex;
                code = 1;
            }
            else {
                index = cosIndex;
                code = 2;
            }
            int startOfNumber = code == 1 ? exp.lastIndexOf("sin") + 3 : exp.lastIndexOf("cos") + 3;
            int endOfNumber = getEndOfNumber(exp, startOfNumber);
            value = exp.substring(startOfNumber, endOfNumber);
            result = code == 1 ? Math.sin(Float.parseFloat(value)) + "" : Math.cos(Float.parseFloat(value)) + "";
            exp = exp.replace(exp.substring(index, endOfNumber), result);

        }
        return exp;
    }

    boolean isExpressionNegativeNumber(String expressionStr) {
        return expressionStr.charAt(0) == '-' && !containsAnyOperation(expressionStr.substring(1));
    }

    boolean containsAnyOperation(String expressionStr) {
        return (expressionStr.contains("/") || expressionStr.contains("*")
                || expressionStr.contains("-") || expressionStr.contains("+"));
    }
}
