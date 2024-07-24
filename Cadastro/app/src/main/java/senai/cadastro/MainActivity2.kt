package senai.cadastro

import android.content.Intent
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
    private  lateinit var adapter: ArrayAdapter<User>
    private var position = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        val db =  DBHelper(this)
        val listuser1 = db.listauserSelectAll()

        adapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,listuser1)

        binding.listview.adapter = adapter

        adapter.notifyDataSetChanged()


        binding.listview.setOnItemClickListener { _, _, position, _ ->

            binding.id.setText(listuser1.get(position).id.toString())

            this.position = position
        }

        binding.button.setOnClickListener {
            var i = Intent(this,MainActivity3::class.java).putExtra("id",binding.id.text)
            startActivity(i)

        }







        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}