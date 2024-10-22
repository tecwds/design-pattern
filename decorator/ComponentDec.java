package decorator;

abstract class Component {
    abstract void draw();
}

class TextBox extends Component{
    @Override
    void draw() {
        System.out.print("drawTextBox");
    }
}

class Table extends Component{
    @Override
    void draw() {
        System.out.print("drawTable");
    }
}

abstract  class Decorator extends Component{}

/**
 完成ScrollDecorator  和 BorderDecorator 类并提交
 **/

class ScrollDecorator extends Decorator {
    Component component;

    public ScrollDecorator(Component component) {
        this.component=component;
    }

    @Override
    void draw() {
        component.draw();
        System.out.print(" drawScroll");
    }
}

class BorderDecorator extends Decorator {
    Component component;

    public BorderDecorator(Component component) {
        this.component=component;
    }

    @Override
    void draw() {
        component.draw();
        System.out.print(" drawBorder");
    }
}



public class ComponentDec {
    public static void main(String[] args){

        Component table = new ScrollDecorator(new BorderDecorator(new Table()));

        Component text=new BorderDecorator(new TextBox());

        table.draw();

        System.out.println();

        text.draw();

    }
}
