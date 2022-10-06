package kz.talipovsn.quadratic;

import android.annotation.SuppressLint;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // Локальные переменные для доступа к компонентам окна
    private EditText editText_a, editText_b, editText_x;
    private TextView textView_sum;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Инициализация переменных доступа к компонентам окна
        editText_a = findViewById(R.id.editText_a);
        editText_b = findViewById(R.id.editText_b);
        editText_x = findViewById(R.id.editText_x);
        textView_sum = findViewById(R.id.textView_sum);
        button = findViewById(R.id.button);

        editText_a.addTextChangedListener(loginTextWatcher);
        editText_b.addTextChangedListener(loginTextWatcher);
        editText_x.addTextChangedListener(loginTextWatcher);


        // Проверка на переворот экрана и восстановление нужных значений в компонентах
        if (savedInstanceState != null) {
            textView_sum.setText(savedInstanceState.getString("sum"));
        }
    }


    private TextWatcher loginTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            String editText_a_input = editText_a.getText().toString().trim();
            String editText_b_input = editText_b.getText().toString().trim();
            String editText_x_input = editText_x.getText().toString().trim();

            button.setEnabled(!editText_a_input.isEmpty() && !editText_b_input.isEmpty() && !editText_x_input.isEmpty());

        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };


    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // Сохранение нужных нам значений компонент при перевороте экрана
        outState.putString("sum", textView_sum.getText().toString());
    }

    // МЕТОД ДЛЯ КНОПКИ РАСЧЕТА
    @SuppressLint("DefaultLocale")
    public void onCalc(View v) {

        // Локальные переменные
        double a, b, x, y;

        try {
            // Считывание введенных входных значений в переменные
            a = Double.parseDouble(editText_a.getText().toString());
            b = Double.parseDouble(editText_b.getText().toString());
            x = Double.parseDouble(editText_x.getText().toString());


        } catch (Exception e) {
            // Выдача всплывающего сообщения на экран об ошибке
            Toast.makeText(MainActivity.this, "Неверные входные данные!",
                    Toast.LENGTH_LONG).show();
            return;
        }



        try {

            // Основной алгоритм
            if (x > 6) {
                y = (5 * (Math.pow(a, 2)) - 2) / ((Math.pow(x, 2)) + (Math.pow(b, 2)));
            }
            else {
                y = (x + (8 * (Math.pow(a, 2)) * b));
            }
            textView_sum.setText(String.valueOf("Ответ: " + y));
        } catch (Exception e) {
            String err = "Нет решения!";
            textView_sum.setText(err);
        }

    }

}
