package br.com.fiap.ecoSafe.utils

import android.util.Base64

fun ByteArray.toBase64(): String {
    return Base64.encodeToString(this, Base64.DEFAULT)
}