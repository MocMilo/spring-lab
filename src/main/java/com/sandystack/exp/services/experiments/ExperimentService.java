package com.sandystack.exp.services.experiments;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class ExperimentService {


    /**
     * Experiment shows how memory is allocated in heap in subsequent spaces
     * -> Eden, S0, S1, oldGen
     * and how Garbage Collection is executed.
     * How it works:
     * Array has 10 mln. elements. In random array cell index the heavy object is allocated.
     * When this object is allocated once again in the same array cell,
     * old reference is garbage collected.
     */
    public void memoryAllocationAndGC() throws Exception {

        int arraySize = 10000000;
        GCMe[] gcmes = new GCMe[arraySize];
        int count = 0;
        Random rnd = new Random();
        while (true) {
            gcmes[rnd.nextInt(arraySize)] = new GCMe();    // new object in random location
            if (count % 10000000 == 0) {
                System.out.print(".");
            }
            count++;
            //Thread.sleep(1);
        }
    }

    /**
     * When we allocate this class we have large memory allocation
     */
    class GCMe {
        long a;
        long aa;
        long aaa;
        long aaaa;
        long aaaaa;
        long aaaaaa;
        long aaaaaaa;
        long aaaaaaaa;
        long aaaaaaaaa;
        long aaaaaaaaaa;
        long aaaaaaaaaaa;
        long aaaaaaaaaaaa;
        long aaaaaaaaaaaaa;
        long aaaaaaaaaaaaaa;
        long aaaaaaaaaaaaaaa;
        long aaaaaaaaaaaaaaaa;
        long aaaaaaaaaaaaaaaaa;
        long aaaaaaaaaaaaaaaaaa;
        long aaaaaaaaaaaaaaaaaaa;
        long aaaaaaaaaaaaaaaaaaaa;
        long aaaaaaaaaaaaaaaaaaaaa;
        long aaaaaaaaaaaaaaaaaaaaaa;
        long aaaaaaaaaaaaaaaaaaaaaaa;
        long aaaaaaaaaaaaaaaaaaaaaaaa;
        long aaaaaaaaaaaaaaaaaaaaaaaaa;
        long aaaaaaaaaaaaaaaaaaaaaaaaaa;
        long aaaaaaaaaaaaaaaaaaaaaaaaaaa;
        long aaaaaaaaaaaaaaaaaaaaaaaaaaaa;
    }
}
