package state;

abstract class State {
    protected TissueMachine tissueMachine;

    //投币
    public void insertQuarter() {
    }

    //退币
    public void ejectQuarter() {
    }

    //按下“出纸巾”按钮
    public void turnCrank() {
    }

    //出纸巾
    public void dispense() {
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


    //投币
    public void insertQuarter() {
        this.state.insertQuarter();
    }

    //退币
    public void ejectQuarter() {
        this.state.ejectQuarter();
    }

    //按下“出纸巾”按钮
    public void turnCrank() {
        this.state.turnCrank();
    }

    //出纸巾
    public void dispense() {
        this.state.dispense();
    }

}

class SoldState extends State {

    public SoldState(TissueMachine tissueMachine) {
        super.tissueMachine = tissueMachine;
    }

    @Override
    public void dispense() {
        if (tissueMachine.getCount() == 0) {
            tissueMachine.setState(tissueMachine.getSoldOutState());
            return;
        }
        tissueMachine.setState(tissueMachine.getNoQuarterState());
    }

    @Override
    public void printState() {
        System.out.println("SoldState");
    }
}

class SoldOutState extends State {
    public SoldOutState(TissueMachine tissueMachine) {
        super.tissueMachine = tissueMachine;
    }

    @Override
    public void printState() {
        System.out.println("SoldOutState");
    }
}

class NoQuarterState extends State {

    public NoQuarterState(TissueMachine tissueMachine) {
        super.tissueMachine = tissueMachine;
    }

    @Override
    public void insertQuarter() {
        // 投币，切换状态
        super.tissueMachine.setState(tissueMachine.getHasQuarterState());
    }

    @Override
    public void printState() {
        System.out.println("NoQuarterState");
    }
}

class HasQuarterState extends State {

    public HasQuarterState(TissueMachine tissueMachine) {
        super.tissueMachine = tissueMachine;
    }

    @Override
    public void insertQuarter() {
        super.insertQuarter();
    }

    @Override
    public void ejectQuarter() {
        // 退币，切换称 No 币状态
        tissueMachine.setState(tissueMachine.getNoQuarterState());
    }

    @Override
    public void turnCrank() {
        // 取纸巾
        tissueMachine.setState(tissueMachine.getSoldState());
    }

    @Override
    public void dispense() {
        super.dispense();
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
