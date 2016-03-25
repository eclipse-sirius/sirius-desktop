/*******************************************************************************
 * Copyright (c) 2016 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.editor.tabbar.actions;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.description.concern.ConcernDescription;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.diagram.ui.tools.api.image.DiagramImagesPath;
import org.eclipse.sirius.diagram.ui.tools.internal.commands.SetDefaultConcernCommand;
import org.eclipse.swt.SWT;

/**
 * An {@link Action} to set filters to default concern.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class ResetToDefaultFiltersAction extends Action {

    /** The ID of this action. */
    public static final String ID = ResetToDefaultFiltersAction.class.getName();

    private TransactionalEditingDomain domain;

    private DDiagram dDiagram;

    /**
     * Default constructor.
     * 
     * @param domain
     *            the {@link TransactionalEditingDomain} on which set filters to
     *            default concern
     * @param dDiagram
     *            the {@link DDiagram} on which set filters to default concern
     */
    public ResetToDefaultFiltersAction(TransactionalEditingDomain domain, DDiagram dDiagram) {
        super(Messages.ResetToDefaultFiltersAction_text, IAction.AS_PUSH_BUTTON);
        setId(ID);
        setToolTipText(Messages.ResetToDefaultFiltersAction_tooltip);
        ImageDescriptor enabledImage = DiagramUIPlugin.Implementation.getBundledImageDescriptor(DiagramImagesPath.UNDO_ICON);
        ImageDescriptor disabledImage = ImageDescriptor.createWithFlags(enabledImage, SWT.IMAGE_DISABLE);
        setImageDescriptor(enabledImage);
        setDisabledImageDescriptor(disabledImage);
        this.domain = domain;
        this.dDiagram = dDiagram;
    }

    @Override
    public boolean isEnabled() {
        boolean isEnabled = true;
        DiagramDescription description = dDiagram.getDescription();
        if (description != null) {
            ConcernDescription defaultConcern = description.getDefaultConcern();
            if (defaultConcern != null) {
                isEnabled = !EcoreUtil.equals(defaultConcern.getFilters(), dDiagram.getActivatedFilters());
            }
        }
        return isEnabled;
    }

    @Override
    public void run() {
        domain.getCommandStack().execute(new SetDefaultConcernCommand(domain, dDiagram));
    }

}
