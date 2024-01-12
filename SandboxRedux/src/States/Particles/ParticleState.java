package States.Particles;

import Particle.Particle;

public class ParticleState {

    public ParticleState() {}

    public String gravityTick(Particle[] surrounding) {
        return "";
    }

    public void selfTick(Particle self) {
        return;
    }

    public void interactTick(Particle[] surrounding, Particle self) {
        return;
    }


}
