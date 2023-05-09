package xyz.android.pc02pregunta3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;


public class MainActivity extends AppCompatActivity{
    EditText nombre, fecha;
    Button btnEnviar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nombre = findViewById(R.id.nombre);
        fecha = findViewById(R.id.fecha);
        btnEnviar = findViewById(R.id.btnEnviar);

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nombreStr = nombre.getText().toString();
                String fechaStr = fecha.getText().toString();

                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                Date fechaNacimiento = null;
                try {
                    fechaNacimiento = dateFormat.parse(fechaStr);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                long edad = calcularEdad(fechaNacimiento);
                ResultadoFragment resultadoFragment = ResultadoFragment.newInstance(nombreStr, edad);
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragmento, resultadoFragment);
                fragmentTransaction.commit();
            }
        });
    }

    private long calcularEdad(Date fechaNacimiento) {
        Date fechaActual = new Date();
        long diferenciaEnMilisegundos = fechaActual.getTime() - fechaNacimiento.getTime();
        long edad = diferenciaEnMilisegundos / 31536000000L; // 1 año = 31,536,000,000 ms
        return edad;
    }
}
