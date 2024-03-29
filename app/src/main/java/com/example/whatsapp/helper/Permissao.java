package com.example.whatsapp.helper;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

public class Permissao {
    /*
        Checa se tem permissão de acesso aos recursos do device,
        caso não tenha solicita ao usuário
     */
    public static boolean validaPermissoes(int requestCode, Activity activity, String[] permissoes) {
        List<String> listaPermissoes = new ArrayList<>();
//        if (Build.VERSION.SDK_INT >= 23)
        for (String permissao : permissoes) {
            if (ContextCompat.checkSelfPermission(activity, permissao) != PackageManager.PERMISSION_GRANTED) {
                listaPermissoes.add(permissao);
            }
        }

        if (listaPermissoes.isEmpty()) {
            return true;
        }

        String[] novasPermissoes = new String[listaPermissoes.size()];
        listaPermissoes.toArray(novasPermissoes);

        ActivityCompat.requestPermissions(activity, novasPermissoes, requestCode);

        return true;
    }
}
