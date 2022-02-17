#ifndef COLOR_H
#define COLOR_H

class Color {
    int red, green, blue;
public:
    Color(int);
    Color(int, int, int);
    int GetRed(void) const;
    int GetGreen(void) const;
    int GetBlue(void) const;
    int GetRGB(void) const;
};

#endif