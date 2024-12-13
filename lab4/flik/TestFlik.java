package flik;


import org.junit.Test;


import static org.junit.Assert.*;

public class TestFlik {
    @Test
    public void testisSameNumber(){
        // Test within the cached range (-128 to 127)
        for (int i=-128; i <= 127; i++){
            Integer a = i;
            Integer b = i;
            assertTrue("Test didn't pass at " + i,
                    Flik.isSameNumber(a,b));
        }

        // Test beyond the cashed range
        int beyondCachedValue = 128;
        Integer a = beyondCachedValue;
        Integer b = beyondCachedValue;
        // This test will likely fail due to new Integer objects being created
        assertFalse("Expected different objects for value + " + beyondCachedValue,
                Flik.isSameNumber(a,b));


    }
}
