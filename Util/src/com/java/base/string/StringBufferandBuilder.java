package com.java.base.string;

/**
 * @author <a href="mailto:zhuhao@189read.com.">���</a> 2015��8��18������2:49:29
 * @version 1.0
 */
public class StringBufferandBuilder {
	public StringBuffer contents = new StringBuffer();
	public StringBuilder sbu = new StringBuilder();

	public void log(String message) {
		for (int i = 0; i < 10; i++) {
			/*
			 * contents.append(i); contents.append(message);
			 * contents.append("\n");
			 */
			contents.append(i);
			contents.append("\n");
			sbu.append(i);
			sbu.append("\n");
		}
	}

	public void getcontents() {
		// System.out.println(contents);
		System.out.println("start print StringBuffer");
		System.out.println(contents);
		System.out.println("end print StringBuffer");
	}

	public void getcontents1() {
		// System.out.println(contents);
		System.out.println("start print StringBuilder");
		System.out.println(sbu);
		System.out.println("end print StringBuilder");
	}

	public static void main(String[] args) throws InterruptedException {
		StringBufferandBuilder ss = new StringBufferandBuilder();
		runthread t1 = new runthread(ss, "love");
		runthread t2 = new runthread(ss, "apple");
		runthread t3 = new runthread(ss, "egg");
		t1.start();
		t2.start();
		t3.start();
		t1.join();
		t2.join();
		t3.join();
	}

}

class runthread extends Thread {
	String message;
	StringBufferandBuilder buffer;

	public runthread(StringBufferandBuilder buffer, String message) {
		this.buffer = buffer;
		this.message = message;
	}

	public void run() {
		while (true) {
			buffer.log(message);
			// buffer.getcontents();
			buffer.getcontents1();
			try {
				sleep(5000000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
