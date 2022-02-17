#include <Windows.h>
#include <iostream>

#include "Color.h"

Color GetPixel(int x, int y, HDC dc) {
    return Color(GetPixel(dc, x, y));
}

bool CheckSafeArea(HDC dc) {
    bool BottomRight = GetPixel(50, 50, dc).GetRGB() == 0x126745;
    bool BottomLeft = GetPixel(0, 50, dc).GetRGB() == 0x126745;
    bool TopRight = GetPixel(50, 0, dc).GetRGB() == 0x126745;
    return BottomRight && BottomLeft && TopRight;
}
int main() {
    HWND window = FindWindow("World of Warcraft", 0);
    HDC windowHdc = GetDC(window);

    bool running = true;
    while (running) {
        if (!CheckSafeArea(windowHdc)) continue;

        std::cout << "Hello!!\r";
    }
}
