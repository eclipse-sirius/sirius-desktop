/*******************************************************************************
 * Copyright (c) 2007, 2008, 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.internal.metamodel.spec;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.EdgeTarget;
import org.eclipse.sirius.diagram.impl.DDiagramLinkImpl;

/**
 * Implementation of DDiagramLinkImpl.java.
 * 
 * @author cbrun, mchauvin, ymortier.
 */
public class DDiagramLinkSpec extends DDiagramLinkImpl {

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.impl.DDiagramLinkImpl#setNode(org.eclipse.sirius.viewpoint.EdgeTarget)
     */
    @Override
    public void setNode(final EdgeTarget newNode) {
        final EdgeTarget oldNode = getNode();
        super.setNode(newNode);
        if (getTarget() == null) {
            DDiagram found = null;
            EObject parent = newNode.eContainer();
            while (found == null && parent != null) {
                if (parent instanceof DDiagram) {
                    found = (DDiagram) parent;
                }
                parent = parent.eContainer();
            }
            if (found != null) {
                setTarget(found);
            }
        }
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DiagramPackage.DDIAGRAM_LINK__NODE, oldNode, getNode()));
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.impl.DDiagramLinkImpl#isAvailable()
     */
    @Override
    public boolean isAvailable() {
        return getTarget() != null && getTarget().eResource() != null;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.impl.DNavigationLinkImpl#getLabel()
     */
    @Override
    public String getLabel() {
        if (getTarget() != null && getTarget().getName() != null) {
            return "Open " + getTarget().getName();
        }
        return super.getLabel();
    }

}
