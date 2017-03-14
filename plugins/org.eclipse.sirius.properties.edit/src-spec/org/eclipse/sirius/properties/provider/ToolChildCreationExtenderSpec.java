/**
 * Copyright (c) 2017 Obeo.
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
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.sirius.properties.DialogButton;
import org.eclipse.sirius.properties.DialogModelOperation;
import org.eclipse.sirius.properties.GroupDescription;
import org.eclipse.sirius.properties.PageDescription;
import org.eclipse.sirius.properties.PropertiesFactory;
import org.eclipse.sirius.properties.provider.PropertiesItemProviderAdapterFactory.ToolChildCreationExtender;
import org.eclipse.sirius.viewpoint.description.tool.ToolFactory;

/**
 * Subclass used to not have to modify the generated code.
 *
 * @author sbegaudeau
 */
public class ToolChildCreationExtenderSpec extends ToolChildCreationExtender {

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
        protected CommandParameter createChildParameter(Object feature, Object child) {
            if (child instanceof DialogModelOperation) {
                // Let's configure the default dialog to create
                DialogModelOperation dialogModelOperation = PropertiesFactory.eINSTANCE.createDialogModelOperation();
                dialogModelOperation.setTitleExpression("New Dialog"); //$NON-NLS-1$

                DialogButton cancelButtonDescription = PropertiesFactory.eINSTANCE.createDialogButton();
                cancelButtonDescription.setLabelExpression("Cancel"); //$NON-NLS-1$
                cancelButtonDescription.setDefault(false);
                cancelButtonDescription.setCloseDialogOnClick(true);
                cancelButtonDescription.setRollbackChangesOnClose(true);
                cancelButtonDescription.setInitialOperation(ToolFactory.eINSTANCE.createInitialOperation());

                DialogButton okButtonDescription = PropertiesFactory.eINSTANCE.createDialogButton();
                okButtonDescription.setLabelExpression("OK"); //$NON-NLS-1$
                okButtonDescription.setDefault(true);
                okButtonDescription.setCloseDialogOnClick(true);
                okButtonDescription.setRollbackChangesOnClose(false);
                okButtonDescription.setInitialOperation(ToolFactory.eINSTANCE.createInitialOperation());

                dialogModelOperation.getButtons().add(cancelButtonDescription);
                dialogModelOperation.getButtons().add(okButtonDescription);

                PageDescription pageDescription = PropertiesFactory.eINSTANCE.createPageDescription();
                pageDescription.setName(Messages.PageDescription__name);
                pageDescription.setLabelExpression("Page"); //$NON-NLS-1$
                pageDescription.setSemanticCandidateExpression(ViewExtensionDescriptionItemProviderSpec.DEFAULT_SEMANTIC_CANDIDATES_EXPRESSION);
                dialogModelOperation.setPage(pageDescription);

                GroupDescription groupDescription = PropertiesFactory.eINSTANCE.createGroupDescription();
                groupDescription.setName(Messages.GroupDescription__name);
                groupDescription.setLabelExpression("Group"); //$NON-NLS-1$
                groupDescription.setSemanticCandidateExpression(ViewExtensionDescriptionItemProviderSpec.DEFAULT_SEMANTIC_CANDIDATES_EXPRESSION);
                dialogModelOperation.getGroups().add(groupDescription);
                pageDescription.getGroups().add(groupDescription);

                return new CommandParameter(null, feature, dialogModelOperation);
            }
            return super.createChildParameter(feature, child);
        }
    }

    @Override
    public Collection<Object> getNewChildDescriptors(Object object, EditingDomain editingDomain) {
        ArrayList<Object> result = new ArrayList<Object>();
        new CreationSwitchSpec(result, editingDomain).doSwitch((EObject) object);
        return result;
    }
}
