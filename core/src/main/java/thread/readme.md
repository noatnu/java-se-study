#Java多线程

## 前提:
>JAVA线程现在的实现是基于操作系统原生线程模型来实现的。因此，现在操作系统支持怎样的线程模型，在很大程度上决定了JAVA虚拟机的线程是怎样映射的。这点在不同的平台上没有办法达成一致。
 对于Sun JDK来说，它的Windows版和Linux版都是使用一对一的线程模型实现的，一条Java线程就映射到一条轻量级进程中，因为Windows和Linux系统提供的线程模型就是一对一的。


## 一:介绍



//https://blog.csdn.net/ftell/article/details/79353571
//https://www.runoob.com/java/java-multithreading.html
//https://blog.csdn.net/qq_34337272/article/details/79640870