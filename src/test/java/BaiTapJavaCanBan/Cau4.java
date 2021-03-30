package BaiTapJavaCanBan;
/*
 * 4.	Viết chương trình nhập số nguyên N, kiểm tra  và xuất kết quả N là số chằn/lẻ
 */

public class Cau4 {
	java.util.Scanner sc;

	public Cau4(java.util.Scanner sc) {
		this.sc = sc;
	}
	public void run() {
		System.out.println("\nTask 4: ");
		System.out.println("Please insert a integer number: ");
		int n = 0;
		try {
			n = Integer.parseInt(this.sc.next());
		} catch (NumberFormatException e) {
			System.err.print(e.getMessage());
		}
		if (n == 0) {
			System.out.println("N=0");
		} else if (n % 2 == 0) {
			System.out.println("N is even number ");
		} else {
			System.out.println("N is odd number ");
		}
	}

}
