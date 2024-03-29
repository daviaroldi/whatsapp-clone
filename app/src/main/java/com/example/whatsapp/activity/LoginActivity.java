package com.example.whatsapp.activity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.whatsapp.R;
import com.example.whatsapp.config.ConfiguracaoFirebase;
import com.example.whatsapp.helper.Permissao;
import com.google.firebase.database.DatabaseReference;

public class LoginActivity extends AppCompatActivity {

    private EditText email;
    private EditText password;
    private Button logar;
    private String[] permissoes = new String[] {
            Manifest.permission.SEND_SMS,
            Manifest.permission.INTERNET
    };

    private DatabaseReference referenciaDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //valida permissões
        Permissao.validaPermissoes(1, this, permissoes);
        findElements();

//        setMaskTelefone();

        logar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String nomeUsuario = nome.getText().toString();
//                String fone = telefone.getText().toString();
//
//                //remove formatacao
//                fone = fone.replace("(", "")
//                           .replace(")", "")
//                           .replace(" ", "")
//                           .replace("-", "");
//
//                //gera token
//                Random random = new Random();
//                int numero = random.nextInt(9999 - 1000) + 1000; //numeros entre 1000 e 9999
//                String token = String.valueOf(numero);
//
//                Preferencias preferencias = new Preferencias(LoginActivity.this);
//                preferencias.salvarUauarioPreferencias(nomeUsuario, fone, token);
//
//                HashMap<String, String> usuario = preferencias.getDadosUsuario();
//
//                String mensagem = "WhatsApp Código de Confirmação: " + token;
//                //envia SMS +55 -> (51999999999)
//                boolean enviadoSMS = enviaSMS("+55" + usuario.get("telefone"), mensagem);
//
//                if (enviadoSMS) {
////                    Intent intent = new Intent(LoginActivity.this, ValidadorActivity.class);
////                    startActivity(intent);
////                    finish();
//                } else {
//                    Toast.makeText(LoginActivity.this, "Problema ao enviar o SMS, tente novamente!", Toast.LENGTH_LONG).show();
//                }
//
//                Log.i("ENVIADO", ""+ enviadoSMS);
            }
        });
    }

//    private boolean enviaSMS(String telefone, String mensagem) {
//        try {
//            SmsManager smsManager = SmsManager.getDefault();
//            smsManager.sendTextMessage(telefone, null, mensagem, null, null);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//
//        return true;
//    }

    /*
        Valida as permissões de acesso aos recursos do device
     */
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        for (int resultado: grantResults) {
            if (resultado == PackageManager.PERMISSION_DENIED) {
                alertaValidacaoPermissao();
            }
        }
    }

    private void alertaValidacaoPermissao() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Permissões negadas");
        builder.setMessage("Para utilizar o app é necessário aceitar as permissões");

        builder.setPositiveButton("CONFIRMAR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void findElements() {
        password = findViewById(R.id.campoLoginSenha);
        logar = findViewById(R.id.botaoLoginLogar);
        email = findViewById(R.id.campoLoginEmail);
    }

//    private void setMaskTelefone() {
//        if (telefone != null) {
//            SimpleMaskFormatter simpleMaskTelefone = new SimpleMaskFormatter("(NN) NNNNN-NNNN");
//            MaskTextWatcher maskTelefone = new MaskTextWatcher(telefone, simpleMaskTelefone);
//            telefone.addTextChangedListener(maskTelefone);
//        }
//    }

    public void abrirCadastroUsuario(View view) {
        Intent intent = new Intent(LoginActivity.this, CadastroUsuarioActivity.class);
        startActivity(intent);
    }
}
