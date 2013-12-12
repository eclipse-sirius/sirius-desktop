/*******************************************************************************
 * Copyright (c) 2007, 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ext.base.collect;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.LinkedList;
import java.util.List;

/**
 * A stack implementation. This class is not Thread Safe.
 * 
 * @param <T>
 *            the type of elements in the stack.
 * @author ymortier
 */
public class StackEx<T> {

    /** The internal stack. */
    private LinkedList<T> internalStack;

    /**
     * Creates a new Stack.
     */
    public StackEx() {
        this.internalStack = new LinkedList<T>();
    }

    /**
     * Pushes the specified Object.
     * 
     * @param object
     *            the object to push.
     */
    public void push(final T object) {
        this.internalStack.add(object);
    }

    /**
     * Pops the last object.
     * 
     * @return the popped object.
     * @throws EmptyStackException
     *             if the stack is empty.
     */
    public T pop() throws EmptyStackException {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return internalStack.removeLast();
    }

    /**
     * Returns the last object of the stack.
     * 
     * @return the last object of the stack.
     * @throws EmptyStackException
     *             if the stack is empty.
     */
    public T peek() throws EmptyStackException {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return internalStack.getLast();
    }

    /**
     * Clears the stack.
     */
    public void clear() {
        internalStack.clear();
    }

    /**
     * Returns <code>true</code> if the stack is empty.
     * 
     * @return <code>true</code> if the stack is empty.
     */
    public boolean isEmpty() {
        return internalStack.isEmpty();
    }

    /**
     * Returns the size of the stack.
     * 
     * @return the size of the stack.
     */
    public int size() {
        return internalStack.size();
    }

    /**
     * Transforms this stack to a list (the begining of the list is the first
     * element pushed on the stack).
     * 
     * @return the resulted list.
     */
    public List<T> toList() {
        final List<T> result = new ArrayList<T>(this.size());
        result.addAll(this.internalStack);
        return result;
    }

}
