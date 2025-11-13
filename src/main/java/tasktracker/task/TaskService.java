package tasktracker.task;

import java.util.Scanner;

public class TaskService {


    private TaskOperation task;



    public String getOperation(TaskOperation task){
        return task.typeOperation();
    }

    public void setOperation (TaskOperation task) {
        this.task = task;
    }


    public void loadMenu(){
        Scanner ns = new Scanner(System.in);
        System.out.println("1.ADD TASK\n"+"2.REMOVE TASK\n");
        int value = ns.nextInt();
    }


    public static void main (String [] args){
        TaskService s = new TaskService();
        s.loadMenu();
    }


}
