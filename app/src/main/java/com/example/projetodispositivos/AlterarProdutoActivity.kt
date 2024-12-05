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

            if (codigoProduto.isEmpty()) {
                Toast.makeText(this, "O código do produto é obrigatório!", Toast.LENGTH_SHORT).show()
            } else {
                // Volta para a tela de menu
                var intent = Intent(this, ProdutoActivity::class.java)
                startActivity(intent)
                finish()
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
}
