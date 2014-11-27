/**
 * LinkedList class implements a doubly-linked list.
 */
public class MyLinkedList<AnyType> implements Iterable<AnyType>
{
    /**
     * Construct an empty LinkedList.
     */
    public MyLinkedList( )
    {
        doClear( );
    }
    
    private void clear( )
    {
        doClear( );
    }
    
    /**
     * Change the size of this collection to zero.
     */
    public void doClear( )
    {
        beginMarker = new Node<>( null, null, null );
        endMarker = new Node<>( null, beginMarker, null );
        beginMarker.next = endMarker;
        
        theSize = 0;
        modCount++;
    }
    /**
     * swap the nodes in position n and m 
     * provided they are within the currentsize
     */
    public void swap(int n, int m)
    {
         if(n < 0 || n >= theSize || m < 0 || m >= theSize) 
               throw new IndexOutOfBoundsException( "the index is out of the range");

         if(n==m) return;
         if(n>m) {
            swap(m, n);
            return;
         }
         Node<AnyType> nNode = getNode(n);
         Node<AnyType> mNode = getNode(m);
         Node<AnyType> nPrev = nNode.prev;
         Node<AnyType> nNext = nNode.next;
         Node<AnyType> mPrev = mNode.prev;
         Node<AnyType> mNext = mNode.next;
         nPrev.next = mNode;
         mNode.prev = nPrev;
         nNode.next = mNext;
         mNext.prev = nNode;
         if(m-n==1) {             
             nNode.prev = mNode;
             mNode.next = nNode;             
         } 
         else {             
             nNode.prev = mPrev;
             mNode.next = nNext;             
             mPrev.next = nNode;
             nNext.prev = mNode;
         }
         
//          Node<AnyType> nNode = getNode(n);
//          AnyType nValue = nNode.data;
//          Node<AnyType> mNode = getNode(m);         
//          nNode.data = mNode.data;
//          mNode.data = nValue;               
    }
    /**
     * shift the list n position forward or backward 
     * the elements in the end or front move to front part or end part of the list
     */
    
    public void shift(int n)
    {
         if (n==0) return;
         if(n>theSize || n<-theSize)
             throw new IndexOutOfBoundsException( "the index is out of the range");
         Node<AnyType> temp1, temp2;
         if(n<0) {
             temp1 = getNode(theSize-1+n);
             temp2 = getNode(theSize+n);                
         }
         else {
             temp1 = getNode(n-1);
             temp2 = getNode(n);
         }  
             temp1.next = endMarker;
             temp2.prev = beginMarker;
             beginMarker.next.prev = endMarker.prev;
             endMarker.prev.next = beginMarker.next;
             beginMarker.next = temp2;
             endMarker.prev = temp1;
         
   }
   /**
     * remove n elements start from position i
     * update new size to thesize plus n
     */
   public void erase(int i, int n) {
         if(i<0||n<0||(i+n)>theSize)
               throw new IndexOutOfBoundsException( "the index is out of the range");
         Node<AnyType> newNode1 = getNode(i);
         Node<AnyType> newNode2 = getNode(i+n-1);
         newNode1.prev.next = newNode2.next;
         newNode2.next.prev = newNode1.prev;
         newNode2.next = null;
         newNode1.prev = null;  
         theSize = theSize - n;       
         
   }
   
   /**
     * insert another MyLinkedList at index
     * passed list become empty
     * update theSize
     */
   public void insertList(MyLinkedList p, int i) {
         if(i<0||i>=theSize) 
               throw new IndexOutOfBoundsException( "the index is out of the range");
         theSize = theSize + p.theSize;
         Node<AnyType> newNode = getNode(i);
         p.beginMarker.next.prev = newNode.prev;
         p.endMarker.prev.next = newNode;
         newNode.prev.next = p.beginMarker.next;
         newNode.prev = p.endMarker.prev;
         p.beginMarker.next = p.endMarker;
         p.endMarker.prev = p.beginMarker; 
   }     
    
    /**
     * Returns the number of items in this collection.
     * @return the number of items in this collection.
     */
    public int size( )
    {
        return theSize;
    }
    
    public boolean isEmpty( )
    {
        return size( ) == 0;
    }
    
    /**
     * Adds an item to this collection, at the end.
     * @param x any object.
     * @return true.
     */
    public boolean add( AnyType x )
    {
        add( size( ), x );   
        return true;         
    }
    
    /**
     * Adds an item to this collection, at specified position.
     * Items at or after that position are slid one position higher.
     * @param x any object.
     * @param idx position to add at.
     * @throws IndexOutOfBoundsException if idx is not between 0 and size(), inclusive.
     */
    public void add( int idx, AnyType x )
    {
        addBefore( getNode( idx, 0, size( ) ), x );
    }
    
    /**
     * Adds an item to this collection, at specified position p.
     * Items at or after that position are slid one position higher.
     * @param p Node to add before.
     * @param x any object.
     * @throws IndexOutOfBoundsException if idx is not between 0 and size(), inclusive.
     */    
    private void addBefore( Node<AnyType> p, AnyType x )
    {
        Node<AnyType> newNode = new Node<>( x, p.prev, p );
        newNode.prev.next = newNode;
        p.prev = newNode;         
        theSize++;
        modCount++;
    }   
    
    
    /**
     * Returns the item at position idx.
     * @param idx the index to search in.
     * @throws IndexOutOfBoundsException if index is out of range.
     */
    public AnyType get( int idx )
    {
        return getNode( idx ).data;
    }
        
