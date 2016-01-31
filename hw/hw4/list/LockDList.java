package list;

public class LockDList extends DList{
	public void lockNode(DListNode node) {
		LockDListNode theNode = (LockDListNode) node;
		theNode.locked = true;
	}
	
	@Override
	protected DListNode newNode(Object item, DListNode prev, DListNode next) {
		return new LockDListNode(item, prev, next);
	}
	@Override
	public void remove(DListNode node) {
		if(((LockDListNode) node).locked) {
			return;
		}
		super.remove(node);
	}
}
