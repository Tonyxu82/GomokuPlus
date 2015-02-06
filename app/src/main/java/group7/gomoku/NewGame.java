package group7.gomoku;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.view.View.OnClickListener;

import java.util.concurrent.TimeUnit;


public class NewGame extends MainActivity {

    Button btnPass;
    ImageButton btnPause;
    TextView textViewTime;
    ImageView imageBoard;
    String cTime;
    Game mGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_game);
        btnPass = (Button) findViewById(R.id.btnPass);
        btnPause = (ImageButton) findViewById(R.id.btn_pause);
        textViewTime = (TextView) findViewById(R.id.textViewTime);

        textViewTime.setText("03:00");
        final CounterClass timer = new CounterClass(180000,1000);

        timer.start();
        //btnPass.setBackgroundColor(BLUE);
        btnPause.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               timer.cancel();


           }

            } );
        btnPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timer.start();
                mGame.changeTurn();
            }
        });

        mGame = new Game(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_new_game, menu);
        super.onCreateOptionsMenu(menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public class CounterClass extends CountDownTimer {
        public CounterClass (long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick (long millisUntilFinished) {
            long millis = millisUntilFinished;
            String ms = String.format("%02d:%02d", TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
                    TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
            //cTime is storing the current time left on the clock into a variable that pauseButton onclicklistener can then store to recover on resume.
            cTime = ms;


            //System.out.println(ms);
            textViewTime.setText(ms);
        }

        @Override
        public void onFinish() {

            textViewTime.setText("DONE.");
        }
    }



}
