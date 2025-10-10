/*******************************************************************************
 * Copyright (c) 2011, 2025 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.interpreter.internal.view;

/**
 * This represents a Variable accessible to the evaluation of an expression.
 * 
 * @author <a href="mailto:laurent.goubet@obeo.fr">Laurent Goubet</a>
 */
public class Variable {
    /** Name of this Variable. */
    private String name;

    /** Value of this Variable. */
    private Object value;

    /**
     * Instantiates a new Variable with no name, nor value.
     */
    public Variable() {
        // Default constructor : empty name, empty value
    }

    /**
     * Instantiates a new Variable with no value.
     * 
     * @param name
     *            Name of the Variable that is to be created.
     */
    public Variable(String name) {
        this.name = name;
    }

    /**
     * Instantiates a new Variable.
     * 
     * @param name
     *            Name of the Variable that is to be created.
     * @param value
     *            Value of this new Variable.
     */
    public Variable(String name, Object value) {
        this(name);
        this.value = value;
    }

    /**
     * Returns this Variable's name.
     * 
     * @return This Variable's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns this Variable's value.
     * 
     * @return This Variable's value.
     */
    public Object getValue() {
        return value;
    }

    /**
     * Sets this variable name.
     * 
     * @param newName
     *            The new name of this variable.
     */
    public void setName(String newName) {
        this.name = newName;
    }

    /**
     * Sets this variable value.
     * 
     * @param newValue
     *            The new value of this variable.
     */
    public void setValue(Object newValue) {
        this.value = newValue;
    }
}
