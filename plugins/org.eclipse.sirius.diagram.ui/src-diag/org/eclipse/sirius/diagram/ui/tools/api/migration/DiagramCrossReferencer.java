/*******************************************************************************
 * Copyright (c) 2007, 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.api.migration;

import java.util.Collection;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.Node;

/**
 * This cross referencer will allow us to detect all GMF elements referencing a DDiagram element and have a way to later
 * fix those cross references.
 * 
 * @author lgoubet <a href="mailto:laurent.goubet@obeo.fr">laurent.goubet@obeo.fr</a>
 */
public class DiagramCrossReferencer extends EcoreUtil.CrossReferencer {
    /** Serial UID of this class, used by serialization. */
    private static final long serialVersionUID = -7662766384716186630L;

    /**
     * Default constructor. Delegates its work to its super-class then calls the cross referencing.
     * 
     * @param resource
     *            Resource on which to create this cross referencer.
     */
    public DiagramCrossReferencer(final Resource resource) {
        super(resource);
        crossReference();
    }

    /**
     * Default constructor. Delegates its work to its super-class then calls the cross referencing.
     * 
     * @param resources
     *            A Collection of resource on which to create this cross referencer.
     */
    public DiagramCrossReferencer(Collection<Resource> resources) {
        super(resources);
        crossReference();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.emf.ecore.util.EcoreUtil.CrossReferencer#crossReference(org.eclipse.emf.ecore.EObject,
     *      org.eclipse.emf.ecore.EReference, org.eclipse.emf.ecore.EObject)
     */
    @Override
    protected boolean crossReference(final EObject eObject, final EReference eReference, final EObject crossReferencedEObject) {
        return (eObject instanceof Node || eObject instanceof Edge) && "element".equals(eReference.getName()); //$NON-NLS-1$
    }

    /**
     * Reset this cross referencer's object set.
     */
    public void clean() {
        done();
    }
}
