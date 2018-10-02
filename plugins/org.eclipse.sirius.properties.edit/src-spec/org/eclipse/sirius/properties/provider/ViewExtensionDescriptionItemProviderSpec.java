/**
 * Copyright (c) 2016, 2017 Obeo.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *
 */
package org.eclipse.sirius.properties.provider;

import java.util.Collection;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.provider.StyledString;
import org.eclipse.sirius.properties.Category;
import org.eclipse.sirius.properties.PropertiesFactory;
import org.eclipse.sirius.properties.PropertiesPackage;

/**
 * Subclass used to not have to modify the generated code.
 *
 * @author sbegaudeau
 */
public class ViewExtensionDescriptionItemProviderSpec extends ViewExtensionDescriptionItemProvider {
    /**
     * The default expression to use as semanticCandidatesExpression for newly created elements.
     */
    public static final String DEFAULT_SEMANTIC_CANDIDATES_EXPRESSION = "var:self"; //$NON-NLS-1$

    /**
     * The constructor.
     *
     * @param adapterFactory
     *            The adapter factory
     */
    public ViewExtensionDescriptionItemProviderSpec(AdapterFactory adapterFactory) {
        super(adapterFactory);
    }

    @Override
    public String getText(Object object) {
        Object styledText = this.getStyledText(object);
        if (styledText instanceof StyledString) {
            return ((StyledString) styledText).getString();
        }
        return super.getText(object);
    }

    @Override
    public Object getStyledText(Object object) {
        return Utils.computeLabel(this, object, "_UI_ViewExtensionDescription_type"); //$NON-NLS-1$
    }

    @Override
    protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
        Category category = PropertiesFactory.eINSTANCE.createCategory();
        category.setName("Category"); //$NON-NLS-1$
        newChildDescriptors.add(createChildParameter(PropertiesPackage.Literals.VIEW_EXTENSION_DESCRIPTION__CATEGORIES, category));
    }
}
