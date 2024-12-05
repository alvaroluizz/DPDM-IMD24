package com.example.projetodispositivos

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.projetodispositivos.databinding.ActivityListarProdutoBinding

class ListarProdutoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListarProdutoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inicializa o ViewBinding
        binding = ActivityListarProdutoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configurar evento de clique no botão "Voltar"
        binding.deletar2.setOnClickListener {
            // Voltar para o menu inicial
            var intent = Intent(this, ProdutoActivity::class.java)
            startActivity(intent)
            finish()
        }

        // Aqui você pode configurar a ListView com os dados, se necessário.
    }
}
