/*******************************************************************************
 * Copyright (c) 2011 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.tools.internal.views.common.navigator;

import java.util.Collections;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.sirius.business.api.helper.SiriusUtil;
import org.eclipse.sirius.business.api.modelingproject.ModelingProject;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionStatus;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ui.business.api.preferences.SiriusUIPreferencesKeys;
import org.eclipse.sirius.ui.business.api.session.IEditingSession;
import org.eclipse.sirius.ui.business.api.session.SessionUIManager;
import org.eclipse.sirius.ui.tools.api.color.VisualBindingManager;
import org.eclipse.sirius.ui.tools.api.views.ViewHelper;
import org.eclipse.sirius.ui.tools.api.views.common.item.AnalysisResourceItem;
import org.eclipse.sirius.ui.tools.api.views.common.item.CommonSessionItem;
import org.eclipse.sirius.ui.tools.api.views.common.item.ItemWrapper;
import org.eclipse.sirius.ui.tools.internal.views.common.FileSessionFinder;
import org.eclipse.sirius.ui.tools.internal.views.common.SessionLabelProvider;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.navigator.ICommonContentExtensionSite;
import org.eclipse.ui.navigator.ICommonLabelProvider;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

/**
 * Sirius label provider for common navigator.
 * 
 * @author mporhel
 * 
 */
public class SiriusCommonLabelProvider implements ICommonLabelProvider, IColorProvider {

    private static final String DIRTY = "*";

    private ILabelProvider sessionLabelProvider;

    /**
     * Constructor.
     */
    public SiriusCommonLabelProvider() {
        sessionLabelProvider = new SessionLabelProvider(ViewHelper.INSTANCE.createAdapterFactory());
    }

    /**
     * {@inheritDoc}
     */
    public Image getImage(Object element) {
        Image img = null;
        if (!(element instanceof IResource) && sessionLabelProvider != null) {
            try {

                // Let eclipse look for file and project icons + nature
                // decoration
                img = sessionLabelProvider.getImage(element);
            } catch (IllegalStateException e) {
                // This can happen when trying to get the label of a CDOObject
                // which
                // Transaction has just been closed
                // Nothing to do, null will returned
            } catch (NullPointerException e) {
                // This can happen when trying to get the label of a CDOObject
                // which transaction has just been closed
                // Nothing to do, null will returned
            }
        }
        return img;
    }

    /**
     * {@inheritDoc}
     */
    public String getText(Object element) {
        String text = null;
        if (element instanceof IProject) {
            IProject project = (IProject) element;
            Option<ModelingProject> modelingProject = ModelingProject.asModelingProject(project);
            if (modelingProject.some() && shoudlShowSessionDirtyStatus(modelingProject.get().getSession())) {
                text = DIRTY + project.getName();
            }
        } else if (element instanceof IFile) {
            text = doGetFileText((IFile) element);
        } else if (!(element instanceof IResource) && sessionLabelProvider != null) {
            try {
                // Let eclipse look for file and project icons + nature
                // decoration
                text = sessionLabelProvider.getText(element);
            } catch (IllegalStateException e) {
                // This can happen when trying to get the label of a CDOObject
                // which transaction has just been closed
                // Nothing to do, null will returned
            } catch (NullPointerException e) {
                // This can happen when trying to get the label of a CDOObject
                // which transaction has just been closed
                // Nothing to do, null will returned
            }
        }
        return text;
    }

    private String doGetFileText(IFile file) {
        String text = null;
        IProject parentProject = file.getProject();
        if (parentProject != null) {
            if (SiriusUtil.SESSION_RESOURCE_EXTENSION.equals(file.getFileExtension())) {

                // Non modeling project case.
                if (!ModelingProject.hasModelingProjectNature(parentProject)) {
                    List<Session> openedSessions = getOpenSessions(file);
                    if (openedSessions.size() == 1) {
                        // legacy case
                        boolean dirty = shoudlShowSessionDirtyStatus(openedSessions.iterator().next());
                        text = dirty ? DIRTY + file.getName() : file.getName();
                    }
                }

            } else {
                // Transient case
                Iterable<Session> transientSessions = Iterables.filter(getOpenSessions(file), new SiriusCommonContentProvider.TransientSessionPredicate());
                if (Iterables.size(transientSessions) == 1) {
                    boolean dirty = shoudlShowSessionDirtyStatus(transientSessions.iterator().next());
                    text = dirty ? DIRTY + file.getName() : file.getName();
                }
            }
        }

        return text;
    }

