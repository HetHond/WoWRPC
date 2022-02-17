#ifndef VECTOR3_H
#define VECTOR3_H

struct Vector3 {
    double x, y, z;

    Vector3(void);
    Vector3(double);
    Vector3(double, double);
    Vector3(double, double, double);

    double GetX(void) const;
    double GetY(void) const;
    double GetZ(void) const;
};

#endif