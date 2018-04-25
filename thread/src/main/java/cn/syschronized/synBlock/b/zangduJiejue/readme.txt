listSize=1 打印的结果
//模拟 只能放一个,添加一个之后就不能够添加元素了
if (list.getSize() < 1) {
    Thread.sleep(2000);
    list.add(data);
}
对MyService进行修改 修改如下
synchronized (list){
    //模拟 只能放一个,添加一个之后就不能够添加元素了
    if (list.getSize() < 1) {
        Thread.sleep(2000);
        list.add(data);
    }
}
加了一个同步锁(list)就防止了多个线程进入的危险