/*******************************************************************************
 * Copyright (c) 2014, 2021 Obeo.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.sequence.tool.internal.template;

/**
 * Simple interface to implement a transformation from an input value to an output value.
 * 
 * @param <F>
 *            the type of the input value.
 * @param <T>
 *            the type of the output value.
 * 
 * @author pcdavid
 */
public interface Transformer<F, T> {
    /**
     * Transform an input value into an output value.
     * 
     * @param from
     *            the input value.
     * @return the output value.
     */
    T apply(F from);
}
