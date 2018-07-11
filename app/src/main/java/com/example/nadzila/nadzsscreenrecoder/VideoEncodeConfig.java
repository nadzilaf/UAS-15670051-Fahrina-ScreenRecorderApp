package com.example.nadzila.nadzsscreenrecoder;

import android.annotation.TargetApi;
import android.media.MediaCodecInfo;
import android.media.MediaFormat;
import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.Objects;

public class VideoEncodeConfig {
    final int width;
    final int height;
    final int bitrate;
    final int framerate;
    final int iframeInterval;
    final String codecName;
    String mimeType = null;
    final MediaCodecInfo.CodecProfileLevel codecProfileLevel;

    public VideoEncodeConfig(int width, int height, int bitrate,
                             int framerate, int iframeInterval,
                             String codecName, String mimeType,
                             MediaCodecInfo.CodecProfileLevel codecProfileLevel) {
        this.width = width;
        this.height = height;
        this.bitrate = bitrate;
        this.framerate = framerate;
        this.iframeInterval = iframeInterval;
        this.codecName = codecName;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            this.mimeType = Objects.requireNonNull(mimeType);
        }
        this.codecProfileLevel = codecProfileLevel;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    MediaFormat toFormat() {
        MediaFormat format = MediaFormat.createVideoFormat(mimeType, width, height);
        format.setInteger(MediaFormat.KEY_COLOR_FORMAT, MediaCodecInfo.CodecCapabilities.COLOR_FormatSurface);
        format.setInteger(MediaFormat.KEY_BIT_RATE, bitrate);
        format.setInteger(MediaFormat.KEY_FRAME_RATE, framerate);
        format.setInteger(MediaFormat.KEY_I_FRAME_INTERVAL, iframeInterval);
        if (codecProfileLevel != null && codecProfileLevel.profile != 0 && codecProfileLevel.level != 0) {
            format.setInteger(MediaFormat.KEY_PROFILE, codecProfileLevel.profile);
            format.setInteger("level", codecProfileLevel.level);
        }
        return format;
    }


    @Override
    public String toString() {
        return "VideoEncodeConfig{" +
                "width=" + width +
                ", height=" + height +
                ", bitrate=" + bitrate +
                ", framerate=" + framerate +
                ", iframeInterval=" + iframeInterval +
                ", codecName='" + codecName + '\'' +
                ", mimeType='" + mimeType + '\'' +
                ", codecProfileLevel=" + (codecProfileLevel == null ? "" : Utils.avcProfileLevelToString(codecProfileLevel)) +
                '}';
    }
}
