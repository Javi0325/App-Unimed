package com.example.appunimed;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText usuario,contraseña;
    Button crearUsuario,ingresar;
    public ArrayList<Usuario>usuarios=new ArrayList<>();
    String nombreSeleccionado,apellidoSelecionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Intent intent = getIntent();
        if (intent != null && intent.getSerializableExtra("ListaNueva") != null) {
            usuarios = (ArrayList<Usuario>) intent.getSerializableExtra("ListaNueva");
        } else {
            // Si no hay datos, inicializar con valores predeterminados
            usuarios.add(new Usuario("Juan", "Pérez", "001", "jperez", "password123", "Calle Falsa 123"));
            usuarios.add(new Usuario("Ana", "López", "002", "alopez", "pass456", "Avenida Siempre Viva 456"));
            usuarios.add(new Usuario("Carlos", "Gómez", "003", "cgomez", "123456", "Boulevard de los Sueños 789"));
        }
        usuario=findViewById(R.id.txtUsuario);
        contraseña=findViewById(R.id.txtContraseña);
        crearUsuario=findViewById(R.id.btnCrear);
        ingresar=findViewById(R.id.btnIngresar);
        usuarios.add(new Usuario("Juan", "Pérez", "001", "jperez", "password123", "Calle Falsa 123"));
        usuarios.add(new Usuario("Ana", "López", "002", "alopez", "pass456", "Avenida Siempre Viva 456"));
        usuarios.add(new Usuario("Carlos", "Gómez", "003", "cgomez", "123456", "Boulevard de los Sueños 789"));
    }
    public void Ingresar(View v){
        String pruebaUsuario=usuario.getText().toString();
        String pruebaContraseña=contraseña.getText().toString();
        boolean Usuarioencontrado = false;

        for (Usuario user : usuarios) {
            if (user.getUsuario().equals(pruebaUsuario) && user.getContraseña().equals(pruebaContraseña)) {
                Usuarioencontrado = true;
                nombreSeleccionado=user.getNombres();
                apellidoSelecionado=user.getApellidos();
                break;
            }
        }

        if (Usuarioencontrado) {
            Intent Datos= new Intent(getApplicationContext(), BotonPrincipal.class);
            Datos.putExtra("nombre",nombreSeleccionado);
            Datos.putExtra("apellido",apellidoSelecionado);
            startActivity(Datos);
        } else {
            Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
            usuario.setText("");
            contraseña.setText("");
        }
    }
    public void Crear(View v){
        Intent it=new Intent(getApplicationContext(), CrearUsuario.class);
        it.putExtra("ListUsuarios",(Serializable) usuarios);
        startActivity(it);
    }
}