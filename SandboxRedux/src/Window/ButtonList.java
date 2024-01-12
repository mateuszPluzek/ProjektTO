package Window;

import States.Window.*;

import java.awt.*;

public class ButtonList {
    private String[] types;
    private ButtonState[] states;
    private Color[] colors;
    private int size;

    public ButtonList() {
        types = new String[]{
                "Air",
                "Sand",
                "Dirt",
                "Gunpowder",
                "Stone",
                "Steel",
                "Wood",
                "Glass",
                "Insulation",
                "Water",
                "Oil",
                "Acid",
                "Steam",
                "Gas",
                "Fire",
                "Virus",
                "Heater",
                "Cooler"

        };
        states = new ButtonState[]{
                new DrawAirState(),
                new DrawSandState(),
                new DrawDirtState(),
                new DrawGunpowderState(),
                new DrawStoneState(),
                new DrawSteelState(),
                new DrawWoodState(),
                new DrawGlassState(),
                new DrawInsulationState(),
                new DrawWaterState(),
                new DrawOilState(),
                new DrawAcidState(),
                new DrawSteamState(),
                new DrawGasState(),
                new DrawFireState(),
                new DrawVirusState(),
                new DrawHeaterState(),
                new DrawCoolerState()
        };
        colors = new Color[]{
                new Color(0xFFFFFF),
                new Color(0xA9A914),
                new Color(0x83421A),//dirt
                new Color(100, 100, 100),//gunpowder
                new Color(0x564F4F),//stone
                new Color(0xADADAD),//steel
                new Color(140, 86, 69),//wood
                new Color(0xFFFFFF),//glass
                new Color(0xB2B252),//insulation
                new Color(0x144EC2),//water
                new Color(0xA2A262),//oil
                new Color(0x098809),//acid
                new Color(0x919191),//steam
                new Color(255, 250, 195),//gas
                new Color(0xAD0808),//fire
                new Color(0x6C2AAD),//virus
                new Color(75, 0, 0),//heater
                new Color(58, 118, 171)//cooler
        };
        size = types.length;
    }

    public String getType (int index) {
        return types[index];
    }

    public ButtonState getState(int index) {
        return states[index];
    }

    public Color getcolor(int index) {
        return colors[index];
    }

    public int getSize() {
        return size;
    }
}
