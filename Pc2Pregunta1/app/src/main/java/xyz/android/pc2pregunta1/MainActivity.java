package xyz.android.pc2pregunta1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class MainActivity extends AppCompatActivity {

    private EditText editTextNombre, editTextFecha, editTextSueldo;
    private RadioGroup radioGroupGenero;
    private RadioButton radioButtonM, radioButtonF;
    private CheckBox checkBoxMusica, checkBoxCine,checkBoxJugar,checkBoxDormir;
    private Button btnmostar,btnlimpiar;
    private TextView resultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextNombre = findViewById(R.id.editTextNombre);
        editTextFecha = findViewById(R.id.editTextFecha);
        editTextSueldo = findViewById(R.id.editTextSueldo);
        radioGroupGenero = findViewById(R.id.radioGroupGenero);
        radioButtonM = findViewById(R.id.radioButtonM);
        radioButtonF = findViewById(R.id.radioButtonF);
        checkBoxMusica = findViewById(R.id.checkBoxMusica);
        checkBoxCine = findViewById(R.id.checkBoxCine);
        checkBoxJugar = findViewById(R.id.checkBoxJugar);
        checkBoxDormir = findViewById(R.id.checkBoxDormir);
        btnmostar = findViewById(R.id.btnmostrar);
        btnlimpiar = findViewById(R.id.btnlimpiar);
        resultado = findViewById(R.id.resultado);

        btnmostar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = editTextNombre.getText().toString();
                String fecha = editTextFecha.getText().toString();
                double sueldo = Double.parseDouble(editTextSueldo.getText().toString());

                LocalDate dateOfBirth = LocalDate.parse(fecha, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                LocalDate now = LocalDate.now();
                int age = Period.between(dateOfBirth, now).getYears();

                String genero = "";
                int selectedGenderId = radioGroupGenero.getCheckedRadioButtonId();
                if (selectedGenderId != -1) {
                    RadioButton selectedGenderButton = findViewById(selectedGenderId);
                    genero = selectedGenderButton.getText().toString();
                }

                String saludo = "";
                if (genero.equals("Masculino")) {
                    saludo = "Sr.";
                } else if (genero.equals("Femenino")) {
                    saludo = "Sra.";
                }
                String actividad = getSelectedActivities();
                String mensaje = saludo + " " + nombre + ", usted tiene: " + age + " años, recibe de sueldo: S/. " +  sueldo
                        +  "soles, es de género" + genero + "y en sus tiempos libres le gusta: " + actividad + ".";
                resultado.setText(mensaje);
            }
        });
        btnlimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextNombre.setText("");
                editTextFecha.setText("");
                editTextSueldo.setText("");
                radioGroupGenero.clearCheck();
                checkBoxMusica.setChecked(false);
                checkBoxCine.setChecked(false);
                checkBoxJugar.setChecked(false);
                checkBoxDormir.setChecked(false);
                resultado.setText("");
            }
        });

    }

    private String getSelectedGender() {
        int selectedRadioButtonId = radioGroupGenero.getCheckedRadioButtonId();
        if (selectedRadioButtonId == radioButtonM.getId()) {
            return "Masculino, ";
        } else if (selectedRadioButtonId == radioButtonF.getId()) {
            return "Femenino, ";
        } else {
            return "";
        }
    }

    private String getSelectedActivities() {
        StringBuilder selectedActivities = new StringBuilder();
        if (checkBoxMusica.isChecked()) {
            selectedActivities.append("Escuchar música, ");
        }
        if (checkBoxCine.isChecked()) {
            selectedActivities.append("Ir al cine, ");
        }
        if (checkBoxJugar.isChecked()) {
            selectedActivities.append("Jugar Futbol, ");
        }
        if (checkBoxDormir.isChecked()) {
            selectedActivities.append("Dormir");
        }
        return selectedActivities.toString();
    }


}