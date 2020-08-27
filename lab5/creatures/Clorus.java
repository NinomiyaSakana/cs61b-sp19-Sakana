package creatures;

import huglife.Creature;
import huglife.Direction;
import huglife.Action;
import huglife.Occupant;

import java.awt.Color;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

import static huglife.HugLifeUtils.random;
import static huglife.HugLifeUtils.randomEntry;

/**
 * @Sakana
 */

public class Clorus extends Creature{
    /**
     * red color.
     */
    private int r;
    /**
     * green color.
     */
    private int g;
    /**
     * blue color.
     */
    private int b;

    /**
     * creates plip with energy equal to E.
     */
    public Clorus(double e) {
        super("clorus");
        r = 0;
        g = 0;
        b = 0;
        energy = e;
    }

    /**
     * creates a plip with energy equal to 1.
     */
    public Clorus() {
        this(1);
    }

    /**
     * Should return a color with red = 99, blue = 76, and green that varies
     * linearly based on the energy of the Plip. If the plip has zero energy,
     * it should have a green value of 63. If it has max energy, it should
     * have a green value of 255. The green value should vary with energy
     * linearly in between these two extremes. It's not absolutely vital
     * that you get this exactly correct.
     */
    public Color color() {
        r = 34;
        b = 231;
        g = 0; //g是int格式，energy是double
        return color(r, g, b);
    }

    /**
     * Do nothing with C, Plips are pacifists.
     */
    public void attack(Creature c) {
        // do nothing.
    }

    /**
     * Plips should lose 0.15 units of energy when moving. If you want to
     * to avoid the magic number warning, you'll need to make a
     * private static final variable. This is not required for this lab.
     */
    //protected double energy; energy变量
    public void move() {
        energy-=0.03;
    }


    /**
     * Plips gain 0.2 energy when staying due to photosynthesis.
     */
    public void stay() {
        energy-=0.01;
    }

    /**
     * Plips and their offspring each get 50% of the energy, with none
     * lost to the process. Now that's efficiency! Returns a baby
     * Plip.
     */
    public Clorus replicate() {
        energy=energy*0.5;
        double babyEnergy=energy; //创建新的energy baby分到了0.5的能量
        return new Clorus(babyEnergy);
    }

    /**
     * Plips take exactly the following actions based on NEIGHBORS:
     * 1. If no empty adjacent spaces, STAY.
     * 2. Otherwise, if energy >= 1, REPLICATE towards an empty direction
     * chosen at random.
     * 3. Otherwise, if any Cloruses, MOVE with 50% probability,
     * towards an empty direction chosen at random.
     * 4. Otherwise, if nothing else, STAY
     * <p>
     * Returns an object of type Action. See Action.java for the
     * scoop on how Actions work. See SampleCreature.chooseAction()
     * for an example to follow.
     */

    //都可以参见sampleCreature
    public Action chooseAction(Map<Direction, Occupant> neighbors) {

        Deque<Direction> emptyNeighbors = new ArrayDeque<>();
        Deque<Direction> plipNeighbors = new ArrayDeque<>();


        // (Google: Enhanced for-loop over keys of NEIGHBORS?)
        // for () {...}
        for(Direction d: neighbors.keySet()){
            if(neighbors.get(d).name().equals("empty")){
                emptyNeighbors.addLast(d);
            }else if(neighbors.get(d).name().equals("plip")){
                plipNeighbors.addLast(d);
            }
        }


        /*
        If there are no empty squares, the Clorus will STAY (even if there are Plips nearby they could attack since plip squares do not count as empty squares).
        Otherwise, if any Plips are seen, the Clorus will ATTACK one of them randomly.
        Otherwise, if the Clorus has energy greater than or equal to one, it will REPLICATE to a random empty square.
        Otherwise, the Clorus will MOVE to a random empty square.
         */
        if (emptyNeighbors.size()==0){
            return new Action(Action.ActionType.STAY);
        } else if(plipNeighbors.size()>0){//Rule,HINT: randomEntry(emptyNeighbors)
            return new Action(Action.ActionType.ATTACK,randomEntry(emptyNeighbors));
        }else if(energy>= 1){
            return new Action(Action.ActionType.REPLICATE,randomEntry(emptyNeighbors));
        }else{
            return new Action(Action.ActionType.MOVE,randomEntry(emptyNeighbors));
        }

    }



}
