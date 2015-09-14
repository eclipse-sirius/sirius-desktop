/*******************************************************************************
 * Copyright (c) 2011, 2015 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.business.internal.operation;

import org.eclipse.sirius.diagram.ui.provider.Messages;

/**
 * Abstract base class for operations which will need to modify an EMF Model.
 * This is defined independently of any command/action framework so that it can
 * be used to factor out code which may need to be called in different contexts
 * (pure EMF, EMF Transaction, GEF/GMF, etc.).
 * 
 * @param <T>
 *            the type of the operation's result. May be Void if the operation
 *            does not return any meaningful value.
 * 
 * @author pcdavid
 */
public abstract class AbstractModelChangeOperation<T> {
    private final String name;

    /**
     * Constructor.
     */
    protected AbstractModelChangeOperation() {
        this(Messages.AbstractModelChangeOperation_name);
    }

    /**
     * Constructor.
     * 
     * @param name
     *            the name of the operation.
     */
    protected AbstractModelChangeOperation(String name) {
        this.name = name;
    }

    /**
     * Returns the name of the operation.
     * 
     * @return the name of the operation.
     */
    public String getName() {
        return name;
    }

    /**
     * Executes the operation.
     * 
     * @return the result of the operation, may be Void.
     */
    public abstract T execute();
}
