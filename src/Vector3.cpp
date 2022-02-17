#include "Vector3.h"

Vector3::Vector3(void) { this->x = y = z = 0; }
Vector3::Vector3(double xyz) { this->x = y = z = xyz; }
Vector3::Vector3(double x, double y) { this->x = x; this->y = y; this->z = 0; }
Vector3::Vector3(double x, double y, double z) { this->x = x; this->y = y; this->z = z; }

double Vector3::GetX(void) const { return this->x; }
double Vector3::GetY(void) const { return this->y; }
double Vector3::GetZ(void) const { return this->z; }