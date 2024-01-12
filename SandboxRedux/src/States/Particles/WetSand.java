package States.Particles;

import Particle.*;

import java.awt.*;

public class WetSand extends ParticleState {
    public WetSand() {
        super();
    }
    @Override
    public String gravityTick(Particle[] surrounding) {
        if(surrounding[6].getType().equals("Air"))
            return "down";
        else
            return "";
    }

    @Override
    public void selfTick(Particle self) {
        if(self.getBoard().getTemp(self.getRow(), self.getCol()) > 110 && self.getSurrounding()[1].getType().equals("Air")) {
            self.setState(new DrySand());
            self.setColour(Color.YELLOW);
            self.getBoard().setParticle(self.getSurrounding()[1].getRow(), self.getSurrounding()[1].getCol(),new Steam(self.getBoard(), self.getSurrounding()[1].getRow(), self.getSurrounding()[1].getCol()));
        }
    }

    public void interactTick(Particle[] surrounding, Particle self) {
        if(surrounding[6].getType().equals("Water")) {
            int j = self.getCol();
            int i = self.getRow();
            self.getBoard().setParticle(i, j, new Water(self.getBoard(), i, j));
            self.getBoard().setParticle(i + 1, j, new Sand(self.getBoard(), surrounding[6].getRow(), surrounding[6].getCol()));
            surrounding[6].setState(new WetSand());
        }
        else if(surrounding[6].getType().equals("Oil")) {
            int j = self.getCol();
            int i = self.getRow();
            self.getBoard().setParticle(i, j, new Oil(self.getBoard(), i, j));
            self.getBoard().setParticle(i + 1, j, new Sand(self.getBoard(), surrounding[6].getRow(), surrounding[6].getCol()));
            surrounding[6].setState(new WetSand());
        }
    }
}
