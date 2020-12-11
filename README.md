# OCP Preparation

This project shows some basic Code examples needed for the OCP exam.
It is separated into packages that represent the chapters in the OCP book.

## Chapter 1 - Advanced Class Design
### instanceof
The *usinginstanceof* package shows how the instanceof operator works.
Instanceof is commonly used to determine if an instance is a subclass of a particular object before applying an explicit cast.

### equals, hashCode and toString
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

### Enums
See the *enums* package.
An enumeration is like a fixed set of static final constants with the advantage that we have type-safe checking at compile time.


## Chapter 2 - Design Patterns and Principles
### Designing an Interface
- Interface = abstract data type, similar to a class that defines public abstract methods
- Classes implementing the interface must provide an implementation for the public abstract methods.
- Methods in an interface are public & abstract by default. (Compiler adds public and abstract to all methods that are not static or default)
- Interface can include constant (public static final) variables, default methods and static methods. Default methods can be optionally overridden.
- An interface may extend another interface. An interface can't extend a class. A class can't extend an interface.
    - Interfaces provide limited support for multiple inheritance because a class may implement multiple interfaces
    - Interfaces may not be marked final or be instantiated directly
    - If a class/interface inherits two default methods with the same signature and doesn't provide its own implementation it fails to compile.
- Example: `public class A implements B, C {}` --> An instance of A may be passed to any method that accepts A, B, C or java.lang.Object as input parameter

#### Purpose of an Interface
- Develop code without having access to the underlying implementation.
- Work in parallel - Team A develops Code that *uses* the interface (and maybe provides a mock implementation). Team B develops code that *implements* the interface

___

### Introducing Functional Programming
For examples see the package *'funcinterfaces'*.
- A **Functional Interface** has a single abstract method.
- They are the basis for lambda expressions in functional programming.
- A lambda expression is a block of code that gets passed around, like an anonymous method.
- Optionally use the `@FunctionalInterface` Annotation so the compiler detects if the Interface has more than one abstract method or no abstract methods at all.

#### Implementing Functional Interfaces with Lambdas
See the package *'simple_lambdas'*.
Shows a simple example on how to implement functional interfaces using lambda expressions.

#### Understanding Lambda Syntax & Spotting invalid lambdas
- Many parts are optional. The following are equivalent.
1. `a -> a.canHop()`
2. `(Animal a) -> { return a.canHop(); }`
- Left side of a lambda: parameter(s)
- Right side of a lambda: body
- Arrow: separates the parameter(s) from the body.
- The parentheses () can be omitted, if there is exactly 1 input parameter and the parameter type is not explicitly stated in the expression.
    - For 0 or more than 1 parameter the parentheses are required.
    - The parameter types don't need to be specified. (But if you specify the type, it needs to be specified for all parameters)
- For single line bodies we can omit the braces {}, semi-colon and return statement.
    - Once its more than 1 line braces, semi-colons (for each statement) and a return statement is required
    - When the return type is void, the return statement is optional
- You can't redeclare a local variable so this is invalid: `(a, b) -> { int a = 0; return 5; }`

___

### Implementing Polymorphism
See chapter *'polymorphism'*
- Is the ability of an interface to support multiple underlying forms. This allows multiple types of objects to be passed to a single method or class.

#### Distinguishing between an Object and a Reference
- Objects are accessed by reference
    - You never have direct access to the memory of the object itself. To be exact, though, the object is an entity that exists in memory, allocated by the JRE
- Regardless of the reference for the object in memory, the object itself doesn't change
```
// Example: 
Lemur lemur = new Lemur();
Object lemurAsObject = lemur;
```
- Even though the Lemur object has been assigned a reference with a different type (here of type Object), the object itself has not changed. It still exists as a Lemur object in memory.
- However the ability to access methods changed. With the lemurAsObject reference we can't access the methods within the Lemur class. (Explicit cast would be needed)

Summary:
1. The **type of object determines** which **properties** exist within the object in memory
2. The **type of the reference** to the object **determines which methods and variables are accessible** to the Java program

#### Casting Object References
- We can reclaim access to all fields and methods of the object by casting it back to the specific subclass it came from:
```
Lemur lemur = new Lemur();
Primate primate = lemur; // Primate only has access to the methods/fields defined in the Primate class
Lemur lemur2 = (Lemur) primate; // Still the same object, but now we gained back access to all its methods/fields 
```
Rules of casting:
1. Casting an object from a subclass to a superclass doesn't require an explicit cast.
1. Casting an object from a superclass to a subclass requires an explicit cast.
1. The compiler will not allow casts to unrelated types.
1. Even when the code compiles without issue, an exception may be thrown at runtime if the object being cast is not actually an instance of that class.

