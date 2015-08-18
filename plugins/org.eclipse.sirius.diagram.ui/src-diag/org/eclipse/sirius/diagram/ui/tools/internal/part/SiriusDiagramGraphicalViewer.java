/******************************************************************************
 * Copyright (c) 2002, 2014 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    IBM Corporation - initial API and implementation
 *    Obeo - Adaptation
 ****************************************************************************/

package org.eclipse.sirius.diagram.ui.tools.internal.part;

import java.lang.reflect.Field;
import java.util.List;

import org.eclipse.draw2d.DeferredUpdateManager;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LightweightSystem;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.internal.parts.PaletteToolTransferDropTargetListener;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramGraphicalViewer;
import org.eclipse.jface.util.TransferDropTargetListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.sirius.diagram.DiagramPlugin;
import org.eclipse.sirius.diagram.ui.tools.api.part.IDiagramDialectGraphicalViewer;
import org.eclipse.sirius.diagram.ui.tools.internal.editor.SiriusPaletteToolDropTargetListener;
import org.eclipse.sirius.diagram.ui.tools.internal.graphical.edit.policies.ChangeBoundRequestRecorder;
import org.eclipse.ui.PlatformUI;

/**
 * {@link org.eclipse.gef.GraphicalViewer} used for the
 * {@link org.eclipse.sirius.diagram.DDiagram} modeler.
 * 
 * @author mchauvin
 */
@SuppressWarnings("restriction")
public class SiriusDiagramGraphicalViewer extends DiagramGraphicalViewer implements IDiagramDialectGraphicalViewer {

    private ChangeBoundRequestRecorder recorder = new ChangeBoundRequestRecorder();

    /**
     * A registry of editparts on the diagram, mapping an element's id string to
     * a list of <code>EditParts</code>.
     */
    private final SemanticElementToEditPartsMap elementToEditPartsMap = new SemanticElementToEditPartsMap();

