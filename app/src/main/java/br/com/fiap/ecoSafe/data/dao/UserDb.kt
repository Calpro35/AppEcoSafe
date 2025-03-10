package br.com.fiap.ecoSafe.data.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.fiap.ecoSafe.data.model.User

//Retorna a instância do DB para as operações do CRUD serem realizadas
@Database(entities = [User::class], version = 1)
abstract class UserDb : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {

        private lateinit var instance: UserDb

        //Context representa a aplicação
        fun getDatabase(context: Context): UserDb {
            //Garantindo a Singleton
            if (!::instance.isInitialized) {
                instance = Room
                    .databaseBuilder(
                        context,
                        //Instância da Classe que representa o Banco
                        UserDb::class.java,
                        //Nome do Banco
                        "usuario_db"
                    )
                    //Permite que a persistência dos dados ocorra no mesmo processo
                    //que gerencia a UI
                    .allowMainThreadQueries()
                    //A cada nova implementação, o Banco é destruído e depois recriado
                    .fallbackToDestructiveMigration()
                    //Cria a Instância do Banco
                    .build()
            }
            return instance
        }
    }
}