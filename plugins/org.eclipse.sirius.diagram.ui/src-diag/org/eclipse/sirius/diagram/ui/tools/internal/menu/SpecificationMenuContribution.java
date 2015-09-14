/*******************************************************************************
 * Copyright (c) 2007, 2015 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.menu;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry;
import org.eclipse.emf.workspace.util.WorkspaceSynchronizer;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.common.core.service.IOperation;
import org.eclipse.gmf.runtime.common.core.service.IProviderChangeListener;
import org.eclipse.gmf.runtime.common.ui.services.action.internal.contributionitem.IContributionItemProvider;
import org.eclipse.gmf.runtime.common.ui.util.IWorkbenchPartDescriptor;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.NoteEditPart;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.sirius.common.ui.SiriusTransPlugin;
import org.eclipse.sirius.common.ui.tools.api.editor.IEObjectNavigable;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.ui.business.internal.navigation.MappingDefinitionFinder;
import org.eclipse.sirius.diagram.ui.part.SiriusDiagramEditor;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.ui.tools.api.image.ImagesPath;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.FileEditorInput;

/**
 * This menu contribution add a "specification" menu in the DDiagram diagram
 * editor to navigate to the diagram description.
 * 
 * @author cbrun
 * 
 */
public class SpecificationMenuContribution implements IContributionItemProvider {

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.common.ui.services.action.internal.contributionitem.IContributionItemProvider#contributeToActionBars(org.eclipse.ui.IActionBars,
     *      org.eclipse.gmf.runtime.common.ui.util.IWorkbenchPartDescriptor)
     */
    @Override
    public void contributeToActionBars(final IActionBars arg0, final IWorkbenchPartDescriptor arg1) {
        // Nothing to contribute

    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.common.ui.services.action.internal.contributionitem.IContributionItemProvider#contributeToPopupMenu(org.eclipse.jface.action.IMenuManager,
     *      org.eclipse.ui.IWorkbenchPart)
     */
    @Override
    public void contributeToPopupMenu(final IMenuManager menu, final IWorkbenchPart part) {
        if (part instanceof SiriusDiagramEditor) {

            final SiriusDiagramEditor diagrampart = (SiriusDiagramEditor) part;
            final EObject element = diagrampart.getDiagramEditPart().resolveSemanticElement();
            if (element instanceof DSemanticDiagram) {
                final EditPart editpart = diagrampart.getDiagramGraphicalViewer().getFocusEditPart();
                if (editpart instanceof IGraphicalEditPart && !(editpart instanceof NoteEditPart)) {
                    final IGraphicalEditPart curPart = (IGraphicalEditPart) editpart;

                    final EObject designerObj = curPart.resolveSemanticElement();
                    /*
                     * designerObj could be null if current edit part is a note
                     * edit part
                     */
                    if (designerObj != null) {
                        buildOpenExistingSpecification(menu, designerObj);
                    }

                }
            }
        } else {
            // no focused edit part
        }
    }

    /**
     * @param menu
     * @param designerDiag
     * @param editpart
     * @param designerObj
     */
    private void buildOpenExistingSpecification(final IMenuManager menu, final EObject designerObj) {
        final EObject description = getDescription(designerObj);
        if (description != null) {
            Resource resource = description.eResource();
            if (resource != null) {
                final IFile modelFile = WorkspaceSynchronizer.getFile(resource);

                ImageDescriptor imageDescriptor = SiriusTransPlugin.getBundledImageDescriptor(ImagesPath.LINK_TO_VIEWPOINT_IMG);
                final IItemLabelProvider labelProvider = (IItemLabelProvider) getAdapterFactory().adapt(description, IItemLabelProvider.class);
                if (labelProvider != null) {
                    imageDescriptor = ExtendedImageRegistry.getInstance().getImageDescriptor(labelProvider.getImage(description));
                }

                if (modelFile != null) {
                    final Action openAction = new Action(Messages.SpecificationMenuContribution_openDefinitionMenuLabel, imageDescriptor) {

                        @Override
                        public void run() {
                            super.run();

                            final IWorkbench workbench = PlatformUI.getWorkbench();
                            final IWorkbenchWindow workbenchWindow = workbench.getActiveWorkbenchWindow();
                            final IWorkbenchPage page = workbenchWindow.getActivePage();
                            try {
                                final IEditorPart editor = page.openEditor(new FileEditorInput(modelFile), workbench.getEditorRegistry().getDefaultEditor(modelFile.getFullPath().toString()).getId());
                                if (editor instanceof IEObjectNavigable) {
                                    ((IEObjectNavigable) editor).navigateToEObject(EcoreUtil.getURI(description));
                                }
                            } catch (final PartInitException exception) {
                                // TODO log
                            }

                        }
                    };
                    openAction.setId(Messages.SpecificationMenuContribution_openDefinitionMenuLabel);
                    menu.insertAfter(IWorkbenchActionConstants.MB_ADDITIONS, openAction);
                }
            }
        }
    }

    private EObject getDescription(final EObject designerObj) {
        return new MappingDefinitionFinder().getDefinition(designerObj);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.common.ui.services.action.internal.contributionitem.IContributionItemProvider#disposeContributions(org.eclipse.gmf.runtime.common.ui.util.IWorkbenchPartDescriptor)
     */
    @Override
    public void disposeContributions(final IWorkbenchPartDescriptor arg0) {
        // Nothing to contribute

    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.common.ui.services.action.internal.contributionitem.IContributionItemProvider#updateActionBars(org.eclipse.ui.IActionBars,
     *      org.eclipse.gmf.runtime.common.ui.util.IWorkbenchPartDescriptor)
     */
    @Override
    public void updateActionBars(final IActionBars arg0, final IWorkbenchPartDescriptor arg1) {
        // Nothing to contribute

    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.common.core.service.IProvider#addProviderChangeListener(org.eclipse.gmf.runtime.common.core.service.IProviderChangeListener)
     */
    @Override
    public void addProviderChangeListener(final IProviderChangeListener arg0) {
        // Nothing to contribute

    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.common.core.service.IProvider#provides(org.eclipse.gmf.runtime.common.core.service.IOperation)
     */
    @Override
    public boolean provides(final IOperation arg0) {
        // Always provide
        return true;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.common.core.service.IProvider#removeProviderChangeListener(org.eclipse.gmf.runtime.common.core.service.IProviderChangeListener)
     */
    @Override
    public void removeProviderChangeListener(final IProviderChangeListener arg0) {
        // Nothing to contribute

    }

    private AdapterFactory getAdapterFactory() {
        return DiagramUIPlugin.getPlugin().getItemProvidersAdapterFactory();
    }

}
