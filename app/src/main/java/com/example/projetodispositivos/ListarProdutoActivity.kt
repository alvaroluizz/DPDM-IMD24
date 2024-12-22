package com.example.projetodispositivos

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.projetodispositivos.databinding.ActivityListarProdutoBinding

class ListarProdutoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListarProdutoBinding

    // Lista de produtos armazenada globalmente
    private val listaProdutos = mutableListOf<Produto>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inicializa o ViewBinding
        binding = ActivityListarProdutoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Carregar os produtos armazenados no SharedPreferences
        carregarProdutos()

        // Criação da lista de strings formatadas para exibição
        var produtosDetalhados = listaProdutos.map { produto ->
            "Código: ${produto.codigoProduto}\n" +
                    "Nome: ${produto.nomeProduto}\n" +
                    "Descrição: ${produto.descricaoProduto}\n" +
                    "Estoque: ${produto.estoque}\n"
        }

        // Criação do adapter e vinculação ao ListView
        var adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, produtosDetalhados)
        binding.listView.adapter = adapter

        // Configurar evento de clique no botão "Voltar"
        binding.deletar2.setOnClickListener {
            // Voltar para a tela anterior
            var intent = Intent(this, ProdutoActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    // Função para carregar os produtos do SharedPreferences e atualizar a lista
    private fun carregarProdutos() {
        var sharedPreferences = getSharedPreferences("produtos", MODE_PRIVATE)
        val produtoList = sharedPreferences.getStringSet("lista_produtos", mutableSetOf()) ?: mutableSetOf()

        // Limpar a lista antes de adicionar novos produtos
        listaProdutos.clear()

        // Converte as strings para objetos Produto e adiciona à lista
        listaProdutos.addAll(produtoList.mapNotNull {
            val parts = it.split(";")
            if (parts.size == 4) {
                Produto(parts[0], parts[1], parts[2], parts[3].toIntOrNull() ?: 0)
            } else {
                null
            }
        })
    }
}
