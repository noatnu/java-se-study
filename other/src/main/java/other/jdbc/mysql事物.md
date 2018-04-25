
# mysql脏读、不可重复读、幻读（虚读）

> 事务的特性：经常作为面试
<ul>
<li>原子性：指处于同一个事务中的多条语句是不可分割的。</li>
<li>
一致性：事务必须使数据库从一个一致性状态变换到另外一个一致性状态。比如，转账，转账前两个账户余额之和为2k，转账之后也应该是2K。
</li>
<li>
隔离性：指多线程环境下，一个线程中的事务不能被其他线程中的事务打扰
</li>
<li>
持久性：事务一旦提交，就应该被永久保存起来。
</li>
</ul>

> 如果不考虑事务的隔离性，会出现以下问题：
<ol>
<li>脏读：指一个线程中的事务读取到了另外一个线程中未提交的数据。</li>
<li>不可重复读：指一个线程中的事务读取到了另外一个线程中提交的update的数据。</li>
<li>虚读：指一个线程中的事务读取到了另外一个线程中提交的insert的数据。</li>
</ol>


|      事务隔离级别       | 脏读 | 不可重复读 | 幻读 |
|-----------|---|---------|----|
|读未提交（read-uncommitted）|是|是|是|
|不可重复读（read-committed）|否|是|是|
|可重复读（repeatable-read）|否|否|是|
|串行化（serializable）|否|否|否|

+ jdbc Connection
* TRANSACTION_SERIALIZABLE 指示不可以发生脏读、不可重复读和虚读的常量。
* TRANSACTION_REPEATABLE_READ 指示不可以发生脏读和不可重复读的常量；虚读可以发生。
* TRANSACTION_READ_UNCOMMITTED 指示可以发生脏读 (dirty read)、不可重复读和虚读 (phantom read) 的常量。
* TRANSACTION_READ_COMMITTED 指示不可以发生脏读的常量；不可重复读和虚读可以发生。

