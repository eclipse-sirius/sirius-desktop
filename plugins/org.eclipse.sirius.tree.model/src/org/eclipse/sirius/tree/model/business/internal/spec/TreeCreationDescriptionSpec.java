/*******************************************************************************
 * Copyright (c) 2010, 2021 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tree.model.business.internal.spec;

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
import org.eclipse.sirius.tree.description.impl.TreeCreationDescriptionImpl;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.RepresentationElementMapping;

/**
 * Implementation od TreeCreationDescription.
 * 
 * @author nlepine
 * 
 */
public class TreeCreationDescriptionSpec extends TreeCreationDescriptionImpl {

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.description.tool.impl.RepresentationCreationDescriptionImpl#getMappings()
     */
    @Override
    public EList<RepresentationElementMapping> getMappings() {
        Resource resource = this.eResource();
        if (resource == null) {
            throw new UnsupportedOperationException();
        }
        ECrossReferenceAdapter crossReferencer = ECrossReferenceAdapter.getCrossReferenceAdapter(resource);
        if (crossReferencer == null) {
            throw new UnsupportedOperationException();
        }
        final List<RepresentationElementMapping> mappings = new LinkedList<RepresentationElementMapping>();
        final Collection<Setting> settings = crossReferencer.getInverseReferences(this, true);
        for (final Setting setting : settings) {
            final EObject eReferencer = setting.getEObject();
            final EStructuralFeature eFeature = setting.getEStructuralFeature();
            if (eReferencer instanceof RepresentationElementMapping && eFeature.equals(DescriptionPackage.eINSTANCE.getRepresentationElementMapping_DetailDescriptions())) {
                mappings.add((RepresentationElementMapping) eReferencer);
            }
        }
        return new BasicEList<RepresentationElementMapping>(mappings);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.description.tool.impl.RepresentationCreationDescriptionImpl#basicGetRepresentationDescription()
     */
    @Override
    public RepresentationDescription basicGetRepresentationDescription() {
        return getTreeDescription();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.description.tool.impl.RepresentationCreationDescriptionImpl#getRepresentationDescription()
     */
    @Override
    public RepresentationDescription getRepresentationDescription() {
        return getTreeDescription();
    }

}
