package _10ThreadSafe.Observer;

public class Test {
    public static void main(String[] args) {
        Sport3DSubject sportsubject = new Sport3DSubject();
        ObserverPerson p1 = new ObserverPerson("11", sportsubject);
        ObserverPerson p2 = new ObserverPerson("22", sportsubject);
        ObserverPerson p3 = new ObserverPerson("33", sportsubject);
        //sportsubject.removeObserver(p2);
        sportsubject.setMessage("中奖信息是12 12 12");
        sportsubject.notifyObserver();
    }
}
