/*******************************************************************************
 * Copyright (c) 2009, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.editor.properties.validation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.provider.ComposedImage;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.IDecorationContext;
import org.eclipse.jface.viewers.ILabelDecorator;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.LabelDecorator;
import org.eclipse.sirius.editor.tools.internal.marker.SiriusEditorInterpreterMarkerService;
import org.eclipse.sirius.ui.business.api.descriptor.ComposedImageDescriptor;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import com.google.common.collect.Lists;

/**
 * Decorates all elements by adding an "Error" or "Warning" decoration if any
 * Interpreter Error marker is defined on this element.
 * 
 * @author alagarde
 * 
 */
public class SiriusInterpreterErrorDecorator extends LabelDecorator implements ILabelDecorator {

    /**
     * Default image descriptor for the error overlay.
     */
    public static final ImageDescriptor ERROR_OVERLAY_DESC = AbstractUIPlugin.imageDescriptorFromPlugin(SiriusEditPlugin.ID, "/icons/full/validation/error_co.png"); //$NON-NLS-1$ ;

    /**
     * Default image descriptor for the error overlay, if the current element
     * has no error, but at least one of its children has.
     */
    public static final ImageDescriptor ERROR_OVERLAY_DESC_CHILDREN_ONLY = AbstractUIPlugin.imageDescriptorFromPlugin(SiriusEditPlugin.ID, "/icons/full/validation/error_co_children_only.png"); //$NON-NLS-1$ ;

    /**
     * Image descriptor for warning overlay.
     */
    public static final ImageDescriptor WARNING_OVERLAY_DESC = AbstractUIPlugin.imageDescriptorFromPlugin(SiriusEditPlugin.ID, "/icons/full/validation/warning_co.png"); //$NON-NLS-1$

    /**
     * Default image descriptor for the warning overlay, if the current element
     * has no warning, but at least one of its children has.
     */
    public static final ImageDescriptor WARNING_OVERLAY_DESC_CHILDREN_ONLY = AbstractUIPlugin.imageDescriptorFromPlugin(SiriusEditPlugin.ID, "/icons/full/validation/warning_co_children_only.png"); //$NON-NLS-1$ ;

    /**
     * Image descriptor for info overlay.
     */
    public static final ImageDescriptor INFO_OVERLAY_DESC = AbstractUIPlugin.imageDescriptorFromPlugin(SiriusEditPlugin.ID, "/icons/full/validation/info_co.png"); //$NON-NLS-1$

    /**
     * Default image descriptor for the info overlay, if the current element has
     * no info, but at least one of its children has.
     */
    public static final ImageDescriptor INFO_OVERLAY_DESC_CHILDREN_ONLY = AbstractUIPlugin.imageDescriptorFromPlugin(SiriusEditPlugin.ID, "/icons/full/validation/info_co_children_only.png"); //$NON-NLS-1$ ;

    private List<ILabelProviderListener> listeners = new ArrayList<ILabelProviderListener>(1);

    private IResource resource;

    /**
     * Default constructor.
     * 
     * @param uri
     *            uri of the ODesign to decorate
     */
    public SiriusInterpreterErrorDecorator(URI uri) {
        super();
        // If the URI is a plugin URI, we created a marker on the workspace
        // root
        if (uri.isPlatformPlugin()) {
            String pluginResourceString = uri.toString();
            if (pluginResourceString != null) {
                this.resource = ResourcesPlugin.getWorkspace().getRoot();
            }
        } else {
            String platformResourceString = uri.toPlatformString(true);
            if (platformResourceString == null) {
                platformResourceString = uri.toFileString();
            }

            if (platformResourceString != null) {
                this.resource = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(platformResourceString));
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addListener(ILabelProviderListener listener) {
        listeners.add(listener);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Image decorateImage(Image image, Object element) {
        return image;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Image decorateImage(Image image, Object element, IDecorationContext context) {
        String uriForElement = getURIForElement(element);
        if (uriForElement != null) {
            Collection<IMarker> markersForElementAndChildren = SiriusEditorInterpreterMarkerService.getValidationMarkersForElementAndChildren(this.resource, uriForElement);
            if (!markersForElementAndChildren.isEmpty()) {
                Collection<IMarker> markersForElementOnly = Lists.newArrayList(SiriusEditorInterpreterMarkerService.getValidationMarkersForElement(this.resource, uriForElement));

                Iterator<IMarker> iter = markersForElementAndChildren.iterator();
                int globalSeverity = -1;
                while ((globalSeverity < IMarker.SEVERITY_ERROR) && iter.hasNext()) {
                    IMarker marker = iter.next();
                    int severity;
                    try {
                        severity = ((Integer) marker.getAttribute(IMarker.SEVERITY)).intValue();
                    } catch (CoreException e) {
                        // We consider that it is just a warning
                        severity = IMarker.SEVERITY_WARNING;
                    }
                    if (severity > globalSeverity) {
                        globalSeverity = severity;
                    }
                }

                if (globalSeverity >= IMarker.SEVERITY_INFO) {
                    ComposedImage img = decorateSeverity(image, globalSeverity, markersForElementOnly.isEmpty());
                    final ImageDescriptor descriptor = new ComposedImageDescriptor(img);
                    return SiriusEditPlugin.getPlugin().getImage(descriptor);
                }
            }
        }

        return image;
    }

    private String getURIForElement(Object element) {
        String uri = null;
        if (element instanceof EObject) {
            EObject eObject = (EObject) element;
            Resource emfResource = eObject.eResource();
            if (emfResource != null) {
                String uriFragment = emfResource.getURIFragment((EObject) element);
                uri = emfResource.getURI().toString() + "#" + uriFragment;
            }
        }
        return uri;

    }

    private ComposedImage decorateSeverity(Image image, Integer severity, boolean childrenContainsErrorButNotElement) {
        List<Object> images = new ArrayList<Object>(2);
        images.add(image);
        if (severity == IMarker.SEVERITY_ERROR) {
            if (childrenContainsErrorButNotElement) {
                images.add(SiriusEditPlugin.getPlugin().getImage(ERROR_OVERLAY_DESC_CHILDREN_ONLY));
            } else {
                images.add(SiriusEditPlugin.getPlugin().getImage(ERROR_OVERLAY_DESC));
            }
        } else if (severity == IMarker.SEVERITY_WARNING) {
            if (childrenContainsErrorButNotElement) {
                images.add(SiriusEditPlugin.getPlugin().getImage(WARNING_OVERLAY_DESC_CHILDREN_ONLY));
            } else {
                images.add(SiriusEditPlugin.getPlugin().getImage(WARNING_OVERLAY_DESC));
            }
        } else if (severity == IMarker.SEVERITY_INFO) {
            if (childrenContainsErrorButNotElement) {
                images.add(SiriusEditPlugin.getPlugin().getImage(INFO_OVERLAY_DESC_CHILDREN_ONLY));
            } else {
                images.add(SiriusEditPlugin.getPlugin().getImage(INFO_OVERLAY_DESC));
            }
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
    @Override
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
    @Override
    public void dispose() {
        listeners = new ArrayList<ILabelProviderListener>(1);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isLabelProperty(Object element, String property) {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean prepareDecoration(Object element, String originalText, IDecorationContext context) {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeListener(ILabelProviderListener listener) {
        listeners.remove(listener);
    }

}
