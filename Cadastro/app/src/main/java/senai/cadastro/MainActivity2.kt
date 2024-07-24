package senai.cadastro

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import senai.cadastro.databinding.ActivityMain2Binding

class MainActivity2 : AppCompatActivity() {

    private  lateinit var binding: ActivityMain2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        val listView: ListView = findViewById(R.id.listview)
        val listUser = intent.getSerializableExtra("lista") as ArrayList<User>
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listUser)
        listView.adapter = adapter

        binding.listview.setOnItemClickListener { _, _, position, _ ->

            binding.editUsername.setText(listUser.get(position).username)
            binding.editEmail.setText(listUser.get(position).email)
            binding.editTelefone.setText(listUser.get(position).telefone)
            binding.editPassword.setText(listUser.get(position).password)
        }

        binding.button.setOnClickListener {

        }







        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}