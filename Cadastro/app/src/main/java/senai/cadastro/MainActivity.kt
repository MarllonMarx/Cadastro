package senai.cadastro

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
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

        val listuser = ArrayList<User>()
        val db =  DBHelper(this)

        val listuser1 = db.listauserSelectAll()

        adapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,listuser1)



        binding.buttonCadastrar.setOnClickListener {

            val username = binding.editUsername.text.toString().trim()
            val email = binding.editEmail.text.toString().trim()
            val telefone = binding.editTelefone.text.toString().trim()
            val password = binding.editPassword.text.toString().trim()

            val result = db.Insertuser(username, email, telefone, password)

            if (!username.isEmpty() && !email.isEmpty() && !telefone.isEmpty() && !password.isEmpty()){


                Snackbar.make(findViewById(android.R.id.content), "Usuário $username Cadastrado com Sucesso", Snackbar.LENGTH_LONG).show()
                listuser.add(User(result.toInt(),username, email, telefone, password))
                adapter.notifyDataSetChanged()

            }else{
                Snackbar.make(findViewById(android.R.id.content), "ERRO! Campos Vazios", Snackbar.LENGTH_LONG).show()
            }

        }

        binding.buttonListar.setOnClickListener {

            var i = Intent(this,MainActivity2::class.java)
            i.putExtra("lista",listuser)
            startActivity(i)
        }
        /*

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

        */
    }


}

