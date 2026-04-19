
public class Main {
    public static void main(String[] args) {

        Manager manager = new Manager();


        manager.createTask(new Task("учеба", "сделать дз", Progress.NEW));
        manager.createTask(new Task("учеба", "сделать дз", Progress.NEW));
        manager.createTask(new Task("учеба", "сделать дз" , Progress.NEW));



    }

}