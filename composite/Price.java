package composite;

import java.util.*;


abstract class Component {

    String des = "";

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public Component(String s) {
        this.des = s;
    }

    public abstract double computerPrice();

    public void addComponent(Component c){
        throw new UnsupportedOperationException("不能添加子节点");
    }
}

//请完成Composite类和Part类提交
class Composite extends Component {

    private ArrayList<Component> subComponent = new ArrayList<Component>();

    public Composite(String s) {
        super(s);
    }

    @Override
    public void addComponent(Component c) {
        subComponent.add(c);
    }

    @Override
    public double computerPrice() {
        double res = 0;
        for (Component c : subComponent) {
            res += c.computerPrice();
        }
        return res;
    }
}

class Part extends Component {

    private ArrayList<Component> subComponent = new ArrayList<Component>();
    private double price;

    public Part(double price, String s) {
        super(s);
        this.price = price;
    }

    @Override
    public void addComponent(Component c) {
        subComponent.add(c);
    }

    @Override
    public double computerPrice() {
        double res = 0;
        for (Component c : subComponent) {
            res += c.computerPrice();
        }
        return price + res;
    }
}

public class Price {
    public static void main(String[] args){
        Composite computer=new Composite("计算机");

        Part mouse=new Part(30,"鼠标");
        Part keyBoard=new Part(40,"键盘");
        Part screen=new Part(800,"显示器");

        computer.addComponent(mouse);
        computer.addComponent(keyBoard);
        computer.addComponent(screen);

        Composite box=new Composite("机箱");

        Part mainBoard=new Part(800,"主板");
        Part displayCard=new Part(500,"显卡");
        Part disk=new Part(400,"硬盘");

        box.addComponent(mainBoard);
        box.addComponent(displayCard);
        box.addComponent(disk);

        computer.addComponent(box);

        System.out.println(box.computerPrice());
        System.out.println(computer.computerPrice());
    }
}
