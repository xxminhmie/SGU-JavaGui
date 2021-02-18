package BaiTapJavaCanBan;
/*
 * 5.	Viết chương trình nhập số nguyên N, kiểm tra  và xuất kết quả N là số âm/zero/dương
 */

public class Cau5 {
	java.util.Scanner sc;

	public Cau5(java.util.Scanner sc) {
		this.sc = sc;
	}
	
	public void run() {
		System.out.println("\nTask 5: ");
		System.out.println("Please insert a integer number: ");
		int n = 0;
		try {
			n = Integer.parseInt(this.sc.next());
		} catch (NumberFormatException e) {
			System.err.print(e.getMessage());
		}
		if (n == 0) {
			System.out.println("N=0");
		} else if (n < 0) {
			System.out.println("N is negative number ");
		} else {
			System.out.println("N is positive number ");
		}
	}

}
