package BaiTapJavaCanBan;
/*
 * 1.	Viết chương trình nhập 2 số nguyên, xuất tổng, hiệu, tích, thương.
 */
public class Cau1 {
	java.util.Scanner sc;
	
	public Cau1(java.util.Scanner sc) {
		this.sc = sc;
	}
	public void run() {
		System.out.println("\nTask 1: ");
		System.out.println("Please insert 2 integer number: ");
		int a = 0;
		int b = 0;

		System.out.print("Insert the first integer number: ");
		a = Integer.parseInt(this.sc.next());

		System.out.print("Insert the second integer number: ");
		b = Integer.parseInt(this.sc.next());

		System.out.println("Sum: " + (a + b));
		System.out.println("Minus: " + (a - b));
		System.out.println("Multiple: " + (a * b));
		System.out.println("Devide: " + a / b);
	}
}
