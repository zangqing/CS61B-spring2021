package flik;

/** An Integer tester created by Flik Enterprises.
 * @author Josh Hug
 * */
public class Flik {
    /** @param a Value 1
     *  @param b Value 2
     *  @return Whether a and b are the same */
    public static boolean isSameNumber(Integer a, Integer b) {
//        return a == b;
        return a.equals(b); //Fixing
    }
}

//Issue explanation
//
//Break down the Integer caching mechanism:
//For values between -128 and 127, Java pre-creates and caches Integer objects.
//When you do Integer a = 127;, it returns a reference to a pre-existing object.
//Integer b = 127; returns the SAME cached object.
//For values 128 and above, Java creates a NEW Integer object each time.
//Integer a = 128; creates a new object
//Integer b = 128; creates ANOTHER new object
//These objects are different, even though they represent the same value
//This is why Flik.isSameNumber(128, 128) returns false -
// it's comparing two different object references, not their values.
//
// The solutions are:
//1, Change Integer to int to expand the numbers' range
//2, Replace "==" with ".equals()" to check the primitive type values instead of object references

