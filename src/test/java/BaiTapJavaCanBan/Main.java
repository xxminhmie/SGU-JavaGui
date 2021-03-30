package BaiTapJavaCanBan;
/*
 * Le Ho Kim Minh
 * 3118410265
 * SGU - Java Thu 2 Tiet 678
 */

public class Main {
	java.util.Scanner sc;
	
	public Main(java.util.Scanner sc) {
		this.sc = sc;
	}
	public static void menu() {
		System.out.println();
		System.out.print("Choose a number from 1 to 9 or 0 to exit: ");
	}
	public void run() {
		int choose = -1;
		while(choose != 0) {
			menu();
			try {
				choose = Integer.parseInt(this.sc.next());
			}catch(NumberFormatException e) {
				System.err.println("\n"+e.getMessage());
			}
			switch (choose){
				case 0:{
					System.out.println("Thank you!!!");
					break;
				}
				case 1:{
					Cau1 cau1 = new Cau1(this.sc);
					cau1.run();
					break;
				}
				case 2:{
					Cau2 cau2 = new Cau2(this.sc);
					cau2.run();
					break;
				}
				case 3:{
					Cau3 cau3 = new Cau3(this.sc);
					cau3.run();
					break;
				}
				case 4:{
					Cau4 cau4 = new Cau4(this.sc);
					cau4.run();
					break;
				}
				case 5:{
					Cau5 cau5 = new Cau5(this.sc);
					cau5.run();
					break;
				}
				case 6:{
					Cau6 cau6 = new Cau6(this.sc);
					cau6.run();
					break;
				}
				case 7:{
					Cau7 cau7 = new Cau7(this.sc);
					cau7.run();
					break;
				}
				case 8:{
					Cau8 cau8 = new Cau8(this.sc);
					cau8.run();
					break;
				}
				case 9:{
					Cau9 cau9 = new Cau9(this.sc);
					cau9.run();
					break;
				}
			}
		}
	}
	public static void main(String[] args) {
		java.util.Scanner sc = new java.util.Scanner(System.in);
		Main main = new Main(sc);
		main.run();
	}

}
