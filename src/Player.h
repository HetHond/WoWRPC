#ifndef PLAYER_H
#define PLAYER_H

#include <string>
#include "Vector3.h"

struct Player {
    std::string player_name;
    std::string target_name;

    Vector3 player_pos;
    int health, max_health;

    std::string zone_text;

    /* Getters Just because it makes everything
       more readable i think but this doesn't
       really serve a purpose */

    /* Returns Player's name */
    std::string GetName(void) const;
    /* Returns Player Target's name */
    std::string GetTargetName(void) const;

    /* Returns Player position vector */
    Vector3 GetPlayerPos(void) const;

    /* Returns current Player's health */
    int GetHealth(void) const;
    /* Returns Player's max health */
    int GetMaxHealth(void) const;

    /* Get current zone text exmpl "stormwing" */
    std::string GetZoneText(void) const;
};

#endif