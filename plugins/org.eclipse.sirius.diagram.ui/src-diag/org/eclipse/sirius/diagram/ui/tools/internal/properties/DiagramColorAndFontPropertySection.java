/*******************************************************************************
 * Copyright (c) 2011, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.properties;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.operations.IOperationHistory;
import org.eclipse.core.commands.operations.IUndoContext;
import org.eclipse.core.commands.operations.ObjectUndoContext;
import org.eclipse.core.commands.operations.OperationHistoryFactory;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.workspace.IWorkspaceCommandStack;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.common.core.command.CompositeCommand;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.common.core.util.Trace;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.internal.properties.Properties;
import org.eclipse.gmf.runtime.diagram.ui.properties.internal.DiagramPropertiesDebugOptions;
import org.eclipse.gmf.runtime.diagram.ui.properties.internal.DiagramPropertiesPlugin;
import org.eclipse.gmf.runtime.diagram.ui.properties.internal.DiagramPropertiesStatusCodes;
import org.eclipse.gmf.runtime.diagram.ui.properties.sections.appearance.DiagramColorsAndFontsPropertySection;
import org.eclipse.gmf.runtime.draw2d.ui.figures.FigureUtilities;
import org.eclipse.gmf.runtime.emf.core.util.PackageUtil;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.diagram.business.api.query.EObjectQuery;
import org.eclipse.sirius.diagram.ui.internal.refresh.diagram.ViewPropertiesSynchronizer;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.diagram.ui.tools.api.image.DiagramImagesPath;
import org.eclipse.sirius.diagram.ui.tools.internal.actions.style.ResetStylePropertiesToDefaultValuesAction;
import org.eclipse.sirius.diagram.ui.tools.internal.actions.style.SetStyleToWorkspaceImageAction;
import org.eclipse.sirius.diagram.ui.tools.internal.dialogs.ColorPalettePopup;
import org.eclipse.sirius.tools.api.command.EditingDomainUndoContext;
import org.eclipse.sirius.viewpoint.description.DescriptionFactory;
import org.eclipse.sirius.viewpoint.description.UserFixedColor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.accessibility.AccessibleAdapter;
import org.eclipse.swt.accessibility.AccessibleEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

/**
 * Class necessary to remove default color option.
 * 
 * @author jdupont
 * 
 */
@SuppressWarnings("restriction")
public class DiagramColorAndFontPropertySection extends DiagramColorsAndFontsPropertySection {

    /** button to set back the view to default color. */
    protected Button resetStylePropertiesToDefaultValuesButton;

    /**
     * button to allow user to select an image in the workspace and set the
     * selected image as view background image.
     */
    protected Button setStyleToWorkspaceImageButton;

    /**
     * button to set the font underlined.
     */
    private Button fontUnderlineButton;

    /**
     * button to set the font struck through.
     */
    private Button fontStrikeThroughButton;

    private boolean bIsCommandInProgress;

    /**
     * {@inheritDoc}
     * 
     * @overrides
     * 
     * @see org.eclipse.gmf.runtime.diagram.ui.properties.sections.appearance.ColorsAndFontsPropertySection#changeColor(org.eclipse.swt.events.SelectionEvent,
     *      org.eclipse.swt.widgets.Button, java.lang.String, java.lang.String,
     *      org.eclipse.jface.resource.ImageDescriptor)
     */
    @Override
    protected RGB changeColor(final SelectionEvent event, final Button button, final String propertyId, final String commandName, final ImageDescriptor imageDescriptor) {

        RGB colorToReturn = null;

        if (!Properties.ID_FILLCOLOR.equals(propertyId) && !Properties.ID_LINECOLOR.equals(propertyId) && !Properties.ID_FONTCOLOR.equals(propertyId)) {
            colorToReturn = super.changeColor(event, button, propertyId, commandName, imageDescriptor);
        } else {
            final ColorPalettePopup popup = new ColorPalettePopup(button.getParent().getShell(), IDialogConstants.BUTTON_BAR_HEIGHT);
            final Rectangle r = button.getBounds();
            final Point location = button.getParent().toDisplay(r.x, r.y);
            popup.open(new Point(location.x, location.y + r.height));
            if (popup.getSelectedColor() == null && !popup.useDefaultColor()) {
                return null;
            }
            // selectedColor should be null if we are to use the default color
            final RGB selectedColor = popup.getSelectedColor();

            final EStructuralFeature feature = (EStructuralFeature) PackageUtil.getElement(propertyId);

            // Update model in response to user

            final List<ICommand> commands = new ArrayList<ICommand>();
            final Iterator<?> it = getInputIterator();

            colorToReturn = selectedColor;
            RGB color = selectedColor;
            while (it.hasNext()) {
                final IGraphicalEditPart ep = (IGraphicalEditPart) it.next();
                color = selectedColor;
                if (popup.useDefaultColor()) {
                    final Object preferredValue = ep.getPreferredValue(feature);
                    if (preferredValue instanceof Integer) {
                        color = FigureUtilities.integerToRGB((Integer) preferredValue);
                    }
                }

                // If we are using default colors, we want to return the color
                // of the first selected element to be consistent
                if (colorToReturn == null) {
                    colorToReturn = color;
                }

                if (color != null) {
                    final RGB finalColor = color; // need a final variable
                    commands.add(createCommand(commandName, ((View) ep.getModel()).eResource(), new Runnable() {

                        @Override
                        public void run() {
                            final ENamedElement element = PackageUtil.getElement(propertyId);
                            if (element instanceof EStructuralFeature) {
                                ep.setStructuralFeatureValue(feature, FigureUtilities.RGBToInteger(finalColor));
                            }

                            // get the view.
                            final View view = (View) ep.getModel();
                            // change the color.
                            final UserFixedColor newColor = DescriptionFactory.eINSTANCE.createUserFixedColor();
                            newColor.setName("<anonymous>");
                            newColor.setBlue(finalColor.blue);
                            newColor.setGreen(finalColor.green);
                            newColor.setRed(finalColor.red);

                            IInterpreter interpreter = new EObjectQuery(view).getSession().getInterpreter();
                            new ViewPropertiesSynchronizer().synchronizeDDiagramElementStyleColorProperties(view, newColor, propertyId, interpreter);
                        }
                    }));
                }
            }
            if (!commands.isEmpty()) {
                executeAsCompositeCommand(commandName, commands);
                final Image overlyedImage = new ColorOverlayImageDescriptor(imageDescriptor.getImageData(), color).createImage();
                disposeImage(button.getImage());
                button.setImage(overlyedImage);
            }
        }
        return colorToReturn;

    }

