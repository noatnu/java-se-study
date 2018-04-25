

wait()
notify()
此两种方法都必须获得该对象的对象级别的锁
否则抛出Exception in thread "main" java.lang.IllegalMonitorStateException

example DTest1.java 抛出异常的原因是没有 "对象监视器"

