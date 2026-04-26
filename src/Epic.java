
import java.util.ArrayList;
import java.util.List;

public class Epic extends Task{

    private List<Integer> subTasksId;

    public Epic(String name, String description, Progress progress) {

        super(name, description, progress);
        subTasksId = new ArrayList<>();
    }

    public void addSubtaskId(int id){
        subTasksId.add(id);
    }

    public void removeSubtaskId(int id){
        subTasksId.remove(id);
    }


    public List<Integer> getSubtaskIds(){
        return subTasksId;
    }

}