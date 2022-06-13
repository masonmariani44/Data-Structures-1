import java.util.ArrayList;
import java.util.Arrays;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MinPQTest {
    private static MinPQ<String> pq;
    private static ArrayList<String> names;
    private static int[] test2Vals = new int[]{7, 8, 0, 1, 9, 10, 5, 8, 1, 0};
    private static int[] test2Exp1 = new int[]{0, 1, 7, 8, 9};
    private static int[] test2Exp2 = new int[]{0, 0, 5, 1, 1, 10, 7, 8, 8, 9};

    public static void main(String[] args) {
	initialize();
	double score = 0.0;
	score += test1();
	score += test2();
	System.out.println("Total Score: " + score);
    }

    private static void initialize() {
	pq = new MinPQ<String>();
	names = new ArrayList<String>();
	getNames("names.txt");
    }

    //test 1 tests the functionality of the priority queue
    private static double test1() {
	System.out.println("***** START of Test 1 *****");
	String s = null;
	String act = null;
	String exp = null;
	double score = 0.0;

	//adding elements
	for(int i = 0; i < names.size(); i++) {
	    s = names.get(i);
	    pq.insert(s);
	}
	String[] nameArray = sortList();
	int min = 0;

	//checking getMin
	System.out.println("Checking getMin...");
	try {
	    act = pq.getMin();
	} catch(Exception e) {
	    e.printStackTrace();
	}
	exp = nameArray[min];
	if(act.equals(exp))
	    score+=1.0;//score = 1.0
	else {
	    System.out.println("Minimum value is not correct.");
	    System.out.println("Expected: " + exp);
	    System.out.println("Actual: " + act);
	}

	//checking size
	int a = pq.size();
	int ex = nameArray.length;
	if(a == ex)
	    score += 1.0; //score = 2.0
	else
	    System.out.println("The PQ size is incorrect.");

	//checking isEmpty
	if(!pq.isEmpty())
	    score += 1.0; //score = 3.0
	else
	    System.out.println("The PQ should not be empty.");

	//checking delMin
	boolean passed = true;
	while(!pq.isEmpty()) {
	    try {
		act = pq.delMin();
	    } catch(Exception e) {
		e.printStackTrace();
	    }
	    exp = nameArray[min];
	    min++;
	    if(!act.equals(exp)) {
		System.out.println("The minimum elements do not match.");
		System.out.println("Expected: " + exp);
		System.out.println("Actual: " + act);
		passed = false;
		break;
	    }
	}
	if(passed)
	    score += 5.0; //score = 8.0

	//checking delMin on an empty queue
	System.out.println("Checking delMin on empty queue...");
	try {
	    act = pq.delMin();
	} catch (EmptyQueueException e) {
	    score += 1.0; //score = 9.0
	}

	//checking getMin on empty queue
	System.out.println("Checking getMin on empty queue...");
	try {
	    act = pq.getMin();
	} catch (EmptyQueueException e) {
	    score += 1.0; //score 10.0
	}

	//checking size and isEmpty on empty queue
	System.out.println("Checking size and isEmpty on empty queue...");
	if(pq.size() == 0)
	    score += 0.5; //score = 10.5
	else 
	    System.out.println("Size should be 0.");
	if(pq.isEmpty())
	    score += 0.5; //score = 11.0
	else
	    System.out.println("PQ should be empty.");

	System.out.println("***** END of Test 1 *****");
	System.out.println("Test 1 score: " + score);
	return score;
    }

    //Test 2 tests the underlying array, including the resizing
    private static double test2() {
	System.out.println("***** START of Test 2 *****");
	double score = 0.0;
	MinPQ<Integer> q = new MinPQ<Integer>(5);
	for(int i = 0; i < 5; i++)
	    q.insert(test2Vals[i]);
	Comparable[] array = q.getArray();
	score += checkArrays(array, test2Exp1); //score = 2.5
	for(int i = 5; i < test2Vals.length; i++)
	    q.insert(test2Vals[i]);
	array = q.getArray();
	score += checkArrays(array, test2Exp2); //score = 5.0
	for(int i = 0; i < 7; i++) {
	    try {
		q.delMin();
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	}
	array = q.getArray();
	if(array.length != 10)
	    System.out.println("Array size is incorrect. Should be 10.");
	else
	    score += 0.5; //score = 5.5
	if(array[0].equals(8) && array[1].equals(9) && array[2].equals(10) && array[3] == null)
	    score += 1.0; //score = 6.5
	else {
	    System.out.println("Array is incorrect. Should be [8, 9, 10, null...].");
	    printArray(array);
	}
	try {
	    q.delMin();
	} catch (Exception e) {
	    e.printStackTrace();
	}
	array = q.getArray();
	if(array.length != 5)
	    System.out.println("Array size is incorrect. Should be 5.");
	else
	    score += 1.0; //score = 7.5
	if(array[0].equals(9) && array[1].equals(10) && array[2] == null)
	    score += 1.5; //score = 9.0
	else {
	    System.out.println("Array is incorrect. Should be [9, 10, null, null, null].");
	    printArray(array);
	}
	System.out.println("***** END of Test 2 *****");
	System.out.println("Test 2 Score: " + score);
	return score;
    }

    private static void printArray(Comparable[] array) {
	System.out.println();
	for(int i = 0; i < array.length; i++)
	    System.out.print("|" + (Integer)array[i] + "|");
	System.out.println();
    }

    private static double checkArrays(Comparable[] act, int[] exp) {
	double score = 0.0;
	if(act.length != exp.length) {
	    System.out.println("Your array is not the right size.");
	    System.out.println("Expected: " + exp.length);
	    System.out.println("Actual: " + act.length);
	} else
	    score += 0.5;
	boolean passed = true;
	for(int i = 0; i < act.length; i ++) {
	    if(!((Integer)act[i]).equals(exp[i])) {
		System.out.println("The values at index " + i + " do not match.");
		System.out.println("Expected: " + exp[i]);
		System.out.println("Actual: " + act[i]);
		passed = false;
		break;
	    }
	}
	if(passed)
	    score += 2.0;
	return score;
    }

    private static String[] sortList() {
	String[] array = new String[names.size()];
	names.toArray(array);
	Arrays.sort(array);
	return array;
    }

    //gets names from a file
    private static void getNames(String fn) {
	BufferedReader br;
	try {
	    br = new BufferedReader(new FileReader(fn));
	    String line = br.readLine();
	    while(line != null) {
		String[] split = line.split(",");
		if(split.length >= 1)
		    names.add(split[0]);
		line = br.readLine();
	    }
	    br.close();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
}
	
    
