package com.example.nadzila.nadzsscreenrecoder;

import android.annotation.TargetApi;
import android.media.MediaFormat;
import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.Objects;

public class AudioEncodeConfig {
    final String codecName;
    final String mimeType;
    final int bitRate;
    final int sampleRate;
    final int channelCount;
    final int profile;

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public AudioEncodeConfig(String codecName, String mimeType,
                             int bitRate, int sampleRate, int channelCount, int profile) {
        this.codecName = codecName;
        this.mimeType = Objects.requireNonNull(mimeType);
        this.bitRate = bitRate;
        this.sampleRate = sampleRate;
        this.channelCount = channelCount;
        this.profile = profile;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    MediaFormat toFormat() {
        MediaFormat format = MediaFormat.createAudioFormat(mimeType, sampleRate, channelCount);
        format.setInteger(MediaFormat.KEY_AAC_PROFILE, profile);
        format.setInteger(MediaFormat.KEY_BIT_RATE, bitRate);
        return format;
    }

    @Override
    public String toString() {
        return "AudioEncodeConfig{" +
                "codecName='" + codecName + '\'' +
                ", mimeType='" + mimeType + '\'' +
                ", bitRate=" + bitRate +
                ", sampleRate=" + sampleRate +
                ", channelCount=" + channelCount +
                ", profile=" + profile +
                '}';
    }
}
