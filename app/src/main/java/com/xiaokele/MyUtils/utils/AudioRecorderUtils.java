package com.xiaokele.MyUtils.utils;

//import it.sauronsoftware.jave.AudioAttributes;
//import it.sauronsoftware.jave.Encoder;
//import it.sauronsoftware.jave.EncoderException;
//import it.sauronsoftware.jave.EncodingAttributes;
//import it.sauronsoftware.jave.InputFormatException;

import android.media.MediaRecorder;
import android.util.Log;

import java.io.File;
import java.io.IOException;

public class AudioRecorderUtils {
    private static final String TAG = "AudioRecorderUtils";
    private static int SAMPLE_RATE_IN_HZ = 8000; // 采样率���

    private MediaRecorder mMediaRecorder;
    private String mVoicePath;

    public AudioRecorderUtils() {
        if (mMediaRecorder == null) {
            mMediaRecorder = new MediaRecorder();
        }
    }

    public void setVoicePath(String path, String filename) {
        this.mVoicePath = path + File.separator + filename + ".amr";
        Log.i(TAG, "mVoicePath:" + mVoicePath);
    }

    public String getVoicePath() {
        return this.mVoicePath;
    }

    public void start() throws IOException {
        File directory = new File(mVoicePath).getParentFile();
        if (!directory.exists() && !directory.mkdirs()) {
            throw new IOException("Path to file could not be created");
        }
        mMediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mMediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.RAW_AMR);
        // mMediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.AMR_NB);
        mMediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        // recorder.setAudioChannels(AudioFormat.CHANNEL_CONFIGURATION_MONO);
        //mMediaRecorder.setAudioSamplingRate(SAMPLE_RATE_IN_HZ);
        mMediaRecorder.setOutputFile(mVoicePath);
        mMediaRecorder.prepare();
        mMediaRecorder.start();
    }

    public void stop() throws IOException {
        mMediaRecorder.stop();
        mMediaRecorder.release();
    }

    public double getAmplitude() {
        if (mMediaRecorder != null) {
            try {
                return (mMediaRecorder.getMaxAmplitude());
            } catch (IllegalStateException e) {
                return 0;
            }
        } else
            return 0;
    }
}