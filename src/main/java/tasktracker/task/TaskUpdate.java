package tasktracker.task;

public class TaskUpdate implements TaskOperation {

    private TaskModel taskModel;

    @Override
    public String typeOperation() {
        return TaskOperation.UPDATE;
    }
}
