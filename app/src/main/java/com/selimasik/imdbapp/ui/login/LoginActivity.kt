package com.selimasik.imdbapp.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.selimasik.imdbapp.R
import com.selimasik.imdbapp.databinding.ActivityLoginBinding
import com.selimasik.imdbapp.ui.filmKategoriListeleme.KategoriListesiActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        auth = FirebaseAuth.getInstance()
        binding.btnGirisYap.setOnClickListener {
            if(binding.edtEmailGiris.text.trim().toString().isNotEmpty() && binding.edtSifreGiris.text.trim().toString().isNotEmpty()){
                loginUser(binding.edtEmailGiris.text.trim().toString(),binding.edtSifreGiris.text.trim().toString())
            }else{
                Toast.makeText(this,resources.getString(R.string.veriler_bos_gecilemez),Toast.LENGTH_SHORT).show()
            }

        }
    }

    fun loginUser(email:String, password:String){

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this){task->
                if(task.isSuccessful){
                    Toast.makeText(this,resources.getString(R.string.giris_basarili),Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this@LoginActivity, KategoriListesiActivity::class.java))
                    finish()
                }
                else{
                    Toast.makeText(this,resources.getString(R.string.kullanici_bulunamadi),Toast.LENGTH_SHORT).show()
                }
            }
    }
}


