package cn.threadcommunication.p_r.p_more_c;

import java.util.ArrayList;
import java.util.List;

public class MyStack {
	private List list = new ArrayList();

	synchronized public void push() {
		try {
			//假死行为
//			if (list.size() == 1) {
//				this.wait();
//			}
			while (list.size() == 1){
				this.wait();
			}
			list.add("anyString=" + Math.random());
			this.notify();
			System.out.println("push=" + list.size());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	synchronized public String pop() {
		String returnValue = "";
		try {
			//假死行为
//			if (list.size() == 0) {
//				System.out.println("pop操作中的："
//						+ Thread.currentThread().getName() + " 线程呈wait状态");
//				this.wait();
//			}
			while (list.size()==0){
				System.out.println("pop操作中的："
						+ Thread.currentThread().getName() + " 线程呈wait状态");
				this.wait();
			}
			returnValue = "" + list.get(0);
			list.remove(0);
			this.notify();
			System.out.println("pop=" + list.size());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return returnValue;
	}
}
