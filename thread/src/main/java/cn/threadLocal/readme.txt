

变量值的共享可以使用 public static 变量的形式，所有的线程都使用同一个public static变量。

class ThreadLocal主要解决的就是每个线程绑定自己的值，可以将ThreadLocal类 比喻成 全局存放安全数据的盒子，
盒子可以存储每个线程的私有数据