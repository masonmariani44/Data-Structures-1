#!/bin/bash

javac *.java

echo "Running Test 1..."
java TreeTest.java test 0 > output0.txt
diff output0.txt test_rbt_output0.txt

echo "Running Test 2..."
java TreeTest.java test 1 > output1.txt
diff output1.txt test_rbt_output1.txt

echo "Running Test 3..."
java TreeTest.java test 2 > output2.txt
diff output2.txt test_rbt_output2.txt

echo "Running Test 4..."
java TreeTest.java test 3 > output3.txt
diff output3.txt test_rbt_output3.txt




