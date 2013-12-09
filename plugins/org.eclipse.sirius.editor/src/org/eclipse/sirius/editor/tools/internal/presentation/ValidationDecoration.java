/*******************************************************************************
 * Copyright (c) 2009, 2010 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.editor.tools.internal.presentation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.provider.ComposedImage;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.IDecorationContext;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.LabelDecorator;
import org.eclipse.jface.viewers.LabelProviderChangedEvent;
import org.eclipse.sirius.ui.business.api.descriptor.ComposedImageDescriptor;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.plugin.AbstractUIPlugin;

public class ValidationDecoration extends LabelDecorator {
    /**
     * Default image descriptor for the error overlay.
     */
    public static final ImageDescriptor ERROR_OVERLAY_DESC = AbstractUIPlugin.imageDescriptorFromPlugin(SiriusEditPlugin.ID, "/icons/full/validation/error_co.gif"); //$NON-NLS-1$;

    /**
     * Image descriptor for warning overlay.
     */
    public static final ImageDescriptor WARNING_OVERLAY_DESC = AbstractUIPlugin.imageDescriptorFromPlugin(SiriusEditPlugin.ID, "/icons/full/validation/warning_co.gif"); //$NON-NLS-1$

    private List<ILabelProviderListener> listeners = new ArrayList<ILabelProviderListener>(1);

    private Map<Object, Integer> severityMap = new HashMap<Object, Integer>();

    /**
     * {@inheritDoc}
     */
    public void addListener(ILabelProviderListener listener) {
        listeners.add(listener);

    }

    /**
     * {@inheritDoc}
     */
    public Image decorateImage(Image image, Object element) {
        return image;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Image decorateImage(Image image, Object element, IDecorationContext context) {
        if (severityMap.containsKey(element)) {
            Integer severity = severityMap.get(element);
            if (severity == Diagnostic.ERROR || severity == Diagnostic.WARNING) {
                ComposedImage img = decorateSeverity(image, severity);
                final ImageDescriptor descriptor = new ComposedImageDescriptor(img);
                return SiriusEditPlugin.getPlugin().getImage(descriptor);
            }
        }
        return image;
    }

    private ComposedImage decorateSeverity(Image image, Integer severity) {
        List<Object> images = new ArrayList<Object>(2);
        images.add(image);
        if (severity == Diagnostic.ERROR) {
            images.add(SiriusEditPlugin.getPlugin().getImage(ERROR_OVERLAY_DESC));
        } else if (severity == Diagnostic.WARNING) {
            images.add(SiriusEditPlugin.getPlugin().getImage(WARNING_OVERLAY_DESC));
        }

        ComposedImage ci = new ComposedImage(images) {

            @Override
            public List<Point> getDrawPoints(Size size) {
                List<Point> results = new ArrayList<Point>();
                results.add(new Point());
                Point overlay = new Point();
                overlay.x = 0;
                overlay.y = 7;
                results.add(overlay);
                return results;
            }
        };
        return ci;
    }

    /**
     * {@inheritDoc}
     */
    public String decorateText(String text, Object element) {
        return text;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String decorateText(String text, Object element, IDecorationContext context) {
        return text;
    }

    /**
     * {@inheritDoc}
     */
    public void dispose() {
        listeners = new ArrayList<ILabelProviderListener>(1);
    }

    private void fireLabelEvent(final LabelProviderChangedEvent event) {
        for (ILabelProviderListener listener : listeners) {
            listener.labelProviderChanged(event);
        }
    }

    /**
     * {@inheritDoc}
     */
    public boolean isLabelProperty(Object element, String property) {
        return true;
    }

    private void notifyViewer(Map<Object, Integer> oldSeverityMap, Map<Object, Integer> newSeverityMap) {
        List<Object> changedObjects = new ArrayList<Object>();
        changedObjects.addAll(oldSeverityMap.keySet());
        changedObjects.addAll(newSeverityMap.keySet());
        fireLabelEvent(new LabelProviderChangedEvent(this, changedObjects.toArray()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean prepareDecoration(Object element, String originalText, IDecorationContext context) {
        return true;
    }

    private static void processDiagnostic(Diagnostic diagnostic, Map<Object, Integer> severityMap) {
        if (diagnostic.getChildren().size() == 0 && diagnostic.getCode() == 0) {
            Iterator<Object> it = severityMap.keySet().iterator();
            while (it.hasNext()) {
                Object obj2 = it.next();
                severityMap.put(obj2, 0);
            }
            return;
        }
        List<?> dataEntries = diagnostic.getData();
        for (Object obj : dataEntries) {
            if (obj instanceof EObject) {
                severityMap.put(obj, diagnostic.getSeverity());
            }
        }
        for (Diagnostic childDiagnostic : diagnostic.getChildren()) {
            processDiagnostic(childDiagnostic, severityMap);
        }
    }

    /**
     * {@inheritDoc}
     */
    public void removeListener(ILabelProviderListener listener) {
        listeners.remove(listener);
    }

    /**
     * Sets the diagnostics for the validation.
     * 
     * @param diagnostic
     *            the diagnostics.
     */
    public void setDiagnostics(Diagnostic diagnostic) {
        Map<Object, Integer> oldSeverityMap = severityMap;
        processDiagnostic(diagnostic, severityMap);
        notifyViewer(oldSeverityMap, severityMap);
    }

}
