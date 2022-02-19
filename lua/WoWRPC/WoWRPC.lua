function ToRGB(red, green, blue)
    local rgb = 0x000000;
    rgb = bit.bor(rgb, bit.lshift(bit.band(red, 0xFF), 16));
    rgb = bit.bor(rgb, bit.lshift(bit.band(green, 0xFF), 8));
    rgb = bit.bor(rgb, bit.lshift(bit.band(blue, 0xFF), 0));
    return rgb;
end

function GetRGB(rgb)
    local values = {};
    table.insert(values, bit.band(bit.rshift(rgb, 16), 0xFF)/255);
    table.insert(values, bit.band(bit.rshift(rgb, 8), 0xFF)/255);
    table.insert(values, bit.band(bit.rshift(rgb, 0), 0xFF)/255);
    return unpack(values);
end

pixelFrame = CreateFrame("Frame",nil,UIParent);

function CreatePixelFrame(x, y)
    local f = CreateFrame("Frame", nil, UIParent);
    f:SetFrameStrata("TOOLTIP");
    f:SetWidth(1);
    f:SetHeight(1);
    f:SetPoint("TOPLEFT",x,y);
    f:Show();
    return f;
end

function CreatePixelTexture(frame)
    local t = frame:CreateTexture(nil,"OVERLAY")
    t:SetTexture("Interface/BUTTONS/WHITE8X8")

    local r, g, b = GetRGB(0x000000);
    t:SetColorTexture(r, g, b);
    t:SetAllPoints(frame)

    frame.texture = t;
    return t
end

textures = {};
function SetPixel(index, r, g, b)
    local texture = textures[index+1];
    if texture == nil then
        local f = CreatePixelFrame(index, 0);
        texture = CreatePixelTexture(f);
        textures[index+1] = texture;
    end

    texture:SetColorTexture(r, g, b);
end

pixelFrame:SetFrameStrata("BACKGROUND");
pixelFrame:SetWidth(GetScreenWidth());
pixelFrame:SetHeight(1);

local t = CreatePixelTexture(pixelFrame);
local r, g, b = GetRGB(0x000000);
t:SetColorTexture(r, g, b);

pixelFrame:SetPoint("TOPLEFT",0,0);
pixelFrame:Show();

local r, g, b = GetRGB(0x126745);
SetPixel(0, r, g, b);
SetPixel(GetScreenWidth()-1, r, g, b);

bytes = {};
function addByte(byte)
    bytes[#bytes+1] = byte;
end

function addChar(char)
    addByte(char);
end

function addShort(short)
    addByte(bit.band(bit.rshift(short, 8), 0xF));
    addByte(bit.band(bit.rshift(short, 0), 0xF));
end

function addInt(int)
    addShort(bit.band(bit.rshift(int, 16), 0xFFFF));
    addShort(bit.band(bit.rshift(int, 0), 0xFFFF));
end

function addLong(long)
    addInt(bit.band(bit.rshift(long, 32), 0xFFFFFFFF));
    addInt(bit.band(bit.rshift(long, 0), 0xFFFFFFFF));
end

function addFloat(float)
    addInt(float);
end

function addDouble(double)
    addLong(double);
end

function addString(string)
    if string == nil then return; end;
    local len = #string;
    addShort(len);
    for i=1, #string do
        addChar(strsub(string, i, i));
    end
end

--[[

Unit -
    Name : String;
    Level : Short;
    Health : Int;
    MaxHealth : Int;

]]

function addUnit(unit)
    local name, realm = UnitName(unit);
    addString(name); -- Name : String;
    addShort(UnitLevel(unit)); -- Level : Short;
    addInt(UnitHealth(unit)); -- Health : Int;
    addInt(UnitHealthMax(unit)); -- MaxHealth : Int;
end

--[[

Player -
    Unit -
        Name : String;
        Level : Short;
        Health : Int;
        MaxHealth : Int;
    Realm : String;
    Target : Unit;
    Position : Double[3];
    ZoneText : String;

]]

function addPlayer(player)
    addUnit(player); -- Self : Unit;

    -- Realm : String;
    local name, realm = UnitName(player);
    if realm == nil then realm = GetRealmName(); end
    addString(realm);
    addUnit(player .. "target") -- Target : Unit;

    -- Position : Double[3];
    -- SetMapToCurrentZone();
    local uiMapID = C_Map.GetBestMapForUnit(player);
    local position = C_Map.GetPlayerMapPosition(uiMapID, player);
    local x, y = position:GetXY();
    addDouble(x); addDouble(y);
end

pixelFrame:SetScript("OnUpdate", function(self, elapsed)
    bytes = {};
    addPlayer("player");
    local bLeftInPixel = 3;
    local curPixelIndex = 1;
    local pixelBytes = {};
    for i, v in ipairs(bytes) do
        if bLeftInPixel <= 0 then 
            SetPixel(curPixelIndex, string.byte(pixelBytes[1])/255, string.byte(pixelBytes[2])/255, string.byte(pixelBytes[3])/255);
            pixelBytes = {};
            curPixelIndex = curPixelIndex + 1;
            bLeftInPixel = 3;
        else 
            pixelBytes[#pixelBytes+1] = v;
            bLeftInPixel = bLeftInPixel - 1;
        end
    end
end)