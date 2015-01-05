/*******************************************************************************
 * Copyright (c) 2009, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.debug.properties;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.properties.sections.AdvancedPropertySection;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.sirius.ext.draw2d.figure.FigureUtilities;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.IPropertySourceProvider;
import org.eclipse.ui.views.properties.PropertyDescriptor;

/**
 * This property section provides GMF debug informations.
 */
public class GMFDebugPropertySection extends AdvancedPropertySection implements IPropertySourceProvider {

    /**
     * Returns the property source of the specified object.
     * 
     * @param object
     *            the object.
     * @return the property source of the specified object.
     */
    public IPropertySource getPropertySource(final Object object) {
        IPropertySource propSrc = null;
        if (object instanceof IPropertySource) {
            propSrc = (IPropertySource) object;
        } else if (object instanceof GraphicalEditPart) {
            propSrc = new GraphicalEditPartPropertySource((GraphicalEditPart) object);
        }
        return propSrc;
    }


    @Override
    protected IPropertySourceProvider getPropertySourceProvider() {
        return this;
    }

    /**
     * Modify/unwrap selection.
     * 
     * @param selected
     *            the current selected object
     * @return the unwrapped object
     */
    protected Object transformSelection(final Object selected) {
        if (selected instanceof EditPart) {
            return selected;
        }
        return selected;
    }

    @Override
    public void setInput(final IWorkbenchPart part, final ISelection selection) {
        if (selection.isEmpty() || !(selection instanceof StructuredSelection)) {
            super.setInput(part, selection);
            return;
        }
        final StructuredSelection structuredSelection = (StructuredSelection) selection;
        final ArrayList<Object> transformedSelection = new ArrayList<Object>(structuredSelection.size());
        final Iterator<?> it = structuredSelection.iterator();
        while (it.hasNext()) {
            final Object r = transformSelection(it.next());
            if (r != null) {
                transformedSelection.add(r);
            }
        }
        super.setInput(part, new StructuredSelection(transformedSelection));
    }

    private class GraphicalEditPartPropertySource implements IPropertySource {

        private final GraphicalEditPart part;

        public GraphicalEditPartPropertySource(final GraphicalEditPart part) {
            this.part = part;
        }

        public Object getEditableValue() {
            return null;
        }

        public IPropertyDescriptor[] getPropertyDescriptors() {
            final List<IPropertyDescriptor> propertyDescriptors = new ArrayList<IPropertyDescriptor>();
            final IFigure figure = part.getFigure();

            final Point location = figure.getBounds().getLocation();
            FigureUtilities.translateToAbsoluteByIgnoringScrollbar(figure, location);

            // If absolute location and relative location are the same only
            // absolute location is displayed because the location (used for id)
            // are the same.
            propertyDescriptors.add(new PropertyDescriptor(figure.getBounds().getLocation(), "Figure relative location"));
            propertyDescriptors.add(new PropertyDescriptor(location, "Figure absolute location"));
            final Dimension size = figure.getBounds().getSize();
            propertyDescriptors.add(new PropertyDescriptor(size, "Figure size"));
            propertyDescriptors.add(new PropertyDescriptor(partToString(part), "Object"));

            return propertyDescriptors.toArray(new IPropertyDescriptor[propertyDescriptors.size()]);
        }

        public Object getPropertyValue(final Object id) {
            Object value = null;
            if (id instanceof Point) {
                value = "x: " + ((Point) id).x + " y: " + ((Point) id).y;
            } else if (id instanceof Dimension) {
                value = "width: " + ((Dimension) id).width + " height: " + ((Dimension) id).height;
            } else if (id instanceof String) {
                value = id;
            }
            return value;
        }

        public boolean isPropertySet(final Object id) {
            return true;
        }

        public void resetPropertyValue(final Object id) {
            //
        }

        public void setPropertyValue(final Object id, final Object value) {
            //
        }
    }

    private String partToString(final GraphicalEditPart part) {
        return part.getClass().getName() + "@" + Integer.toHexString(part.hashCode());
    }
}