    /*
     * Look for opened sessions on parent file : detect main aird for non
     * modeling projects, all aird for modeling ones, semantic file for
     * transient sessions.
     */
    private List<Session> getOpenSessions(IFile file) {

        return Lists.newArrayList(Iterables.filter(FileSessionFinder.getSelectedSessions(Collections.singletonList(file)), new Predicate<Session>() {
            public boolean apply(Session input) {
                return input.isOpen();
            }
        }));
    }

    /**
     * Test if the "*" prefix should be displayed : the session should be dirty
     * and if the preference DesignerUIPreferencesKeys.PREF_SAVE_WHEN_NO_EDITOR
     * value is true, it should be displayed only when there is no editor.
     * 
     * @param session
     *            the session to test.
     * @return true if the dirty decorator
     */
    public static boolean shoudlShowSessionDirtyStatus(Session session) {
        IEditingSession uiSession = SessionUIManager.INSTANCE.getUISession(session);

        if (session != null && uiSession != null && session.isOpen() && uiSession.isOpen()) {

            IPreferenceStore preferenceStore = SiriusEditPlugin.getPlugin().getPreferenceStore();
            boolean shouldBeSavedWithoutEditor = preferenceStore != null && preferenceStore.getBoolean(SiriusUIPreferencesKeys.PREF_SAVE_WHEN_NO_EDITOR.name());

            // When option PREF_SAVE_WHEN_NO_EDITOR is enabled, show dirty
            // status only when there si no editors to detect bugs.
            if (SessionStatus.DIRTY == session.getStatus() && (!shouldBeSavedWithoutEditor || uiSession.getEditors().isEmpty())) {
                return true;
            }

        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    public void addListener(ILabelProviderListener listener) {
        if (sessionLabelProvider != null) {
            sessionLabelProvider.addListener(listener);
        }
    }

    /**
     * {@inheritDoc}
     */
    public void removeListener(ILabelProviderListener listener) {
        if (sessionLabelProvider != null) {
            sessionLabelProvider.removeListener(listener);
        }
    }

    /**
     * {@inheritDoc}
     */
    public boolean isLabelProperty(Object element, String property) {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    public Color getForeground(final Object element) {

        Color foreground = null;
        try {
            // In modeling project, do not change the color for resource items.
            if (element instanceof ItemWrapper) {
                if (!isInModelingProject((ItemWrapper) element) || !(element instanceof AnalysisResourceItem)) {
                    foreground = VisualBindingManager.getDefault().getColorFromName("dark_gray"); //$NON-NLS-1$
                }
            } else if (element instanceof DRepresentation) {
                foreground = VisualBindingManager.getDefault().getColorFromName("dark_blue"); //$NON-NLS-1$
            }
        } catch (IllegalStateException e) {
            // This can happen when trying to get the label of a CDOObject which
            // Transaction has just been closed
            // Nothing to do, null will returned
        } catch (NullPointerException e) {
            // This can happen when trying to get the label of a CDOObject
            // which transaction has just been closed
            // Nothing to do, null will returned
        }
        return foreground;
    }

    /**
     * Check that the given object is in a modeling project.
     * 
     * @param object
     *            the object to check.
     * @return true if the object is in a modeling project.
     */
    private boolean isInModelingProject(CommonSessionItem object) {
        IResource parentResource = null;

        CommonSessionItem wrapper = object;
        while (wrapper != null && wrapper.getParent() != null && parentResource == null) {
            Object parent = wrapper.getParent();
            if (parent instanceof IResource) {
                parentResource = (IResource) parent;
            } else if (parent instanceof CommonSessionItem) {
                wrapper = (CommonSessionItem) wrapper.getParent();
            } else {
                break;
            }
        }

        if (parentResource != null) {
            IProject project = parentResource.getProject();
            return project != null ? ModelingProject.hasModelingProjectNature(project) : false;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    public Color getBackground(Object element) {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public void dispose() {
        if (sessionLabelProvider != null) {
            sessionLabelProvider.dispose();
            sessionLabelProvider = null;
        }
    }

    /**
     * {@inheritDoc}
     */
    public void saveState(IMemento aMemento) {
    }

    /**
     * {@inheritDoc}
     */
    public void restoreState(IMemento aMemento) {
    }

    /**
     * {@inheritDoc}
     */
    public String getDescription(Object anElement) {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public void init(ICommonContentExtensionSite aConfig) {

    }
}
