package proxy;

import java.util.HashMap;

class Student {

    private String sno;
    private final String sname;

    public Student(String sno, String sname) {
        this.sno = sno;
        this.sname = sname;
    }

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }
}

interface VoteManagerInterface {
    void addStudent(Student s);
    void vote(Student voter, Student select);
    int getVoteNum(Student s);
}

class VoteManager implements VoteManagerInterface {

    private final HashMap<String, Integer> map = new HashMap<String, Integer>();

    @Override
    public void addStudent(Student s) {
        map.put(s.getSno(), 0);
    }

    @Override
    public void vote(Student voter, Student select) {
        if (map.containsKey(select.getSno())) {
            Integer v = map.get(select.getSno());
            map.put(select.getSno(), v + 1);
        } else {
            map.put(select.getSno(), 0);
        }
    }

    @Override
    public int getVoteNum(Student s) {
        if (map.containsKey(s.getSno())) {
            return map.get(s.getSno());
        }
        return 0;
    }
}

class VoteManagerProxy implements VoteManagerInterface {

    VoteManagerInterface voteManager;
    HashMap<String, String> map = new HashMap<String, String>();

    public VoteManagerProxy(VoteManagerInterface voteManager) {
        this.voteManager = voteManager;
    }

    @Override
    public void addStudent(Student s) {
        voteManager.addStudent(s);
    }

    @Override
    public void vote(Student voter, Student select) {
        // feature：学生不能对自己投票
        if (voter.getSno().equals(select.getSno())) {
            // 不能投票
            return;
        }

        // feature：学生不能对某个人投多次票，但是有多个票权
        if (map.containsKey(voter.getSno()) && map.get(voter.getSno()).equals(select.getSno())) {
            // no!
            return;
        }
        voteManager.vote(voter, select);
        map.put(voter.getSno(), select.getSno());
    }

    @Override
    public int getVoteNum(Student s) {
        return voteManager.getVoteNum(s);
    }
}

public class Vote {

    public static void main(String[] args) {
        Student s1 = new Student("001", "张三");
        Student s2 = new Student("002", "李四");
        Student s3 = new Student("003", "王五");

        VoteManagerInterface v = new VoteManagerProxy(new VoteManager());

        v.addStudent(s1);
        v.addStudent(s2);
        v.addStudent(s3);

        v.vote(s1, s1); // no
        v.vote(s1, s2); // yes s2: 1
        v.vote(s1, s2); // no
        v.vote(s1, s3); // yes s3: 1
        v.vote(s2, s1); // yes s1: 1
        v.vote(s3, s2); // yes s2: 2
        v.vote(s3, s1); // yes s1: 2
        v.vote(s3, s3); // no

        // s1: 2
        // s2: 2
        // s3: 1

        System.out.println(v.getVoteNum(s1));
        System.out.println(v.getVoteNum(s2));
        System.out.println(v.getVoteNum(s3));
    }
}
