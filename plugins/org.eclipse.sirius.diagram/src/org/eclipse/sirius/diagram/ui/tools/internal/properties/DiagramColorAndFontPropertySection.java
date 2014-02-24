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
package org.eclipse.sirius.diagram.ui.tools.internal.properties;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.internal.properties.Properties;
import org.eclipse.gmf.runtime.diagram.ui.properties.sections.appearance.DiagramColorsAndFontsPropertySection;
import org.eclipse.gmf.runtime.draw2d.ui.figures.FigureUtilities;
import org.eclipse.gmf.runtime.emf.core.util.PackageUtil;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.diagram.business.api.query.EObjectQuery;
import org.eclipse.sirius.diagram.internal.refresh.diagram.ViewPropertiesSynchronizer;
import org.eclipse.sirius.diagram.ui.tools.internal.dialogs.ColorPalettePopup;
import org.eclipse.sirius.viewpoint.description.DescriptionFactory;
import org.eclipse.sirius.viewpoint.description.UserFixedColor;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Button;

/**
 * Class necessary to remove default color option.
 * 
 * @author jdupont
 * 
 */
@SuppressWarnings("restriction")
public class DiagramColorAndFontPropertySection extends DiagramColorsAndFontsPropertySection {

    /**
     * {@inheritDoc}
     * 
     * @overides
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

}
