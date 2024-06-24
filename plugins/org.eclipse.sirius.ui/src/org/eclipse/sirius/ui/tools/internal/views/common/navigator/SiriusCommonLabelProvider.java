/*******************************************************************************
 * Copyright (c) 2011, 2024 THALES GLOBAL SERVICES and others.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.tools.internal.views.common.navigator;

import java.util.Collections;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.Adapters;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.DecorationOverlayIcon;
import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.jface.viewers.IDecoration;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.sirius.business.api.helper.SiriusUtil;
import org.eclipse.sirius.business.api.modelingproject.ModelingProject;
import org.eclipse.sirius.business.api.query.DRepresentationDescriptorQuery;
import org.eclipse.sirius.business.api.query.DRepresentationQuery;
import org.eclipse.sirius.business.api.query.IFileQuery;
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
import org.eclipse.sirius.ui.tools.api.views.common.item.RepresentationDescriptionItem;
import org.eclipse.sirius.ui.tools.internal.views.common.FileSessionFinder;
import org.eclipse.sirius.ui.tools.internal.views.common.SessionLabelProvider;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.model.IWorkbenchAdapter;
import org.eclipse.ui.navigator.ICommonContentExtensionSite;
import org.eclipse.ui.navigator.ICommonLabelProvider;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

/**
 * Sirius label provider for common navigator.
 *
 * @author mporhel
 *
 */
public class SiriusCommonLabelProvider extends ColumnLabelProvider implements ICommonLabelProvider, IColorProvider {

    /**
     * Default image descriptor for the "Sirius Modeling" overlay.
     */
    public static final ImageDescriptor SIRIUS_MODELING_OVERLAY_DESC = AbstractUIPlugin.imageDescriptorFromPlugin(SiriusEditPlugin.ID, "/icons/full/ovr16/SessionDecorator.gif"); //$NON-NLS-1$ ;

    /**
     * Error image descriptor for the error decorator overlay.
     */
    public static final ImageDescriptor ERROR_OVERLAY_DESC = AbstractUIPlugin.imageDescriptorFromPlugin(SiriusEditPlugin.ID, "/icons/full/validation/error_co.png"); //$NON-NLS-1$ ;

    /**
     * Warning image descriptor for the warning decorator overlay.
     */
    public static final ImageDescriptor WARNING_OVERLAY_DESC = AbstractUIPlugin.imageDescriptorFromPlugin(SiriusEditPlugin.ID, "/icons/full/validation/warning_co.png"); //$NON-NLS-1$ ;

    /**
     * Info image descriptor for the info decorator overlay.
     */
    public static final ImageDescriptor INFO_OVERLAY_DESC = AbstractUIPlugin.imageDescriptorFromPlugin(SiriusEditPlugin.ID, "/icons/full/validation/info_co.png"); //$NON-NLS-1$ ;

    /**
     * The overlay decorator used to distinguish representation types/categories from actual representations
     * (instances).
     */
    private static final ImageDescriptor REPRESENTATION_DESCRIPTION_OVERLAY_DESC = AbstractUIPlugin.imageDescriptorFromPlugin(SiriusEditPlugin.ID, "/icons/full/ovr16/description_decorator.png"); //$NON-NLS-1$ ;

    private static final String DISABLED_REPRESENTATION_SUFFIX = "_disabled"; //$NON-NLS-1$

    private static final String DIRTY = "*"; //$NON-NLS-1$

    private ILabelProvider sessionLabelProvider;

    /**
     * Constructor.
     */
    public SiriusCommonLabelProvider() {
        sessionLabelProvider = new SessionLabelProvider(ViewHelper.INSTANCE.createAdapterFactory());
    }

