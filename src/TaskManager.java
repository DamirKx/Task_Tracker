import java.util.List;

public interface TaskManager {

    public void createTask(Task task);
    public void createEpic(Epic epic);
    public void createSubtask(SubTask subTask);

    public void deleteTask(int id);
    public void deleteEpic(int id);
    public void deleteSubtask(int id);

    public void updateTask(int id, Task task);
    public void updateEpic(int id, Epic epic);
    public void updateSubtask(int id, SubTask subTask);

    public void clearTasks();
    public void clearEpics();
    public void clearSubtask(int epicId);
    public void clearAll();

    public Task getTaskById(int id);
    public Epic getEpicById(int id);
    public SubTask getSubtaskById(int id);

    public List<Task> getAllTasks();
    public List<Epic> getAllEpics();
    public List<SubTask> getAllSubtask();

}
