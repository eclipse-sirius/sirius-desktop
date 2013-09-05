/*******************************************************************************
 * Copyright (c) 2011 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.internal.metamodel.description.tool.spec;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.emf.ecore.util.ECrossReferenceAdapter;

import com.google.common.collect.Lists;

import org.eclipse.sirius.description.DescriptionPackage;
import org.eclipse.sirius.description.PasteTargetDescription;
import org.eclipse.sirius.description.tool.impl.PasteDescriptionImpl;

/**
 * Implementation of PasteDescription.
 * 
 * @author mporhel
 */
public class PasteDescriptionSpec extends PasteDescriptionImpl {

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.description.tool.impl.PasteDescriptionImpl#getContainers()
     */
    @Override
    public EList<PasteTargetDescription> getContainers() {
        if (this.eResource() == null) {
            throw new UnsupportedOperationException();
        }
        ECrossReferenceAdapter crossReferencer = ECrossReferenceAdapter.getCrossReferenceAdapter(this.eResource());
        if (crossReferencer == null) {
            throw new UnsupportedOperationException();
        }
        final List<PasteTargetDescription> pasteTargetDescriptions = Lists.newLinkedList();
        final Collection<Setting> settings = crossReferencer.getInverseReferences(this, true);
        for (final Setting setting : settings) {
            final EObject eReferencer = setting.getEObject();
            final EStructuralFeature eFeature = setting.getEStructuralFeature();
            if (eReferencer instanceof PasteTargetDescription && eFeature.equals(DescriptionPackage.eINSTANCE.getPasteTargetDescription_PasteDescriptions())) {
                pasteTargetDescriptions.add((PasteTargetDescription) eReferencer);
            }
        }
        return new BasicEList<PasteTargetDescription>(pasteTargetDescriptions);
    }

}
