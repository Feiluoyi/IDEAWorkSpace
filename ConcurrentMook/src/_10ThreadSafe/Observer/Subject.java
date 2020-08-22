package _10ThreadSafe.Observer;

public interface Subject {
    public void registobserver(Observer observer);
    public void removeObserver(Observer observer);
    public void notifyObserver();
    public void setMessage(String msg);
}
