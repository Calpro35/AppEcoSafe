package br.com.fiap.ecoSafe.data.repository

import android.content.Context
import br.com.fiap.ecoSafe.data.dao.UserDb
import br.com.fiap.ecoSafe.data.model.User

class ContatoRepository(context: Context) {

    //Criando a instância do banco chamando o método estático. A dependência está sendo
    //obtida manualmente
    private val db = UserDb.getDatabase(context).userDao()

    fun salvar(usuario: User): Long {
        return db.salvar(usuario)
    }

    fun atualizar(usuario: User): Int {
        return db.atualizar(usuario)
    }

    fun excluir(usuario: User): Int {
        return db.excluir(usuario)
    }

    fun buscarContatoPeloId(id: Int): User {
        return db.buscarUsuarioPeloId(id)
    }

    fun verificarLogin(email: String, senha: String): Boolean {
        return db.verificarLogin(email, senha)
    }

}