/*******************************************************************************
 * Copyright (c) 2014 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.synchronizer;

import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.EObject;

/**
 * An adapter to put on a {@link EObject} to indicate that it would contains
 * children {@link EObject}s if it was synchronized. This is usefull to do lazy
 * synchronization, see {@link CreatedOutput#synchronizeChildren()}.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class HasChildrenNotYetSynchronizedMarker extends AdapterImpl {

    @Override
    public boolean isAdapterForType(Object type) {
        return type == getClass();
    }

}
