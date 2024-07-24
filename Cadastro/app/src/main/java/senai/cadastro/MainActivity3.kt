package senai.cadastro

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import senai.cadastro.databinding.ActivityMain3Binding

class MainActivity3 : AppCompatActivity() {
    private lateinit var binding: ActivityMain3Binding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain3Binding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        val db =  DBHelper(this)

        val intentId = intent

        intentId.getStringExtra("id").toString().toInt()

        var list = db.listuserSelectById(intentId.getStringExtra("id").toString().toInt())

        binding.editUsername.setText(list.get(0).username)
        binding.editEmail.setText(list.get(0).email)
        binding.editTelefone.setText(list.get(0).telefone)
        binding.editPassword.setText(list.get(0).password)

        binding.buttonAtualizar.setOnClickListener {
            val id =  intentId.getStringExtra("id").toString().toInt()
            val username = binding.editUsername.text.toString()
            val email = binding.editEmail.text.toString().trim()
            val telefone = binding.editTelefone.text.toString()
            val password = binding.editPassword.text.toString()

            val user = User(id, username, email, telefone, password)
            db.UpdateUser(user)

            var i = Intent(this,MainActivity2::class.java)
            startActivity(i)

            finish()
        }

        binding.buttonExcluir.setOnClickListener {

            db.Deleteuser(intentId.getStringExtra("id").toString().toInt())

            var i = Intent(this,MainActivity2::class.java)
            startActivity(i)

            finish()
        }




        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}