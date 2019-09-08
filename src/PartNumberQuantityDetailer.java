import java.util.Arrays;
import java.util.Comparator;
 
public class PartNumberQuantityDetailer {
    // initialize a two dimensional array
    static Integer[][] itemIdAndQty = new Integer[5][2];
 
    public static void main(String[] args) {
	// initialize array values
	itemIdAndQty[0][0] = 1234;
	itemIdAndQty[0][1] = 46;
	itemIdAndQty[1][0] = 5443;
	itemIdAndQty[1][1] = 564;
	itemIdAndQty[2][0] = 362;
	itemIdAndQty[2][1] = 24;
	itemIdAndQty[3][0] = 6742;
	itemIdAndQty[3][1] = 825;
	itemIdAndQty[4][0] = 347;
	itemIdAndQty[4][1] = 549;
	System.out.println("Before sorting");
	// show the contents of array
	displayArray();
	// sort the array on item id(first column)
	Arrays.sort(itemIdAndQty, new Comparator<Integer[]>() {
	   	@Override
                //arguments to this method represent the arrays to be sorted   
		public int compare(Integer[] o1, Integer[] o2) {
                    //get the item ids which are at index 0 of the array
	            Integer itemIdOne = o1[0];
	            Integer itemIdTwo = o2[0];
		    // sort on item id
		    return itemIdOne.compareTo(itemIdTwo);
		}
	});
	// display array after sort
	System.out.println("After sorting on item id in ascending order");
	displayArray();
	// sort array on quantity(second column)
	Arrays.sort(itemIdAndQty, new Comparator<Integer[]>() {
		@Override
		public int compare(Integer[] o1, Integer[] o2) {
	            Integer quantityOne = o1[1];
		    Integer quantityTwo = o2[1];
		    // reverse sort on quantity
		    return quantityOne.compareTo(quantityTwo);
		}
	});
	// display array after sort
	System.out.println("After sorting on quantity in ascending order");
	displayArray();
 
    }
 
    private static void displayArray() {
	System.out.println("-------------------------------------");
	System.out.println("Item id\t\tQuantity");
	for (int i = 0; i < itemIdAndQty.length; i++) {
		Integer[] itemRecord = itemIdAndQty[i];
		System.out.println(itemRecord[0] + "\t\t" + itemRecord[1]);
	}
	System.out.println("-------------------------------------");
    }
}