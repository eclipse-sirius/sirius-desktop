/*******************************************************************************
 * Copyright (c) 2008, 2018 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.business.internal.metamodel.description.tool.spec;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.ECrossReferenceAdapter;
import org.eclipse.sirius.diagram.description.DescriptionPackage;
import org.eclipse.sirius.diagram.description.DragAndDropTargetDescription;
import org.eclipse.sirius.diagram.description.tool.impl.ContainerDropDescriptionImpl;

/**
 * Implementation of ContainerDropDescription.
 * 
 * @author ymortier
 */
public class ContainerDropDescriptionSpec extends ContainerDropDescriptionImpl {


    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.description.tool.impl.ContainerDropDescriptionImpl#getContainers()
     */
    @Override
    public EList<DragAndDropTargetDescription> getContainers() {
        Resource r = this.eResource();
        if (r == null) {
            throw new UnsupportedOperationException();
        }
        ECrossReferenceAdapter crossReferencer = ECrossReferenceAdapter.getCrossReferenceAdapter(r);
        if (crossReferencer == null) {
            throw new UnsupportedOperationException();
        }
        final List<DragAndDropTargetDescription> dndTargetDescriptions = new LinkedList<DragAndDropTargetDescription>();
        final Collection<Setting> settings = crossReferencer.getInverseReferences(this, true);
        for (final Setting setting : settings) {
            final EObject eReferencer = setting.getEObject();
            final EStructuralFeature eFeature = setting.getEStructuralFeature();
            if (eReferencer instanceof DragAndDropTargetDescription && eFeature.equals(DescriptionPackage.eINSTANCE.getDragAndDropTargetDescription_DropDescriptions())) {
                dndTargetDescriptions.add((DragAndDropTargetDescription) eReferencer);
            }
        }
        return new BasicEList<DragAndDropTargetDescription>(dndTargetDescriptions);
    }

}
