package state.work;

class Role {
    // 生命值
    private int vitality;
    private State state;

    private final State normal = new NormalState(this);
    private final State strong = new StrongState(this);
    private final State dead = new DeadState(this);

    public Role() {
        this.vitality = 5;
        this.state = normal;
    }

    // 被攻击
    public void attacked() {
        state.attacked();
    }

    // 捡到加强道具
    public void pickProp() {
        state.pickProp();
    }

    public int setVitality(int vitality) {
        this.vitality = vitality;
        return this.vitality;
    }

    public void printState() {
        System.out.println("生命值为：" + this.vitality);
        System.out.print("状态为： ");
        this.state.printState();
    }

    public int getVitality() {
        return vitality;
    }

    public void setState(State state) {
        this.state = state;
    }

    public State getNormal() {
        return normal;
    }

    public State getStrong() {
        return strong;
    }

    public State getDead() {
        return dead;
    }
}

abstract class State {
    protected Role role;

    public abstract void attacked();

    public abstract void pickProp();

    public abstract void printState();
}

class NormalState extends State {

    public NormalState(Role role) {
        super.role = role;
    }

    @Override
    public void attacked() {
        System.out.println("被攻击了");
        int vitality = role.setVitality(role.getVitality() - 1);

        if (vitality == 0) {
            role.setState(role.getDead());
        }
    }

    @Override
    public void pickProp() {
        System.out.println("捡到强化道具");
        role.setState(role.getStrong());
    }

    @Override
    public void printState() {
        System.out.println("NormalState");
        System.out.println();
    }
}

class StrongState extends State {

    public StrongState(Role role) {
        super.role = role;
    }

    @Override
    public void attacked() {
        System.out.println("被攻击了");

        // 变回普通模式
        role.setState(role.getNormal());
    }

    @Override
    public void pickProp() {
        // doNothing
        System.out.println("捡到强化道具");
    }

    @Override
    public void printState() {
        System.out.println("StrongState");
        System.out.println();
    }
}

class DeadState extends State {

    public DeadState(Role role) {
        super.role = role;
    }

    @Override
    public void attacked() {
        // doNothing
    }

    @Override
    public void pickProp() {
        // doNothing
    }

    @Override
    public void printState() {
        System.out.println("DeadState");
        System.out.println("你死了...");
        System.out.println();
    }
}

public class GameState {
    public static void main(String[] args) {
        Role role = new Role();
        role.attacked();
        role.printState();

        role.attacked();
        role.printState();

        role.attacked();
        role.printState();

        role.pickProp();
        role.printState();

        role.attacked();
        role.printState();

        role.attacked();
        role.printState();

        role.pickProp();
        role.printState();

        role.pickProp();
        role.printState();

        role.attacked();
        role.printState();

        role.attacked();
        role.printState();

        role.printState();
        role.printState();
        role.printState();
        role.printState();
    }
}
