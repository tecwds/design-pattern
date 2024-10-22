package state;

interface TurnstileState {
    void coin();

    void pass();
}

class LockedTurnstileState implements TurnstileState {

    Turnstile turnstile;

    public LockedTurnstileState(Turnstile turnstile) {
        this.turnstile = turnstile;
    }

    @Override
    public void coin() {
        turnstile.unLock();
        turnstile.setState(turnstile.getUnlockedState());
    }

    @Override
    public void pass() {
        turnstile.alarm();
//        turnstile.setState(turnstile.getLockedState());
    }
}


class UnLockedTurnstileState implements TurnstileState {

    Turnstile turnstile;

    public UnLockedTurnstileState(Turnstile turnstile) {
        this.turnstile = turnstile;
    }

    @Override
    public void coin() {
        turnstile.thankYou();
//        turnstile.setState(turnstile.getUnlockedState());
    }

    @Override
    public void pass() {
        turnstile.lock();
        turnstile.setState(turnstile.getLockedState());
    }
}

class Turnstile {
    private TurnstileState lockedState;
    private final TurnstileState unlockedState;
    private TurnstileState state = lockedState;

    public Turnstile() {
        lockedState = new LockedTurnstileState(this);
        unlockedState = new UnLockedTurnstileState(this);
    }

    public TurnstileState getLockedState() {
        return lockedState;
    }

    public TurnstileState getUnlockedState() {
        return unlockedState;
    }

    public void setState(TurnstileState state) {
        this.state = state;
    }

    public void coin() {
        state.coin();
    }

    public void pass() {
        state.pass();
    }

    public void unLock() {
        System.out.println("turnstile unlock");
    }

    public void lock() {
        System.out.println("turnstile lock");
    }

    public void alarm() {
        System.out.println("alarm");
    }

    public void thankYou() {
        System.out.println("think you");
    }
}


public class LockState {
    public static void main(String[] args) {

        Turnstile turnstile = new Turnstile();

        turnstile.setState(turnstile.getLockedState());
        turnstile.coin();
        turnstile.coin();
        turnstile.pass();
        turnstile.pass();

    }
}
