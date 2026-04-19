public class SubTask extends Task{
    private int epicId;

    public SubTask(String name, String description,  Progress progress, int epicId) {
        super(name, description, progress);
        this.epicId = epicId;
    }


    public int getEpicId() {
        return epicId;
    }

    public void setEpicId(int epicId) {
        this.epicId = epicId;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}