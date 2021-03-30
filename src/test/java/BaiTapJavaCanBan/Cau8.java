package BaiTapJavaCanBan;
/*
 * 8.	Viết chương trình nhập số tự nhiên N, nhập N phần tử của mảng a, xuất kết quả :
 */

public class Cau8 {
	java.util.Scanner sc;
	
	public int n;
	public Integer[] arr;

	public Cau8(java.util.Scanner sc) {
		this.sc = sc;
	}
	public void run() {
		System.out.println("\nTask 8:");
		System.out.print("Please insert a natural number: ");
		this.n = 0;
		try {
			this.n = Integer.parseInt(this.sc.next());
		} catch (NumberFormatException e) {
			System.err.print(e.getMessage());
		}
		if (this.n < 0) {
			System.out.println("N is not a natural number");
		} else {
			this.arr = new Integer[this.n];
			//nhap mang
			for(int i=0; i<this.n; i++) {
				System.out.print("Insert element "+(i+1)+": ");
				this.arr[i] = Integer.parseInt(this.sc.next());
			}
			this.a();
			this.b();
			this.c();
			this.d();
			this.e();
		}
	}

	
	public void a() {
		//a.	Xuat các phần tử của mảng a và tổng của chúng
		System.out.println("\nTask 8a:");
		System.out.print("--->Elements of array: ");
		int tonga= 0;
		for(int i=0; i<this.n; i++) {
			System.out.print(arr[i]+"\t");
			tonga+=this.arr[i];
		}
		System.out.print("\n--->Sum: "+tonga);
	}
	public void b(){
		//b.	Xuat các phần tử chẵn của mảng a và tổng của chúng
		System.out.println("\nTask 8b:");
		System.out.print("--->Even elements of array: ");		
		int tongb= 0;
		for(int i=0; i<n; i++) {
			if(arr[i]%2==0) {
				System.out.print(arr[i]+"\t");
				tongb+=this.arr[i];
			}
		}
		System.out.print("\n--->Sum: "+tongb);
	}
	public void c() {
		//c.	Xuat các phần tử lẻ của mảng a và tổng của chúng
		System.out.println("\nTask 8c:");
		System.out.print("--->Odd elements of array: ");		
		int tongc= 0;
		for(int i=0; i<n; i++) {
			if(arr[i]%2!=0) {
				System.out.print(arr[i]+"\t");
				tongc+=this.arr[i];
			}
		}
		System.out.print("\n--->Sum: "+tongc);
	}
	
	public void d() {
		//d.	Xuat các phần tử là số nguyên tố của mảng a và tổng của chúng
		System.out.println("\nTask 8d:");
		System.out.print("--->Prime elements of array: ");	
		int tongd= 0;
		for(int i=0; i<this.n; i++) {
			if(arr[i]>=2) {
				int dem=0;
				for (int j=2; j <= Math.sqrt(this.arr[i]); j++) {
					if (this.arr[i] % j == 0) {
						dem++;
					}
				}
				if(dem==0) {
					System.out.print(this.arr[i]+"\t");
					tongd+=this.arr[i];
				}
				
			}
		}
		System.out.print("\nSum: "+tongd);
	}
	public void e() {
		//e.	Thêm 1 phần tử mới vào mảng
		System.out.println("\nTask 8e:");
		System.out.print("Insert a new element: ");	
		int newE = 0;
		try {
			newE = Integer.parseInt(this.sc.next());
			this.arr = this.addX(newE);
			this.n++;
			System.out.print("New array: ");
			for(int i=0; i<this.n; i++) {
				System.out.print(this.arr[i]+"\t");
			}
		}catch(NumberFormatException e) {
			System.err.println(e.getMessage());
		}
	}
	public Integer[] addX(int x) 
    { 
        Integer newarr[] = new Integer[this.n + 1]; 
        for (int i = 0; i < this.n; i++) 
            newarr[i] = this.arr[i]; 
 
        newarr[n] = x; 
  
        return newarr; 
    } 
	public void f() {
		//f.	Xoá phần tử thứ k của mảng a
		System.out.println("\nTask 8f:");
		System.out.print("Insert index of element want to delete: ");
		try {
			int k = Integer.parseInt(this.sc.next());
			this.arr = this.delK(k);
			this.n--;
			System.out.print("The array after delete the k element: ");
			for(int i=0; i<this.n; i++) {
				System.out.print(this.arr[i]+"\t");
			}
		}catch(NumberFormatException e) {
			System.out.print("\n"+e.getMessage());
		}
		
	}
	public Integer[] delK(int k) 
    { 
        Integer newarr[] = new Integer[this.n - 1]; 
        for (int i = 0; i < this.n; i++) {
        	if(i!=k) {
        		newarr[i]= this.arr[i];
        	}
        }
        return newarr; 
    } 
	public void g() {
		//g.nhập 1 số x, kiểm tra x có trong mảng a không, 
		//nếu có thì trả về vị trí của x trong mảng a
		System.out.println("\nTask 8e:");
		System.out.print("Insert value of element: ");
		try {
			int value = Integer.parseInt(this.sc.next());
			int index = -1;
			for(int i=0; i<this.n;i++) {
				if(this.arr[i]==value) {
					index = this.arr[i];
					break;
				}
			}
			if(index!=-1) {
				System.out.println("The index of this value is: "+(index+1));
			}else {
				System.out.println("Cannot found!!!");
			}
		}catch(NumberFormatException e) {
			System.out.print(e.getMessage());
		}
		
		
	}

	

}
