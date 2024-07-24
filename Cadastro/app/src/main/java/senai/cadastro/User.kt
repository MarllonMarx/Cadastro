package senai.cadastro

import java.io.Serializable

class User(val id: Int = 0,
           var username : String = "", var email : String = "", var telefone : String = "", var password : String = ""):Serializable {
    override fun toString(): String {
        return  username
    }


}