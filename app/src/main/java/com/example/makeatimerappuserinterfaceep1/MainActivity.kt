package com.example.makeatimerappuserinterfaceep1

import android.os.Bundle
import android.os.CountDownTimer
import android.os.health.TimerStat
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    enum class TimerState{
        Stopped, Paused, Running
    }

    private lateinit var timer: CountDownTimer
    private var timerLengthSeconds = 0L //:Long을 참조하지 않아도 가능
    private var timerState = TimerState.Stopped

    private  var secondReamining = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        //액션바에 지정할 이미지와 아이디
        supportActionBar?.setIcon(R.drawable.ic_timer)
        supportActionBar?.title="     Timer"

        fab_start.setOnClickListener{v->
            startTimer() //시작
            timerState = TimerState.Running //멈춤->진행
            updateButtons()
        }

        fab_pause.setOnClickListener{v->
            timer.cancel() //카운트 취소
            timerState = TimerState.Paused//중지
            updateButtons()
        }

        fab_stop.setOnClickListener{v->
            timer.cancel()
            onTimerFinished()
        }
    }

    override fun onResume() {
        super.onResume()
        initTimer()

        //TODO: remove backgroundTimer, hid notification
    }

    override fun onPause() {
        super.onPause()

        if(timerState==TimerState.Running){//진행중에 다른 화면으로 갔다면
            timer.cancel()
            //TODO: start background Timer and show notification
        }else if(timerState == TimerState.Paused)
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
