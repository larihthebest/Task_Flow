package com.example.taskflow.repository

import android.content.ContentValues
import android.content.Context
import com.example.taskflow.model.Tarefa
import com.example.taskflow.model.TarefaDAO

class TarefaRepository(context: Context) {
    companion object {
        private const val table = "tarefas"
    }

    private val db = TarefaDAO(context).writableDatabase

    // CRUD
    fun insert(tarefa: Tarefa): Long {
        val contentValues = ContentValues().apply {
            put("titulo", tarefa.titulo)
            put("descricao", tarefa.descricao)
        }
        return db.insert(table, null, contentValues)
    }

    fun findOne(tarefa: Tarefa): Tarefa? {
        val cursor = db.query(
            table,
            arrayOf("id"),
            "titulo = ? AND descricao = ?",
            arrayOf(tarefa.titulo, tarefa.descricao),
            null,
            null,
            null
        )
        val result = cursor.moveToFirst()
        if (!result) {
            cursor.close()
            return null
        }
        val id = cursor.getLong(0)
        cursor.close()
        return Tarefa(id, tarefa.titulo, tarefa.descricao)
    }

    fun getAllTarefas(): List<Tarefa> {
        val tarefas = mutableListOf<Tarefa>()
        val cursor = db.query(table, arrayOf("id", "titulo", "descricao"), null, null, null, null, null)
        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getLong(0)
                val titulo = cursor.getString(1)
                val descricao = cursor.getString(2)
                tarefas.add(Tarefa(id, titulo, descricao))
            } while (cursor.moveToNext())
        }
        cursor.close()
        return tarefas
    }
}
