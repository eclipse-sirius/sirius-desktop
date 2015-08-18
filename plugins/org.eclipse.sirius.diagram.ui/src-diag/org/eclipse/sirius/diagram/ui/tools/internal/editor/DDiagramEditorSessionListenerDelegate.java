/*******************************************************************************
 * Copyright (c) 2011, 2012 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.editor;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.edit.provider.ComposedImage;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.sirius.business.api.session.SessionListener;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.diagram.ui.tools.api.graphical.edit.palette.PaletteManager;
import org.eclipse.sirius.diagram.ui.tools.api.graphical.edit.palette.ToolFilter;
import org.eclipse.sirius.diagram.ui.tools.api.image.DiagramImagesPath;
import org.eclipse.sirius.ecore.extender.business.api.permission.IPermissionAuthority;
import org.eclipse.sirius.ecore.extender.business.api.permission.LockStatus;
import org.eclipse.sirius.ecore.extender.business.api.permission.PermissionAuthorityRegistry;
import org.eclipse.sirius.ui.business.api.descriptor.ComposedImageDescriptor;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorRegistry;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;

/**
 * {@link SessionListener} used to manage {@link DDiagramEditorImpl}
 * {@link SessionListener} events.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class DDiagramEditorSessionListenerDelegate implements Runnable {

    /** The PERMISSION_GRANTED_TO_CURRENT_USER_EXCLUSIVELY icon descriptor. */
    private static final ImageDescriptor LOCK_BY_ME_IMAGE_DESCRIPTOR = SiriusEditPlugin.Implementation
            .getBundledImageDescriptor("icons/full/decorator/permission_granted_to_current_user_exclusively.gif"); //$NON-NLS-1$

    /** The PERMISSION_GRANTED_TO_CURRENT_USER_EXCLUSIVELY icon descriptor. */
    private static final ImageDescriptor LOCK_BY_OTHER_IMAGE_DESCRIPTOR = SiriusEditPlugin.Implementation.getBundledImageDescriptor("icons/full/decorator/permission_denied.gif"); //$NON-NLS-1$

    /** The NO_WRITE_PERMISSION icon description icon descriptor. */
    private static final ImageDescriptor NO_WRITE_PERMISSION_IMAGE_DESCRIPTOR = SiriusEditPlugin.Implementation.getBundledImageDescriptor("icons/full/decorator/permission_no_write.gif"); //$NON-NLS-1$

    private DDiagramEditorImpl dDiagramEditorImpl;

    /** Singleton instance of the initial Image for the NO_LOCK status */
    private Image initialTitleImage;

    /** Singleton instance of the Image for the LOCK_BY_ME status */
    private Image lockByMeImage;

    /** Singleton instance of the Image for the LOCK_BY_OTHER status */
    private Image lockByOtherImage;

    /** Singleton instance of the Image for the REPRESENTATION_FROZEN status */
    private Image frozenRepresentationImage;

    /**
     * A {@link ToolFilter} that hides any tool that cannot be applied if the
     * DDiagram is locked.
     */
    private ToolFilter toolFilterWhenRepresentationIsLocked;

    private int changeKind;
    
    private Image noWritePermissionImage;

    /**
     * Default constructor.
     * 
     * @param dDiagramEditorImpl
     *            the {@link DDiagramEditorImpl}
     * @param toolFilterWhenRepresentationIsLocked
     *            the {@link ToolFilter} used to hide the tools of the pallette
     *            when the diagram is LOCKED_BY_OTHER
     * @param changeKind
     *            the type of change
     */
    public DDiagramEditorSessionListenerDelegate(DDiagramEditorImpl dDiagramEditorImpl, ToolFilter toolFilterWhenRepresentationIsLocked, int changeKind) {
        this.dDiagramEditorImpl = dDiagramEditorImpl;
        this.toolFilterWhenRepresentationIsLocked = toolFilterWhenRepresentationIsLocked;
        this.changeKind = changeKind;
    }

    /**
     * Overridden to manage events of {@link DDiagramEditorImpl}.
     * 
     * {@inheritDoc}
     */
    // CHECKSTYLE:OFF
    public void run() {
        PaletteManager paletteManager = dDiagramEditorImpl.getPaletteManager();
        Diagram gmfDiagram = dDiagramEditorImpl.getDiagram();
        switch (changeKind) {
        case SessionListener.DIRTY:
        case SessionListener.SYNC:
            dDiagramEditorImpl.firePropertyChangeInUIThread(IEditorPart.PROP_DIRTY);
            break;
        case IWorkbenchPart.PROP_TITLE:
            dDiagramEditorImpl.firePropertyChangeInUIThread(IEditorPart.PROP_TITLE);
            break;
        case SessionListener.SELECTED_VIEWS_CHANGE_KIND:
            /* Available tools could change */
            reloadPalette(paletteManager, gmfDiagram, false);
            break;
        case SessionListener.VSM_UPDATED:
            /* clean and reload the palette when a .odesign is reloaded */
            reloadPalette(paletteManager, gmfDiagram, true);
            break;
        case SessionListener.REPLACED:
            /* session was reloaded, we need to reload the diagram */
            if (dDiagramEditorImpl.getDocumentProvider() != null) {
                final IEditorInput input = dDiagramEditorImpl.getEditorInput();
                dDiagramEditorImpl.setInput(input);
                /*
                 * Tool filters may reference invalid semantic elements, we need
                 * to reload the palette. The setInput can potentially cause a
                 * change on gmfDiagram of the current editor, so we must use
                 * dDiagramEditorImpl.getDiagram() instead of using the
                 */
                reloadPalette(paletteManager, dDiagramEditorImpl.getDiagram(), false);
            }
            break;
        case SessionListener.REPRESENTATION_EDITION_PERMISSION_GRANTED:
            /*
             * removing the tool filter so that all tools available when
             * representation is not locked are accessible
             */
            if (paletteManager != null) {
                paletteManager.removeToolFilter(toolFilterWhenRepresentationIsLocked);
                if (dDiagramEditorImpl.getTabbar() != null) {
                    dDiagramEditorImpl.getTabbar().reinitToolBar(dDiagramEditorImpl.getDiagramGraphicalViewer().getSelection());
                }
            }
            /* update editor's title image and palette */
            updateTitleImage(getInitialImage());
            IPermissionAuthority permissionAuthority = PermissionAuthorityRegistry.getDefault().getPermissionAuthority(dDiagramEditorImpl.getRepresentation());
            if (permissionAuthority == null || permissionAuthority.canEditInstance(dDiagramEditorImpl.getRepresentation())) {
                updateTitleImage(getInitialImage());
            } else {
                updateTitleImage(getNoWritePermissionImage());
            }
            reloadPalette(paletteManager, gmfDiagram, false);
            break;
        case SessionListener.REPRESENTATION_EDITION_PERMISSION_GRANTED_TO_CURRENT_USER_EXCLUSIVELY:
            if (paletteManager != null) {
                paletteManager.removeToolFilter(toolFilterWhenRepresentationIsLocked);
                if (dDiagramEditorImpl.getTabbar() != null) {
                    dDiagramEditorImpl.getTabbar().reinitToolBar(dDiagramEditorImpl.getDiagramGraphicalViewer().getSelection());
                }
            }
            updateTitleImage(getLockByMeImage());
            permissionAuthority = PermissionAuthorityRegistry.getDefault().getPermissionAuthority(dDiagramEditorImpl.getRepresentation());
            if (permissionAuthority == null || permissionAuthority.canEditInstance(dDiagramEditorImpl.getRepresentation())) {
                updateTitleImage(getLockByMeImage());
            }
            reloadPalette(paletteManager, gmfDiagram, false);
            break;
        case SessionListener.REPRESENTATION_EDITION_PERMISSION_DENIED:
            /*
             * create a toolfilter that will hide any creation tool
             */
            if (paletteManager != null) {
                paletteManager.addToolFilter(toolFilterWhenRepresentationIsLocked);
                if (dDiagramEditorImpl.getTabbar() != null) {
                    dDiagramEditorImpl.getTabbar().reinitToolBar(dDiagramEditorImpl.getDiagramGraphicalViewer().getSelection());
                }
            }
            /* update title image and palette */
            updateTitleImage(getLockByOtherImage());
            permissionAuthority = PermissionAuthorityRegistry.getDefault().getPermissionAuthority(dDiagramEditorImpl.getRepresentation());
            if (permissionAuthority == null || LockStatus.LOCKED_BY_OTHER.equals(permissionAuthority.getLockStatus(dDiagramEditorImpl.getRepresentation()))) {
                updateTitleImage(getLockByOtherImage());
            } else {
                updateTitleImage(getNoWritePermissionImage());
            }
            reloadPalette(paletteManager, gmfDiagram, false);
            break;
        case SessionListener.REPRESENTATION_FROZEN:
            if (paletteManager != null) {
                paletteManager.addToolFilter(toolFilterWhenRepresentationIsLocked);
                reloadPalette(paletteManager, gmfDiagram, false);
                if (dDiagramEditorImpl.getTabbar() != null) {
                    dDiagramEditorImpl.getTabbar().reinitToolBar(dDiagramEditorImpl.getDiagramGraphicalViewer().getSelection());
                }
            }
            updateTitleImage(getFrozenRepresentationImage());
            break;
        default:
            break;
        }
    }
    // CHECKSTYLE:ON

    private void updateTitleImage(Image newTitleImage) {
        if (newTitleImage != null && !newTitleImage.equals(dDiagramEditorImpl.getTitleImage())) {
            dDiagramEditorImpl.setTitleImage(newTitleImage);
        }
    }

    private void reloadPalette(PaletteManager paletteManager, Diagram gmfDiagram, boolean clean) {
        if (paletteManager != null && !paletteManager.isDisposed()) {
            paletteManager.update(gmfDiagram, clean);
        }
    }

    /**
     * Get lazily the initial Image of the editor.
     * 
     * @return the initial Image
     */
    private Image getInitialImage() {
        if (initialTitleImage == null || initialTitleImage.isDisposed()) {
            IEditorRegistry editorRegistry = PlatformUI.getWorkbench().getEditorRegistry();
            IEditorDescriptor editorDesc = editorRegistry.findEditor(dDiagramEditorImpl.getSite().getId());
            initialTitleImage = DiagramUIPlugin.getPlugin().getImage(editorDesc.getImageDescriptor());
        }
        return initialTitleImage;
    }

    /**
     * Get lazily the Image for the LOCK_BY_ME status.
     * 
     * @return the Image for the LOCK_BY_ME status
     */
    private Image getLockByMeImage() {
        if (lockByMeImage == null || lockByMeImage.isDisposed()) {
            lockByMeImage = DiagramUIPlugin.getPlugin().getImage(LOCK_BY_ME_IMAGE_DESCRIPTOR);
        }
        return lockByMeImage;
    }

    /**
     * Get lazily the Image for the LOCK_BY_OTHER status.
     * 
     * @return the Image for the LOCK_BY_OTHER status
     */
    private Image getLockByOtherImage() {
        if (lockByOtherImage == null || lockByOtherImage.isDisposed()) {
            lockByOtherImage = DiagramUIPlugin.getPlugin().getImage(LOCK_BY_OTHER_IMAGE_DESCRIPTOR);
        }
        return lockByOtherImage;
    }

    /**
     * Get lazily the Image for the REPRESENTATION_FROZEN state.
     * 
     * @return the Image for the REPRESENTATION_FROZEN state
     */
    private Image getFrozenRepresentationImage() {
        if (frozenRepresentationImage == null || frozenRepresentationImage.isDisposed()) {
            Image refreshImage = DiagramUIPlugin.getPlugin().getImage(DiagramUIPlugin.Implementation.getBundledImageDescriptor(DiagramImagesPath.REFRESH_IMG));
            List<Object> images = new ArrayList<Object>(2);
            images.add(refreshImage);
            Image lockByOtherOverlayImage = SiriusEditPlugin.getPlugin()
                    .getImage(SiriusEditPlugin.Implementation.getBundledImageDescriptor("icons/full/decorator/permission_denied_overlay.gif")); //$NON-NLS-1$
            images.add(lockByOtherOverlayImage);
            ImageDescriptor composedImageDescriptor = new ComposedImageDescriptor(new ComposedImage(images));
            frozenRepresentationImage = DiagramUIPlugin.getPlugin().getImage(composedImageDescriptor);
        }
        return frozenRepresentationImage;
    }

    /**
     * Lazily gets the image to use when there is no write permission for the
     * DRepresentation.
     * 
     * @return the image to use when there is no write permission for the
     *         DRepresentation
     */
    private Image getNoWritePermissionImage() {
        if (noWritePermissionImage == null || noWritePermissionImage.isDisposed()) {
            noWritePermissionImage = DiagramUIPlugin.getPlugin().getImage(NO_WRITE_PERMISSION_IMAGE_DESCRIPTOR);
        }
        return noWritePermissionImage;
    }
}
