/******************************************************************************
 * Copyright (c) 2002, 2021 IBM Corporation and others and others.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    IBM Corporation - initial API and implementation
 *    Obeo - Adaptation
 ****************************************************************************/

package org.eclipse.sirius.diagram.ui.tools.internal.part;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.MessageFormat;
import java.util.List;

import org.eclipse.draw2d.DeferredUpdateManager;
import org.eclipse.draw2d.EventDispatcher;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LightweightSystem;
import org.eclipse.draw2d.SWTEventDispatcher;
import org.eclipse.draw2d.ToolTipHelper;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.internal.parts.PaletteToolTransferDropTargetListener;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramGraphicalViewer;
import org.eclipse.jface.util.TransferDropTargetListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.sirius.common.ui.tools.api.util.EclipseUIUtil;
import org.eclipse.sirius.diagram.tools.internal.DiagramPlugin;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.diagram.ui.tools.api.part.IDiagramDialectGraphicalViewer;
import org.eclipse.sirius.diagram.ui.tools.internal.editor.SiriusPaletteToolDropTargetListener;
import org.eclipse.sirius.diagram.ui.tools.internal.graphical.edit.policies.ChangeBoundRequestRecorder;
import org.eclipse.sirius.viewpoint.SiriusPlugin;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.graphics.Color;

/**
 * {@link org.eclipse.gef.GraphicalViewer} used for the {@link org.eclipse.sirius.diagram.DDiagram} modeler.
 *
 * @author mchauvin
 */
@SuppressWarnings("restriction")
public class SiriusDiagramGraphicalViewer extends DiagramGraphicalViewer implements IDiagramDialectGraphicalViewer {
    private static final int TOOLTIP_HIDE_DELAY = 10_000;

    private ChangeBoundRequestRecorder recorder = new ChangeBoundRequestRecorder();

    /**
     * A registry of editparts on the diagram, mapping an element's id string to a list of <code>EditParts</code>.
     */
    private final SemanticElementToEditPartsMap elementToEditPartsMap = new SemanticElementToEditPartsMap();

    /**
     * The background color used for the diagram. Stored here so that we can properly dispose if on close.
     */
    private Color backgroundColor;

    /**
     * return the viewer's change bound request recorder.
     *
     * @return the viewer's change bound request recorder.
     */
    public ChangeBoundRequestRecorder getChangeBoundRequestRecorder() {
        return recorder;
    }

    @Override
    public void registerEditPartForSemanticElement(final EObject element, final EditPart ep) {
        elementToEditPartsMap.registerEditPartForElement(element, ep);
    }

    @Override
    public void unregisterEditPartForSemanticElement(final EObject element, final EditPart ep) {
        elementToEditPartsMap.unregisterEditPartForElement(element, ep);
    }

    @Override
    public void unregisterEditPart(final EditPart ep) {
        elementToEditPartsMap.unregisterEditPart(ep);
    }

    @Override
    public <T extends EditPart> List<T> findEditPartsForElement(final EObject element, final Class<T> editPartClass) {
        return elementToEditPartsMap.findEditPartsForElement(element, editPartClass);
    }

    @Override
    protected void handleDispose(DisposeEvent e) {
        super.handleDispose(e);
        if (this.backgroundColor != null) {
            this.backgroundColor.dispose();
            this.backgroundColor = null;
        }
    }

    /**
     * <code>boolean</code> <code>true</code> if client wishes to disable updates on the figure canvas,
     * <code>false</code> indicates normal updates are to take place.
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

    @Override
    public boolean areUpdatesDisabled() {
        return getLightweightSystemWithUpdateToggle().getToggleUpdateManager().shouldDisableUpdates();
    }

    /**
     * Special version of the ToggleUpdateManager normally used in the super-class, with a workaround to avoid infinite
     * loops triggered during figure validation.
     *
     * @author ymortier
     */
    private static class ToggleUpdateManager extends DeferredUpdateManager {

        private boolean disableUpdates;

        private final Field invalidFiguresField;

        private final Field validatingField;

        ToggleUpdateManager() {
            invalidFiguresField = getAccessibleField("invalidFigures"); //$NON-NLS-1$
            validatingField = getAccessibleField("validating"); //$NON-NLS-1$
        }

