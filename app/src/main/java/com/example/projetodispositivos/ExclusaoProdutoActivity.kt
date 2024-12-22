package com.example.projetodispositivos

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.projetodispositivos.databinding.ActivityExclusaoProdutoBinding

class ExclusaoProdutoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityExclusaoProdutoBinding

    // Lista de produtos armazenada globalmente
    private val listaProdutos = mutableListOf<Produto>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inicializa o ViewBinding
        binding = ActivityExclusaoProdutoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Carregar os produtos armazenados no SharedPreferences
        carregarProdutos()

        // Configurar evento de clique para deletar o produto
        binding.deletar.setOnClickListener {
            var codigoProduto = binding.etCodigoProduto6.text.toString()

            if (codigoProduto.isBlank()) {
                // Exibe mensagem de erro caso o código não seja informado
                Toast.makeText(this, "O código do produto é obrigatório!", Toast.LENGTH_SHORT).show()
            } else {
                // Tentamos excluir o produto com o código informado
                var produtoExcluido = excluirProduto(codigoProduto)

                if (produtoExcluido) {
                    // Exibe uma mensagem de sucesso se o produto foi encontrado e excluído
                    Toast.makeText(this, "Produto excluído com sucesso!", Toast.LENGTH_SHORT).show()

                    // Volta para a tela de Produtos
                    var intent = Intent(this, ProdutoActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    // Exibe uma mensagem caso o produto não seja encontrado
                    Toast.makeText(this, "Produto não encontrado!", Toast.LENGTH_SHORT).show()
                }
            }
        }

        // Configura o evento de clique para limpar o campo
        binding.btnLimpar3.setOnClickListener {
            binding.etCodigoProduto6.text.clear()
        }
    }

    // Função para carregar os produtos do SharedPreferences e atualizar a lista
    private fun carregarProdutos() {
        var sharedPreferences = getSharedPreferences("produtos", MODE_PRIVATE)
        var produtoList = sharedPreferences.getStringSet("lista_produtos", mutableSetOf()) ?: mutableSetOf()

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

    // Função para excluir o produto baseado no código
    private fun excluirProduto(codigoProduto: String): Boolean {
        // Tenta encontrar o produto com o código correspondente na lista
        var produtoExcluido = listaProdutos.find { it.codigoProduto == codigoProduto }

        return if (produtoExcluido != null) {
            // Remove o produto da lista
            listaProdutos.remove(produtoExcluido)

            // Salva a lista atualizada no SharedPreferences
            salvarProdutos()

            true
        } else {
            false
        }
    }

    // Função para salvar a lista atualizada de produtos no SharedPreferences
    private fun salvarProdutos() {
        var sharedPreferences = getSharedPreferences("produtos", MODE_PRIVATE)
        var editor = sharedPreferences.edit()

        // Converte a lista de objetos Produto para um Set de strings
        val produtoList = listaProdutos.map {
            "${it.codigoProduto};${it.nomeProduto};${it.descricaoProduto};${it.estoque}"
        }.toMutableSet()

        // Salva a lista atualizada
        editor.putStringSet("lista_produtos", produtoList)
        editor.apply()
    }
}
