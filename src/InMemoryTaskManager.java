import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryTaskManager implements TaskManager{


    public int id = 0;

    private Map<Integer, Task> tasks= new HashMap<>();
    private Map<Integer, Epic> epics = new HashMap<>();
    private Map<Integer, SubTask> subtasks = new HashMap<>();

    public int getId() {
        return id;
    }
    @Override
    public void createTask(Task task){
        task.setId(id);
        tasks.put(id, task);
        id++;
    }

    @Override
    public void createEpic(Epic epic){
        epic.setId(id);
        epics.put(id, epic);
        id++;
    }

    @Override
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

    @Override
    public void deleteTask(int id){
        if (tasks.containsKey(id)){
            tasks.remove(id);
        } else {
            System.out.println("Введите правильный идентификатор");
        }
    }

    @Override
    public void deleteEpic(int id){
        if (epics.containsKey(id)) {
            Epic epic = epics.get(id);
            for (Integer subtaskId : epic.getSubtaskIds()){
                subtasks.remove(subtaskId);
            }
            epics.remove(id);
        } else {
            System.out.println("Такого эпика нет");
        }
    }

    @Override
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

    @Override
    public void updateTask(int id, Task task){
        if (tasks.containsKey(id)){
            tasks.put(id, task);
        } else {
            System.out.println("Введите правильный идентификатор");
        }
    }

    @Override
    public void updateEpic(int id, Epic epic){
        if (epics.containsKey(id)) {
            epics.put(id, epic);
        } else {
            System.out.println("Такого эпика нет");
        }
    }

    @Override
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

    @Override
    public void clearTasks(){
        tasks.clear();
        System.out.println("Все задачи удалены!");
    }

    @Override
    public void clearEpics(){
        epics.clear();
        System.out.println("Все эпики удалены!");

    }

    @Override
    public void clearSubtask(int epicId) {
        Epic epic = epics.get(epicId);
        for(Integer subtaskId : epic.getSubtaskIds()){
            subtasks.remove(subtaskId);
        }
        System.out.println("Все подзадачи эпика удалены");
    }

    @Override
    public void clearAll(){
        tasks.clear();
        epics.clear();
        subtasks.clear();
    }

    @Override
    public Task getTaskById(int id){
        return tasks.get(id);

    }

    @Override
    public Epic getEpicById(int id){
        return epics.get(id);
    }

    @Override
    public SubTask getSubtaskById(int id){
        return subtasks.get(id);
    }

    @Override
    public List<Task> getAllTasks() {
        return new ArrayList<>(tasks.values());
    }

    @Override
    public List<Epic> getAllEpics(){
        return new ArrayList<>(epics.values());
    }

    @Override
    public List<SubTask> getAllSubtask(){
        return new ArrayList<>(subtasks.values());
    }

    public void updateEpicStatus(int epicId){
        boolean allDone = true;
        boolean allNew = true;

        List<Integer> subtaskIds= epics.get(epicId).getSubtaskIds();

        for (int i = 0; i < subtaskIds.size(); i++){
            SubTask subTask = subtasks.get(subtaskIds.get(i));
            if (!subTask.getProgress().equals(Progress.DONE)){
                allDone = false;
            }
            if (!subTask.getProgress().equals(Progress.NEW)) {
                allNew = false;
            }
        }

        if (subtaskIds.isEmpty() || allNew){
            epics.get(epicId).setProgress(Progress.NEW);
        } else if (allDone) {
            epics.get(epicId).setProgress(Progress.DONE);
        } else {
            epics.get(epicId).setProgress(Progress.IN_PROGRESS);
        }
    }

}