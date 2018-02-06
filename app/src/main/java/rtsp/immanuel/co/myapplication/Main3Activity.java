package rtsp.immanuel.co.myapplication;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.majorkernelpanic.streaming.MediaStream;
import net.majorkernelpanic.streaming.Session;
import net.majorkernelpanic.streaming.SessionBuilder;
import net.majorkernelpanic.streaming.audio.AudioQuality;
import net.majorkernelpanic.streaming.gl.SurfaceView;
import net.majorkernelpanic.streaming.rtsp.RtspClient;
import net.majorkernelpanic.streaming.rtsp.RtspServer;
import net.majorkernelpanic.streaming.video.VideoQuality;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.hardware.Camera.CameraInfo;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.Toast;

public class Main3Activity extends AppCompatActivity implements SurfaceHolder.Callback, RtspServer.CallbackListener, Session.Callback{

    private final static String TAG = "MainActivity";

    private SurfaceView mSurfaceView;
    private  Session mSession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);


        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_main);

        mSurfaceView = (SurfaceView) findViewById(R.id.surface);




        // Sets the port of the RTSP server to 1234
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(this).edit();
        editor.putString(  RtspServer.KEY_PORT, String.valueOf(1234));
        editor.commit();



        // Configures the SessionBuilder
        mSession =  SessionBuilder.getInstance()
                .setCallback(this)
                .setSurfaceView((net.majorkernelpanic.streaming.gl.SurfaceView) mSurfaceView)
                .setPreviewOrientation(90)
                .setContext(getApplicationContext())
                .setAudioEncoder(SessionBuilder.AUDIO_AAC)
                .setAudioQuality(new AudioQuality(8000, 16000))
                .setVideoEncoder(SessionBuilder.VIDEO_H264)
                //.setVideoQuality(new VideoQuality(320,240,20,500000))
                .build();

        mSurfaceView.getHolder().addCallback(this);

        ((net.majorkernelpanic.streaming.gl.SurfaceView) mSurfaceView).setAspectRatioMode(net.majorkernelpanic.streaming.gl.SurfaceView.ASPECT_RATIO_PREVIEW);
        String ip, port, path;

        // Starts the RTSP server
        this.startService(new Intent(this,RtspServer.class));

        Log.d("test", "1");



        mSession.startPreview(); //camera preview on phone surface
        mSession.start();

    }

    @Override
    public void onResume()
    {
        super.onResume();
        mSession.stopPreview();
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        mSession.release();
        mSurfaceView.getHolder().removeCallback(this);
    }

    //region   ----------------------------------implement methods required
    @Override
    public void onError(RtspServer server, Exception e, int error) {
        Log.e("Server", e.toString());
    }

    @Override
    public void onMessage(RtspServer server, int message) {
        Log.e("Server", "unkown message");
    }

    @Override
    public void onBitrateUpdate(long bitrate) {

    }

    @Override
    public void onSessionError(int reason, int streamType, Exception e) {

    }

    @Override
    public void onPreviewStarted() {

    }

    @Override
    public void onSessionConfigured() {

    }

    @Override
    public void onSessionStarted() {

    }

    @Override
    public void onSessionStopped() {

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    //endregion
}