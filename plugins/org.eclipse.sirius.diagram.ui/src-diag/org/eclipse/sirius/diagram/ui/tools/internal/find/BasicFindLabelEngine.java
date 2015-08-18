/*******************************************************************************
 * Copyright (c) 2008, 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.find;

import java.util.Collection;
import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ITextAwareEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.sirius.common.tools.api.find.AbstractFindLabelEngine;
import org.eclipse.sirius.diagram.ui.tools.api.figure.AirStyleDefaultSizeNodeFigure;
import org.eclipse.sirius.diagram.ui.tools.api.figure.FigureQuery;
import org.eclipse.sirius.ext.base.Option;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;

/**
 * A basic implementation of the AbstractFindLabelEngine. This engine simple
 * search for labels starting with the search criterion
 * 
 * @author glefur
 */
public class BasicFindLabelEngine extends AbstractFindLabelEngine {
    /**
     * A predicate to identify edit parts which are label and which can thus be
     * matched with text.
     */
    private static final Predicate<EditPart> IS_LABEL_EDIT_PART = new Predicate<EditPart>() {
        public boolean apply(final EditPart part) {
            final boolean result;
            if (part instanceof AbstractGraphicalEditPart && part instanceof ITextAwareEditPart) {
                result = true;
            } else if (part instanceof IGraphicalEditPart && ((IGraphicalEditPart) part).getFigure() instanceof AirStyleDefaultSizeNodeFigure) {
                result = true;
            } else {
                result = false;
            }
            return result;
        }
    };

    /**
     * All diagrams label.
     */
    protected List allDiagramLabels;

    /**
     * The editor where the search is performed.
     */
    protected DiagramEditor editor;

    /**
     * This constructor will initialize the engine with all labels contained
     * within <tt>currentEditor</tt>.
     * 
     * @param currentEditor
     *            The editor on which the search is performed.
     */
    public BasicFindLabelEngine(final DiagramEditor currentEditor) {
        super();
        this.editor = currentEditor;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.common.tools.api.find.AbstractFindLabelEngine#filterLabels(java.lang.String)
     */
    @Override
    protected List filterLabels(final String search) {
        final List<EditPart> result = Lists.newArrayList();
        for (Object obj : allLabels()) {
            final AbstractGraphicalEditPart label = (AbstractGraphicalEditPart) obj;
            if (matches(label, search)) {
                result.add(label);
            }
        }
        return result;
    }

    private boolean matches(final AbstractGraphicalEditPart label, final String search) {
        return matches(getText(label), search);
    }

    private boolean matches(final String text, final String search) {
        return text.indexOf(search) != -1;
    }

    /**
     * Returns the text associated to a label edit part and which is used for
     * textual search.
     */
    private String getText(final AbstractGraphicalEditPart label) {
        final IFigure figure = label.getFigure();
        final String text = getText(figure);
        return text != null ? text : ""; //$NON-NLS-1$
    }

    private String getText(final IFigure figure) {
        Option<String> result = new FigureQuery(figure).getText();
        if (result.some()) {
            return result.get();
        }
        return null;
    }

    private List allLabels() {
        if (allDiagramLabels == null) {
            findAllDiagramLabels();
        }
        return allDiagramLabels;
    }

    /**
     * Finds all the edit parts in the editor which represent labels, and are
     * thus candidates for text matching.
     */
    private void findAllDiagramLabels() {
        allDiagramLabels = Lists.newArrayList();
        allDiagramLabels.addAll(Collections2.filter((Collection<? extends EditPart>) findAllEditParts(), IS_LABEL_EDIT_PART));
    }

    private Collection<? extends EditPart> findAllEditParts() {
        final DiagramEditPart diagram = editor.getDiagramEditPart();
        final Collection<? extends EditPart> roots = Lists.newArrayList(diagram);
        roots.addAll(diagram.getConnections());
        final Collection<? extends EditPart> editParts = Lists.newArrayList();
        for (EditPart root : roots) {
            addAllContainedEditParts(root, editParts);
        }
        return editParts;
    }

    /**
     * Add an edit part and all its descendants into the specified collection.
     */
    private void addAllContainedEditParts(final EditPart ep, final Collection coll) {
        coll.add(ep);
        for (Object child : ep.getChildren()) {
            addAllContainedEditParts((EditPart) child, coll);
        }
    }
}
