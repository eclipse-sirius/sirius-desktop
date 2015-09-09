/*******************************************************************************
 * Copyright (c) 2008-2015 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.business.api.session.analysis;

import java.util.Collection;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.emf.transaction.RunnableWithResult;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.window.Window;
import org.eclipse.sirius.business.api.session.danalysis.DAnalysisSelector;
import org.eclipse.sirius.common.ui.tools.api.util.EclipseUIUtil;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.provider.Messages;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IDecoratorManager;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.ElementListSelectionDialog;

/**
 * A dialog which select smartly analysis.
 *
 * @author mchauvin
 */
public class SmartDialogAnalysisSelector implements DAnalysisSelector {

    @Override
    public DAnalysis selectSmartlyAnalysisForAddedResource(final Resource resource, final Collection<DAnalysis> allAnalysis) {
        return selectSmartlyAnalysis(allAnalysis);
    }

    @Override
    public DAnalysis selectSmartlyAnalysisForAddedRepresentation(final DRepresentation representation, final Collection<DAnalysis> allAnalysis) {
        return selectSmartlyAnalysis(allAnalysis);
    }

    /**
     * Asks the user to select the analysis.
     *
     * @param allAnalysis
     *            all available analysis
     * @return selected analysis
     */
    private DAnalysis selectSmartlyAnalysis(final Collection<DAnalysis> allAnalysis) {

        final ILabelProvider provider = new AdapterFactoryLabelProvider(SiriusEditPlugin.getPlugin().getItemProvidersAdapterFactory()) {
            private IDecoratorManager decoratorManager = PlatformUI.getWorkbench().getDecoratorManager();

            @Override
            public String getText(final Object object) {
                if (object instanceof DAnalysis) {
                    return ((DAnalysis) object).eResource().getURI().toString();
                }
                return super.getText(object);
            }

            @Override
            public Image getImage(Object object) {
                return decoratorManager.decorateImage(super.getImage(object), object);
            }
        };
        RunnableWithResult<Object> runnable = new RunnableWithResult.Impl<Object>() {

            @Override
            public void run() {
                final ElementListSelectionDialog dialog = new ElementListSelectionDialog(Display.getDefault().getActiveShell(), provider) {

                    /**
                     * do not allow cancel {@inheritDoc}
                     *
                     * @see org.eclipse.ui.dialogs.SelectionDialog#createButtonsForButtonBar(org.eclipse.swt.widgets.Composite)
                     */
                    @Override
                    protected void createButtonsForButtonBar(final Composite parent) {
                        createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, true);
                    }

                };

                dialog.setTitle(Messages.SmartDialogAnalysisSelector_title);
                dialog.setMessage(Messages.SmartDialogAnalysisSelector_message);
                dialog.setElements(allAnalysis.toArray());
                if (dialog.open() == Window.OK) {
                    if (dialog.getFirstResult() != null) {
                        setResult(dialog.getFirstResult());
                    }
                }
            }
        };
        /* synch execution as the user need to choose before we can get further */
        EclipseUIUtil.displaySyncExec(runnable);
        if (runnable.getResult() instanceof DAnalysis) {
            return (DAnalysis) runnable.getResult();
        }
        return (DAnalysis) allAnalysis.toArray()[0];
    }
}
