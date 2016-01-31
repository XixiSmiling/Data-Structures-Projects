/* DListNode.java */



/**
 *  A DListNode is a node in a DList (doubly-linked list).
 */

public class DListNode<T> {

  /**
   *  item references the item stored in the current node.
   *  prev references the previous node in the DList.
   *  next references the next node in the DList.
   *
   *  DO NOT CHANGE THE FOLLOWING FIELD DECLARATIONS.
   */

  public T item;
  protected DListNode<T> prev;
  protected DListNode<T> next;

  /**
   *  DListNode() constructor.
   *  @param i the item to store in the node.
   *  @param p the node previous to this node.
   *  @param n the node following this node.
   */
  DListNode(T i, DListNode<T> p, DListNode<T> n) {
    item = i;
    prev = p;
    next = n;
  }
}