    @Override
    public Image getImage(Object element) {
        Image img = null;
        if (!(element instanceof IResource) && sessionLabelProvider != null) {
            try {
                IProject adaptedProject = Adapters.adapt(element, IProject.class);
                // IProject image is not handled here: element is not an IResource but can be adapted to IProject.
                // Do not look for elements adapted as IProject in calls to sessionLabelProvider.
                // PossibleChildren / TriggerPoint expressions evaluation change with 2023-03
                // See result of
                // org.eclipse.ui.internal.navigator.extensions.NavigatorContentDescriptorManager.findDescriptors(Object,
                // Map<VisibilityAssistant, EvaluationCache>, VisibilityAssistant, boolean, boolean)
                // for org.eclipse.jdt.internal.core.PackageFragmentRoot or
                // org.eclipse.jdt.internal.core.JrtPackageFragmentRoot which are now adaptable into IProject
                // displayed in a EcoreModelingProject for example: src folder, JRE System library modules.
                if (adaptedProject == null) {

                    // Let eclipse look for file and project icons + nature decoration.
                    img = sessionLabelProvider.getImage(element);
                    if (img != null) {
                        // If the current element is a dangling representation, its icon is grayed. The grayed image is
                        // computed only once for each type of representation.
                        img = getDisabledRepresentationImage(element, img);
                        // If the current element is a RepresentationDescriptionItem, its icon is decorated with a small
                        // overlay to distinguish them from the instances.
                        img = getDecoratedRepresentationDescriptionImage(element, img);
                    }
                }
            } catch (IllegalStateException | NullPointerException e) {
                // This can happen when trying to get the label of a CDOObject which transaction has just been closed
                // Nothing to do, null will returned
            }
        } else if (element instanceof IFile) {
            // This file is not in a ModelingProject (check in <possibleChildren> and <triggerPoints> of
            // "org.eclipse.ui.navigator.navigatorContent" of plugin.xml)
            IFile file = (IFile) element;

            if (new IFileQuery(file).isResourceHandledByOpenedSession()) {
                // Add "Sirius Modeling" overlay on this semantic file.
                String fileExtension = file.getFileExtension();

                // -1 means no problem (see org.eclipse.core.resources.IResource.findMaxProblemSeverity(String, boolean,
                // int)).
                int severity = -1;
                try {
                    // We retrieve the severity from the problem marker.
                    severity = file.findMaxProblemSeverity(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE);
                } catch (CoreException e) {
                    // We do nothing (no severity decorator will be displayed)
                }
                // Create a key to store/restore the image in image registry of
                // SiriusEditPlugin
                String imgKey = fileExtension + "Decorated" + severity; //$NON-NLS-1$
                // Get the existing image (if any)
                img = SiriusEditPlugin.getPlugin().getImageRegistry().get(imgKey);
                // If the image has already been computed, use it.
                if (img == null) {
                    // Get the base image to overlay
                    ImageDescriptor imageDescriptor = null;
                    IWorkbenchAdapter wbAdapter = file.getAdapter(IWorkbenchAdapter.class);
                    if (wbAdapter != null) {
                        imageDescriptor = wbAdapter.getImageDescriptor(file);
                    }
                    if (imageDescriptor != null) {
                        // Add an overlay with the "Sirius Modeling" overlay
                        img = addIFileDecorators(severity, imageDescriptor);
                        SiriusEditPlugin.getPlugin().getImageRegistry().put(imgKey, img);
                    }
                }
            }
        }
        return img;
    }

    /***
     * Returns a disabled (grayed out) representation image if the given element is an invalid representation. The
     * grayed image is computed only once for each type of representation.
     * 
     * @param element
     *            the element whose representation image needs to be checked.
     * @param image
     *            the current image of the element.
     * @return the disabled representation image if the element is invalid; otherwise, the original image.
     */
    private Image getDisabledRepresentationImage(Object element, Image image) {
        Image disabledRepresentationImage = image;
        DRepresentationDescriptor descRep = getRepresentationDescriptor(element);
        if (descRep != null && isInvalidRepresentation(descRep)) {
            StringBuilder sB = new StringBuilder();
            sB.append(descRep.getClass().getName());
            RepresentationDescription description = descRep.getDescription();
            if (description != null) {
                sB.append('_');
                sB.append(description.getClass().getName());
            }
            sB.append(DISABLED_REPRESENTATION_SUFFIX);
            String key = sB.toString();
            Image disabledImage = SiriusEditPlugin.getPlugin().getImageRegistry().get(key);
            if (disabledImage == null) {
                ImageDescriptor desc = ImageDescriptor.createFromImage(disabledRepresentationImage);
                ImageDescriptor disabledDesc = ImageDescriptor.createWithFlags(desc, SWT.IMAGE_DISABLE);
                SiriusEditPlugin.getPlugin().getImageRegistry().put(key, disabledDesc);
                disabledImage = SiriusEditPlugin.getPlugin().getImageRegistry().get(key);
            }
            disabledRepresentationImage = disabledImage;
        }
        return disabledRepresentationImage;
    }

