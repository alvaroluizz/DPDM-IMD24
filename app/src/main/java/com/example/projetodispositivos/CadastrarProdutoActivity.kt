package com.example.projetodispositivos

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.projetodispositivos.databinding.ActivityCadastrarProdutoBinding

class CadastrarProdutoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCadastrarProdutoBinding

    // Lista de produtos armazenada globalmente
    private val listaProdutos = mutableListOf<Produto>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inicializa o View Binding
        binding = ActivityCadastrarProdutoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Carregar os produtos armazenados no SharedPreferences
        carregarProdutos()

        binding.btnSalvar.setOnClickListener {
            var codigoProduto = binding.etCodigoProduto.text.toString()
            var nomeProduto = binding.etNomeProduto.text.toString()
            var descricaoProduto = binding.etDescricaoProduto.text.toString()
            var estoque = binding.etEstoque.text.toString()

            // Verificar se todos os campos foram preenchidos
            if (codigoProduto.isEmpty() || nomeProduto.isEmpty() || descricaoProduto.isEmpty() || estoque.isEmpty()) {
                // Exibir Toast caso algum campo não tenha sido preenchido
                Toast.makeText(this, "Por favor, preencha todos os campos!", Toast.LENGTH_SHORT).show()
            } else {
                // Exibir Toast de sucesso
                Toast.makeText(this, "Produto salvo com sucesso!", Toast.LENGTH_SHORT).show()

                // Criar o objeto Produto
                var produto = Produto(codigoProduto, nomeProduto, descricaoProduto, estoque.toIntOrNull() ?: 0)

                // Adicionar o novo produto à lista
                listaProdutos.add(produto)

                // Salvar todos os produtos atualizados
                salvarProdutos()

                // Redireciona para a tela ProdutoActivity
                var intent = Intent(this, ProdutoActivity::class.java)
                startActivity(intent)

                finish()
            }
        }

        // Configurar evento do botão Limpar
        binding.btnLimpar.setOnClickListener {
            // Limpar os campos de texto
            binding.etCodigoProduto.text.clear()
            binding.etNomeProduto.text.clear()
            binding.etDescricaoProduto.text.clear()
            binding.etEstoque.text.clear()
        }
    }

    // Função para carregar os produtos do SharedPreferences
    private fun carregarProdutos() {
        var sharedPreferences = getSharedPreferences("produtos", Context.MODE_PRIVATE)
        var produtoList = sharedPreferences.getStringSet("lista_produtos", mutableSetOf()) ?: mutableSetOf()

        // Limpar a lista antes de adicionar novos produtos
        listaProdutos.clear()

        // Converte as strings para objetos Produto e adiciona à lista
        produtoList.mapNotNull {
            var parts = it.split(";")
            if (parts.size == 4) {
                Produto(parts[0], parts[1], parts[2], parts[3].toIntOrNull() ?: 0)
            } else {
                null
            }
        }.also { listaProdutos.addAll(it) }
    }

    // Função para salvar todos os produtos no SharedPreferences
    private fun salvarProdutos() {
        var sharedPreferences = getSharedPreferences("produtos", Context.MODE_PRIVATE)
        var editor = sharedPreferences.edit()

        // Converte a lista de objetos Produto para strings
        val produtoList = listaProdutos.map {
            "${it.codigoProduto};${it.nomeProduto};${it.descricaoProduto};${it.estoque}"
        }.toMutableSet()

        // Salva a lista atualizada de produtos no SharedPreferences
        editor.putStringSet("lista_produtos", produtoList)
        editor.apply()
    }
}
