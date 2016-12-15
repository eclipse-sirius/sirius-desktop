/**
 * Copyright (c) 2016 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *
 */
package org.eclipse.sirius.properties.provider;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.sirius.properties.GroupDescription;
import org.eclipse.sirius.properties.PageDescription;
import org.eclipse.sirius.properties.PropertiesFactory;
import org.eclipse.sirius.properties.ViewExtensionDescription;
import org.eclipse.sirius.properties.provider.PropertiesItemProviderAdapterFactory.DescriptionChildCreationExtender;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;
import org.eclipse.sirius.viewpoint.description.Group;

/**
 * Subclass used to not have to modify the generated code.
 *
 * @author sbegaudeau
 */
public class DescriptionChildCreationExtenderSpec extends DescriptionChildCreationExtender {

    /**
     * Subclass used to not have to modify the generated code.
     * 
     * @author sbegaudeau
     */
    protected static class CreationSwitchSpec extends CreationSwitch {
        /**
         * The constructor.
         * 
         * @param newChildDescriptors
         *            The new child descriptors
         * @param editingDomain
         *            The editing domain
         */
        CreationSwitchSpec(List<Object> newChildDescriptors, EditingDomain editingDomain) {
            super(newChildDescriptors, editingDomain);
        }

        @Override
        public Object caseGroup(Group object) {
            ViewExtensionDescription ved = PropertiesFactory.eINSTANCE.createViewExtensionDescription();
            ved.setIdentifier("Properties"); //$NON-NLS-1$
            PageDescription page = PropertiesFactory.eINSTANCE.createPageDescription();
            page.setIdentifier("Default"); //$NON-NLS-1$
            page.setLabelExpression("Page"); //$NON-NLS-1$
            page.setSemanticCandidateExpression(ViewExtensionDescriptionItemProvider.DEFAULT_SEMANTIC_CANDIDATES_EXPRESSION);
            ved.getPages().add(page);
            GroupDescription group = PropertiesFactory.eINSTANCE.createGroupDescription();
            group.setIdentifier("Default"); //$NON-NLS-1$
            group.setLabelExpression("Group"); //$NON-NLS-1$
            group.setSemanticCandidateExpression(ViewExtensionDescriptionItemProvider.DEFAULT_SEMANTIC_CANDIDATES_EXPRESSION);
            ved.getGroups().add(group);
            page.getGroups().add(group);
            newChildDescriptors.add(createChildParameter(DescriptionPackage.Literals.GROUP__EXTENSIONS, ved));
            return null;
        }

    }

    @Override
    public Collection<Object> getNewChildDescriptors(Object object, EditingDomain editingDomain) {
        ArrayList<Object> result = new ArrayList<Object>();
        new CreationSwitchSpec(result, editingDomain).doSwitch((EObject) object);
        return result;
    }
}
