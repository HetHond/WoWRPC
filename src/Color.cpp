#include "Color.h"

Color::Color(int rgb) {
    this->red = (rgb >> 16) & 0xFF;
    this->green = (rgb >> 8) & 0xFF;
    this->blue = (rgb >> 0) & 0xFF;
}

Color::Color(int red, int green, int blue) {
    this->red = red; this->green = green; this->blue = blue;
}

int Color::GetRed(void) const { return this->red; }
int Color::GetGreen(void) const { return this->green; }
int Color::GetBlue(void) const { return this->blue; }
int Color::GetRGB(void) const { 
    return (red << 16) | (green << 8) | (blue << 0); 
}