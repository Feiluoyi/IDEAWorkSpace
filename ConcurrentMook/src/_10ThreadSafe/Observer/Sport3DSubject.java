package _10ThreadSafe.Observer;

import com.sun.xml.internal.bind.v2.schemagen.XmlSchemaGenerator;

import java.util.LinkedHashSet;

public class Sport3DSubject implements Subject{
    private String msg;
    private LinkedHashSet<Observer> observers=new LinkedHashSet<>();

    public Sport3DSubject() {
        this.observers = observers;
    }

    @Override
    public void registobserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        if(observers.contains(observer))
            observers.remove(observer);
    }

    @Override
    public void notifyObserver() {
        //System.out.println("通知所有订阅者:");
        for (Observer observer : observers) {
            observer.update(msg);
        }
    }

    @Override
    public void setMessage(String msg) {
        this.msg=msg;
        System.out.println("通知所有订阅者:");
        notifyObserver();
        System.out.println("信息通知完成");
    }
}
