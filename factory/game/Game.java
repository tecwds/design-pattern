package factory.game;

abstract class Tree{};

abstract class Floor{};

class WinterTree extends  Tree{

    public WinterTree(){

        System.out.println("createWinterTree");

    }

}

class SummerTree extends  Tree{

    public SummerTree(){

        System.out.println("createSummerTree");

    }

}

class WinterFloor extends  Floor{

    public WinterFloor(){

        System.out.println("createWinterFloor");

    }

}

class SummerFloor extends  Floor{

    public SummerFloor(){

        System.out.println("createSummerFloor");

    }

}

interface AbstractFactory {

    Tree createTree();

    Floor createFloor();

}



//请提交WinterFactory 及 SummerFactory 类
class WinterFactory implements AbstractFactory {
    @Override
    public Tree createTree() {
        return new WinterTree();
    }

    @Override
    public Floor createFloor() {
        return new WinterFloor();
    }
}

class SummerFactory implements AbstractFactory {
    @Override
    public Tree createTree() {
        return new SummerTree();
    }

    @Override
    public Floor createFloor() {
        return new SummerFloor();
    }
}


class GameDialog{

    AbstractFactory factory;

    Tree tree;

    Floor floor;

    public void setFactory(AbstractFactory factory) {

        this.factory = factory;

    }

    public void init(){

        tree=factory.createTree();

        floor=factory.createFloor();

    }

}



public class Game{

    public static void main(String[] args){

        GameDialog gameDialog=new GameDialog();

        gameDialog.setFactory(new WinterFactory());

        gameDialog.init();

        gameDialog.setFactory(new SummerFactory());

        gameDialog.init();

    }
}
