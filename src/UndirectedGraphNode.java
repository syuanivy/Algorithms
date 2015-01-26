import java.util.*;

/**
 * Created by ivy on 1/11/15.
 */
public class UndirectedGraphNode {
    int label;
    List<UndirectedGraphNode> neighbors;
    UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>();}

    public static UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if(node == null) return null;
        HashMap<Integer,UndirectedGraphNode> copied = new HashMap<Integer,UndirectedGraphNode>();
        UndirectedGraphNode clone = new UndirectedGraphNode(node.label);
        cloneDFS(node, clone, copied);
        return clone;
    }

    public static void cloneDFS(UndirectedGraphNode node, UndirectedGraphNode clone, HashMap<Integer,UndirectedGraphNode> copied){
        copied.put(node.label, clone);
        for (UndirectedGraphNode x: node.neighbors){
            if(!copied.containsKey(x.label)){
                UndirectedGraphNode neighborclone = new UndirectedGraphNode(x.label);
                clone.neighbors.add(neighborclone);
                cloneDFS(x, neighborclone, copied);
            }else{
                clone.neighbors.add(copied.get(x.label));
            }
        }
        return;
    }


    public boolean isConnected(UndirectedGraphNode u, UndirectedGraphNode v){
        HashSet<UndirectedGraphNode> visited = new HashSet<UndirectedGraphNode>();
        return connectedDFS(u, v, visited);
    }

    public boolean connectedDFS(UndirectedGraphNode u, UndirectedGraphNode v,HashSet<UndirectedGraphNode> visited){
        visited.add(u);
        for (UndirectedGraphNode x:u.neighbors){
            if(x == v) return true;
            else if(!visited.contains(x)) connectedDFS(x, v, visited);
        }
        return false;
    }

    //BFS, shortest path from u to v
    public boolean connectedBFS(UndirectedGraphNode u, UndirectedGraphNode v, HashSet<UndirectedGraphNode> visited){
        Queue<UndirectedGraphNode> q = new LinkedList<UndirectedGraphNode>();
        visited.add(u);
        q.add(u);

        UndirectedGraphNode current;
        while(!q.isEmpty()){
            current =  q.remove();
            for (UndirectedGraphNode x : current.neighbors){
                if(x == v) return true;
                else if (!visited.contains(x)) {
                    visited.add(x);
                    q.add(x);
                }
            }
        }

        return false;
    }
}