        private Field getAccessibleField(final String name) {
            try {
                final Field field = this.getClass().getSuperclass().getDeclaredField(name);
                field.setAccessible(true);
                return field;
            } catch (final SecurityException e) {
                DiagramPlugin.getDefault().logError(MessageFormat.format(Messages.ToggleUpdateManager_fieldAccessError, name), e);
            } catch (final NoSuchFieldException e) {
                DiagramPlugin.getDefault().logError(MessageFormat.format(Messages.ToggleUpdateManager_fieldAccessError, name), e);
            }
            return null;
        }

        /**
         * @return the disableUpdates
         */
        public boolean shouldDisableUpdates() {
            return disableUpdates;
        }

        @Override
        protected void sendUpdateRequest() {
            EclipseUIUtil.displayAsyncExec(new UpdateRequest());
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

        @Override
        public synchronized void performUpdate() {
            if (!shouldDisableUpdates()) {
                super.performUpdate();
            }
        }

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
                    DiagramPlugin.getDefault().logError(MessageFormat.format(Messages.ToggleUpdateManager_fieldAccessError, invalidFiguresField.getName()), e);
                } catch (final IllegalAccessException e) {
                    DiagramPlugin.getDefault().logError(MessageFormat.format(Messages.ToggleUpdateManager_fieldAccessError, invalidFiguresField.getName()), e);
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
                    DiagramPlugin.getDefault().logError(MessageFormat.format(Messages.ToggleUpdateManager_fieldAccessError, validatingField.getName()), e);
                } catch (final IllegalAccessException e) {
                    DiagramPlugin.getDefault().logError(MessageFormat.format(Messages.ToggleUpdateManager_fieldAccessError, validatingField.getName()), e);
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
                    DiagramPlugin.getDefault().logError(MessageFormat.format(Messages.ToggleUpdateManager_fieldAccessError, validatingField.getName()), e);
                } catch (final IllegalAccessException e) {
                    DiagramPlugin.getDefault().logError(MessageFormat.format(Messages.ToggleUpdateManager_fieldAccessError, validatingField.getName()), e);
                }
            }
        }

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
        if (getContents() != null) {
            // This setFocus to null must be provided by the super class
            // directly.
            // The corresponding GEF bugzilla is 458416.
            if (newSelection instanceof IStructuredSelection) {
                setFocus(null);
            }

            super.setSelection(newSelection);
        }
    }

    /**
     * Returns the EditPart located at the given location which will accept mouse events.
     *
     * @param location
     *            The mouse location
     * @return The EditPart located at the given location which will accept mouse events
     */
    public EditPart findMouseEventTargetAt(Point location) {
        IFigure figure = getLightweightSystem().getRootFigure().findMouseEventTargetAt(location.x, location.y);
        EditPart part = null;
        while (part == null && figure != null) {
            part = (EditPart) getVisualPartMap().get(figure);
            figure = figure.getParent();
        }
        if (part == null) {
            return getContents();
        }
        return part;
    }

    @Override
    public void setEditDomain(EditDomain domain) {
        super.setEditDomain(domain);

        // Change the tooltip hide delay using reflection API
        EventDispatcher eventDispatcher = getEventDispatcher();
        Method getToolTipHelperMethod = null;
        try {
            getToolTipHelperMethod = SWTEventDispatcher.class.getDeclaredMethod("getToolTipHelper"); //$NON-NLS-1$
            getToolTipHelperMethod.setAccessible(true);
            ToolTipHelper toolTipHelper = (ToolTipHelper) getToolTipHelperMethod.invoke(eventDispatcher);
            toolTipHelper.setHideDelay(SiriusDiagramGraphicalViewer.TOOLTIP_HIDE_DELAY);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
            SiriusPlugin.getDefault().error(Messages.SiriusDiagramGraphicalViewer_tooltipDisplayDelay, e);
        }
    }

    /**
     * Remember the current background color used by the diagram, so that it can be properly disposed when the viewer is
     * closed.
     * 
     * @param color
     *            the background color used by the diagram.
     */
    public void setBackgroundColor(Color color) {
        if (this.backgroundColor != null) {
            this.backgroundColor.dispose();
        }
        this.backgroundColor = color;
    }
}
