package br.com.fiap.ecoSafe.data.repository

import android.content.Context
import br.com.fiap.ecoSafe.data.dao.UserDb
import br.com.fiap.ecoSafe.data.model.User

class UserRepository(context: Context) {

    //Criando a instância do banco chamando o método estático. A dependência está sendo
    //obtida manualmente
    private val db = UserDb.getDatabase(context).userDao()

    public fun salvar(usuario: User): Long {
        return db.salvar(usuario)
    }

    public fun atualizar(usuario: User): Int {
        return db.atualizar(usuario)
    }

    public fun excluir(usuario: User): Int {
        return db.excluir(usuario)
    }

    public fun buscarContatoPeloId(id: Int): User {
        return db.buscarUsuarioPeloId(id)
    }

    public fun verificarLogin(email: String, senha: String): Boolean {
        return db.verificarLogin(email, senha)
    }

}