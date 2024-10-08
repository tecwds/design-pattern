package composite;

import java.util.*;

abstract class MenuComponent {
    String des = "";

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public MenuComponent(String s) {
        this.des = s;
    }

    public abstract void print();

    public void addComponent(MenuComponent c) {
        throw new UnsupportedOperationException("不能添加子节点");
    }
}


//请完成MenuItem 类和CompositeMenu 类提交
class MenuItem extends MenuComponent {

    private ArrayList<MenuComponent> subMenu = new ArrayList<MenuComponent>();

    public MenuItem(String s) {
        super(s);
    }

    @Override
    public void print() {
        System.out.println(this.des);
        for (MenuComponent c : subMenu) {
            c.print();
        }
    }

    @Override
    public void addComponent(MenuComponent c) {
        subMenu.add(c);
    }
}

class CompositeMenu extends MenuComponent {

    private ArrayList<MenuComponent> subMenu = new ArrayList<MenuComponent>();

    public CompositeMenu(String s) {
        super(s);
    }

    @Override
    public void print() {
        System.out.println(this.des);
        for (MenuComponent c : subMenu) {
            c.print();
        }
    }

    @Override
    public void addComponent(MenuComponent c) {
        subMenu.add(c);
    }
}

public class News {

    public static void main(String[] args) {

        MenuComponent news = new CompositeMenu("news");
        CompositeMenu chinaNews = new CompositeMenu("chinaNews");
        MenuComponent jpnNews = new MenuItem("jpnNews");
        MenuComponent gzNews = new MenuItem("GuiZhouNews");
        MenuComponent gdNews = new MenuItem("GuangDongNews");

        chinaNews.addComponent(gzNews);
        chinaNews.addComponent(gdNews);

        news.addComponent(chinaNews);
        news.addComponent(jpnNews);
        news.print();


    }

}


