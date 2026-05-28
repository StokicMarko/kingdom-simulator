import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import utility.collection.ArrayList;
import utility.collection.ListADT;

import static org.junit.jupiter.api.Assertions.*;

class MyArrayListTest {

  private ListADT<String> list;   // programmed to the interface

  @BeforeEach
  void setUp() {
    list = new ArrayList<>();
  }

  // zero
  @Test
  void newListIsEmpty() {
    assertTrue(list.isEmpty());
    assertEquals(0, list.size());
  }

  @Test
  void emptyListIsNotFull() {
    // could be a bug in the lib
    assertFalse(list.isFull());
  }

  // one
  @Test
  void addThenGetReturnsElement() {
    list.add("A");
    assertEquals(1, list.size());
    assertEquals("A", list.get(0));
    assertTrue(list.contains("A"));
  }

  @Test
  void removeSingleElementEmptiesList() {
    list.add("A");
    assertEquals("A", list.remove(0));
    assertTrue(list.isEmpty());
  }

  // many
  @Test
  void addAppendsInOrder() {
    list.add("A");
    list.add("B");
    list.add("C");
    assertEquals("A", list.get(0));
    assertEquals("B", list.get(1));
    assertEquals("C", list.get(2));
  }

  @Test
  void removeFromMiddleShiftsRemaining() {
    list.add("A");
    list.add("B");
    list.add("C");
    assertEquals("B", list.remove(1));
    assertEquals(2, list.size());
    assertEquals("C", list.get(1));
  }

  @Test
  void indexOfReturnsFirstOccurrence() {
    list.add("A");
    list.add("B");
    list.add("B");
    assertEquals(1, list.indexOf("B"));
  }

  // boundary
  @Test
  void insertAtIndexEqualToSizeAppends() {
    list.add("A");
    list.add(list.size(), "B");   // index == size is legal
    assertEquals("B", list.get(1));
    assertEquals(2, list.size());
  }

  // exceptions
  @Test
  void removeMissingElementThrows() {
    list.add("A");
    assertThrows(IllegalStateException.class, () -> list.remove("Z"));
  }
}
