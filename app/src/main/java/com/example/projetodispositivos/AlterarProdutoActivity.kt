package com.example.projetodispositivos

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.projetodispositivos.databinding.ActivityAlterarProdutoBinding

class AlterarProdutoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAlterarProdutoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inicializa o View Binding
        binding = ActivityAlterarProdutoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configura o botão Salvar
        binding.btnSalvar2.setOnClickListener {
            var codigoProduto = binding.etCodigoProduto2.text.toString()
            var nomeProduto = binding.etCodigoProduto3.text.toString()
            var descricaoProduto = binding.etCodigoProduto4.text.toString()
            var estoque = binding.etCodigoProduto5.text.toString()

            // Verificar se os campos foram preenchidos
            if (codigoProduto.isEmpty() || nomeProduto.isEmpty() || descricaoProduto.isEmpty() || estoque.isEmpty()) {
                Toast.makeText(this, "Todos os campos são obrigatórios!", Toast.LENGTH_SHORT).show()
            } else {
                // Cria um novo objeto Produto com os dados inseridos
                var produtoAlterado = Produto(codigoProduto, nomeProduto, descricaoProduto, estoque.toIntOrNull() ?: 0)

                // Altera o produto caso encontrar
                if (alterarProduto(produtoAlterado)) {
                    Toast.makeText(this, "Produto alterado com sucesso!", Toast.LENGTH_SHORT).show()
                    var intent = Intent(this, ProdutoActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this, "Produto não encontrado!", Toast.LENGTH_SHORT).show()
                }
            }
        }

        // Configura o botão Limpar
        binding.btnLimpar2.setOnClickListener {
            binding.etCodigoProduto2.text.clear()
            binding.etCodigoProduto3.text.clear()
            binding.etCodigoProduto4.text.clear()
            binding.etCodigoProduto5.text.clear()
        }
    }

    // Função para alterar o produto
    private fun alterarProduto(produtoAlterado: Produto): Boolean {
        var sharedPreferences = getSharedPreferences("produtos", MODE_PRIVATE)
        var produtoList = sharedPreferences.getStringSet("lista_produtos", mutableSetOf()) ?: mutableSetOf()

        // Encontrar o produto e substituí-lo
        var produtoExcluido = produtoList.find {
            it.startsWith(produtoAlterado.codigoProduto)
        }

        return if (produtoExcluido != null) {
            // Remove o produto antigo
            produtoList.remove(produtoExcluido)

            // Adiciona o produto alterado
            produtoList.add("${produtoAlterado.codigoProduto};${produtoAlterado.nomeProduto};${produtoAlterado.descricaoProduto};${produtoAlterado.estoque}")

            // Salva a lista atualizada
            var editor = sharedPreferences.edit()
            editor.putStringSet("lista_produtos", produtoList)
            editor.apply()

            true
        } else {
            false
        }
    }
}
