package com.example.nadzila.nadzsscreenrecoder;

import android.media.MediaCodec;
import android.media.MediaFormat;
import android.os.Build;
import android.util.Log;
import android.view.Surface;

import java.util.Objects;

class VideoEncoder extends BaseEncoder {
    private static final boolean VERBOSE = false;
    private VideoEncodeConfig mConfig;
    private Surface mSurface;


    VideoEncoder(VideoEncodeConfig config) {
        super(config.codecName);
        this.mConfig = config;
    }

    @Override
    protected void onEncoderConfigured(MediaCodec encoder) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            mSurface = encoder.createInputSurface();
        }
        if (VERBOSE) Log.i("@@", "VideoEncoder create input surface: " + mSurface);
    }

    @Override
    protected MediaFormat createMediaFormat() {
        return mConfig.toFormat();
    }
    Surface getInputSurface() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            return Objects.requireNonNull(mSurface, "doesn't prepare()");
        }
        return null;
    }

    @Override
    public void release() {
        if (mSurface != null) {
            mSurface.release();
            mSurface = null;
        }
        super.release();
    }


}