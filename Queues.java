/*
 * Susan Hatem
 * PP1016
 * https://github.com/ProgrammerSusan/SwitchingQueues
 */

public class Queues {

	/*  the data structure   */ 
	private int  head, tail; // indexes into n[]
	private int size;
	private Customer [] n;
	private boolean server;
	
	public Queues(int s, boolean ser) {
		size = s;
		n = new Customer [s];
		server = ser;
	}
	
	/* initialize queue pointers */
	public void initialize() {
		head = tail = 0;
	}
	
	/* returns head and tail values */
	public Customer getHead() {
		return n[head];
	}
	
	public Customer getTail() {
		return n[tail];
	}

	/* test for full */
	public boolean isFull() {
	return head == tail;
	}
	
	public boolean getServer() {
		return server;
	}
	
	public void status() {
		if(getServer()) {
			System.out.print("Server is busy");
		}
		else {
			System.out.print("Server is idle");
		}
	}
	
	public void changeStatus(boolean t) {
		this.server = t;
	} 

	/* test for empty */
	public boolean isEmpty() {
		return head == (tail + 1) % size;
	}
	
	/* other test for empty */
	public boolean empty() {
		return (head == 0) && (tail == 0) || head == tail;
	}

	/* insert x at tail */
	public void insert(Customer x) {
		try { 
			if ( !isEmpty() ) {
				n[tail] = x;
				tail = (tail + 1) % size;
			}
		}
		catch(Exception e) {
			System.out.println("Error");
		}
	}
	
	/* delete from head */
	public Customer delete() {
		Customer h = n[head];
		try {
			if ( !isEmpty() ) {
				n[head] = n[(head + 1) % size];
				head = (head + 1) % size;
			}
		}
		catch(Exception e) {
			System.out.println("Error");
		}
		return h;
    }
	
	/* updates wait time of each customer in the Queue */
	public void updateQueue() {
		if ( !isEmpty() ) {
			for (int i = head; i != tail && i < size; i++) {
				n[i].update(1);
			}
			System.out.println();
		}
		else {
			System.out.print("Empty Queue");
		}
	}
	
	/* print from head to tail */
	/* note, this is not a queue operation, but is handy for debugging */
	public void printQueue() {
		if ( !isEmpty() ) {
			for (int i = head; i != tail && i < size; i++) {
				System.out.print(n[i] + " ");
			}
			System.out.println();
		}
		else {
			System.out.print("Empty Queue");
		}
	}

}
