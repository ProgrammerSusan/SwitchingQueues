/*
 * Susan Hatem
 * PP1016
 * https://github.com/ProgrammerSusan/SwitchingQueues
 */
import java.util.Random;

public class Customer {
	
	public static void main(String [] args) {
		
		//initialize variables
		int tick = 1;
		Random r = new Random();
		Random s1 = new Random(5);
		Random s2 = new Random(5);
		Customer n;
		
		//initialize queue
		Queues one = new Queues(50, false);
		Queues two = new Queues(50, false);
		
		
		//initialize head and tail pointers
		one.initialize();
		two.initialize();
		
		//Initialize time it takes server to complete
		int server1 = 0;
		int server2 = 0;
		
				
		//begin loop to count ticks
		while(tick <= 20) {
		
			//Incriments wait time of customers in Queue
			if(!(one.empty()) ) {
				one.updateQueue();
			}
			if(!(two.empty())) {
				two.updateQueue();
			}
	
			/* Sets rate at which customers are entering the Queue */
			int onegen = getPoissonRandom(0.25);
			int twogen = getPoissonRandom(0.25);
			
			/* sets how long servers will take to perform service */
			if(server1 == -1) {
				server1 = s1.nextInt(5) + 1;
				one.changeStatus(false);
			}
			if(server2 == -1) {
				server2 = s2.nextInt(5) + 1;
				two.changeStatus(false);
			}
			
			/*Shows server status*/
			System.out.print("Server one: ");
			one.status();
			System.out.print("\nServer two: ");
			two.status();
			
			System.out.println();

			/* Adds customer object to the Queue */
			if(onegen == 1) {
				n = new Customer(r.nextInt(50), false, 0, 0, false);
				int test = r.nextInt(2);
				/* Switches Customer */
				if(test == 0) {
					n.switched(true);
					two.insert(n);
				}
				one.insert(n);
			}
			
			/* Adding customer to second Queue */
			if(twogen == 1) {
				two.insert(n = new Customer(r.nextInt(30), false, 0, 0, false));
			}
			
			/* Customers in each Queue */
			System.out.println("\nTick #" + tick + " Customers in queue one: ");
				one.printQueue();
			
			System.out.println("\nTick #" + tick + " Customers in queue two: ");
				two.printQueue();
		
			System.out.println();
		
			/*Removing customers getting serviced*/
			if(!(one.getServer()) && !(one.empty())) {
				Customer d = one.delete();
				int m = d.getID();
				
				if(!(d.getSwitch())) {
					System.out.println("Server one start service on customer " + d.getID());
					one.changeStatus(true);
				}
				if(!(one.isFull())) {
					d = one.delete();
					System.out.println("Server one start service on customer " + d.getID());
					one.changeStatus(true);
				}
				two.searchQueue(m);
			}
			else if(one.empty() && server1 == 0) {
				server1 = 0;
				one.changeStatus(false);
			}
			else {
				server1--;
			}
			
			if(!(two.getServer()) && !(two.empty())) {
				Customer f = two.delete();
				System.out.println("Server two start service on customer " + f.getID());
				two.changeStatus(true);
			}
			else if(two.empty() && server2 == 0) {
				server2 = 0;
				two.changeStatus(false);
			}
			else {
				server2--;
			}

			/* Incrementing tick */
			tick++;
			
		}
		
		
		
	}
	
	private int id, time, change;
	private boolean done, swit;
	
	public Customer(int i, boolean d, int t, int c, boolean s) {
		id = i;
		done = d;
		time = t;
		change = c;
		swit = s;
	}
	
	/*accessor methods*/
	public int getID() {
		return id;
	}
	
	public int getTime() {
		return time;
	}
	
	public int getChange() {
		return change;
	}
	
	public boolean getDone() {
		return done;
	}
	public boolean getSwitch() {
		return swit;
	}
	
	/* updates time - if time without switch is 0 that means the customer did not switch*/
	public void update(int m) {
		if (this.swit) {
			this.time = time + m;
			this.change = change + m;
		}
		else {
			this.time = time + m; 
		}
	}
	
	
	public void switched(boolean t) {
		this.swit = t;
	}
	
	public void isDone(boolean t) {
		this.done = t;
	}

	public static int getPoissonRandom(double mean) {
	     Random r = new Random();			
	     double L = Math.exp(-mean);
	     int k = 0;
	     double p = 1.0;
	     do {
	         p = p * r.nextDouble();
	         k++;
	     } while (p > L);
	     return k - 1;
	 }
	
	public String toString() {
		return "\nCustomer: " + id + " Wait time: " + time + " Wait time without switch: " + change;
	}

}
