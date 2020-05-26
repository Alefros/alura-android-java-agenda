package br.com.alura.agenda.database;

import androidx.annotation.NonNull;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import br.com.alura.agenda.model.TipoTelefone;

import static br.com.alura.agenda.model.TipoTelefone.FIXO;

class AgendaMigrations {

    private static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE aluno ADD COLUMN sobrenome TEXT");
        }
    };

    private static final Migration MIGRATION_2_3 = new Migration(2, 3) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE aluno ADD COLUMN momentoDoCadastro INTEGER");
        }
    };

    private static final Migration MIGRATION_3_4 = new Migration(3, 4) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {

            //Criar uma nova tabela temporária
            database.execSQL("CREATE TABLE IF NOT EXISTS `Aluno_novo` (" +
                    "`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                    " `nome` TEXT," +
                    " `telefoneFixo` TEXT," +
                    " `telefoneCelular` TEXT," +
                    " `email` TEXT," +
                    " `momentoDoCadastro` INTEGER)");

            //Copiar dados da tabela antiga
            database.execSQL("INSERT INTO Aluno_novo (id, nome, telefoneFixo, email) " +
                    "SELECT id, nome, telefone, email FROM Aluno");

            //Remover a tabela antiga
            database.execSQL("DROP TABLE Aluno");

            //Renomear a tabela temporária para o nome da tabela antiga
            database.execSQL("ALTER TABLE Aluno_novo RENAME TO Aluno");

        }
    } ;

    private static final Migration MIGRATION_4_5 = new Migration(4, 5) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {

            //Criar nova tabela de alunos
            database.execSQL("CREATE TABLE IF NOT EXISTS `Aluno_novo` (" +
                    "`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                    " `nome` TEXT," +
                    " `email` TEXT," +
                    " `momentoDoCadastro` INTEGER)");

            //Copiar dados da tabela atual
            database.execSQL("INSERT INTO Aluno_novo (id, nome, email) " +
                    "SELECT id, nome, email FROM Aluno");

            //Criar tabela de telefones
            database.execSQL("CREATE TABLE IF NOT EXISTS `Telefone` (" +
                    "`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                    "`numero` TEXT," +
                    " `tipo` TEXT," +
                    " `alunoId` INTEGER NOT NULL)");

            //Copiar dados de telefone da tabela de alunos antiga
            database.execSQL("INSERT INTO Telefone (numero, alunoId) " +
                    "SELECT telefoneFixo, id FROM Aluno");

            //Adicionando tipo de telefone
            database.execSQL("UPDATE Telefone SET tipo = ?", new TipoTelefone[] {FIXO});

            //Remover a tabela de alunos antiga
            database.execSQL("DROP TABLE Aluno");

            //Renomear a tabela temporária para o nome da tabela antiga
            database.execSQL("ALTER TABLE Aluno_novo RENAME TO Aluno");


        }
    } ;

    static final Migration[] TODAS_MIGRATIONS = {
            MIGRATION_1_2, MIGRATION_2_3, MIGRATION_3_4, MIGRATION_4_5};

}
