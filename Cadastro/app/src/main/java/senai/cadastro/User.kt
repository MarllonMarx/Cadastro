package senai.cadastro

class User(val id: Int = 0,
           var username : String = "", var email : String = "", var telefone : String = "", var password : String = "") {
    override fun toString(): String {
        return  username
    }


}