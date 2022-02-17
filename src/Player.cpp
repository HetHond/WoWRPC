#include "Player.h"

std::string Player::GetName(void) const { return player_name; }
std::string Player::GetTargetName(void) const { return target_name; }

Vector3 Player::GetPlayerPos(void) const { return player_pos; }
std::string Player::GetZoneText(void) const { return zone_text; }

int Player::GetHealth(void) const { return health; }
int Player::GetMaxHealth(void) const { return max_health; }