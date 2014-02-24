/*******************************************************************************
 * Copyright (c) 2012 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.properties;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.properties.sections.appearance.ColorsAndFontsPropertySection;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.business.api.query.DDiagramElementQuery;
import org.eclipse.sirius.diagram.business.api.query.ViewQuery;
import org.eclipse.sirius.diagram.edit.api.part.AbstractDiagramContainerEditPart;
import org.eclipse.sirius.diagram.tools.internal.commands.ResetStylePropertiesToDefaultValuesCommand;
import org.eclipse.sirius.viewpoint.Style;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

/**
 * A {@link SelectionAdapter} to reset some style properties to their default
 * values.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class ResetStylePropertiesToDefaultValuesSelectionAdapter extends SelectionAdapter {

    private ColorsAndFontsPropertySection colorAndFontPropertySection;

    /**
     * Default constructor.
     * 
     * @param colorAndFontPropertySection
     *            the {@link ColorsAndFontsPropertySection} from which get
     *            selected styles for which reset customizations
     */
    public ResetStylePropertiesToDefaultValuesSelectionAdapter(ColorsAndFontsPropertySection colorAndFontPropertySection) {
        this.colorAndFontPropertySection = colorAndFontPropertySection;
    }

    @Override
    public void widgetSelected(SelectionEvent e) {
        List<?> input = colorAndFontPropertySection.getInput();
        if (!input.isEmpty()) {
            DDiagram dDiagram = (DDiagram) colorAndFontPropertySection.getSingleInput().getNotationView().getDiagram().getElement();
            TransactionalEditingDomain domain = TransactionUtil.getEditingDomain(dDiagram);
            Map<View, DDiagramElement> customizedViews = new LinkedHashMap<View, DDiagramElement>();
            Map<IGraphicalEditPart, Style> oldStyles = new HashMap<IGraphicalEditPart, Style>();
            for (Object obj : input) {
                if (obj instanceof IGraphicalEditPart) {
                    IGraphicalEditPart graphicalEditPart = (IGraphicalEditPart) obj;
                    View notationView = graphicalEditPart.getNotationView();
                    DDiagramElement dDiagramElement = null;
                    EObject semanticElement = graphicalEditPart.resolveSemanticElement();
                    if (semanticElement instanceof DDiagramElement) {
                        dDiagramElement = (DDiagramElement) semanticElement;
                        if (new DDiagramElementQuery(dDiagramElement).isCustomized() || new ViewQuery(notationView).isCustomized()) {
                            customizedViews.put(notationView, dDiagramElement);
                            oldStyles.put(graphicalEditPart, dDiagramElement.getStyle());
                        }
                    } else if (dDiagramElement == null && new ViewQuery(notationView).isCustomized()) {
                        customizedViews.put(notationView, dDiagramElement);
                    }
                }
            }
            if (!customizedViews.isEmpty()) {
                Command resetStylePropertiesToDefaultValuesCommand = new ResetStylePropertiesToDefaultValuesCommand(domain, dDiagram, customizedViews);
                domain.getCommandStack().execute(resetStylePropertiesToDefaultValuesCommand);
                Set<Entry<IGraphicalEditPart, Style>> entrySet = oldStyles.entrySet();
                for (Entry<IGraphicalEditPart, Style> entry : entrySet) {
                    if (entry.getKey() instanceof AbstractDiagramContainerEditPart) {
                        AbstractDiagramContainerEditPart graphicalEditPart = (AbstractDiagramContainerEditPart) entry.getKey();
                        graphicalEditPart.reInitFigure();
                    }
                }
            }
        }
    }

    /**
     * Tells if the specified {@link View} is customized.
     * 
     * @param view
     *            the specified {@link View}
     * @return true if the specified {@link View} is customized, false else
     */
    public static boolean isCustomizedView(View view) {
        boolean isCustomized = false;
        EObject element = view.getElement();
        if (element instanceof DDiagramElement) {
            DDiagramElement dDiagramElement = (DDiagramElement) element;
            DDiagramElementQuery dDiagramElementQuery = new DDiagramElementQuery(dDiagramElement);
            if (dDiagramElementQuery.isCustomized()) {
                isCustomized = true;
            }
        }
        ViewQuery viewQuery = new ViewQuery(view);
        if (!isCustomized && viewQuery.isCustomized()) {
            isCustomized = true;
        }
        return isCustomized;
    }

}
