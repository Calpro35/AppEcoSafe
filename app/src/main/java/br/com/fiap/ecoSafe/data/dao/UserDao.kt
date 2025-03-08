package br.com.fiap.ecoSafe.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import br.com.fiap.ecoSafe.data.model.User

//INterface utilizada pelo Room
@Dao
interface UserDao {

    @Insert
    fun salvar(user: User): Long

    @Update
    fun atualizar(user: User): Int

    @Delete
    fun excluir(user: User): Int

    @Query("SELECT * FROM tbl_usuario WHERE id = :id")
    fun buscarUsuarioPeloId(id: Int): User

    @Query("SELECT * FROM tbl_usuario WHERE email = :email AND password = :senha")
    fun verificarLogin(email: String, senha: String): Boolean

}