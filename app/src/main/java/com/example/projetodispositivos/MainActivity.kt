package com.example.projetodispositivos

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.projetodispositivos.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inicializa o View Binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configurar eventos de clique
        binding.btnEntrar.setOnClickListener {
            var login = binding.Login.text.toString()
            var password = binding.Senha.text.toString()

            // Verifica se o login e a senha são "admin"
            if (login == "admin" && password == "admin") {
                // Redireciona para a tela de ProdutoActivity
                var intent = Intent(this, ProdutoActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                // Exibe mensagem de erro
                Toast.makeText(this, "Login ou senha incorretos!", Toast.LENGTH_SHORT).show()
            }
        }

        // Configura o clique no texto "Esqueci minha senha"
        binding.EsqueciSenha.setOnClickListener {
            Toast.makeText(this, "Recuperação de senha não implementada!", Toast.LENGTH_SHORT).show()
        }
    }
}
