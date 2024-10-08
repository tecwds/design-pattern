package cor;

abstract class Manager {
    protected Manager superior;

    public abstract boolean handle(int fee);

    public void setSuccessor(Manager superior) {
        this.superior = superior;
    }

}

// 部门经理
class ProjectManager extends Manager {

    @Override
    public boolean handle(int fee) {
        if (fee > 1000) {
            return superior.handle(fee);
        }
        System.out.println("ProjectManager pass");
        return true;
    }
}

class DepartmentManager extends Manager {

    @Override
    public boolean handle(int fee) {
        if (fee > 2000) {
            return superior.handle(fee);
        }
        System.out.println("DepartmentManager pass");
        return true;
    }
}

class TopManager extends Manager {

    @Override
    public boolean handle(int fee) {
        if (fee < 10000) {
            System.out.println("TopManager pass");
            return true;
        }
        System.out.println("TopManager refuse");
        return false;
    }
}

public class RequestApproval {
    public static void main(String[] args) {
        int[] arr = {500, 1500, 2500, 29000};
        Manager pm = new ProjectManager();
        Manager dm = new DepartmentManager();
        Manager topm = new TopManager();
        pm.setSuccessor(dm);
        dm.setSuccessor(topm);

        for (int i = 0; i < arr.length; i++) {
            pm.handle(arr[i]);
        }
    }
}