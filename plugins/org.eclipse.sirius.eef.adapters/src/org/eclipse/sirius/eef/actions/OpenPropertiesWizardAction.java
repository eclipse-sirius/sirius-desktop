/*******************************************************************************
 * Copyright (c) 2009, 2021 Obeo.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.eef.actions;

import java.util.Collection;
import java.util.Map;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.eef.runtime.context.impl.DomainPropertiesEditionContext;
import org.eclipse.emf.eef.runtime.impl.operation.WizardEditingOperation;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.business.api.action.AbstractExternalJavaAction;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.eef.adapters.Messages;
import org.eclipse.sirius.eef.util.VPDecoratorHelper;
import org.eclipse.sirius.tools.api.SiriusPlugin;

/**
 * An External Java Action which opens an EEF wizard/dialog on the selected object, if one is available.
 * 
 * @author Goulwen Le Fur
 */
public class OpenPropertiesWizardAction extends AbstractExternalJavaAction {
    @Override
    public boolean canExecute(Collection<? extends EObject> selections) {
        return true;
    }

    @Override
    public void execute(Collection<? extends EObject> selections, Map<String, Object> parameters) {
        if (selections.size() > 0) {
            try {
                EObject eObject = (EObject) selections.toArray()[0];
                VPDecoratorHelper helper = new VPDecoratorHelper(eObject);
                if (helper.canAdapt()) {
                    TransactionalEditingDomain editingDomain = org.eclipse.emf.transaction.util.TransactionUtil.getEditingDomain(eObject);
                    DomainPropertiesEditionContext propertiesEditionContext = new DomainPropertiesEditionContext(null, null, editingDomain,
                            DiagramUIPlugin.getPlugin().getItemProvidersAdapterFactory(), helper.createSemanticAdapterFromDSemanticDecorator().getEObject());
                    WizardEditingOperation wizardEditingCommand = new WizardEditingOperation(propertiesEditionContext);
                    wizardEditingCommand.execute(new NullProgressMonitor(), null);
                    propertiesEditionContext.dispose();
                }
            } catch (ExecutionException e) {
                SiriusPlugin.getDefault().error(Messages.OpenPropertiesWizardAction_errorOpeningWizard, e);
            }
        }
    }
}
