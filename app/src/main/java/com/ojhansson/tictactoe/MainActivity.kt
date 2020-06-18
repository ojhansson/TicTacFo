package com.ojhansson.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ojhansson.tictactoe.fragments.MenuFragment


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val transaction = supportFragmentManager.beginTransaction()
        val newFragment = MenuFragment()
        transaction.replace(R.id.fragmentContainer, newFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}






//        val transaction: androidx.fragment.app.FragmentTransaction = supportFragmentManager.beginTransaction()
//        transaction.replace(R.id.fragment_container, MenuFragment())
//        transaction.addToBackStack(null)
//        transaction.commit()


//    var player1 = Player("", false)
//    var player2 = Player("", false)
//
//    override fun onSaveInstanceState(outState: Bundle?) {
//        super.onSaveInstanceState(outState)
//        outState?.putParcelable(EXTRA_PLAYER1, player1)
//        outState?.putParcelable(EXTRA_PLAYER2, player2)
//    }
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_start)
//
//
//        cpuSwitch.setOnCheckedChangeListener { buttonView, isChecked ->
//            if (isChecked) {
//                player2Field.setText("TTTBot")
//                player2.isCpu = true
//                player2Field.isEnabled = false
//            } else {
//                player2Field.setText("")
//                player2.isCpu = false
//                player2Field.isEnabled = true
//            }
//
//        }
//    }
//
//    fun onPlayClick(view: View) {
//        setPlayers()
//        startGame()
//    }
//
//    fun setPlayers() {
//        player1.name = player1Field.text.toString()
//        player2.name = player2Field.text.toString()
//    }
//
//    fun startGame() {
//
//        val gameIntent = Intent(this, GameActivity::class.java).apply {
//            putExtra(EXTRA_PLAYER1, player1)
//            putExtra(EXTRA_PLAYER2, player2)
//        }
//        startActivity(gameIntent)
//    }



//    fun twoStart(view: View) {
//        gameType = "twoPlayer"
//        val intent = Intent(this, GameActivity::class.java).apply{
//            putExtra(GAME_TYPE, gameType)
//        }
//        startActivity(intent)
//    }
//}





//    fun showPlayersFragment() {
//        val transaction = fragmentManager.beginTransaction()
//        val newFragment = MenuFragment()
//        transaction.replace(R.id.fragmentContainer, newFragment)
//        transaction.addToBackStack(null)
//        transaction.commit()
//        playersFragmentLoaded = true
//    }
//
//
//    fun showTestFragment() {
//        val transaction = fragmentManager.beginTransaction()
//        val newFragment = TestFragment()
//        transaction.replace(R.id.fragmentContainer, newFragment)
//        transaction.addToBackStack(null)
//        transaction.commit()
//        testFragmentLoaded = true
//        playersFragmentLoaded = false
//    }



