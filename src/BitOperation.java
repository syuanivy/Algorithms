import java.util.LinkedList;
import java.util.List;

/**
 * Created by ivy on 2/8/15.
 */
public class BitOperation {
    //single number
    public int singleNumber(int[] A) {
        int single =0;
        for(int i:A){
            single ^=i;
        }
        return single;
    }

    //Gray Code
    public List<Integer> grayCode(int n) {
        List<Integer> result = new LinkedList<>();
        for(int i=0;i<1<<n;i++)
            result.add(i^i>>1);
        return result;
    }
}
