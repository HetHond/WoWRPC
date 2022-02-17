package com.hethond.math;

public class Vector3f {
    public float x, y, z;

    public Vector3f() { this.x = y = z = 0; }
    public Vector3f(float xyz) { this.x = y = z = xyz; }
    public Vector3f(float x, float y) { this.x = x; this.y = y; this.z = 0; }
    public Vector3f(float x, float y, float z) { this.x = x; this.y = y; this.z = z; }
    public Vector3f(Vector3f vec) { this(vec.x(), vec.y(), vec.z()); }

    public float x() { return x; }
    public float y() { return y; }
    public float z() { return z; }
}
