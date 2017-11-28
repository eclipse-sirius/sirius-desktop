/*******************************************************************************
 * Copyright (c) 2015 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.editor.properties.sections.style.bundledimagedescription;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.sirius.diagram.BundledImageShape;
import org.eclipse.sirius.diagram.description.style.BundledImageDescription;
import org.eclipse.sirius.diagram.description.style.StylePackage;
import org.eclipse.sirius.diagram.internal.queries.BundledImageExtensionQuery;

public class BundledImageDescriptionShapePropertySectionSpec extends BundledImageDescriptionShapePropertySection {

    /**
     * @see org.eclipse.sirius.diagram.editor.properties.sections.AbstractComboPropertySection#getEnumerationFeatureValues()
     */
    @Override
    protected List<?> getChoiceOfValues() {
        ArrayList values = new ArrayList<>();
        for (int i = 0; i < BundledImageShape.VALUES.size(); i++) {
            BundledImageShape bundledImageShape = BundledImageShape.VALUES.get(i);
            if (!bundledImageShape.equals(BundledImageShape.PROVIDED_SHAPE_LITERAL)) {
                values.add(bundledImageShape);
            }
        }
        BundledImageExtensionQuery bundledImageExtensionQuery = new BundledImageExtensionQuery();
        values.addAll(bundledImageExtensionQuery.getExtendedLabelsForVSM());
        return values;
    }

    @Override
    protected void handleComboModified() {
        int index = combo.getSelectionIndex();
        if (index < 0) {
            return;
        } else if (index < BundledImageShape.VALUES.size() - 1) {
            super.handleComboModified();
        } else {
            BundledImageExtensionQuery bundledImageExtensionQuery = new BundledImageExtensionQuery();
            IConfigurationElement[] elements = bundledImageExtensionQuery.getExtensions();
            // To compute the selected provided shape index we need to remove
            // the default shape
            int providedBundleImageShapeIndex = index - (BundledImageShape.VALUES.size() - 1);
            IConfigurationElement configurationElement = elements[providedBundleImageShapeIndex];
            String identifier = bundledImageExtensionQuery.getIdentifier(configurationElement);

            // Selected value without the plugin name
            EditingDomain editingDomain = ((IEditingDomainProvider) getPart()).getEditingDomain();
            if (eObjectList.size() == 1) {
                /* apply the property change to single selected object */
                CompoundCommand cc = new CompoundCommand("Set provided shape to Bundle Image Description");
                cc.append(SetCommand.create(editingDomain, eObject, getFeature(), BundledImageShape.PROVIDED_SHAPE_LITERAL));
                cc.append(SetCommand.create(editingDomain, eObject, StylePackage.eINSTANCE.getBundledImageDescription_ProvidedShapeID(), identifier));
                editingDomain.getCommandStack().execute(cc);
            } else {
                CompoundCommand compoundCommand = new CompoundCommand();
                /* apply the property change to all selected elements */
                for (Iterator<EObject> i = eObjectList.iterator(); i.hasNext();) {
                    EObject nextObject = i.next();
                    compoundCommand.append(SetCommand.create(editingDomain, nextObject, getFeature(), BundledImageShape.PROVIDED_SHAPE_LITERAL));
                    compoundCommand.append(SetCommand.create(editingDomain, nextObject, StylePackage.eINSTANCE.getBundledImageDescription_ProvidedShapeID(), identifier));
                }
                editingDomain.getCommandStack().execute(compoundCommand);
            }
        }
    }

    /**
     * Get the value of the feature as text for the text field of the section.
     * 
     * @return The value of the feature as text.
     */
    @Override
    protected String getFeatureAsText() {
        final EStructuralFeature eFeature = getFeature();
        final String label = getPropertyLabel(eObject.eGet(eFeature));
        if (BundledImageShape.PROVIDED_SHAPE_LITERAL.getName().equals(label) && eObject instanceof BundledImageDescription) {
            BundledImageDescription bundledImageDescription = (BundledImageDescription) eObject;
            String providedShapeID = bundledImageDescription.getProvidedShapeID();

            BundledImageExtensionQuery bundledImageExtensionQuery = new BundledImageExtensionQuery();
            return bundledImageExtensionQuery.getExtendedLabelForVSM(providedShapeID);
        }
        return label != null ? label : getDefaultFeatureAsText();
    }
}
