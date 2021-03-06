/******************************************************************************
 * Copyright (C) Devamatre Inc 2009-2018. All rights reserved.
 * 
 * This code is licensed to Devamatre under one or more contributor license 
 * agreements. The reproduction, transmission or use of this code, in source 
 * and binary forms, with or without modification, are permitted provided 
 * that the following conditions are met:
 * 1. Redistributions of source code must retain the above copyright
 * 	  notice, this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE AUTHOR AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE AUTHOR OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS
 * OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT
 * LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY
 * OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 *      
 * Devamatre reserves the right to modify the technical specifications and or 
 * features without any prior notice.
 *****************************************************************************/
package com.rslakra.datastructure.lists;

/**
 * @author Rohtash Lakra (rohtash.lakra@devamatre.com)
 * @author Rohtash Singh Lakra (rohtash.singh@gmail.com)
 * @created 2018-01-06 10:00:21 AM
 * @version 1.0.0
 * @since 1.0.0
 */
public class ArrayList<E> implements List<E> {

	private static final int DEFAULT_SIZE = 16;
	private int capacity;
	private Object[] items;
	private int size;

	/**
	 * Initialize with the specified capacity.
	 * 
	 * @param capacity
	 */
	private void initWithCapacity(int capacity) {
		items = new Object[capacity];
		size = 0;
	}

	/**
	 * 
	 * @param capacity
	 */
	public ArrayList(int capacity) {
		this.capacity = capacity;
		initWithCapacity(capacity);
	}

	/**
	 * 
	 */
	public ArrayList() {
		this(DEFAULT_SIZE);
	}

	/**
	 * @return
	 * @see com.devamatre.algorithms.lists.List#size()
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * @return
	 * @see com.devamatre.algorithms.lists.List#isEmpty()
	 */
	@Override
	public boolean isEmpty() {
		return (size == 0);
	}

	/**
	 * Checks the capacity and if it's less it doubles it.
	 */
	private void checkCapacity() {
		if (size == capacity) {
			Object[] temp = items;
			capacity *= 2;
			items = new Object[capacity];
			System.arraycopy(temp, 0, items, 0, temp.length);
			temp = null;
		}
	}

	/**
	 * @param item
	 * @return
	 * @see com.devamatre.algorithms.lists.List#contains(java.lang.Object)
	 */
	@Override
	public boolean contains(Object item) {
		int index = indexOf(item);
		return (index >= 0 && index < size);
	}

	/**
	 * @param item
	 * @return
	 * @see com.devamatre.algorithms.lists.List#add(java.lang.Object)
	 */
	@Override
	public boolean add(E item) {
		if (item != null) {
			checkCapacity();
			items[size++] = item;
			return true;
		}

		return false;
	}

	/**
	 * @param o
	 * @return
	 * @see com.devamatre.algorithms.lists.List#remove(java.lang.Object)
	 */
	@Override
	public boolean remove(Object item) {
		if (item != null) {
			for (int i = 0; i < size; i++) {
				if (item.equals(items[i])) {
					size--;
					Object[] temp = items;
					System.arraycopy(temp, i + 1, items, i, size - i);
					temp = null;
					return true;
				}
			}
		}

		return false;
	}

	/**
	 * @param items
	 * @return
	 * @see com.devamatre.algorithms.lists.List#containsAll(com.devamatre.algorithms.lists.List)
	 */
	@Override
	public boolean containsAll(List<?> items) {
		return false;
	}

	/**
	 * @param newItems
	 * @return
	 * @see com.devamatre.algorithms.lists.List#addAll(com.devamatre.algorithms.lists.List)
	 */
	@Override
	public boolean addAll(List<? extends E> newItems) {
		if (newItems != null) {
			boolean added = false;
			for (int i = 0; i < newItems.size(); i++) {
				added = add(newItems.get(i));
			}

			if (added) {
				return true;
			}
		}

		return false;
	}

	/**
	 * Validates the specified index.
	 * 
	 * @param index
	 */
	private void validateIndex(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("Invalid Index:" + index);
		}
	}

	/**
	 * @param index
	 * @param newItems
	 * @return
	 * @see com.devamatre.algorithms.lists.List#addAll(int,
	 *      com.devamatre.algorithms.lists.List)
	 */
	@Override
	public boolean addAll(int index, List<? extends E> newItems) {
		validateIndex(index);

		return false;
	}

	/**
	 * @param items
	 * @return
	 * @see com.devamatre.algorithms.lists.List#removeAll(com.devamatre.algorithms.lists.List)
	 */
	@Override
	public boolean removeAll(List<?> items) {
		if (items != null) {
		}

		return false;
	}

	/**
	 * @param items
	 * @return
	 * @see com.devamatre.algorithms.lists.List#retainAll(com.devamatre.algorithms.lists.List)
	 */
	@Override
	public boolean retainAll(List<?> items) {
		return false;
	}

	/**
	 * 
	 * @see com.devamatre.algorithms.lists.List#clear()
	 */
	@Override
	public void clear() {
		initWithCapacity(capacity);
	}

	/**
	 * @param index
	 * @return
	 * @see com.devamatre.algorithms.lists.List#get(int)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public E get(int index) {
		E item = null;
		validateIndex(index);

		if (!isEmpty()) {
			item = (E) items[index];
		}

		return item;
	}

	/**
	 * @param index
	 * @param element
	 * @return
	 * @see com.devamatre.algorithms.lists.List#set(int, java.lang.Object)
	 */
	@Override
	public E set(int index, E element) {
		validateIndex(index);

		return null;
	}

	/**
	 * @param index
	 * @param element
	 * @see com.devamatre.algorithms.lists.List#add(int, java.lang.Object)
	 */
	@Override
	public void add(int index, E element) {
		if (index < 0) {
			throw new IndexOutOfBoundsException("Invalid Index:" + index);
		}
	}

	/**
	 * @param index
	 * @return
	 * @see com.devamatre.algorithms.lists.List#remove(int)
	 */
	@Override
	public E remove(int index) {
		validateIndex(index);
		return null;
	}

	/**
	 * @param o
	 * @return
	 * @see com.devamatre.algorithms.lists.List#indexOf(java.lang.Object)
	 */
	@Override
	public int indexOf(Object item) {
		if (item != null) {
			for (int i = 0; i < size; i++) {
				if (item.equals(items[i])) {
					return i;
				}
			}
		}

		return -1;
	}

	/**
	 * @param o
	 * @return
	 * @see com.devamatre.algorithms.lists.List#lastIndexOf(java.lang.Object)
	 */
	@Override
	public int lastIndexOf(Object item) {
		if (item != null) {
			for (int i = size - 1; i >= 0; i--) {
				if (item.equals(items[i])) {
					return i;
				}
			}
		}

		return -1;
	}

	/**
	 * @param fromIndex
	 * @param toIndex
	 * @return
	 * @see com.devamatre.algorithms.lists.List#subList(int, int)
	 */
	@Override
	public List<E> subList(int fromIndex, int toIndex) {
		return null;
	}

	/**
	 * Returns the string representation of this object.
	 * 
	 * @return
	 * @see java.lang.Object#toString()
	 */
	@SuppressWarnings("unchecked")
	public String toString() {
		StringBuilder sBuilder = new StringBuilder("[");
		if (!isEmpty()) {
			for (int i = 0; i < size; i++) {
				sBuilder.append((E) items[i]);
				if (i < size - 1) {
					sBuilder.append(", ");
				}
			}
		}

		return sBuilder.append("]").toString();
	}

}
