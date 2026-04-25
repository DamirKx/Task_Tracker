import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Manager {

    public int id = 0;

    private Map<Integer, Task> tasks= new HashMap<>();
    private Map<Integer, Epic> epics = new HashMap<>();
    private Map<Integer, SubTask> subtasks = new HashMap<>();

    public int getId() {
        return id;
    }

    public void createTask(Task task){
        task.setId(id);
        tasks.put(id, task);
        id++;
    }
    
    public void createEpic(Epic epic){
        epic.setId(id);
        epics.put(id, epic);
        id++;
    }

    public void createSubtask(SubTask subTask){
        if (epics.containsKey(subTask.getEpicId())){
            subTask.setId(id);
            subtasks.put(id, subTask);
            Epic epic = epics.get(subTask.getEpicId());
            epic.addSubtaskId(id);
            updateEpicStatus(subTask.getEpicId());
            id++;
        } else {
            System.out.println("Не найден эпик");
        }
    }

    public void deleteTask(int id){
        if (tasks.containsKey(id)){
            tasks.remove(id);
        } else {
            System.out.println("Введите правильный идентификатор");
        }
    }

    public void deleteEpic(int id){
        if (epics.containsKey(id)) {
            epics.remove(id);
        } else {
            System.out.println("Такого эпика нет");
        }
    }

    public void deleteSubtask(int id){
        if (subtasks.containsKey(id)){
            SubTask subTask = subtasks.get(id);
            Epic epic = epics.get(subTask.getEpicId());
            epic.removeSubtaskId(id);
            subtasks.remove(id);
            updateEpicStatus(subTask.getEpicId());
        } else {
            System.out.println("Такой подзадачи нет");
        }
    }

    public void updateTask(int id, Task task){
        if (tasks.containsKey(id)){
            tasks.put(id, task);
        } else {
            System.out.println("Введите правильный идентификатор");
        }
    }

    public void updateEpic(int id, Epic epic){
        if (epics.containsKey(id)) {
            epics.put(id, epic);
        } else {
            System.out.println("Такого эпика нет");
        }
    }

    public void updateSubtask(int id, SubTask subTask){
        if (subtasks.containsKey(id)){
            SubTask oldSubtask = subtasks.get(id);

            if (oldSubtask != subTask){
                Epic epic = epics.get(oldSubtask.getEpicId());
                epic.removeSubtaskId(id);
                Epic newEpic = epics.get(subTask.getEpicId());
                newEpic.addSubtaskId(subTask.getId());
            }
            subtasks.put(id, subTask);
            updateEpicStatus(subTask.getEpicId());
        }
    }

    public void clearTasks(){
        tasks.clear();
        System.out.println("Все задачи удалены!");
    }

    public void clearEpic(){
        epics.clear();
        System.out.println("Все эпики удалены!");

    }

    public void clearAll(){
        tasks.clear();
        epics.clear();
        subtasks.clear();
    }

    public Task getTaskById(int id){
        return tasks.get(id);

    }

    public Epic getEpicById(int id){
        return epics.get(id);
    }

    public SubTask getSubtaskById(int id){
        return subtasks.get(id);
    }

    public void updateEpicStatus(int epicId){
        boolean allDone = true;

        List<Integer> subtaskIds= epics.get(epicId).getSubtaskIds();

        for (int i = 0; i < subtaskIds.size(); i++){
            SubTask subTask = subtasks.get(subtaskIds.get(i));
            if (!subTask.getProgress().equals(Progress.DONE)){
                allDone = false;
            }
        }

        if (subtaskIds.isEmpty()){
            epics.get(epicId).setProgress(Progress.NEW);
        } else if (allDone) {
            epics.get(epicId).setProgress(Progress.DONE);
        } else {
            epics.get(epicId).setProgress(Progress.IN_PROGRESS);
        }
    }

}