    /**
     * Returns a decorated representation description image if the given element is a RepresentationDescriptionItem. The
     * decorated image is computed only once for each representation description.
     * 
     * @param element
     *            the element whose representation description needs to be decorated.
     * @param image
     *            the current image of the element.
     * @return the decorated representation description image if the element is a RepresentationDescriptionItem;
     *         otherwise, the original image.
     */
    private Image getDecoratedRepresentationDescriptionImage(Object element, Image image) {
        Image decoratedRepresentationDescriptionImage = image;
        if (element instanceof RepresentationDescriptionItem) {
            Object wrapped = ((RepresentationDescriptionItem) element).getWrappedObject();
            if (wrapped instanceof RepresentationDescription) {
                // Decorate representation descriptions with a small overlay to distinguish them from the instances.
                String key = ((RepresentationDescription) wrapped).eClass().getName() + "_decorated"; //$NON-NLS-1$
                Image baseImg = decoratedRepresentationDescriptionImage;
                decoratedRepresentationDescriptionImage = SiriusEditPlugin.getPlugin().getImageRegistry().get(key);
                if (decoratedRepresentationDescriptionImage == null) {
                    ImageDescriptor[] imageDescriptors = new ImageDescriptor[5];
                    imageDescriptors[IDecoration.BOTTOM_RIGHT] = SiriusCommonLabelProvider.REPRESENTATION_DESCRIPTION_OVERLAY_DESC;
                    decoratedRepresentationDescriptionImage = new DecorationOverlayIcon(baseImg, imageDescriptors).createImage();
                    SiriusEditPlugin.getPlugin().getImageRegistry().put(key, decoratedRepresentationDescriptionImage);
                }
            }
        }
        return decoratedRepresentationDescriptionImage;
    }

    private Image addIFileDecorators(int severity, ImageDescriptor imageDescriptor) {
        ImageDescriptor[] imageDescriptors = new ImageDescriptor[5];
        imageDescriptors[IDecoration.TOP_RIGHT] = SiriusCommonLabelProvider.SIRIUS_MODELING_OVERLAY_DESC;
        switch (severity) {
        case IMarker.SEVERITY_ERROR:
            imageDescriptors[IDecoration.BOTTOM_LEFT] = SiriusCommonLabelProvider.ERROR_OVERLAY_DESC;
            break;
        case IMarker.SEVERITY_WARNING:
            imageDescriptors[IDecoration.BOTTOM_LEFT] = SiriusCommonLabelProvider.WARNING_OVERLAY_DESC;
            break;
        case IMarker.SEVERITY_INFO:
            imageDescriptors[IDecoration.BOTTOM_LEFT] = SiriusCommonLabelProvider.INFO_OVERLAY_DESC;
            break;
        default:
            break;
        }
        return new DecorationOverlayIcon(imageDescriptor.createImage(), imageDescriptors).createImage();
    }

    private boolean isInvalidRepresentation(Object element) {
        DRepresentationDescriptor representationDescriptor = getRepresentationDescriptor(element);
        return representationDescriptor != null && !new DRepresentationDescriptorQuery(representationDescriptor).isRepresentationValid();
    }

    private DRepresentationDescriptor getRepresentationDescriptor(Object element) {
        Object candidateElement = element;
        if (element instanceof ItemWrapper) {
            candidateElement = ((ItemWrapper) element).getWrappedObject();
        }
        DRepresentationDescriptor repDesc = null;
        if (candidateElement instanceof DRepresentation) {
            repDesc = new DRepresentationQuery((DRepresentation) candidateElement).getRepresentationDescriptor();
        } else if (candidateElement instanceof DRepresentationDescriptor) {
            repDesc = (DRepresentationDescriptor) candidateElement;
        }
        return repDesc;
    }

