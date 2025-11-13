package tasktracker.task;

public class TaskAdd implements TaskOperation {

    private TaskModel taskModel;


    @Override
    public String typeOperation() {
        return TaskOperation.ADD;
    }
}
