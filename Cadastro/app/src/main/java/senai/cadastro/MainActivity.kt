package senai.cadastro

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar
import senai.cadastro.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private  lateinit var adapter: ArrayAdapter<User>

    private  var post = -1

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)


        val db =  DBHelper(this)

        val listuser = db.listauserSelectAll()

        adapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,listuser)
        binding.listView.adapter = adapter

        binding.listView.setOnItemClickListener { _, _, position, _ ->
        binding.editUsername.setText(listuser.get(position).username)
        binding.editEmail.setText(listuser.get(position).email)
        binding.editTelefone.setText(listuser.get(position).telefone)
        binding.editPassword.setText(listuser.get(position).password)

         this.post = position
        }

        binding.buttonCadastrar.setOnClickListener {
            val username = binding.editUsername.text.toString()
            val email = binding.editEmail.text.toString()
            val telefone = binding.editTelefone.text.toString()
            val password = binding.editPassword.text.toString()

            val result = db.Insertuser(username, email, telefone, password)

            if (result > 0){
                Snackbar.make(findViewById(android.R.id.content), "Cadastro Efetuado com sucesso", Snackbar.LENGTH_LONG).show()
                listuser.add(User(result.toInt(),username, email, telefone, password))
                adapter.notifyDataSetChanged()

            }else{
                Snackbar.make(findViewById(android.R.id.content), "ERRO! Tente Novamente", Snackbar.LENGTH_LONG).show()
            }

        }

        binding.buttonAtualizar.setOnClickListener {

            if (post >= 0) {

                val id = listuser[post].id
                val username = binding.editUsername.text.toString()
                val email = binding.editEmail.text.toString()
                val telefone = binding.editTelefone.text.toString()
                val password = binding.editPassword.text.toString()

                val result = db.UpdateUser(id, username, email, telefone, password)

                if (result > 0) {
                    Snackbar.make(findViewById(android.R.id.content), "Atualizado com Sucesso", Snackbar.LENGTH_LONG).show()

                    listuser[post].username = username
                    listuser[post].email = username
                    listuser[post].telefone = username
                    listuser[post].password = username

                    adapter.notifyDataSetChanged()

                } else {
                    Snackbar.make(findViewById(android.R.id.content), "ERRO! Tente Novamente", Snackbar.LENGTH_LONG).show()
                }
            }

        }

        binding.buttonExcluir.setOnClickListener {
            if (post >=0){
                val id = listuser[post].id

                val result = db.Deleteuser(id)

                if (result > 0) {
                    Snackbar.make(findViewById(android.R.id.content), "Deletado com Sucesso", Snackbar.LENGTH_LONG).show()
                    listuser.removeAt(post)

                    adapter.notifyDataSetChanged()

                } else {
                    Snackbar.make(findViewById(android.R.id.content), "ERRO! Tente Novamente", Snackbar.LENGTH_LONG).show()
                }
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}