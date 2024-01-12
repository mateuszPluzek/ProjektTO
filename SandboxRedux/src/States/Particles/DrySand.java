package States.Particles;

import Particle.*;
import RandomNum.RandomEvent;

public class DrySand extends ParticleState {

    public DrySand() {
        super();
    }
    @Override
    public String gravityTick(Particle[] surrounding) {
        if(surrounding[6].getType().equals("Air"))
            return "down";
        else if(surrounding[5].getType().equals("Air") && surrounding[3].getType().equals("Air")) {
            if(surrounding[7].getType().equals("Air") && surrounding[4].getType().equals("Air")) {
                return RandomEvent.eventChance(5) ? "leftdown" : "rightdown";
            }
            return "leftdown";
        }
        else if(surrounding[7].getType().equals("Air") && surrounding[4].getType().equals("Air"))
            return "rightdown";
        else
            return "";
    }

    @Override
    public void selfTick(Particle self) {

        if(self.getBoard().getTemp(self.getRow(), self.getCol()) > 700) {
            int j = self.getCol();
            int i = self.getRow();
            self.getBoard().setParticle(i, j, new Glass(self.getBoard(), i, j));
        }
    }

    @Override
    public void interactTick(Particle[] surrounding, Particle self) {
        if(surrounding[6].getType().equals("Water")) {
            int j = self.getCol();
            int i = self.getRow();
            self.getBoard().setParticle(i, j, new Water(self.getBoard(), i, j));
            self.getBoard().setParticle(i + 1, j, new Sand(self.getBoard(), surrounding[6].getRow(), surrounding[6].getCol()));
        }
        else if(surrounding[6].getType().equals("Oil")) {
            int j = self.getCol();
            int i = self.getRow();
            self.getBoard().setParticle(i, j, new Oil(self.getBoard(), i, j));
            self.getBoard().setParticle(i + 1, j, new Sand(self.getBoard(), surrounding[6].getRow(), surrounding[6].getCol()));
        }

    }
}
