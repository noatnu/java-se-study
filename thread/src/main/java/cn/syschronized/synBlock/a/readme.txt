
线程名称为：A在1521794711601进入同步块
线程名称为：A在1521794714601离开同步块
线程名称为：B在1521794714601进入同步块
线程名称为：B在1521794717602离开同步块

关键代码 anyString必须是对象实例变量
    private String anyString = new String();
	public void setUsernamePassword(String username, String password) {
		try {
			synchronized (anyString) {
				System.out.println("线程名称为：" + Thread.currentThread().getName()
						+ "在" + System.currentTimeMillis() + "进入同步块");
				usernameParam = username;
				Thread.sleep(3000);
				passwordParam = password;
				System.out.println("线程名称为：" + Thread.currentThread().getName()
						+ "在" + System.currentTimeMillis() + "离开同步块");
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