___

### Understanding Design Principles
- See package *'design_principles'*
- A design principle is an established idea or best practice. They lead to:
    - Lead to more logical code
    - Code that's easier to understand 
    - Classes that can be reused in other relationships or applications
    - Code that can be maintained more easily

#### Encapsulating Data
- Fundamental principle of object-oriented design
- Fields and methods in a class. *Private* instance members that have *public* methods to retrieve or modify data (e.g. getters, setters)
- Idea behind this principle: Only the class itself should have direct access to its data. 
    - Thanks to encapsulation we can ensure that an object of a class only has valid data:
    - E.g. (See `Animal` class in the subpackage encapsulation): Non-null, non-empty species field. Age is >= 0. This can be achieved by following the encapsulation principle.
    - This way anytime an Animal object is passed to a method, it can simply be used without any validation needed.

#### Creating Java Beans
- Since encapsulation is so prevalent in Java there is a standard for creating classes that store data, called JavaBeans. Rules:
1. Properties are private
1. Getters begin with `get` for non-boolean properties. Otherwise they may begin with `is` or `get`. (This does not apply for the Boolean wrapper class)
1. Setters begin with `set`
1. The method name must have the prefix `set/get/is` followed by the first letter of the property in uppercase, followed by the rest of the property name

#### Applying the Is-a Relationship
- See the instanceof operator which can be used to determine when an object is an instance of a class, superclass or interface
- Example: `Cat extends Pet` --> A Cat is-a Pet
- Is-a Relationship is known as the *inheritance test*
- If A is-a B, then any instance of A can be treated like an instance of B

#### Applying the Has-a Relationship = Composition
- When an object contains a particular property or value
- The has-a relationship is the property of an object having a named data object or primitive as member
- The has-a relationship is also known as the *object composition test*
- Example: `Bird` class and `Beak` (=Schnabel) class.
    - Separate classes with different attributes and values
    - The Bird class has a Beak attribute defined --> Bird has-a Beak
- Careful when combining the Is-a and Has-a Relationship:
    - If a parent class has-a object as protected/ public member, then any child of the parent must have that object as a member. (No problem for private members because they are not inherited in Java)
    - Example: `Primate` class has-a `Tail`. 
        - `Monkey extends Primate` --> No Problem. Monkey is-a Primate and has-a Tail
        - `Chimpanzee extends Primate` --> Problem. Chimpanzee is-a Primate but does not have a Tail --> The data model would be incorrect.

#### Composing Objects
- *Object composition* is the property of constructing a class using references to other classes in order to reuse the functionality of the other classes.
- Is an alternate to inheritance and often used to simulate polymorphic behavior that cannot be achieved via single inheritance.
    - Example: See package `design_principles.composition` 

#### Einschub: Association vs Composition vs Aggregation (Not in the OCP book)
- Association: Foo uses Bar: 
```
public class Foo {
    void myMethod(Bar bar) {
    }
}
```
- Composition: I own an object and am responsible for its lifetime. When Foo dies, so does Bar:
```
public class Foo {
    private Bar bar = new Bar(); 
}
```
- Aggregation: I have an object which I've borrowed from someone else. When Foo dies, Bar may live on:
```
public class Foo { 
    private Bar bar; 
    Foo(Bar bar) { 
       this.bar = bar; 
    }
}
```

___

### Working with Design Patterns
- Design pattern = An established general solution to a commonly occurring software development problem.
- In the next chapter we see 4 *creational patterns* (= Design pattern that manages the creation of objects inside an application)

