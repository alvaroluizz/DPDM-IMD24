package com.example.projetodispositivos

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.projetodispositivos.databinding.ActivityCadastrarProdutoBinding

class CadastrarProdutoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCadastrarProdutoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inicializa o View Binding
        binding = ActivityCadastrarProdutoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configurar evento do botão Salvar
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

                // Redirecionar para a tela de Menu (ProdutoActivity)
                var intent = Intent(this, ProdutoActivity::class.java)
                startActivity(intent)

                // Finalizar a tela de Cadastro de Produto
                finish()
            }
        }

        // Configurar evento do botão Limpar
        binding.btnLimpar.setOnClickListener {
            binding.etCodigoProduto.text.clear()
            binding.etNomeProduto.text.clear()
            binding.etDescricaoProduto.text.clear()
            binding.etEstoque.text.clear()
        }
    }
}
