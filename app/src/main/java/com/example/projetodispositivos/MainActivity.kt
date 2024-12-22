package com.example.projetodispositivos

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.projetodispositivos.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    // Lista de produtos armazenada globalmente
    private val listaProdutos = mutableListOf<Produto>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inicializa o View Binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Recupera os dados salvos de login
        var savedLogin = recuperarTexto(this, "login")
        var savedPassword = recuperarTexto(this, "password")

        // Preenche os campos com os dados salvos
        if (savedLogin.isNotEmpty()) {
            binding.Login.setText(savedLogin)
        }
        if (savedPassword.isNotEmpty()) {
            binding.Senha.setText(savedPassword)
        }

        // Carregar os produtos do SharedPreferences
        carregarProdutos()

        // Configura o clique no botão de login
        binding.btnEntrar.setOnClickListener {
            var login = binding.Login.text.toString()
            var password = binding.Senha.text.toString()

            if (login == "admin" && password == "admin") {
                // Salva os dados de login e senha no SharedPreferences
                salvarTexto(this, "login", login)
                salvarTexto(this, "password", password)

                // Exibe um Toast com a quantidade de produtos carregados
                if (listaProdutos.isNotEmpty()) {
                    Toast.makeText(this, "Carregados ${listaProdutos.size} produtos.", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Nenhum produto encontrado.", Toast.LENGTH_SHORT).show()
                }

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

    // Função para salvar texto no SharedPreferences
    fun salvarTexto(context: Context, key: String, texto: String) {
        var sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        var editor = sharedPreferences.edit()
        editor.putString(key, texto)
        editor.apply()
    }

    // Função para recuperar texto do SharedPreferences
    fun recuperarTexto(context: Context, key: String): String {
        var sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        return sharedPreferences.getString(key, "") ?: ""
    }

    // Função para carregar os produtos do SharedPreferences
    fun carregarProdutos() {
        val sharedPreferences = getSharedPreferences("produtos", MODE_PRIVATE)
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
