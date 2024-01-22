# Minimize Mutability


Recall that to guarantee immutability, a class must not permit itself to be subclassed. This can be done by making the class final, but there is another, more flexible alternative. Instead of making an immutable class final, you can make all of its constructors private or package-private and add public static factories in place of the public constructors. (code example `Point.java`)

Resist the urge to write a setter for every getter. Classes should be immutable unless there’s a very good reason to make them mutable.

Immutable classes provide many advantages, and their only disadvantage is the potential for performance problems under certain circumstances.
You should always make small value objects, such as PhoneNumber and Complex, immutable.
You should seriously consider making larger value objects, such as String and BigInteger, immutable as well.

You should provide a public mutable companion class for your immutable class only once you’ve confirmed that it’s necessary to achieve satisfactory performance(code example : `MutablePoint.java && MutablePointTest.java`)