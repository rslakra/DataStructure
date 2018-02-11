/******************************************************************************
 * Copyright (C) Devamatre Inc 2009-2018
 * 
 * This code is licensed to Devamatre under one or more contributor license 
 * agreements. The reproduction, transmission or use of this code or the 
 * snippet is not permitted without prior express written consent of Devamatre. 
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the license is distributed on an "AS IS" BASIS, WITHOUT 
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied and the 
 * offenders will be liable for any damages. All rights, including  but not
 * limited to rights created by patent grant or registration of a utility model 
 * or design, are reserved. Technical specifications and features are binding 
 * only insofar as they are specifically and expressly agreed upon in a written 
 * contract.
 * 
 * You may obtain a copy of the License for more details at:
 *      http://www.devamatre.com/licenses/license.txt.
 *      
 * Devamatre reserves the right to modify the technical specifications and or 
 * features without any prior notice.
 *****************************************************************************/
package com.rslakra.testcases;

import com.rslakra.java.Node;
import com.rslakra.java.datastructure.LinkedList;

/**
 * 
 * @author Rohtash Singh Lakra
 * @date 11/03/2016 04:45:23 PM
 *
 */
public class TestLinkedList {
	
	/**
	 * 
	 * @param type
	 * @return
	 */
	private final static LinkedList<Integer> createIntegerLinkedList() {
		LinkedList<Integer> list = new LinkedList<Integer>();
		Node<Integer> second = new Node<Integer>(2);
		list.push(new Node<Integer>(1));
		list.append(second);
		list.push(new Node<Integer>(0));
		list.append(new Node<Integer>(3));
		list.insertAfter(second, new Node<Integer>(4));
		list.removeAt(2);
		return list;
	}
	
	/**
	 * 
	 * @param type
	 * @return
	 */
	private final static LinkedList<String> createStringLinkedList() {
		LinkedList<String> list = new LinkedList<String>();
		list.push(new Node<String>("One"));
		Node<String> second = new Node<String>("Two");
		list.append(second);
		list.push(new Node<String>("Zero"));
		list.append(new Node<String>("Three"));
		list.insertAfter(second, new Node<String>("Four"));
		return list;
	}
	
	/**
	 * Lists the linked list.
	 */
	public void printLinedList() {
		LinkedList<Integer> intLinkedList = createIntegerLinkedList();
		System.out.println(intLinkedList);
		System.out.println("Size:" + intLinkedList.size());
		
		LinkedList<String> stringLinkedList = createStringLinkedList();
		System.out.println("Size:" + stringLinkedList.size());
		System.out.println("Size with recursion:" + stringLinkedList.size(stringLinkedList.getHead()));
		System.out.println(stringLinkedList);
	}
	
	/**
	 * 
	 */
	public void removeNodes() {
		LinkedList<Integer> intLinkedList = createIntegerLinkedList();
		System.out.println("Size:" + intLinkedList.size());
		System.out.println(intLinkedList);
		
		// remove
		intLinkedList.removeFirst();
		intLinkedList.removeFirst();
		System.out.println(intLinkedList);
		
		// remove
		intLinkedList.remove(1);
		System.out.println(intLinkedList);
	}
	
	/**
	 * 
	 */
	public void findNodes() {
		LinkedList<Integer> list = createIntegerLinkedList();
		System.out.println(list);
		
		System.out.println("contain 3:" + list.contain(3));
		System.out.println("contain 16:" + list.contain(16));
		System.out.println("contain 2:" + list.contain(2));
		System.out.println();
		
		// contain
		System.out.println("contain using recursion 2:" + list.contain(list.getHead(), 2));
		System.out.println("contain using recursion 10:" + list.contain(list.getHead(), 10));
		System.out.println("contain using recursion 4:" + list.contain(list.getHead(), 4));
		
		// contain
		LinkedList<String> stringLinkedList = createStringLinkedList();
		System.out.println(stringLinkedList);
		System.out.println("contain \"Four\":" + stringLinkedList.contain("Four"));
		System.out.println("contain \"Zero\":" + stringLinkedList.contain("ZERO"));
		System.out.println("contain \"Ten\":" + stringLinkedList.contain("Ten"));
		
	}
	
	/**
	* 
	*/
	public void swapNodes() {
		LinkedList<Integer> list = createIntegerLinkedList();
		System.out.println(list);
		
		// swap nodes
		list.swapNodes(1, 3);
		list.swapNodes(1, 4);
		list.swapNodes(2, 3);
		list.swapNodes(0, 3);
		list.swapNodes(2, 4);
		list.swapNodes(2, 2);
		System.out.println(list);
	}
	
	/**
	 * 
	 * @param index
	 */
	public void getValue(int index) {
		LinkedList<Integer> list = createIntegerLinkedList();
		System.out.println(list);
		System.out.println(list.getValue(index));
	}
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// TestLinkedList linkList = new TestLinkedList();
		// linkList.printLinedList();
		// System.out.println();
		// linkList.removeNodes();
		// System.out.println();
		// linkList.findNodes();
		// System.out.println();
		// linkList.swapNodes();
		// System.out.println();
		// linkList.getValue(3);
		// linkList.getValue(1);
		
		LinkedList<Integer> list = new LinkedList<>();
		list.append(new Node<Integer>(1));
		list.append(new Node<Integer>(2));
		list.append(new Node<Integer>(3));
		list.append(new Node<Integer>(4));
		list.append(new Node<Integer>(5));
		System.out.println(list);
		
		list.insertAt(3, new Node<Integer>(20));
		System.out.println(list);
	}
	
}
