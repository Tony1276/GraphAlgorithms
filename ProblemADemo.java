package assg2p1;

import java.io.IOException;

public class ProblemADemo {
    public static void main(String[] args) throws IOException {
        String datain = "src/data/a2p1_problemA_input_02.csv";
        ProblemA test = new ProblemA(datain);
        test.findNumberOfRoutes();
        
    }
}