    @Override
    public String getText(Object element) {
        String text = null;
        if (element instanceof IProject) {
            IProject project = (IProject) element;
            Option<ModelingProject> modelingProject = ModelingProject.asModelingProject(project);
            if (modelingProject.some() && SiriusCommonLabelProvider.shoudlShowSessionDirtyStatus(modelingProject.get().getSession())) {
                text = SiriusCommonLabelProvider.DIRTY + project.getName();
            }
        } else if (element instanceof IFile) {
            text = doGetFileText((IFile) element);
        } else if (!(element instanceof IResource) && sessionLabelProvider != null) {
            try {
                IProject adaptedProject = Adapters.adapt(element, IProject.class);
                // IProject text is already handled for IProject case.
                // Do not look for elements adapted as IProject in calls to sessionLabelProvider.
                // PossibleChildren / TriggerPoint expressions evaluation change with 2023-03
                // See result of
                // org.eclipse.ui.internal.navigator.extensions.NavigatorContentDescriptorManager.findDescriptors(Object,
                // Map<VisibilityAssistant, EvaluationCache>, VisibilityAssistant, boolean, boolean)
                // for org.eclipse.jdt.internal.core.PackageFragmentRoot or
                // org.eclipse.jdt.internal.core.JrtPackageFragmentRoot which are now adaptable into IProject
                // displayed in a EcoreModelingProject for example: src folder, JRE System library modules.
                if (adaptedProject == null) {
                    // Let eclipse look for file and project icons + nature
                    // decoration
                    text = sessionLabelProvider.getText(element);
                }
            } catch (IllegalStateException | NullPointerException e) {
                // This can happen when trying to get the label of a CDOObject which transaction has just been closed
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
                        boolean dirty = SiriusCommonLabelProvider.shoudlShowSessionDirtyStatus(openedSessions.iterator().next());
                        text = dirty ? SiriusCommonLabelProvider.DIRTY + file.getName() : file.getName();
                    }
                }

            } else {
                // Transient case
                Iterable<Session> transientSessions = Iterables.filter(getOpenSessions(file), new SiriusCommonContentProvider.TransientSessionPredicate());
                if (Iterables.size(transientSessions) == 1) {
                    boolean dirty = SiriusCommonLabelProvider.shoudlShowSessionDirtyStatus(transientSessions.iterator().next());
                    text = dirty ? SiriusCommonLabelProvider.DIRTY + file.getName() : file.getName();
                }
            }
        }

        return text;
    }

    /*
     * Look for opened sessions on parent file : detect main aird for non modeling projects, all aird for modeling ones,
     * semantic file for transient sessions.
     */
    private List<Session> getOpenSessions(IFile file) {

        return Lists.newArrayList(Iterables.filter(FileSessionFinder.getSelectedSessions(Collections.singletonList(file)), new Predicate<Session>() {
            @Override
            public boolean apply(Session input) {
                return input.isOpen();
            }
        }));
    }

    /**
     * Test if the "*" prefix should be displayed : the session should be dirty and if the preference
     * DesignerUIPreferencesKeys.PREF_SAVE_WHEN_NO_EDITOR value is true, it should be displayed only when there is no
     * editor.
     *
     * @param session
     *            the session to test.
     * @return true if the dirty decorator
     */
    public static boolean shoudlShowSessionDirtyStatus(Session session) {
        if (SessionUIManager.INSTANCE != null) {
            IEditingSession uiSession = SessionUIManager.INSTANCE.getUISession(session);

            if (session != null && uiSession != null && session.isOpen() && uiSession.isOpen()) {

                IPreferenceStore preferenceStore = SiriusEditPlugin.getPlugin().getPreferenceStore();
                boolean shouldBeSavedWithoutEditor = preferenceStore != null && preferenceStore.getBoolean(SiriusUIPreferencesKeys.PREF_SAVE_WHEN_NO_EDITOR.name());

                // When option PREF_SAVE_WHEN_NO_EDITOR is enabled, show dirty
                // status only when there is no editors to detect bugs.
                if (SessionStatus.DIRTY == session.getStatus() && (!shouldBeSavedWithoutEditor || uiSession.getSiriusEditors().isEmpty())) {
                    return true;
                }

            }
        }
        return false;
    }

    @Override
    public void addListener(ILabelProviderListener listener) {
        if (sessionLabelProvider != null) {
            sessionLabelProvider.addListener(listener);
        }
    }

    @Override
    public void removeListener(ILabelProviderListener listener) {
        if (sessionLabelProvider != null) {
            sessionLabelProvider.removeListener(listener);
        }
    }

    @Override
    public boolean isLabelProperty(Object element, String property) {
        return true;
    }

    @Override
    public Color getForeground(final Object element) {

        Color foreground = null;
        try {
            // In modeling project, do not change the color for resource items.
            if (element instanceof ItemWrapper) {
                if (!isInModelingProject((ItemWrapper) element) || !(element instanceof AnalysisResourceItem)) {
                    foreground = VisualBindingManager.getDefault().getColorFromName("dark_gray"); //$NON-NLS-1$
                }
            } else if (element instanceof DRepresentationDescriptor) {
                foreground = VisualBindingManager.getDefault().getColorFromName("dark_blue"); //$NON-NLS-1$
            }

            if (isInvalidRepresentation(element)) {
                foreground = VisualBindingManager.getDefault().getColorFromName("light_gray"); //$NON-NLS-1$
            }
        } catch (IllegalStateException | NullPointerException e) {
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

    @Override
    public Color getBackground(Object element) {
        return null;
    }

    @Override
    public void dispose() {
        if (sessionLabelProvider != null) {
            sessionLabelProvider.dispose();
            sessionLabelProvider = null;
        }
    }

    @Override
    public void saveState(IMemento aMemento) {
    }

    @Override
    public void restoreState(IMemento aMemento) {
    }

    @Override
    public String getDescription(Object anElement) {
        return null;
    }

    @Override
    public void init(ICommonContentExtensionSite aConfig) {

    }
}