    /**
     * Changes the item at position idx.
     * @param idx the index to change.
     * @param newVal the new value.
     * @return the old value.
     * @throws IndexOutOfBoundsException if index is out of range.
     */
    public AnyType set( int idx, AnyType newVal )
    {
        Node<AnyType> p = getNode( idx );
        AnyType oldVal = p.data;
        
        p.data = newVal;   
        return oldVal;
    }
    
    /**
     * Gets the Node at position idx, which must range from 0 to size( ) - 1.
     * @param idx index to search at.
     * @return internal node corresponding to idx.
     * @throws IndexOutOfBoundsException if idx is not between 0 and size( ) - 1, inclusive.
     */
    private Node<AnyType> getNode( int idx )
    {
        return getNode( idx, 0, size( ) - 1 );
    }

    /**
     * Gets the Node at position idx, which must range from lower to upper.
     * @param idx index to search at.
     * @param lower lowest valid index.
     * @param upper highest valid index.
     * @return internal node corresponding to idx.
     * @throws IndexOutOfBoundsException if idx is not between lower and upper, inclusive.
     */    
    private Node<AnyType> getNode( int idx, int lower, int upper )
    {
        Node<AnyType> p;
        
        if( idx < lower || idx > upper )
            throw new IndexOutOfBoundsException( "getNode index: " + idx + "; size: " + size( ) );
            
        if( idx < size( ) / 2 )
        {
            p = beginMarker.next;
            for( int i = 0; i < idx; i++ )
                p = p.next;            
        }
        else
        {
            p = endMarker;
            for( int i = size( ); i > idx; i-- )
                p = p.prev;
        } 
        
        return p;
    }
    
    /**
     * Removes an item from this collection.
     * @param idx the index of the object.
     * @return the item was removed from the collection.
     */
    public AnyType remove( int idx )
    {
        return remove( getNode( idx ) );
    }
    
    /**
     * Removes the object contained in Node p.
     * @param p the Node containing the object.
     * @return the item was removed from the collection.
     */
    private AnyType remove( Node<AnyType> p )
    {
        p.next.prev = p.prev;
        p.prev.next = p.next;
        theSize--;
        modCount++;
        
        return p.data;
    }
    
    /**
     * Returns a String representation of this collection.
     */
    public String toString( )
    {
        StringBuilder sb = new StringBuilder( "[ " );

        for( AnyType x : this )
            sb.append( x + " " );
        sb.append( "]" );

        return new String( sb );
    }

    /**
     * Obtains an Iterator object used to traverse the collection.
     * @return an iterator positioned prior to the first element.
     */
    public java.util.Iterator<AnyType> iterator( )
    {
        return new LinkedListIterator( );
    }

    /**
     * This is the implementation of the LinkedListIterator.
     * It maintains a notion of a current position and of
     * course the implicit reference to the MyLinkedList.
     */
    private class LinkedListIterator implements java.util.Iterator<AnyType>
    {
        private Node<AnyType> current = beginMarker.next;
        private int expectedModCount = modCount;
        private boolean okToRemove = false;
        
        public boolean hasNext( )
        {
            return current != endMarker;
        }
        
        public AnyType next( )
        {
            if( modCount != expectedModCount )
                throw new java.util.ConcurrentModificationException( );
            if( !hasNext( ) )
                throw new java.util.NoSuchElementException( ); 
                   
            AnyType nextItem = current.data;
            current = current.next;
            okToRemove = true;
            return nextItem;
        }
        
        public void remove( )
        {
            if( modCount != expectedModCount )
                throw new java.util.ConcurrentModificationException( );
            if( !okToRemove )
                throw new IllegalStateException( );
                
            MyLinkedList.this.remove( current.prev );
            expectedModCount++;
            okToRemove = false;       
        }
    }
    
    /**
     * This is the doubly-linked list node.
     */
    private static class Node<AnyType>
    {
        public Node( AnyType d, Node<AnyType> p, Node<AnyType> n )
        {
            data = d; prev = p; next = n;
        }
        
        public AnyType data;
        public Node<AnyType>   prev;
        public Node<AnyType>   next;
    }
    
    private int theSize;
    private int modCount = 0;
    private Node<AnyType> beginMarker;
    private Node<AnyType> endMarker;
}

class TestLinkedList
{
    public static void main( String [ ] args )
    {
        MyLinkedList<Integer> lst = new MyLinkedList<>( );

        for( int i = 0; i < 10; i++ )
                lst.add( i );
        for( int i = 20; i < 30; i++ )
                lst.add( 0, i );

        lst.remove( 0 );
        lst.remove( lst.size( ) - 1 );

        System.out.println( lst );
                
        lst.swap(1, 0);
        System.out.println(lst);
        
        lst.swap(1, 10);
        System.out.println(lst);
        
        lst.shift(4);
        System.out.println(lst);
        
        lst.shift(-3);
        System.out.println(lst);

        lst.erase(17,1);
        System.out.println(lst);
        System.out.println("the new size of lst is: " + lst.size());
        
        lst.erase(2,3);
        System.out.println(lst);
        
        MyLinkedList<Integer> lst2 = new MyLinkedList<>( );
        for( int i = 0; i < 10; i++ )
                lst2.add( i );
        
        System.out.println(lst2);        
        lst.insertList(lst2, 5);
        System.out.println(lst);
        System.out.println("the new size of lst is: " + lst.size());
        System.out.println(lst2);
        
        java.util.Iterator<Integer> itr = lst.iterator( );
        while( itr.hasNext( ) )
        {
                itr.next( );
                itr.remove( );
                System.out.println( lst );
        }
    }
}
