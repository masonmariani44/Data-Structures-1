import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;


@SuppressWarnings("unchecked")
public class TreeTest {
    private static Tree tree;
    public static void main(String[] args) {
	tree = new Tree();
	String fn = args[0] + "_input" + Integer.parseInt(args[1]) + ".txt";
	BufferedReader br;
	try {
	    br = new BufferedReader(new FileReader(fn));
	    String cmd = br.readLine();
	    while(cmd != null) {
		processCmd(cmd);
		cmd = br.readLine();
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private static void processCmd(String cmd) {
	String[] split = cmd.split(" ");
	if(split[0].equals("put")) { 
	    tree.put(Integer.parseInt(split[1]), split[2]);
	}
	else if(split[0].equals("get")) {
	    System.out.println(tree.get(Integer.parseInt(split[1])));
	} else if(split[0].equals("contains")) {
	    System.out.println(tree.contains(Integer.parseInt(split[1])));
	} else if(split[0].equals("isEmpty")) {
	    System.out.println(tree.isEmpty());
	} else if(split[0].equals("size")) {
	    if(split.length <= 1) {
		System.out.println(tree.size());
	    } else {
		System.out.println(tree.size(Integer.parseInt(split[1])));
	    }
	} else if(split[0].equals("height")) {
	    if(split.length <= 1) {
		System.out.println(tree.height());
	    } else {
		System.out.println(tree.height(Integer.parseInt(split[1])));
	    }
	}
    }
}