#### Applying the Singleton Pattern
- Creating an object in memory only once in an application and have it shared by multiple classes. 
- That object is sharable by all classes and threads.
- Example (see `design_patterns.singleton`: Manage the amount of hay available for food to the zoo animals across all classes.
    - Solution 1: Pass the same `HayManager` object to every class and method that uses it. Problem: Will result in lots of pointers and is hard to manage.
    - Solution 2: Create a singleton `HayManager` object. --> No need to pass it around the application
- Singletons may improve performance by loading reusable data that would otherwise be time consuming to store and reload each time it is needed
- Singletons are created as `private static` variables within the class, often with the name `instance`
- They are accessed via a single `public static` method, often named `getInstance()`, which returns the reference to the singleton object.
- All **constructors** are marked **private**. This ensures that no other class is capable of instantiation another version of the class. (This also implicitly makes the class final.) 
- The methods have the modifier `synchronized` which prevents two processes from running the same method at the exact same time
- Another approach to create a singleton is via a static initialization block (see the `StaffRegister` class)
- Singletons are commonly used for configuration data, reusable data caches, to coordinate access to shared resources etc.

#### Applying Lazy Instantiation to Singletons
- Another technique is to delay the creation of the singleton until the first time the getInstance() method is called.
    - The creation itself is in the `getInstance()` method. There is a check - when the instance is null, create a new instance, otherwise return it. (See the `VisitorTicketTracker` class)
- Lazy instantiation is a software design pattern where we create a reusable object the first time it is requested.
    - This results in reduced memory usage and improves the performance when an application starts up.
    - However that also means there might be a noticeable delay for the user the first time a particular type of resource is needed.
- In applications that run across multiple computers, the static singleton solution starts to require special consideration, as each computer would have its own JVM. So a singleton could be implemented with a database or queue server rather than a static object.

#### Creating Immutable Object
- Goal is to create **read-only** objects that can be shared and used by multiple classes
- The state of an immutable object does not change after they are created.
- Thread safe (because the state never changes)
1. Use a constructor to set all properties.
1. Mark all instance variables `private` and `final`
1. No setters
1. Don't allow referenced mutable objects to be modified or accessed directly
    - Example: No getter for an ArrayList - otherwise we could modify the contents since an ArrayList itself is mutable. Instead create wrapper methods e.g. to get an element of the List or create a copy when returning the List. The Collections API also has a convenient `Collections.unmodifiableList()` method.
1. Prevent methods from being overridden --> Mark the class or methods with `final` (or make the constructor private and use the factory pattern)
- Example: See package `design_patterns.immutable`, class `Animal`

#### "Modifying" an Immutable Object
- To be exact you can't modify immutable objects.
- We can create a new immutable object that contains all of the same information as the original object + whatever we want to change.

#### Builder Pattern (Not relevant for OCP Exam)
- Commonly used for objects that require numerous values to be set at the time the object is instantiated.
- Imagine: Constructor with 3 input parameters. Over the time the constructor grows and has 5 more input parameters
    - Problem: We have to add five new values to the constructor which makes it hard to create such an object
    - Problem: Every time we add a parameter, Users of the class are required to update their constructor calls --> difficult to use & maintain
    - Possible solution 1: Several constructors --> Problem: can be difficult to manage
    - Possible solution 2: Setter methods --> Problem: The object is not immutable and maybe not possible because the new attributes might depend on each other
    - Best solution: **Builder pattern** - The parameters are passed to a builder object through method chaining. A final build call will then create that object. This is also commonly used for immutable objects, since they don't have setters. See package `design_patterns.builder`.
- Setters of the Builder return an instance of the Builder itself - `this`
    - That way the Builder methods can be chained together and be called in any order.
- The `build()` Method actually creates the object we want.
    - We can also add some validation in the build() method to check whether all required fields got set.
    - Or we can set default values for anything the user failed to specify on the builder object.
- Leads to more maintainable code
- Tightly coupled vs Loosely coupled Code
    - **Tightly coupled**: Classes are highly dependent - A minor change in one class may greatly impact the other class
    - **Loosely coupled**: Classes with minimal dependencies on each other. This is preferred in practice.
    - In the Builder Pattern the class and its Builder are tightly coupled. In this case that's fine because it is required.
    - Often the Builder is written as static inner class or at least in the same package. 
        - Advantage: If one is changed, the other can be quickly updated.
        - Advantage: The constructor of the class can be made private which forces users to use the Builder for creating the actual object they want.
          
#### Factory Pattern (Not relevant for OCP Exam)
- Write code that creates objects in which the precise type of the object may not be known until runtime.
- That way we are only loosely coupling the underlying creation implementation.
- Using a factory class to produce instances of objects based on a set of input parameters.
- Similar to the builder pattern but focused on supporting class polymorphism.
- Factory patterns are often implemented using `static` methods that return objects and do not require a pointer to an instance of the factory class (because it is a static method)
- Good practice to postfix `Factory` to the class name of the Factory
- Supports loose coupling between the class that the Factory produces and the class using the Factory. We can change the rules in the Factory without any other code changes.
- Example see the `design_patterns.factory` package.
    - Abstract `Food` class with 3 implementations: `Fish, Hay, Pellets`
    - `FoodFactory` with a `Food getFood()` method that returns specific Food depending on the input
    - `ZooKeeper` that calls `FoodFactory.getFood()` with some input parameter to get Food. 
    - Thanks to the FoodFactory the `ZooKeeper` and `Food` class is loosely coupled. 

## Chapter 3 - Generics and Collections
### Reviewing OCA Collections
- **ArrayList** implements List, which implements Collection which implements Iterable
    - Is an object that contains other object. It can't contain primitives
- **Array** is a built-in data structure that contains other objects or primitives
- See package `oca_collections`

### Working with Generics
- Generics allow you to write and use parameterized types.
```
List<String> names = new ArrayList<String>();
names.add(new StringBuilder("Webby"); // Compile time error
// Without Generics there will be an exception at runtime if you only want to operate on Strings
```
#### Generic classes
- Declare a **formal type parameter** in angle brackets: `public class Crate<T> {}` (see *custom_class* package)
- That generic Type `T` is available anywhere within the Crate class.
- When instantiating the class you tell the compiler what T should be for that particular Instance: `Crate<Elephant> elephantCrate = new Crate<>();`
Naming conventions:

| Letter       | Meaning                              |
|--------------|--------------------------------------|
| E            | Element                              |
| K            | Map Key                              |
| V            | Map Value                            |
| N            | Number                               |
| T            | Generic data type                    |
| S, U, V, ... | When multiple generic types are used |

#### Generic interfaces
- Interfaces can also declare a formal type parameter. (see *custom_interface* package)
- Classes implementing that interface have 3 ways to deal with the generic type:
    1. Specify the generic type in the class. And implement the methods with that specified type.
    1. Make the class itself generic and implement the method with the generic type specified in the class. (The name of the generic type can be the same as in the interface)
    1. Not use generics at all (Not recommended!) - Replace the generic type with *Object*
    
#### Generics behind the scenes & limitations
Behind the scenes the compiler replaces all Generics with *Object*. 
This way there is only one class file needed for a class that uses generics. That process is called **type erasure**.
The compiler then adds the relevant casts for your code to work with the actual specified types.
- Most limitations of Generics are due to type erasure. You can't do the following:
    - Call the constructor: new T() is not allowed because at runtime it would be 'new Object()'
    - Create an array of that static type: Because you would be creating an Array of Objects
    - Call instanceof: Because at runtime List\<Integer> and List\<String> look the same because of type erasure
    - Use primitive type as generic parameter: However you can just use the wrapper class
    - Create a static variable as generic type parameter: Because the type is linked to the instance of the class

#### Generic Methods
- Before the return type we declare the formal type parameter (e.g. *\<T>*): 
    - `public static <T> Crate<T> ship(T t) {...}`
    - Formal type parameter \<T>
    - Return type Crate\<T>
    - Parameter type T
- The formal type parameter has to be specified on the method unless it can obtain the generic formal type parameter from the class/ interface
- Example see the *generic_methods* package

#### Bounds
- Bounded wildcards restrict which types we can use for the formal type parameter
- A **bounded parameter type** is a generic type that specifies a bound for the generic
- A **wildcard generic type** is an unknown generic type represented with a question mark '**?**'

| Type of bound             | Syntax         | Example                                                          |
|---------------------------|----------------|------------------------------------------------------------------|
| Unbounded wildcard        | ?              | List<?> l = new ArrayList\<String>();                             |
| Wildcard with upper bound | ? extends type | List<? extends Exception> l = new ArrayList\<RuntimeException>(); |
| Wildcard with lower bound | ? super typ    | List<? super Exception> l = new ArrayList\<Object>();             |

##### Unbounded Wildcards
- Represents any data type
- '?' is used to specify that any type is okay
- See *bounds.unbounded_wildcard* package

##### Upper-Bounded Wildcards
```List<? extends Number> list = new ArrayList<Integer>();```
- Any class that extends Number or Number itself can be used as the formal parameter --> e.g. Integer
- When working with upper bounds or unbounded wildcards in Lists, the List becomes logically immutable
    - Technically you can still remove elements to the list but not add elements
- See *bounds.upper_bounded_wildcard* package

##### Lower-Bounded Wildcards
- With a lower bound we say that we want an Object of that class or Objects that are a superclass or that class. 
    - ```List<? super String> list; // A list of String objects or objects that are a superclass of String```
- Lower-Bounded Wildcards are needed when trying to add different types of Lists (e.g. ```List<String> and List<Object>```)to the same method.
Explanation:

| Code                                                                             | Method compiles                          | Can pass a List\<String>                  | Can pass a List\<Object> |
|----------------------------------------------------------------------------------|------------------------------------------|-------------------------------------------|--------------------------|
| public static void addSound(List\<?> list) { list.add("quack"); }                | No (unbounded generics are immutable)    | Yes                                       | Yes                      |
| public static void addSound(List\<? extends Object> list) { list.add("quack"); } | No (upperbounded generics are immutable) | Yes                                       | Yes                      |
| public static void addSound(List\<Object> list)  { list.add("quack"); }          | Yes                                      | No (with generics, must pass exact match) | Yes                      |
| public static void addSound(List\<? super String> list) { list.add("quack"); }   | Yes                                      | Yes                                       | Yes                      |

##### Putting it all together
- The *bounds.examples* package sums the generic bound chapter up.

### Using Lists, Sets, Maps and Queues
- Collection = Group of objects contained in a single object
- **List** 
    - Ordered collection of elements 
    - Duplicates allowed 
    - Elements can be accessed by an int index
- **Set**
    - Collection without duplicates
- **Queue** 
    - Orders its elements in a specific order for processing.
    - Typically FiFo (First in First out) but other orders are possible e.g. LiFo (Last in First out)
- **Map**
    - The Elements in a Map are key/ value pairs
    - Maps keys to values. 
    - No duplicate keys allowed
- **Collection** is the root interface. List, Set and Queue implement that interface. 
Map doesn't implement the Collection interface but it is still considered a collection (note the lowercase) because it contains a group of elements.
The reason it doesn't implement Collection is because different methods are needed due to the key/ value pairs.

#### Common Collection Methods
- The Collection Framework uses generics. 
- Provides a bunch of useful methods for working with lists, sets or queues
- See the *collections.common_methods* package
- **add()** --> `boolean add(E element)`
    - Inserts a new element into the Collection and returns whether it was successful 
    - E.g. returns true for Lists, but false when trying to add duplicates to a Set
- **remove()** --> `boolean remove(Object object)`
    - Removes a single (the first) matching value in the Collection and returns whether the element was successfully removed.
    - There is an overloaded remove method that uses int as the index to remove an element. Notice that that method might throw an IndexOutOfBoundsException.
- **isEmpty()** --> `boolean isEmpty()`
    - Looks at how many elements are in the Collection and returns true when the size is 0.
- **size()** --> `int size()`
    - Looks at how many elements are in the Collection and returns the amount of elements inside
- **clear()** --> `void clear()`
    - Discards all elements of the Collection. After that the Collection is empty and has a size of 0.
- **contains()** --> `boolean contains(Object object)`
    - Checks if a certain value is in the Collection.
    - Calls the `equals()` method on each element of the ArrayList to see if there are any matches. 

#### Using the List Interface
- Used when you want an **ordered** collection that can contain **duplicate** entries. (That's what all List implementations have in common)
- Items can be retrieved and inserted at specific positions in the list based on an int index.
- Each element of the List has an index and that index begins with zero

##### Comparing List Implementations
**ArrayList**
- Like a resizable array
    - When adding elements, the ArrayList automatically grows
- You can look up any element in constant time (O(1))
- Adding or removing an element is slower
- ==> Use when you are reading more often than writing

##### Intermezzo - Big O Notation
- Is used to talk about the performance of algorithms
    - Let's you compare the order of magnitude (Grössenordnungsdifferenz) performance rather than the exact performance
- "n" is used to reflect the number of elements or size of the data
- The most common big O notation values:
- **O(1) - constant time** 
    - The size of the collection doesn't matter. The answer will always take the same amount of time to return.
    - E.g. returning the String literal "Panda" or returning the last element of an array
- **O(log n) - logarithmic time**
    - A logarithm is a mathematical function that grows much more slowly than the data size
        - E.g. log(8) at base 2 gives you 3 and log(1024) at base 2 gives you 10
    - Is much better than linear time
    - E.g. binary search because it doesn't look at the majority of the elements
- **O(n) - linear time**
    - The performance will grow linearly with respect to the size of the collection
    - E.g. looping through a list and returning the number of elements matching "Panda"
- **O(n²) - n squared time**
    - Code that has nested loops where each loop goes through the data takes n squared time.

**LinkedList**
- Implements both, List and Queue
    - Has all methods of List and additional methods to facilitate adding or removing from the beginning and/ or ending of the list.
- You can access, add and remove from the beginning and end of the list in constant time (O(1))
- Tradeoff: Dealing with the arbitrary (willkürlich) index takes linear time (O(n))
- Good choice when you'll be using the List as a Queue

##### Working with List Methods
- The methods in Lists are for working with indexes. Here the most common methods:

| Method                         | Description                                                  |
|--------------------------------|--------------------------------------------------------------|
| void add(E element)            | Adds element to end                                          |
| void add(int index, E element) | Adds element at index and moves the rest toward the end      |
| E get(int index)               | Returns element at index                                     |
| int indexOf(Object o)          | Returns first matching index or -1 if not found              |
| int lastIndexOf(Object o)      | Returns last matching index or -1 if not found               |
| void remove(int index)         | Removes element at index and moves the rest toward the front |
| E set(int index, E e)          | Replaces element at index and returns original               |

#### Using the Set Interface
- Used when you don't want duplicate entries

**HashSet**
- Stores elements in a hash table
    - Uses the `hashCode()` method to retrieve objects more efficiently
- Adding elements has constant time - O(1)
- Checking if an element is in the set has constant time - O(1)
    - equals() is used to determine equality
    - hashCode() is used to know which bucket to look in for the element
    - Best case: All hash Codes of the objects are unique and Java has to call equals() on only one object
    - Worst case: All implementations return the same hashCode and Java has to call equals() on every element of the set anyway 
- Tradeoff: You lose the order in which you inserted the elements
- Most commonly used form of a Set

**TreeSet**
- Stores elements in a sorted tree structure
- Elements are always in a sorted order
- Tradeoff: Adding and checking if an element is present need logarithmic time: O(log n)
- TreeSet implements the Interface `NavigableSet` that lets you slice up the collection

![HashSet vs TreeSet img](./src/de/mino/assets/pictures/HashSet_vs_TreeSet.png "HashSet vs TreeSet")

##### Working with Set Methods
- See package *collections.sets*

##### NavigableSet Interface
- As said, can be used when working with TreeSets: `NavigableSet<Integer> set = new TreeSet<>();`

| Method         | Description                                                       |
|----------------|-------------------------------------------------------------------|
| E lower(E e)   | Returns greatest element that is < e, or null if no such element  |
| E floor(E e)   | Returns greatest element that is <= e, or null if no such element |
| E ceiling(E e) | Returns smallest element that is >= e, or null if no such element |
| E higher(E e)  | Returns smallest element that is > e, or null if no such element  |

#### Using the Queue Interface
- Used when elements are added and removed in a specific order
- Commonly used for sorting elements prior to processing them
- By default a queue is assumed FIFO (first-in, first-out). The other common format is LIFO (last-in, first-out)

##### Comparing Queue implementations
**LinkedList**
- Earlier we thematized `LinkedList`. Beside being a list, it is also a double-ended queue
    - Double ended means that you can insert and remove elements from both the front and the back
    - LinkedList implements List and Queue (which is the advantage over ArrayDeque) but isn't as efficient as a "pure" queue
    - LinkedList works the exact same way as ArrayDeque

**ArrayDeque**
 - Is a "pure" double-ended queue
 - Stores its elements in a resizable Array
 - Is more efficient than a LinkedList
 - Beside the methods that come from the Collection interface, Deque offers a lot of methods. Those are the most important ones:

| Method             | Description                                                                                               | For queue | For Stack |
|--------------------|-----------------------------------------------------------------------------------------------------------|-----------|-----------|
| boolean add(E e)   | **Adds** an element to the **back of the queue** and returns true or throws an exception                  | Yes       | No        |
| E element()        | Returns **next element** or throws an exception if empty queue                                            | Yes       | No        |
| boolean offer(E e) | **Adds** an element to the **back of the queue** and returns whether successful                           | Yes       | No        |
| E remove()         | **Removes** and returns **next element** or throws an exception if empty queue                            | Yes       | No        |
| void push(E e)     | **Adds** an element to the **front of the queue**                                                         | Yes       | Yes       |
| E poll()           | **Removes** and returns **next element** or returns null if empty queue --> Removes the head of the queue | Yes       | No        |
| E peek()           | Returns **next element** or returns null if empty queue --> Returns the head of the queue                 | Yes       | Yes       |
| E pop()            | **Removes** and returns **next element** or throws an exception if empty queue                            | No        | Yes       |

- offer & add = Add elem to the back
- push = Add elem to the front
- peek & element = Head of the queue
- poll & remove/pop = Remove the head

- Except for push, all methods are available in the `Queue` interface as well. Push makes `ArrayDeque` a double-ended queue
- There are basically two sets of methods
    - One throws an exception when something goes wrong
    - The other uses a different return value when something goes wrong
    - offer, poll and peek methods are more commonly used than the ones with exceptions
- Examples see the `collections.queue` package

FIFO vs LIFO
- FIFO = Stack
    - push/ poll/ peek
- LIFO = Single-ended queue
    - offer/ poll/ peek
- ArrayDeque can be used as a Stack or a Queue

#### Map
- Stores data in key value pairs
- Values are identified by an (unique) key
- Example: Contact list in a phone. You look up the name and get the phone number 
    - --> A persons name would be the key and the phone number the value

##### Comparing Map Implementations
**HashMap**
- Stores the keys in a hash table
- Uses the `hashCode()`method of the keys to retrieve their values more efficiently
    - Adding & retrieving the element by key have constant time - O(1) 
- Tradeoff: You lose the order in which you inserted the elements
    - Most of the time that is not a problem. If the order is needed you can use a `LinkedHashMap`

**TreeMap**
- Stores the keys in a sorted tree structure
- Advantage: Keys are always in sorted order
- Tradeoff: Adding and checking if a key is present take logarithmic time - O(log n)

##### Working with Map Methods
- Map doesn't extend `Collection` so the methods are specified on the `Map` interface
- Many methods use the generic type parameters `K` vor Key and `V` for Value

| Method                              | Description                                                      |
|-------------------------------------|------------------------------------------------------------------|
| void clear()                        | Removes all keys and values from the map.                        |
| boolean isEmpty()                   | Returns whether the map is empty.                                |
| int size()                          | Returns the number of entries (key/value pairs) in the map.      |
| V get(Object key)                   | Returns the value mapped by key or null if none is mapped.       |
| V put(K key, V value)               | Adds or replaces key/value pair. Returns previous value or null. |
| V remove(Object key)                | Removes and returns value mapped to key. Returns null if none.   |
| boolean containsKey(Object key)     | Returns whether key is in map.                                   |
| boolean containsValue(Object value) | Returns whether value is in map.                                 |
| Set keySet()                        | Returns *Set* of all keys.                                       |
| Collection values()                 | Returns *Collection* of all values.                              |

- See the `collections.map` package for examples with HashMap and TreeMap

#### Comparing Collection types
- Comparing the characteristics of the different Collection Framework types:

| Type  | Can contain duplicate elements? | Elements ordered?                | Has keys and values? | Must add/ remove in specific order? |
|-------|---------------------------------|----------------------------------|----------------------|-------------------------------------|
| List  | Yes                             | Yes (by index)                   | No                   | No                                  |
| Map   | Yes (for values)                | No                               | Yes                  | No                                  |
| Queue | Yes                             | Yes (retrieved in defined order) | No                   | Yes                                 |

- Comparing the Collection attributes:

| Type                   | Java Collections Framework instance | Sorted? | Calls hashCode? | Calls compareTo? | Can contain nulls?                                           |
|------------------------|-------------------------------------|---------|-----------------|------------------|--------------------------------------------------------------|
| ArrayList              | List                                | No      | No              | No               | Yes                                                          |
| LinkedList             | List, Queue                         | No      | No              | No               | Yes                                                          |
| Stack (deprecated)     | List                                | No      | No              | No               | Yes                                                          |
| Vector (deprecated)    | List                                | No      | No              | No               | Yes                                                          |
| HashSet                | Set                                 | No      | Yes             | No               | Yes                                                          |
| TreeSet                | Set                                 | Yes     | No              | Yes              | No null elements  (makes sense because cant sort with nulls) |
| ArrayDeque             | Queue                               | No      | No              | No               | No null elements                                             |
| HashMap                | Map                                 | No      | Yes             | No               | Yes                                                          |
| TreeMap                | Map                                 | Yes     | No              | Yes              | No null keys  (makes sense because cant sort with nulls)     |
| Hashtable (deprecated) | Map                                 | No      | Yes             | No               | No null keys or values                                       |

Which data structures *don't* allow null values?
- TreeSet, TreeMap - Data structures that involve sorting do not allow null values (Makes sense - you can't compare to null values to sort)
- ArrayDeque - It has methods that use null as a meaning like poll(). It uses null to indicate that the collection is empty.
- Hashtable (No special meaning, just old and deprecated) 

Choosing the right collection type by a given description of a problem:
- Pick the top zoo map off a stack of maps
    - Solution: ArrayDeque
    - Reason: Description is of a last-in, first-out structure. So a Stack is needed which is a type of Queue. (Stack is deprecated so we use the better ArrayDeque)
- Sell tickets to people in the order they appear in the line & tell them their position in line
    - Solution: LinkedList
    - Reason: First-in, first-out data structure. So a Queue is needed. Since we also need indexes the only collection that meets both requirements is LinkedList. (Otherwise we could use the more efficient ArrayDeque)
- Write down the names of all elephants you see in the zoo so you can tell them to your friend's 3-year old every time she asks. The elephants do not have unique names.
    - Solution: ArrayList
    - Reason: There are duplicates --> List is needed. You will be accessing the list more often than updating it because the 3-year old will ask over and over. That's why an ArrayList is better than an LinkedList here.
- List the unique animals that you want to see at the zoo today
    - Solution: HashSet
    - Reason: Keyword unique --> Set is needed. There are no requirements on a sorted order so we use the most efficient Set.
- List the unique animals that you want to see at the zoo today in alphabetical order
    - Solution: TreeSet
    - Reason: Keyword unique --> Set is needed. This time we need to sort so we cannot use the HashSet.
- Look up animals based on a unique identifier
    - Solution: HashMap
    - Reason: Looking up by key so we need a Map. We have no requirements on ordering or sorting so we use the most efficient Map.   

## Chapter 4 - Functional Programming
### Functional interfaces
The package *'funcinterfaces'* shows the common functional interfaces
that were added with Java8 to the *java.util.function* package with simple use cases.

| Functional Interfaces | #Parameters  | Return Type | Single Abstract Method |
|-----------------------|--------------|-------------|------------------------|
| Supplier&lt;T>        | 0            | T           | get                    |
| Consumer&lt;T>        | 1 (T)        | void        | accept                 |
| BiConsumer<T, U>      | 2 (T, U)     | void        | accept                 |
| Predicate&lt;T>       | 1 (T)        | boolean     | test                   |
| BiPredicate<T, U>     | 2 (T, U)     | boolean     | test                   |
| Function<T, R>        | 1 (T)        | R           | apply                  |
| BiFunction<T, U, R>   | 2 (T, U)     | R           | apply                  |
| UnaryOperator&lt;T>   | 1 (T)        | T           | apply                  |
| BinaryOperator&lt;T>  | 2 (T, T)     | T           | apply                  |

### Optional
The Optional type was also added with Java 8. It can be used to express "we don't know" or "not applicable".
An Optional is like a Box that might have a value inside or might be empty. 
Returning an Optional is a clear statement in the API that there might not be a value in there. 
This also avoids null values and NPE's.

The basics are shown inside the *'optional'* package.


### Streams
A stream is a sequence of data. 
Streams are comparable with an assembly line in a factory
A Stream consists of 3 parts:
* 1 Source: Where the stream comes from
* 0..n Intermediate operations: Transforms the stream into another one.
Since streams use lazy evaluation, the intermediate operations do not run until the terminal operation runs
* 1 Terminal operation: Actually produces a result. Streams can only be used once. The stream is no longer
valid after a terminal operation completes.

Source --> Intermediate operations --> Output from terminal operation

---
Generally the flow is like this: 
1. Elem 1 gets passed from the Source to intermediate operation 1
2. Elem 1 gets passed from intermediate operation 1 to intermediate operation 2 (and so on for all intermediate operations)

However, there are exceptions: e.g. the intermediate operation *sorted()* needs all elements of the stream to work.

---

Examples are shown in the *'streams'* package.



| Method                              | What happens for Infinite Streams? | Return Value | Reduction |
|-------------------------------------|------------------------------------|--------------|-----------|
| count()                             | Does not terminate                 | long         | Yes       |
| min()/ max()                        | Does not terminate                 | Optional&lt;T>  | Yes       |
| findAny()/ findFirst()              | Terminates                         | Optional&lt;T>  | No        |
| allMatch()/ anyMatch()/ noneMatch() | Sometimes terminates               | boolean      | No        |
| forEach()                           | Does not terminate                 | void         | No        |
| reduce()                            | Does not terminate                 | Varies       | Yes       |
| collect()                           | Does not terminate                 | Varies       | Yes       |


## Primitive Streams
The package 'primitivestreams' shows how to use primitive streams.

There are 3 types of primitive streams in Java:
1. **IntStream** - Used for int, short, byte and char
1. **LongStream** - Used for long
1. **DoubleStream** - Used for double and float

These primitive Streams have the regular Stream methods + convenience methods for common tasks such as calculating an average.
 
There are Mapping methods to map a stream to another Stream type (This is the case for all Streams - Stream, IntStream, LongStream, DoubleStream)

There are special functional Interfaces for the primitive Streams. They are the equivalent to the general functional Interfaces we thematized in the package 'funcinterfaces'
- IntSupplier
- IntConsumer
- IntPredicate
- IntFunction
- IntUnaryOperator
- IntBinaryOperator
- (Same for Long and Double)

The type name tells us what primitive type is involved. 
 
### Optional with primitive Streams
There are 3 types of Optional primitives
1. OptionalInt
1. OptionalLong
1. OptionalDouble

The difference to a regular Optional is that a regular Optional such as `Optional<Double>` is for the Double wrapper class whereas the OptionalDouble is for the primitive double.

## Working with advanced Stream Pipeline Concepts
This chapter is in the 'advancedstreamconcepts' package.

The class `AdvancedStreamPipeline` contains the following topics:

Contains an example that proves that Streams are lazily evaluated.
Shows that some of the intermediate operations for Streams are also available for Optional.
Points out the problematic with checked Exceptions and Functional Interfaces.

The class `CollectingResults` Shows many predefined collectors. (Remember that Collectors are used in the terminal operation `collect()`)
It includes `joining()`, `averagingInt()`, `toCollection()`, `toMap()`, `groupingBy()` and`partitioningBy()`
