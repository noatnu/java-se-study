package cn.threadcommunication.p_r.p_more_c;

public class Run {
	public static void main(String[] args) throws InterruptedException {
		MyStack myStack = new MyStack();

		P p = new P(myStack);

		C r1 = new C(myStack);
		C r2 = new C(myStack);
		C r3 = new C(myStack);
		C r4 = new C(myStack);
		C r5 = new C(myStack);

		P_Thread pThread = new P_Thread(p);
		pThread.start();

		C_Thread cThread1 = new C_Thread(r1);
		C_Thread cThread2 = new C_Thread(r2);
		C_Thread cThread3 = new C_Thread(r3);
		C_Thread cThread4 = new C_Thread(r4);
		C_Thread cThread5 = new C_Thread(r5);
		cThread1.start();
		cThread2.start();
		cThread3.start();
		cThread4.start();
		cThread5.start();
		/**
		 * Exception in thread "Thread-2" java.lang.IndexOutOfBoundsException: Index: 0, Size: 0
		 at java.util.ArrayList.rangeCheck(ArrayList.java:657)
		 at java.util.ArrayList.get(ArrayList.java:433)
		 at cn.threadcommunication.p_r.p_more_c.MyStack.pop(MyStack.java:30)
		 at cn.threadcommunication.p_r.p_more_c.C.popService(C.java:13)
		 at cn.threadcommunication.p_r.p_more_c.C_Thread.run(C_Thread.java:15)

		 */
	}

}
