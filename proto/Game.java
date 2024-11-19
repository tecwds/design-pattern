package proto;

abstract class Tree implements Cloneable {
    @Override
    public Tree clone() {
        try {
            return (Tree) super.clone();
        } catch (CloneNotSupportedException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
};

abstract class Floor implements Cloneable {
    @Override
    public Floor clone() {
        try {
            return (Floor) super.clone();
        } catch (CloneNotSupportedException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
};

class WinterTree extends Tree {
    public WinterTree() {
        System.out.println("createWinterTree");
    }
}

class SummerTree extends Tree {
    public SummerTree() {
        System.out.println("createSummerTree");
    }
}

class WinterFloor extends Floor {
    public WinterFloor() {
        System.out.println("createWinterFloor");
    }
}

class SummerFloor extends Floor {
    public SummerFloor() {
        System.out.println("createSummerFloor");
    }

}

interface AbstractFactory {
    Tree createTree();
    Floor createFloor();
}

class PrototypeFactory implements AbstractFactory {
    private final Tree tree;
    private final Floor floor;

    public PrototypeFactory(Tree tree, Floor floor) {
        this.tree = tree;
        this.floor = floor;
    }

    @Override
    public Tree createTree() {
        return tree.clone();
    }

    @Override
    public Floor createFloor() {
        return floor.clone();
    }
}


class GameDialog {

    AbstractFactory factory;
    Tree tree;
    Floor floor;

    public void setFactory(AbstractFactory factory) {
        this.factory = factory;
    }

    public void init() {
        tree = factory.createTree();
        floor = factory.createFloor();
    }
}


public class Game {

    public static void main(String[] args) {
        GameDialog gameDialog = new GameDialog();

        PrototypeFactory prototypeFactory = new PrototypeFactory(new WinterTree(), new WinterFloor());
        gameDialog.setFactory(prototypeFactory);
        gameDialog.init();

        prototypeFactory = new PrototypeFactory(new SummerTree(), new SummerFloor());
        gameDialog.setFactory(prototypeFactory);
        gameDialog.init();

    }
}