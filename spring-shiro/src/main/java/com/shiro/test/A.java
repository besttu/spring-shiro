package com.shiro.test;

public class A {

	private static int c;

	public static void main(String[] args) {
	}

	static class B {
		B() {
			System.out.println(c);
			
		}
	}

}
