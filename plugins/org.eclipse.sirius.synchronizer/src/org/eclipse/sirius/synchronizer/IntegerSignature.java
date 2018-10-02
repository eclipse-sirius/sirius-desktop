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

/**
 * 
 * A signature implementation wrapping an Integer value.
 * 
 * @author Cedric Brun <cedric.brun@obeo.fr>
 * 
 */

public class IntegerSignature implements Signature {

    private Integer value;

    public IntegerSignature(Integer value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object arg0) {
        if (arg0 instanceof IntegerSignature) {
            return this.value.equals(((IntegerSignature) arg0).value);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.value.hashCode();
    }

}
