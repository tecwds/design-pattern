package state;

abstract class State {
    protected TissueMachine tissueMachine;

    public void insertQuarter() {
        //投币
    }

    public void ejectQuarter() {
        //退币
    }

    public void turnCrank() {
        //按下“出纸巾”按钮

    }

    public void dispense() {
        //出纸巾
    }

    public abstract void printState();
}

class TissueMachine {

    private final State soldOutState;
    private final State noQuarterState;
    private final State hasQuarterState;
    private final State soldState;
    private State state;

    private final int count; //纸巾数

    public TissueMachine(int numbers) {
        count = numbers;
        soldOutState = new SoldOutState(this);
        noQuarterState = new NoQuarterState(this);
        hasQuarterState = new HasQuarterState(this);
        soldState = new SoldState(this);
        state = noQuarterState;
    }


    public State getHasQuarterState() {
        return hasQuarterState;
    }

    public State getNoQuarterState() {
        return noQuarterState;
    }

    public State getSoldState() {
        return soldState;
    }

    public State getSoldOutState() {
        return soldOutState;
    }

    public int getCount() {
        return count;
    }

    public void setState(State state) {
        this.state = state;
    }

    public void printState() {
        this.state.printState();
    }


    public void insertQuarter() {
        //投币
        this.state.insertQuarter();
    }

    public void ejectQuarter() {
        //退币
        this.state.ejectQuarter();
    }


    public void turnCrank() {
        //按下“出纸巾”按钮
        this.state.turnCrank();

    }


    public void dispense() {
        //出纸巾
        this.state.dispense();
    }

}

class SoldState extends State {

    SoldState(TissueMachine tissueMachine) {
        super.tissueMachine = tissueMachine;
    }

    @Override
    public void printState() {
        System.out.println("SoldState");
    }
}

class SoldOutState extends State {
    SoldOutState(TissueMachine tissueMachine) {
        super.tissueMachine = tissueMachine;
    }

    @Override
    public void printState() {
        System.out.println("SoldOutState");
    }
}

class NoQuarterState extends State {

    NoQuarterState(TissueMachine tissueMachine) {
        super.tissueMachine = tissueMachine;
    }

    @Override
    public void printState() {
        System.out.println("NoQuarterState");
    }
}

class HasQuarterState extends State {

    HasQuarterState(TissueMachine tissueMachine) {
        super.tissueMachine = tissueMachine;
    }

    @Override
    public void printState() {
        System.out.println("HasQuarterState");
    }
}


public class SaleState {
    public static void main(String[] args) {
        TissueMachine tissueMachine = new TissueMachine(10);
        tissueMachine.printState();
        tissueMachine.insertQuarter();
        tissueMachine.printState();
        tissueMachine.ejectQuarter();
        tissueMachine.printState();
        tissueMachine.insertQuarter();
        tissueMachine.printState();
        tissueMachine.turnCrank();
        tissueMachine.printState();
        tissueMachine.dispense();
        tissueMachine.printState();
    }
}
