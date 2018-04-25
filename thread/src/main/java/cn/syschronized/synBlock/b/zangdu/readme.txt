脏读出现了
虽然 这加上了synchronized但是还是出现了脏读
public class MyOneList {

	private List list = new ArrayList();

	synchronized public void add(String data) {
		list.add(data);
	};

	synchronized public int getSize() {
		return list.size();
	};

}
原因出在MyService
线程MyThread1和MyThread2都进入MyService,由于MyService没有枷锁就出现了两个线程进入同一个对象的情况
MyService 代码逻辑上只能够添加一个元素
if (list.getSize() < 1) {
    Thread.sleep(2000);
    list.add(data);
}
