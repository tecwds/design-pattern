package strategy;

import java.util.ArrayList;
import java.util.List;

class SalaryManager {
    private final List<Double> workHourList = new ArrayList<Double>();
    private SaryComputerStrategy strage;
    private double hourlyRate;

    public void setHourlyRate(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public void setStrage(SaryComputerStrategy strage) {
        this.strage = strage;
    }

    public void addWorkHour(double h) {
        workHourList.add(h);
    }

    public double computerSalary() {
        return strage.computerSalary(workHourList, hourlyRate);
    }
}

interface SaryComputerStrategy {
    double computerSalary(List<Double> list, double rate);
}


//完成SaryComputerStrategyOne 和 SaryComputerStrategyTwo 类并提交

class SaryComputerStrategyOne implements SaryComputerStrategy {
    @Override
    public double computerSalary(List<Double> list, double rate) {
        double total = 0;
        for (double it : list) {
            total += it;
        }
        return total > 160 ? total * 1.2 * rate : total * rate;
    }
}

class SaryComputerStrategyTwo implements SaryComputerStrategy {
    @Override
    public double computerSalary(List<Double> list, double rate) {
        double total = 0;
        for (double it : list) {
            total += it > 40 ? it * 1.2 : it;
        }
        return total * rate;
    }
}

public class WorkStrategy {
    public static void main(String[] args) {
        SalaryManager salaryManager = new SalaryManager();
        salaryManager.setHourlyRate(50);
        salaryManager.addWorkHour(52);
        salaryManager.addWorkHour(40.5);
        salaryManager.addWorkHour(38.5);
        salaryManager.addWorkHour(60);

        salaryManager.setStrage(new SaryComputerStrategyOne());
        System.out.println(salaryManager.computerSalary());

        salaryManager.setStrage(new SaryComputerStrategyTwo());
        System.out.println(salaryManager.computerSalary());
    }
}
