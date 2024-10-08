package factory.ui;

abstract class Button{};

abstract class TextBox{};


class WindowButton extends Button {

    public WindowButton(){

        System.out.println("createWindowsButton");

    }

}



class MotifButton extends Button {

    public MotifButton(){

        System.out.println("createMotifButton");

    }

}



class WindowTextBox extends  TextBox{

    public WindowTextBox(){

        System.out.println("createWindowTextBox");

    }

}



class MotifTextBox extends  TextBox{

    public MotifTextBox(){

        System.out.println("createMotifTextBox");

    }

}



interface AbstractFactory {

    Button createButton();

    TextBox createTextBox();

}



class Dialog{

    AbstractFactory factory;

    Button button;

    TextBox textBox;

    public void setFactory(AbstractFactory factory) {

        this.factory = factory;

    }

    public void init(){

        button=factory.createButton();

        textBox=factory.createTextBox();

    }

}

//请提交WindowFactory  和 Motiffactory
class WindowFactory implements AbstractFactory {
    @Override
    public Button createButton() {
        return new WindowButton();
    }

    @Override
    public TextBox createTextBox() {
        return new WindowTextBox();
    }
}

class Motiffactory implements AbstractFactory {
    @Override
    public Button createButton() {
        return new MotifButton();
    }

    @Override
    public TextBox createTextBox() {
        return new MotifTextBox();
    }
}


public class UI{

    public static void main(String[] args){

        Dialog dialog=new Dialog();

        dialog.setFactory(new WindowFactory());

        dialog.init();

        dialog.setFactory(new Motiffactory());

        dialog.init();

    }



}

