/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return empty list if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
public class NestedIterator implements Iterator<Integer> {
    Stack<Iterator<NestedInteger>> st;
    Integer nextEle;

    public NestedIterator(List<NestedInteger> nestedList) {
        st = new Stack();
        st.add(nestedList.iterator());
        nextEle = getNextElement();
        
    }

    private Integer getNextElement() {
        Integer result = null;
        while(result==null) {
            if(st.peek().hasNext()){
                NestedInteger ele = st.peek().next();
                if(ele.isInteger()) {
                    result = ele.getInteger();
                } else {
                    st.add(ele.getList().iterator());
                }
            } else{
                st.pop();
                if(st.isEmpty()) break;
            }
        }
        return result;
    }

    @Override
    public Integer next() {
        Integer curr = nextEle;
        nextEle = getNextElement();
        return curr;
    }

    @Override
    public boolean hasNext() {
        if(nextEle!=null) return true;
        return false;
        
    }
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */