package _10ThreadSafe.Observer;

public class ObserverPerson implements Observer{
    private String name;

    public ObserverPerson(String name,Subject subject) {
        this.name = name;
        subject.registobserver(this);
    }

    @Override
    public void update(String msg) {
        System.out.println(this.name+" "+msg);
    }
}
