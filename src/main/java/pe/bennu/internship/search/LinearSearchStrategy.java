package pe.bennu.internship.search;

public class LinearSearchStrategy implements SearchStrategy {
    @Override
    public int search(double[] numArray, double numberToSearch) {
        if(numArray == null || numArray.length == 0) return -1;

        int i;
        for(i = 0; i < numArray.length; i++) {
            if(numArray[i] == numberToSearch) return i;
        }

        return -1;
    }
}
