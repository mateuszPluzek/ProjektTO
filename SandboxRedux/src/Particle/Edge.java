package Particle;

import States.Particles.ParticleState;
import Window.Board;
import java.awt.*;

//  help class used so in edge cases other particles don't try to interact with bull values
public class Edge extends Particle{

    public Edge(Board board) {
        super(board, Color.BLACK, "Edge", 0, new ParticleState(), -1, -1);
    }

}