    /**
     * return the viewer's change bound request recorder.
     * 
     * @return the viewer's change bound request recorder.
     */
    public ChangeBoundRequestRecorder getChangeBoundRequestRecorder() {
        return recorder;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.ui.tools.api.part.IDiagramDialectGraphicalViewer#registerEditPartForSemanticElement(org.eclipse.emf.ecore.EObject,
     *      org.eclipse.gef.EditPart)
     */
    public void registerEditPartForSemanticElement(final EObject element, final EditPart ep) {
        elementToEditPartsMap.registerEditPartForElement(element, ep);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.ui.tools.api.part.IDiagramDialectGraphicalViewer#unregisterEditPartForSemanticElement(org.eclipse.emf.ecore.EObject,
     *      org.eclipse.gef.EditPart)
     */
    public void unregisterEditPartForSemanticElement(final EObject element, final EditPart ep) {
        elementToEditPartsMap.unregisterEditPartForElement(element, ep);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.ui.tools.api.part.IDiagramDialectGraphicalViewer#unregisterEditPart(org.eclipse.gef.EditPart)
     */
    public void unregisterEditPart(final EditPart ep) {
        elementToEditPartsMap.unregisterEditPart(ep);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.ui.tools.api.part.IDiagramDialectGraphicalViewer#findEditPartsForElement(String,
     *      Class)
     */
    public <T extends EditPart> List<T> findEditPartsForElement(final EObject element, final Class<T> editPartClass) {
        return elementToEditPartsMap.findEditPartsForElement(element, editPartClass);
    }

    /**
     * <code>boolean</code> <code>true</code> if client wishes to disable
     * updates on the figure canvas, <code>false</code> indicates normal updates
     * are to take place.
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.diagram.ui.parts.DiagramGraphicalViewer#enableUpdates(boolean)
     */
    @Override
    public void enableUpdates(final boolean enable) {
        if (enable) {
            getLightweightSystemWithUpdateToggle().enableUpdates();
        } else {
            getLightweightSystemWithUpdateToggle().disableUpdates();
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.diagram.ui.parts.DiagramGraphicalViewer#areUpdatesDisabled()
     */
    @Override
    public boolean areUpdatesDisabled() {
        return getLightweightSystemWithUpdateToggle().getToggleUpdateManager().shouldDisableUpdates();
    }

    /**
     * Special version of the ToggleUpdateManager normally used in the
     * super-class, with a workaround to avoid infinite loops triggered during
     * figure validation.
     * 
     * @author ymortier
     */
    private static class ToggleUpdateManager extends DeferredUpdateManager {

        private static final String ERROR_INVALID_FIGURES_FIELD = "Error while getting the invalidFigures field.";

        private static final String ERROR_VALIDATING_FIELD = "Error while getting the validating field.";

        private boolean disableUpdates;

        private final Field invalidFiguresField;

        private final Field validatingField;

        public ToggleUpdateManager() {
            invalidFiguresField = getAccessibleField("invalidFigures"); //$NON-NLS-1$
            validatingField = getAccessibleField("validating"); //$NON-NLS-1$
        }

        private Field getAccessibleField(final String name) {
            try {
                final Field field = this.getClass().getSuperclass().getDeclaredField(name);
                field.setAccessible(true);
                return field;
            } catch (final SecurityException e) {
                DiagramPlugin.getDefault().logError("Error while getting the " + name + " field.", e);
            } catch (final NoSuchFieldException e) {
                DiagramPlugin.getDefault().logError("Error while getting the " + name + " field.", e);
            }
            return null;
        }

        /**
         * @return the disableUpdates
         */
        public boolean shouldDisableUpdates() {
            return disableUpdates;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        protected void sendUpdateRequest() {
            PlatformUI.getWorkbench().getDisplay().asyncExec(new UpdateRequest());
        }

        /**
         * @param disableUpdates
         *            the disableUpdates to set
         */
        public synchronized void setDisableUpdates(final boolean disableUpdates) {
            final boolean prevDisableUpdates = this.disableUpdates;
            this.disableUpdates = disableUpdates;
            if (!disableUpdates && prevDisableUpdates != disableUpdates) {
                sendUpdateRequest();
            }
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public synchronized void performUpdate() {
            if (!shouldDisableUpdates()) {
                super.performUpdate();
            }
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void performValidation() {
            if (!shouldDisableUpdates()) {
                if (getInvalidFigures().isEmpty() || getValidating()) {
                    return;
                }
                try {
                    IFigure fig;
                    setValidating(true);
                    fireValidating();
                    for (int i = 0; i < getInvalidFigures().size(); i++) {
                        fig = (IFigure) getInvalidFigures().get(i);
                        fig.validate();
                    }
                } finally {
                    getInvalidFigures().clear();
                    setValidating(false);
                }
            }
        }

        /**
         * Return the list of figures that are invalids.
         * 
         * @return list of invalids figures.
         */
        public List getInvalidFigures() {
            if (invalidFiguresField != null) {
                try {
                    return (List) invalidFiguresField.get(this);
                } catch (final IllegalArgumentException e) {
                    DiagramPlugin.getDefault().logError(ERROR_INVALID_FIGURES_FIELD, e);
                } catch (final IllegalAccessException e) {
                    DiagramPlugin.getDefault().logError(ERROR_INVALID_FIGURES_FIELD, e);
                }
            }
            return null;
        }

        /**
         * Return the value of validating.
         * 
         * @return the value of validating.
         */
        public boolean getValidating() {
            if (validatingField != null) {
                try {
                    return validatingField.getBoolean(this);
                } catch (final IllegalArgumentException e) {
                    DiagramPlugin.getDefault().logError(ERROR_VALIDATING_FIELD, e);
                } catch (final IllegalAccessException e) {
                    DiagramPlugin.getDefault().logError(ERROR_VALIDATING_FIELD, e);
                }
            }
            return false;
        }

        /**
         * Define the validating attribute.
         * 
         * @param validating
         *            the new validating value.
         */
        public void setValidating(final boolean validating) {
            if (validatingField != null) {
                try {
                    validatingField.setBoolean(this, validating);
                } catch (final IllegalArgumentException e) {
                    DiagramPlugin.getDefault().logError(ERROR_VALIDATING_FIELD, e);
                } catch (final IllegalAccessException e) {
                    DiagramPlugin.getDefault().logError(ERROR_VALIDATING_FIELD, e);
                }
            }
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void queueWork() {
            if (!shouldDisableUpdates()) {
                super.queueWork();
            }
        }
    }

    /**
     * @author ymortier
     */
    private static class LightweightSystemWithUpdateToggle extends LightweightSystem {

        /**
         * @return the {@link ToggleUpdateManager}.
         */
        public ToggleUpdateManager getToggleUpdateManager() {
            return (ToggleUpdateManager) getUpdateManager();
        }

        /**
         * disable updates on the figure canvas
         */
        public void disableUpdates() {
            getToggleUpdateManager().setDisableUpdates(true);
        }

        /**
         * allow updates on the figure canvas to occur
         */
        public void enableUpdates() {
            getToggleUpdateManager().setDisableUpdates(false);
        }
    }

    private LightweightSystemWithUpdateToggle getLightweightSystemWithUpdateToggle() {
        return (LightweightSystemWithUpdateToggle) getLightweightSystem();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.diagram.ui.parts.DiagramGraphicalViewer#createLightweightSystem()
     */
    @Override
    protected LightweightSystem createLightweightSystem() {
        final LightweightSystem lws = new LightweightSystemWithUpdateToggle();
        lws.setUpdateManager(new ToggleUpdateManager());
        return lws;
    }

    @Override
    public void addDropTargetListener(TransferDropTargetListener listener) {
        if (!(listener instanceof PaletteToolTransferDropTargetListener) || listener instanceof SiriusPaletteToolDropTargetListener) {
            super.addDropTargetListener(listener);
        }
    }

    @Override
    public void setSelection(ISelection newSelection) {
        // This setFocus to null must be provided by the super class directly.
        // The corresponding GEF bugzilla is 458416.
        if (newSelection instanceof IStructuredSelection) {
            setFocus(null);
        }

        super.setSelection(newSelection);
    }
}
