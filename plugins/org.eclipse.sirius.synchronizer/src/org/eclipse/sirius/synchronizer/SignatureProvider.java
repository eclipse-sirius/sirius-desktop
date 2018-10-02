/*******************************************************************************
 * Copyright (c) 2011 Obeo.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.synchronizer;

public interface SignatureProvider {

    /**
     * Returns the signature matching the given OutputDescriptor.
     * 
     * @param descriptor
     *            the OutputDescriptor to get the signature from
     * @return the signature matching the given OutputDescriptor
     */
    Signature getSignature(OutputDescriptor descriptor);

}
