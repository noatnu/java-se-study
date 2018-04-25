package cn.syschronized.synBlock.b.zangduJiejue;

public class MyService {

	public MyOneList addServiceMethod(MyOneList list, String data) {
		try {
			synchronized (list){
				//模拟 只能放一个,添加一个之后就不能够添加元素了
				if (list.getSize() < 1) {
					Thread.sleep(2000);
					list.add(data);
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return list;
	}

}
