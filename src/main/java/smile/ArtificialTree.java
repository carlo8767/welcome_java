package smile;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ArtificialTree {



    public List<String> rootNode;
    public LinkedList<Map<String, Integer>> nodeLeft;
    public LinkedList<Map<String, Integer>> nodeRight;

    public  ArtificialTree(){
        this.rootNode = new ArrayList<>();
    }


    public LinkedList<Map<String, Integer>> getNodeRight() {
        return nodeRight;
    }

    public void setNodeRight(LinkedList<Map<String, Integer>> nodeRight) {
        this.nodeRight = nodeRight;
    }

    public List<String> getRootNode() {
        return rootNode;
    }

    public void setRootNode(List<String> rootNode) {
        this.rootNode = rootNode;
    }


}
