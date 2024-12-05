package com.example.projetodispositivos

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.projetodispositivos.databinding.ActivityProdutoBinding

class ProdutoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProdutoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inicializa o ViewBinding
        binding = ActivityProdutoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configurar eventos de clique para os botões
        binding.btnCadastrarProduto.setOnClickListener {
            // Criar o Intent para abrir a tela de Cadastrar Produto
            var intent = Intent(this, CadastrarProdutoActivity::class.java)
            startActivity(intent)
        }

        binding.btnListarProduto.setOnClickListener {
            // Ação para o botão Listar Produtos
            var intent = Intent(this, ListarProdutoActivity::class.java)
            startActivity(intent)
        }

        binding.btnAlterarProduto.setOnClickListener {
            // Ação para o botão Alterar Produto
            var intent = Intent(this, AlterarProdutoActivity::class.java)
            startActivity(intent)
        }

        binding.btnDeletarProduto.setOnClickListener {
            // Ação para o botão Deletar Produto
            var intent = Intent(this, ExclusaoProdutoActivity::class.java)
            startActivity(intent)
        }
    }
}
