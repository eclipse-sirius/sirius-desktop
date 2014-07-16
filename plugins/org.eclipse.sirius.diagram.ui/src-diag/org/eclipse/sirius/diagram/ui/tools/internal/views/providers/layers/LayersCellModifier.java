/*******************************************************************************
 * Copyright (c) 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.views.providers.layers;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramWorkbenchPart;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.sirius.business.api.query.IdentifiedElementQuery;
import org.eclipse.sirius.common.tools.api.util.EqualityHelper;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.description.Layer;
import org.eclipse.sirius.diagram.tools.internal.command.ChangeLayerActivationCommand;
import org.eclipse.sirius.diagram.ui.tools.api.editor.DDiagramEditor;
import org.eclipse.swt.widgets.Item;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.progress.IProgressService;

/**
 * The cell modifier.
 * 
 * @author mchauvin
 */
public class LayersCellModifier implements ICellModifier {

    /** The EMF adapter */
    private final LayersActivationAdapter layerActivationAdapter;

    private final IDiagramWorkbenchPart diagramPart;

    private final String[] layerColumns;

    /**
     * Construct a new cell modifier.
     * 
     * @param adapter
     *            the layer activation adapter
     * @param part
     *            the workbench diagram part
     * @param columns
     *            the layer table columns
     */
    public LayersCellModifier(final LayersActivationAdapter adapter, final IDiagramWorkbenchPart part, final String[] columns) {
        layerActivationAdapter = adapter;
        diagramPart = part;
        layerColumns = columns;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.viewers.ICellModifier#canModify(java.lang.Object,
     *      java.lang.String)
     */
    public boolean canModify(final Object element, final String property) {

        if (property.equals(layerColumns[0])) {
            /* first column */
            return true;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.viewers.ICellModifier#getValue(java.lang.Object,
     *      java.lang.String)
     */
    public Object getValue(final Object element, final String property) {

        final Layer layer = (Layer) element;
        Object result = null;

        if (property.equals(layerColumns[0])) {
            /* first column */
            final DiagramEditPart diaEditPart = diagramPart.getDiagramEditPart();
            final Object obj = diaEditPart.getModel();
            if (obj instanceof View) {
                final EObject designerElement = ((View) obj).getElement();
                if (designerElement instanceof DDiagram) {
                    final List<Layer> activatedLayers = ((DDiagram) designerElement).getActivatedLayers();
                    if (EqualityHelper.contains(activatedLayers, (EObject) element)) {
                        result = Boolean.TRUE;
                    } else {
                        result = Boolean.FALSE;
                    }
                }
            }
        } else {
            /* second column */
            result = new IdentifiedElementQuery(layer).getLabel();
        }
        return result;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.viewers.ICellModifier#modify(java.lang.Object,
     *      java.lang.String, java.lang.Object)
     */
    public void modify(final Object element, final String property, final Object value) {

        Object objElement;

        if (element instanceof Item) {
            objElement = ((Item) element).getData();
        } else {
            objElement = element;
        }

        final Layer layer = (Layer) objElement;

        if (property.equals(layerColumns[0])) {
            final DiagramEditPart diaEditPart = diagramPart.getDiagramEditPart();
            final Object obj = diaEditPart.getModel();
            if (obj instanceof View) {

                final EObject designerElement = ((View) obj).getElement();

                final DDiagramEditor diagramEditor = (DDiagramEditor) diagramPart.getDiagramGraphicalViewer().getProperty(DDiagramEditor.EDITOR_ID);

                if (designerElement instanceof DDiagram && diagramEditor != null) {
                    final DDiagram dDiagram = (DDiagram) designerElement;
                    layerActivationAdapter.setPaletteManager(diagramEditor.getPaletteManager());

                    final IWorkbench wb = PlatformUI.getWorkbench();
                    final IProgressService ps = wb.getProgressService();
                    try {
                        ps.busyCursorWhile(new IRunnableWithProgress() {

                            public void run(final IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
                                monitor.beginTask("Apply layer modifications...", 1);
                                TransactionalEditingDomain domain = TransactionUtil.getEditingDomain(dDiagram);
                                Command changeActivatedLayersCmd = new ChangeLayerActivationCommand(domain, dDiagram, layer, monitor);
                                domain.getCommandStack().execute(changeActivatedLayersCmd);
                                monitor.done();
                            }
                        });
                    } catch (final InvocationTargetException e) {
                        if (e.getCause() instanceof RuntimeException) {
                            throw (RuntimeException) e.getCause();
                        }
                        throw new RuntimeException(e.getCause());
                    } catch (final InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }
}
