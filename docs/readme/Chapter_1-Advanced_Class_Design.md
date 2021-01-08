# Chapter 1 - Advanced Class Design
## instanceof
The *usinginstanceof* package shows how the instanceof operator works.
Instanceof is commonly used to determine if an instance is a subclass of a particular object before applying an explicit cast.

## equals, hashCode and toString
These are methods provided by java.lang.Object so they can be overridden by any class.

**String toString()**
- Java calls the toString method when printing an object.
- The default implementation doesn't provide a human readable result: `return getClass().getName() + "@" + Integer.toHexString(hashCode());`

**boolean equals(Object obj)** 
- Java uses *==* to compare primitives and to check if two variables refer to the same object.
- The implementation in the *Object* class does the same: `return (this == obj);`
- Should return false when the object passed is null or of the wrong type
- We can override this method with an own implementation of when objects of a class are considered equal. E.g.:
```
@Override 
public boolean equals(Object obj) {
    if (!(obj instanceof Lion)) return false;
    Lion otherLion = (Lion) obj;
    return this.idNumber == otherLion.idNumber;
}
```
     
**int hashCode()**
- When you override `equals()`, you are also expected to override `hashCode()`
- The hashCode is used when storing the object as **key in a map**
- A hash code is a number that puts instances of a class into a finite number of categories.
- Example: Card deck: Put the 52 cards in 13 categories - one for each number (2, 3, ..., K, A).
- It is common to not include boolean and char variables in the hash code.
- The official JavaDoc stated a contract for hashCode:
    - The result of hashCode() must not change. So don't include variables that change for an object (e.g. include the name but not the weight)
    - If equals() returns true, then calling hashCode() on these objects must return the same result. --> This means that hashCode() can only use a subset of the variables that equals() uses.
    - If equals() returns false, then calling hashCode() on these objects can return the same result.

## Enums
See the *enums* package.
An enumeration is like a fixed set of static final constants with the advantage that we have type-safe checking at compile time.
