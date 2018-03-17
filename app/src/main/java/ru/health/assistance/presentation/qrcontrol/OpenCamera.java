package ru.health.assistance.presentation.qrcontrol;

import android.hardware.Camera;

import com.google.zxing.client.android.camera.open.CameraFacing;

/**
 * Created by sasha_merkulev on 02.05.2017.
 */

public class OpenCamera {
    private final int index;
    private final Camera camera;
    private final CameraFacing facing;
    private final int orientation;

    public OpenCamera(int index, Camera camera, CameraFacing facing, int orientation) {
        this.index = index;
        this.camera = camera;
        this.facing = facing;
        this.orientation = orientation;
    }

    public Camera getCamera() {
        return camera;
    }

    public CameraFacing getFacing() {
        return facing;
    }

    public int getOrientation() {
        return orientation;
    }

    @Override
    public String toString() {
        return "Camera #" + index + " : " + facing + ',' + orientation;
    }
}
