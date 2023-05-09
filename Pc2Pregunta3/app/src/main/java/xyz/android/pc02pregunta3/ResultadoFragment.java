package xyz.android.pc02pregunta3;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class ResultadoFragment extends Fragment {
    private static final String ARG_NOMBRE = "nombre";
    private static final String ARG_EDAD = "edad";

    private String nombre;
    private long edad;

    public ResultadoFragment() {
        // Required empty public constructor
    }

    public static ResultadoFragment newInstance(String nombre, long edad) {
        ResultadoFragment fragment = new ResultadoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_NOMBRE, nombre);
        args.putLong(ARG_EDAD, edad);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            nombre = getArguments().getString(ARG_NOMBRE);
            edad = getArguments().getLong(ARG_EDAD);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_resultado, container, false);
        TextView resultadoTextView = view.findViewById(R.id.txt_resultado);
        String mensaje = "Hola " + nombre + ", tu edad es " + edad + " años.";
        resultadoTextView.setText(mensaje);
        return view;
    }
}