package com.simoncat.framework.core.tuple;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class CoupleTest {

    private static final TestObject objectA = new TestObject("test1", 1);
    private static final TestObject objectB = new TestObject("test2", 2);
    private static final TestObject[] objectArray = new TestObject[]{objectA, objectB};
    private static final Set<TestObject> objectSet = new HashSet<>(Arrays.asList(objectA, objectB));

    public void testOf() {
        Optional<Couple<TestObject>> couple = Couple.of(objectA, objectB);
        assertTrue("Can successfully create Couple instance", couple.isPresent());
        
        Optional<Couple<TestObject>> coupleFromArray = Couple.of(objectArray);
        assertTrue("Can successfully create Couple instance from array", coupleFromArray.isPresent());
        
        Optional<Couple<TestObject>> coupleFromSet = Couple.of(objectSet);
        assertTrue("Can successfully create Couple instance from list", coupleFromSet.isPresent());
    }
    
    public void testGetTwoElementArray() {
        Couple<TestObject> couple = Couple.of(objectA, objectB).get();
        List<TestObject> list = couple.getTwoElementList();
        assertTrue("Can always get both of 2 elements", Arrays.equals(list.toArray(), objectArray));
    }

    static class TestObject {
        private final String name;
        private final int weight;

        public TestObject(String name, int weight) {
            this.name = name;
            this.weight = weight;
        }

        public String getName() {
            return name;
        }

        public int getWeight() {
            return weight;
        }
    }
}
