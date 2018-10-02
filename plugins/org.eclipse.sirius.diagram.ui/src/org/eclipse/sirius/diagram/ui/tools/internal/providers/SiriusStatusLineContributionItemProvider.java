/*******************************************************************************
 * Copyright (c) 2017 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.ui.tools.internal.providers;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.common.core.service.IOperation;
import org.eclipse.gmf.runtime.common.core.util.StringStatics;
import org.eclipse.gmf.runtime.common.ui.services.statusline.AbstractStatusLineContributionItemProvider;
import org.eclipse.gmf.runtime.common.ui.services.statusline.StatusLineMessageContributionItem;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.business.api.query.EObjectQuery;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.ui.IWorkbenchPage;

/**
 * Provider to display diagram synchronize state in the status bar.
 * 
 * @author lredor
 */
public class SiriusStatusLineContributionItemProvider extends AbstractStatusLineContributionItemProvider {

    /**
     * Specific StatusLineMessageContributionItem to display diagram synchronize state.
     * 
     * @author lredor
     */
    public class SiriusStatusLineMessageContributionItem extends StatusLineMessageContributionItem {
        @Override
        public String getText(Object element) {
            String result = StringStatics.BLANK;
            if (element instanceof IStructuredSelection) {
                IStructuredSelection ss = (IStructuredSelection) element;
                if (ss.size() == 1) {
                    Object firstElement = ss.getFirstElement();
                    if (firstElement instanceof EObject) {
                        result = getText((EObject) firstElement);
                    } else if (firstElement instanceof IGraphicalEditPart) {
                        result = getText((IGraphicalEditPart) firstElement);
                    }
                }
            } else if (element instanceof IGraphicalEditPart) {
                result = getText((IGraphicalEditPart) element);
            }
            return result;

        }

        private String getText(IGraphicalEditPart editPart) {
            Object model = editPart.getModel();
            if (model instanceof View) {
                EObject eObject = ((View) model).getElement();
                return getText(eObject);
            }
            return StringStatics.BLANK;
        }

        private String getText(EObject selectedElement) {
            String result = StringStatics.BLANK;
            Option<DDiagram> optionalDiagram = new EObjectQuery(selectedElement).getParentDiagram();
            if (optionalDiagram.some()) {
                if (optionalDiagram.get().isSynchronized()) {
                    result = Messages.SiriusStatusLineContributionItemProvider_diagramSynchronized;
                } else {
                    result = Messages.SiriusStatusLineContributionItemProvider_diagramUnsynchronized;
                }
            }
            return result;
        }
    }

    /**
     * Default constructor.
     */
    public SiriusStatusLineContributionItemProvider() {
    }

    @Override
    public List<IContributionItem> getStatusLineContributionItems(IWorkbenchPage workbenchPage) {
        List<IContributionItem> a = new ArrayList<IContributionItem>(1);
        a.add(new SiriusStatusLineMessageContributionItem());
        return a;
    }

    @Override
    public boolean provides(IOperation operation) {
        return true;
    }
}
