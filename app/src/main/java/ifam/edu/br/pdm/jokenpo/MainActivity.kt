package ifam.edu.br.pdm.jokenpo

import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.Random

class MainActivity : AppCompatActivity() {

    private lateinit var player1: ImageView
    private lateinit var player2: ImageView
    private lateinit var buttonPedra: ImageButton
    private lateinit var buttonPapel: ImageButton
    private lateinit var buttonTesoura: ImageButton
    private lateinit var buttonSpock: ImageButton
    private lateinit var buttonLagarto: ImageButton

    private var jogada1 = 0
    private var jogada2 = 0

    private lateinit var some: AlphaAnimation
    private lateinit var aparece: AlphaAnimation

    private var mediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        player1 = findViewById(R.id.imageViewP1)
        player2 = findViewById(R.id.imageViewP2)
        buttonPedra = findViewById(R.id.buttonPedra)
        buttonPapel = findViewById(R.id.buttonPapel)
        buttonTesoura = findViewById(R.id.buttonTesoura)
        buttonSpock = findViewById(R.id.buttonSpock)
        buttonLagarto = findViewById(R.id.buttonLagarto)

        some = AlphaAnimation(1f, 0f)
        aparece = AlphaAnimation(0f, 1f)

        some.duration = 1500
        aparece.duration = 250

        some.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {
                player2.visibility = View.VISIBLE
            }

            override fun onAnimationEnd(animation: Animation?) {
                player2.visibility = View.INVISIBLE
                player2.startAnimation(aparece)
            }

            override fun onAnimationRepeat(animation: Animation?) {

            }
        })

        aparece.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {
                sorteioJogadaInimigo()
                player2.visibility = View.INVISIBLE
            }

            override fun onAnimationEnd(animation: Animation?) {
                verificaJogada()
                player2.visibility = View.VISIBLE
            }

            override fun onAnimationRepeat(animation: Animation?) {

            }
        })
    }

    fun toqueBotao(view: View) {
        tocaSom()
        player1.scaleX = -1f

        when (view.id) {
            R.id.buttonPedra -> {
                player1.setImageResource(R.drawable.pedra)
                jogada1 = 1
            }

            R.id.buttonPapel -> {
                player1.setImageResource(R.drawable.papel)
                jogada1 = 2
            }

            R.id.buttonTesoura -> {
                player1.setImageResource(R.drawable.tesoura)
                jogada1 = 3
            }

            R.id.buttonSpock -> {
                player1.setImageResource(R.drawable.spock)
                jogada1 = 4
            }

            R.id.buttonLagarto -> {
                player1.setImageResource(R.drawable.lagarto)
                jogada1 = 5
            }
        }

        player2.startAnimation(some)
        player2.setImageResource(R.drawable.interrogacao)
    }

    fun sorteioJogadaInimigo() {
        val r = Random()

        when (r.nextInt(5)) {
            0 -> {
                player2.setImageResource(R.drawable.pedra)
                jogada2 = 1
            }

            1 -> {
                player2.setImageResource(R.drawable.papel)
                jogada2 = 2
            }

            2 -> {
                player2.setImageResource(R.drawable.tesoura)
                jogada2 = 3
            }

            3 -> {
                player2.setImageResource(R.drawable.spock)
                jogada2 = 4
            }

            4 -> {
                player2.setImageResource(R.drawable.lagarto)
                jogada2 = 5
            }
        }
    }

    fun verificaJogada() {
        when {
            jogada1 == jogada2 -> Toast.makeText(this, "Empate!", Toast.LENGTH_SHORT).show()

            jogada1 == 1 && (jogada2 == 3 || jogada2 == 5) ||
                    jogada1 == 2 && (jogada2 == 1 || jogada2 == 4) ||
                    jogada1 == 3 && (jogada2 == 2 || jogada2 == 5) ||
                    jogada1 == 4 && (jogada2 == 1 || jogada2 == 3) ||
                    jogada1 == 5 && (jogada2 == 2 || jogada2 == 4) ->
                Toast.makeText(this, "Ganhei!", Toast.LENGTH_SHORT).show()

            jogada2 == 1 && (jogada1 == 3 || jogada1 == 5) ||
                    jogada2 == 2 && (jogada1 == 1 || jogada1 == 4) ||
                    jogada2 == 3 && (jogada1 == 2 || jogada1 == 5) ||
                    jogada2 == 4 && (jogada1 == 1 || jogada1 == 3) ||
                    jogada2 == 5 && (jogada1 == 2 || jogada1 == 4) ->
                Toast.makeText(this, "Perdi!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun tocaSom() {
        mediaPlayer?.start()
    }
}
