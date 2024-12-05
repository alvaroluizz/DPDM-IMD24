package com.example.projetodispositivos

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.projetodispositivos.databinding.ActivityExclusaoProdutoBinding

class ExclusaoProdutoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityExclusaoProdutoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inicializa o ViewBinding
        binding = ActivityExclusaoProdutoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configurar eventos de clique
        binding.deletar.setOnClickListener {
            var codigoProduto = binding.etCodigoProduto6.text.toString()

            if (codigoProduto.isBlank()) {
                Toast.makeText(this, "O código do produto é obrigatório!", Toast.LENGTH_SHORT).show()
            } else {
                // Aqui você pode implementar a lógica para deletar o produto.
                Toast.makeText(this, "Produto excluído com sucesso!", Toast.LENGTH_SHORT).show()

                // Retorna para a tela do menu principal
                var intent = Intent(this, ProdutoActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

        binding.btnLimpar3.setOnClickListener {
            // Limpa o campo de entrada
            binding.etCodigoProduto6.text.clear()
        }
    }
}
