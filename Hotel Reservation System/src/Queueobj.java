
public class Queueobj {
	private int capacity;
	static String QueueArray[];
	int front=0;
	int rear=-1;
	int currentSize=0;


	public Queueobj(int SizeofQueue){
		this.capacity=SizeofQueue;
		QueueArray=new String[this.capacity];
	}
	public void addQueue(String customerName) {

		if (Fullcheck()) { //method to check if queue array is full..return true if full.
			System.out.println("Queue is full. Unable to add customer: "+customerName);
		} else {
			rear++;
			if(rear == capacity-1){ //when queue has reached the end..queue is added to the start by making rear(0)
				rear = 0;
			}
			QueueArray[rear] = customerName; //adding customer name to queue array  
			currentSize++; // increment array current size var
			System.out.println(customerName+ " is added to Queue.");
		}
	}

	public void removeQueue() { // check if the Queue is empty..if empty returns true.
		if (Emptycheck()) {
			System.out.println("Queue is Empty");
		} else {
			front++;
			if(front == capacity-1){ //check if array is equal to total capacity and output the last
				System.out.println(QueueArray[front-1]);
				front = 0;
			} else {
				System.out.println(QueueArray[front-1]);
			}
			currentSize--;
		}
	}
	
	public void removeQueue(String CustomerName) { // check if the Queue is empty..if empty returns true.
		if (Emptycheck()) {
			System.out.println("Queue is Empty");
		} else {
			for(int i=0;i<QueueArray.length;i++){
				if(QueueArray[i].equalsIgnoreCase(CustomerName)){
					QueueArray[i].equals(null);
					break;
				}
			}			
			front++;
			if(front == capacity-1){ //check if array is equal to total capacity and output the last
				System.out.println(QueueArray[front-1]);
				front = 0;
			}
			currentSize--;
		}
	}

	public boolean Fullcheck(){
		boolean status = false;
		if (currentSize == capacity){
			status = true;            
		}
		return status;
	}

	public boolean Emptycheck(){
		boolean status = false;
		if (currentSize == 0){
			status = true;
		}
		return status;
	}


}