    /**
     * This method has been overridden to add the undo context to the command.
     * 
     * {@inheritDoc}
     */
    @Override
    protected CommandResult executeAsCompositeCommand(String actionName, List commands) {
        if (bIsCommandInProgress)
            return null;

        bIsCommandInProgress = true;

        CompositeCommand command = new CompositeCommand(actionName, commands);
        IOperationHistory history = OperationHistoryFactory.getOperationHistory();

        command.addContext(getUndoContext());

        try {
            IStatus status = history.execute(command, new NullProgressMonitor(), null);

            if (status.getCode() == DiagramPropertiesStatusCodes.CANCELLED || status.getSeverity() == IStatus.CANCEL || status.getSeverity() == IStatus.ERROR) {
                refresh();
            }

        } catch (ExecutionException e) {
            Trace.catching(DiagramPropertiesPlugin.getDefault(), DiagramPropertiesDebugOptions.EXCEPTIONS_CATCHING, getClass(), "executeAsCompositeCommand", e); //$NON-NLS-1$
            DiagramUIPlugin.getPlugin().getLog().log(new Status(IStatus.ERROR, DiagramUIPlugin.ID, e.getLocalizedMessage()));
        }

        bIsCommandInProgress = false;

        return command.getCommandResult();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.diagram.ui.properties.sections.appearance.ShapeColorsAndFontsPropertySection#createFontsGroup(org.eclipse.swt.widgets.Composite)
     */
    @Override
    protected Composite createFontsGroup(final Composite parent) {
        final Composite toolBar = super.createFontsGroup(parent);

        final Image imageUndo = DiagramUIPlugin.getPlugin().getBundledImage(DiagramImagesPath.UNDO_ICON);
        final Image imageImage = DiagramUIPlugin.getPlugin().getBundledImage(DiagramImagesPath.IMAGE_ICON);
        final Image imageUnderline = DiagramUIPlugin.getPlugin().getBundledImage(DiagramImagesPath.UNDERLINE_ICON);
        final Image imageStrikeThrough = DiagramUIPlugin.getPlugin().getBundledImage(DiagramImagesPath.STRIKE_THROUGH_ICON);

        boolean isReadOnly = isReadOnly();

        fontUnderlineButton = new Button(toolBar, SWT.TOGGLE);
        fontUnderlineButton.setImage(imageUnderline);
        fontUnderlineButton.setEnabled(!isReadOnly);
        fontUnderlineButton.getAccessible().addAccessibleListener(new AccessibleAdapter() {
            @Override
            public void getName(final AccessibleEvent e) {
                e.result = "Underline";
            }
        });

        fontStrikeThroughButton = new Button(toolBar, SWT.TOGGLE);
        fontStrikeThroughButton.setImage(imageStrikeThrough);
        fontStrikeThroughButton.setEnabled(!isReadOnly);
        fontStrikeThroughButton.getAccessible().addAccessibleListener(new AccessibleAdapter() {
            @Override
            public void getName(final AccessibleEvent e) {
                e.result = "StrikeThrough";
            }
        });

        new Label(toolBar, SWT.LEFT);
        new Label(toolBar, SWT.LEFT);
        new Label(toolBar, SWT.LEFT);

        setStyleToWorkspaceImageButton = new Button(toolBar, SWT.PUSH);
        setStyleToWorkspaceImageButton.setToolTipText(SetStyleToWorkspaceImageAction.SET_STYLE_TO_WORKSPACE_IMAGE_ACTION_NAME);
        setStyleToWorkspaceImageButton.setImage(imageImage);
        setStyleToWorkspaceImageButton.setEnabled(false);

        resetStylePropertiesToDefaultValuesButton = new Button(toolBar, SWT.PUSH);
        resetStylePropertiesToDefaultValuesButton.setToolTipText(ResetStylePropertiesToDefaultValuesAction.ACTION_NAME);
        resetStylePropertiesToDefaultValuesButton.setImage(imageUndo);
        resetStylePropertiesToDefaultValuesButton.addSelectionListener(new ResetStylePropertiesToDefaultValuesSelectionAdapter(this));
        resetStylePropertiesToDefaultValuesButton.setEnabled(!isReadOnly);

        fontUnderlineButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent event) {
                updateFontUnderline();
            }
        });

        fontStrikeThroughButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent event) {
                updateFontStrikeThrough();
            }
        });
        return toolBar;
    }

    private void updateFontUnderline() {
        // Update model in response to user
        final List<ICommand> commands = new ArrayList<ICommand>();
        final Iterator<?> it = getInputIterator();

        while (it.hasNext()) {
            final IGraphicalEditPart ep = (IGraphicalEditPart) it.next();
            commands.add(createCommand(FONT_COMMAND_NAME, ((View) ep.getModel()).eResource(), new Runnable() {

                @Override
                public void run() {
                    ep.setStructuralFeatureValue(NotationPackage.eINSTANCE.getFontStyle_Underline(), Boolean.valueOf(fontUnderlineButton.getSelection()));
                }
            }));
        }

        executeAsCompositeCommand(FONT_COMMAND_NAME, commands);
    }

    private void updateFontStrikeThrough() {
        // Update model in response to user
        final List<ICommand> commands = new ArrayList<ICommand>();
        final Iterator<?> it = getInputIterator();

        while (it.hasNext()) {
            final IGraphicalEditPart ep = (IGraphicalEditPart) it.next();
            commands.add(createCommand(FONT_COMMAND_NAME, ((View) ep.getModel()).eResource(), new Runnable() {

                @Override
                public void run() {
                    ep.setStructuralFeatureValue(NotationPackage.eINSTANCE.getFontStyle_StrikeThrough(), Boolean.valueOf(fontStrikeThroughButton.getSelection()));
                }
            }));
        }

        executeAsCompositeCommand(FONT_COMMAND_NAME, commands);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.diagram.ui.properties.sections.appearance.ShapeColorsAndFontsPropertySection#refresh()
     */
    @Override
    public void refresh() {
        if (!isDisposed()) {
            super.refresh();
            executeAsReadAction(new Runnable() {

                @Override
                public void run() {
                    final IGraphicalEditPart ep = getSingleInput();
                    if (ep != null) {
                        final View view = (View) ep.getModel();
                        boolean isCustomizedView = ResetStylePropertiesToDefaultValuesSelectionAdapter.isCustomizedView(view);
                        final boolean isReadOnly = isReadOnly();
                        if (resetStylePropertiesToDefaultValuesButton != null) {
                            resetStylePropertiesToDefaultValuesButton.setEnabled(!isReadOnly && isCustomizedView);
                        }
                        if (fontUnderlineButton != null) {
                            boolean underlined = (Boolean) ep.getStructuralFeatureValue(NotationPackage.eINSTANCE.getFontStyle_Underline());
                            fontUnderlineButton.setSelection(underlined);
                            fontUnderlineButton.setEnabled(!isReadOnly);
                        }
                        if (fontStrikeThroughButton != null) {
                            boolean striked = (Boolean) ep.getStructuralFeatureValue(NotationPackage.eINSTANCE.getFontStyle_StrikeThrough());
                            fontStrikeThroughButton.setSelection(striked);
                            fontStrikeThroughButton.setEnabled(!isReadOnly);
                        }
                    }
                }
            });
        }
    }

    private IUndoContext getUndoContext() {
        IUndoContext undoContext;

        final TransactionalEditingDomain domain = getEditingDomain();

        if (domain != null) {
            if (domain.getCommandStack() instanceof IWorkspaceCommandStack) {
                undoContext = ((IWorkspaceCommandStack) domain.getCommandStack()).getDefaultUndoContext();
            } else {
                undoContext = new EditingDomainUndoContext(domain);
            }
        } else {
            undoContext = new ObjectUndoContext(this);
        }

        return undoContext;
    }
}
