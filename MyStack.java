/**
 * Stack class implements a FILO structure.
 */
public class MyStack<AnyType> 
{
    private MyLinkedList<AnyType> stack;
    private int size;
    
    /**
     * construct new stack, set size to zero.
     */      
    public MyStack() {
        stack = new MyLinkedList<AnyType>();
        size = 0;
    }
    /**
     * add new item in front of the stack.
     * increment the size by one
     */      
    public void push(AnyType item) {
        stack.add(0, item);
        size++;
    }
    /**
     * remove item from front of the stack
     * @return the item being removed
     */      
    public AnyType pop() {
        size--;
        return stack.remove(0);
    }
    
    /**
     * return the item from front of the stack
     * 
     */     
    public AnyType top() {
        return stack.get(0);
    }

    /**
     * test MyStack class and demonstrate the balanced symbol algorithm.
     */
    public static void main(String[] args) {
        MyStack<Character> result = new MyStack<>();
        int index;
        boolean balanced = true;
        String m1 = "{6+4}*[7+1][[]()(())";
        
        // check each symbol, put "([{" into the stack
        // check balance or not when meet ")]}" and stop loop if not balance
        for(int i=0; i<m1.length()&&balanced; i++) { 
            if("([{".indexOf(m1.charAt(i))!= -1 )
                result.push(m1.charAt(i));
            else if(")]}".indexOf(m1.charAt(i))!=-1) {
                if((m1.charAt(i)==')'&&result.pop()!='(')||
                   (m1.charAt(i)==']'&&result.pop()!='[')||
                   (m1.charAt(i)=='}'&&result.pop()!='{'))
                    balanced = false;                
            }
         } 
         
         //if the stack is not empty, pop all the elements and the symbol is not balanced
        while(result.size!=0) {
            balanced = false;
            result.pop();
        }
        
        System.out.println("the size of the stack is " + result.size);
        if(balanced)
            System.out.println("the expression " + m1 + " is balanced");
        else
            System.out.println("the expression " + m1 + " is not balanced");
        
        String m2 = "[({}{})]";
        balanced = true;
        for(int i=0; i<m2.length()&&balanced; i++) {
            if("([{".indexOf(m2.charAt(i))!= -1 )
                result.push(m2.charAt(i));
            else if(")]}".indexOf(m2.charAt(i))!=-1) {
                if((m2.charAt(i)==')'&&result.pop()!='(')||
                   (m2.charAt(i)==']'&&result.pop()!='[')||
                   (m2.charAt(i)=='}'&&result.pop()!='{'))
                    balanced = false;                
            }
         } 
        while(result.size!=0) {
            balanced = false;
            result.pop();
        }
        System.out.println("the size of the stack is " + result.size);
        if(balanced)
            System.out.println("the expression " + m2 + " is balanced");
        else
            System.out.println("the expression " + m2 + " is not balanced");
    }
}