package br.com.fiap.ecoSafe.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbl_usuario")
data class User(
    @PrimaryKey(autoGenerate = true) var id: Long = 0L,
    var name: String = "",
    var email: String = "",
    var password: String = ""
)
