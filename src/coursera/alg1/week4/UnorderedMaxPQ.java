package coursera.alg1.week4;

public class UnorderedMaxPQ {

	private int[] array;
	
	private int n=0;;
	
	public UnorderedMaxPQ(int capacity){
		this.array=new int[capacity];
	}
	
	public boolean isEmpty(){
		return n==0;
	}
	
	public void insert(int val){
		array[n++]=val;
	}
	
	public int delMax(){
		int max=0;
		for(int i=1;i<n;i++){
			if(array[max]<array[i]){
				max=i;
			}
		}
		
		int temp=array[max];
		array[max]=array[n-1];
		array[n-1]=temp;
		return array[--n];
	}

}
