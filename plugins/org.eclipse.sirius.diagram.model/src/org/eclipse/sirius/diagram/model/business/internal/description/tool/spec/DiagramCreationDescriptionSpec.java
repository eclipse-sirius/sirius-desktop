/*******************************************************************************
 * Copyright (c) 2007, 2008 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.model.business.internal.description.tool.spec;

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
import org.eclipse.sirius.diagram.description.tool.impl.DiagramCreationDescriptionImpl;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.RepresentationElementMapping;

/**
 * Implementation of
 * {@link org.eclipse.sirius.viewpoint.description.DiagramDescription}.
 * 
 * @author cbrun
 */
public class DiagramCreationDescriptionSpec extends DiagramCreationDescriptionImpl {

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.description.tool.impl.RepresentationCreationDescriptionImpl#basicGetRepresentationDescription()
     */
    @Override
    public RepresentationDescription basicGetRepresentationDescription() {
        return getDiagramDescription();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.description.tool.impl.RepresentationCreationDescriptionImpl#getRepresentationDescription()
     */
    @Override
    public RepresentationDescription getRepresentationDescription() {
        return getDiagramDescription();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.description.tool.impl.RepresentationCreationDescriptionImpl#getMappings()
     */
    @Override
    public EList<RepresentationElementMapping> getMappings() {
        Resource r = this.eResource();
        if (r == null) {
            throw new UnsupportedOperationException();
        }
        ECrossReferenceAdapter crossReferencer = ECrossReferenceAdapter.getCrossReferenceAdapter(r);
        if (crossReferencer == null) {
            throw new UnsupportedOperationException();
        }
        final List<RepresentationElementMapping> edgeMappings = new LinkedList<RepresentationElementMapping>();
        final Collection<Setting> settings = crossReferencer.getInverseReferences(this, true);
        for (final Setting setting : settings) {
            final EObject eReferencer = setting.getEObject();
            final EStructuralFeature eFeature = setting.getEStructuralFeature();
            if (eReferencer instanceof RepresentationElementMapping && eFeature.equals(DescriptionPackage.eINSTANCE.getRepresentationElementMapping_DetailDescriptions())) {
                edgeMappings.add((RepresentationElementMapping) eReferencer);
            }
        }
        return new BasicEList<RepresentationElementMapping>(edgeMappings);
    }
}
