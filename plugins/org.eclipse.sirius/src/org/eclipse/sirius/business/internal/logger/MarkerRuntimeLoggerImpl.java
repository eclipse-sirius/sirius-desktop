/*******************************************************************************
 * Copyright (c) 2009, 2015 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.internal.logger;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.business.api.logger.MarkerRuntimeLogger;
import org.eclipse.sirius.business.api.logger.RuntimeLogger;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.viewpoint.SiriusPlugin;

/**
 * Default implementation of {@link MarkerRuntimeLogger}.
 * 
 * @author smonnier
 */
public class MarkerRuntimeLoggerImpl implements RuntimeLogger, MarkerRuntimeLogger {
    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.api.logger.RuntimeLogger#error(org.eclipse.emf.ecore.EObject,
     *      org.eclipse.emf.ecore.EStructuralFeature, java.lang.String)
     */
    public void error(final EObject odesignObject, final EStructuralFeature feature, final String message) {
        MarkerRuntimeLoggerImpl.addMarkerFor(odesignObject, feature, message, IMarker.SEVERITY_ERROR);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.api.logger.RuntimeLogger#error(org.eclipse.emf.ecore.EObject,
     *      org.eclipse.emf.ecore.EStructuralFeature, java.lang.Throwable)
     */
    public void error(final EObject odesignObject, final EStructuralFeature feature, final Throwable exception) {
        String message = StringUtil.EMPTY_STRING;
        if (exception != null) {
            message = exception.getMessage();
        }
        error(odesignObject, feature, message);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.api.logger.RuntimeLogger#info(org.eclipse.emf.ecore.EObject,
     *      org.eclipse.emf.ecore.EStructuralFeature, java.lang.Throwable)
     */
    public void info(final EObject odesignObject, final EStructuralFeature feature, final Throwable exception) {
        String message = StringUtil.EMPTY_STRING;
        if (exception != null) {
            message = exception.getMessage();
        }
        info(odesignObject, feature, message);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.api.logger.RuntimeLogger#info(org.eclipse.emf.ecore.EObject,
     *      org.eclipse.emf.ecore.EStructuralFeature, java.lang.String)
     */
    public void info(final EObject odesignObject, final EStructuralFeature feature, final String message) {
        MarkerRuntimeLoggerImpl.addMarkerFor(odesignObject, feature, message, IMarker.SEVERITY_INFO);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.api.logger.RuntimeLogger#warning(org.eclipse.emf.ecore.EObject,
     *      org.eclipse.emf.ecore.EStructuralFeature, java.lang.Throwable)
     */
    public void warning(final EObject odesignObject, final EStructuralFeature feature, final Throwable exception) {
        String message = StringUtil.EMPTY_STRING;
        if (exception != null) {
            message = exception.getMessage();
        }
        warning(odesignObject, feature, message);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.api.logger.RuntimeLogger#warning(org.eclipse.emf.ecore.EObject,
     *      org.eclipse.emf.ecore.EStructuralFeature, java.lang.String)
     */
    public void warning(final EObject odesignObject, final EStructuralFeature feature, final String message) {
        MarkerRuntimeLoggerImpl.addMarkerFor(odesignObject, feature, message, IMarker.SEVERITY_WARNING);
    }

    /**
     * Clears all errors in the loggers.
     */
    public void clearAll() {
        // Nothing happens
    }

    /**
     * Clears all logged entries for the EObject.
     * 
     * @param object
     *            EObject we want to clearAll logged entries
     */
    public void clear(final EObject object) {
        if (object != null) {
            Resource objectResource = object.eResource();
            if (objectResource != null && objectResource.getURI() != null && objectResource.getURI().isPlatformResource()) {
                IWorkspaceRoot root = EcorePlugin.getWorkspaceRoot();
                final String relativePath = objectResource.getURI().toPlatformString(true);

                // relativePath can be null if the ODesign is located on a
                // CDO Repository
                if (relativePath != null && root != null) {
                    try {
                        if (!"".equals(relativePath) && root.findMember(relativePath) != null) { //$NON-NLS-1$
                            final IMarker[] markers = root.findMember(relativePath).findMarkers(MarkerRuntimeLogger.MARKER_TYPE, false, IResource.DEPTH_ZERO);
                            for (final IMarker marker : markers) {
                                marker.delete();
                            }
                        }
                    } catch (final CoreException e) {
                        SiriusPlugin.getDefault().getLog().log(e.getStatus());
                    }
                    for (final EObject subobject : object.eContents()) {
                        this.clear(subobject);
                    }
                }
            }
        }
    }

    /**
     * Adds a marker on the targeted EObject.
     * 
     * @param markerTarget
     *            An eObject for which an EMF marker must be added
     * @param message
     *            The message of the marker we want to display in the Problem
     *            View.
     * @param markerSeverity
     *            The severity of the marker
     */
    private static void addMarkerFor(final EObject markerTarget, final EStructuralFeature feature, final String message, final int markerSeverity) {
        try {
            final IResource resource = MarkerRuntimeLoggerImpl.findMarkerTargetResource(markerTarget);
            if (resource != null && !MarkerRuntimeLoggerImpl.checkIfAlreadyMarked(resource, markerTarget, message, markerSeverity)) {
                final IMarker marker = resource.createMarker(MarkerRuntimeLogger.MARKER_TYPE);
                marker.setAttribute(IMarker.SEVERITY, markerSeverity);
                marker.setAttribute(MarkerRuntimeLogger.URI_MARKER_ATTRIBUTE, EcoreUtil.getURI(markerTarget).toString());
                String markerMessage = StringUtil.EMPTY_STRING;
                if (feature != null) {
                    markerMessage += "Feature: " + feature.getName() + " ";
                }
                if (message != null) {
                    markerMessage += message;
                }
                marker.setAttribute(IMarker.MESSAGE, markerMessage);
            }
        } catch (final CoreException e) {
            SiriusPlugin.getDefault().getLog().log(e.getStatus());
        }
    }

    /**
     * Finds a IResource for an EObject.
     * 
     * @param markerTarget
     *            The {@link EObject} which we want the IResource.
     * @return The IResource of markerTarget
     */
    private static IResource findMarkerTargetResource(final EObject markerTarget) {
        if (markerTarget != null) {
            Resource markerTargetResource = markerTarget.eResource();
            if (markerTargetResource != null && markerTargetResource.getURI() != null && markerTargetResource.getURI().isPlatformResource()) {
                final String relativePath = markerTargetResource.getURI().toPlatformString(true);
                IWorkspaceRoot root = EcorePlugin.getWorkspaceRoot();
                if (root != null) {
                    return root.findMember(relativePath);
                }
            }
        }
        return null;
    }

    /**
     * Tests whether a marker with the specified properties already exists.
     * 
     * @param resource
     *            the resource in which to look for the marker.
     * @param markerTarget
     *            the object on which to look for a marker.
     * @param markerMessage
     *            the message of the marker to look for.
     * @param markerSeverity
     *            the severity of the marker to look for.
     * @return <code>true</code> if and only if a marker matching all the
     *         specified properties already exists.
     * @throws CoreException
     *             if the specified resource is not accessible.
     */
    private static boolean checkIfAlreadyMarked(final IResource resource, final EObject markerTarget, final String markerMessage, final int markerSeverity) throws CoreException {
        final IMarker[] markers = resource.findMarkers(MarkerRuntimeLogger.MARKER_TYPE, false, IResource.DEPTH_ZERO);
        if (markers != null && markers.length != 0) {
            for (final IMarker marker : markers) {
                final Object target = marker.getAttribute(MarkerRuntimeLogger.URI_MARKER_ATTRIBUTE);
                final Object severity = marker.getAttribute(IMarker.SEVERITY);
                final Object message = marker.getAttribute(IMarker.MESSAGE);
                if (MarkerRuntimeLoggerImpl.matchesTarget(markerTarget, target) && MarkerRuntimeLoggerImpl.matchesSeverity(markerSeverity, severity)
                        && MarkerRuntimeLoggerImpl.matchesMessage(markerMessage, message)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean matchesTarget(final EObject markerTarget, final Object target) {
        return target instanceof String && ((String) target).equals(EcoreUtil.getURI(markerTarget).toString());
    }

    private static boolean matchesSeverity(final Integer markerSeverity, final Object severity) {
        return severity instanceof Integer && (Integer) severity == markerSeverity;
    }

    private static boolean matchesMessage(final String markerMessage, final Object message) {
        return message instanceof String && ((String) message).equals(markerMessage);
    }
}
