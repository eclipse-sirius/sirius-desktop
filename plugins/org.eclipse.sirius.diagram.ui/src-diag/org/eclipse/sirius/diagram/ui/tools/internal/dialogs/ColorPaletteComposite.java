/*******************************************************************************
 * Copyright (c) 2024 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.ui.tools.internal.dialogs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.sirius.diagram.ui.tools.api.color.ColorManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DragSource;
import org.eclipse.swt.dnd.DragSourceAdapter;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.swt.dnd.DropTarget;
import org.eclipse.swt.dnd.DropTargetAdapter;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

/**
 * This class defines a color palette composed by a set of colored buttons.
 * 
 * @author <a href="mailto:glenn.plouhinec@obeo.fr">Glenn Plouhinec</a>
 */
public class ColorPaletteComposite extends Composite {

    /**
     * The size of a color button.
     */
    public static final int BUTTON_SIZE = 25;

    /**
     * The horizontal spacing between color buttons.
     */
    public static final int BUTTONS_HORIZONTAL_SPACING = 2;

    /**
     * The data key used to drag and drop colors.
     */
    protected static final String BUTTON_COLOR_DATA_KEY = "color"; //$NON-NLS-1$

    /**
     * The maximum number of colors per line.
     */
    private int numberOfColumns;

    /**
     * Defines if dropping is allowed in this color palette.
     */
    private boolean droppingAllowed;

    /**
     * The color selected in this color palette.
     */
    private RGB paletteSelectedColor;

    /**
     * The list of colors.
     */
    private List<RGB> colors = new ArrayList<>();

    /**
     * A map associating a RGB color with the corresponding Image.
     */
    private final Map<RGB, Image> rgbToImages = new HashMap<>();

    /**
     * A map associating a button with the corresponding RGB color.
     */
    private final Map<Button, RGB> buttonMap = new LinkedHashMap<>();

    /**
     * This constructor has been defined to allow sub-classing.
     */
    private ColorPaletteComposite() {
        super(null, 0);
    }

    /**
     * Creates an instance of {@link ColorPaletteComposite}.
     * 
     * @param parent
     *            the parent composite.
     * @param style
     *            the SWT style.
     * @param colors
     *            the list of colors to display.
     * @param numberOfColumns
     *            the maximum number of colors per line.
     * @param droppingAllowed
     *            defines if dropping is allowed.
     */
    protected ColorPaletteComposite(final Composite parent, final int style, final List<RGB> colors, final int numberOfColumns, final boolean droppingAllowed) {
        super(parent, style);
        this.colors = colors;
        this.numberOfColumns = numberOfColumns;
        this.droppingAllowed = droppingAllowed;
        init();
    }

    /**
     * Creates an instance of {@link ColorPaletteComposite}.
     * 
     * @param parent
     *            the parent composite
     * @param colors
     *            the list of colors to display.
     * @param numberOfColumns
     *            the maximum number of colors per line.
     * @param droppingAllowed
     *            defines if dropping is allowed.
     */
    public ColorPaletteComposite(final Composite parent, final List<RGB> colors, final int numberOfColumns, final boolean droppingAllowed) {
        this(parent, SWT.NONE, colors, numberOfColumns, droppingAllowed);
    }

    /**
     * Used to initialize the palette: defines the layout and creates color buttons.
     */
    protected void init() {
        final GridLayout layout = new GridLayout(this.numberOfColumns, true);
        layout.marginHeight = 0;
        layout.horizontalSpacing = BUTTONS_HORIZONTAL_SPACING;
        setLayout(layout);
        final GridData layoutData = new GridData(SWT.FILL, SWT.CENTER, true, true, 1, 1);
        setLayoutData(layoutData);

        final int dropOperations = DND.DROP_MOVE | DND.DROP_COPY;
        final DropTarget target = new DropTarget(this, dropOperations);
        target.setTransfer(TextTransfer.getInstance());
        target.addDropListener(new DropTargetAdapter() {
            @Override
            public void drop(final DropTargetEvent dropEvent) {
                if (dropEvent.data instanceof final String droppedColorString) {
                    dropColorOnPalette(droppedColorString);
                }
            }
        });

        this.refreshColorButtons();
    }

    /**
     * Indicates if dropping is allowed for this color palette.
     * 
     * @return droppingAllowed.
     */
    public boolean isDroppingAllowed() {
        return droppingAllowed;
    }

    /**
     * Returns the list of colors.
     * 
     * @return colors.
     */
    public List<RGB> getColors() {
        return colors;
    }

    /**
     * Returns the selected color.
     * 
     * @return paletteSelectedColor
     */
    public RGB getPaletteSelectedColor() {
        return paletteSelectedColor;
    }

    /**
     * Refresh all color buttons of the palette. Creates new buttons if there are new colors. Remove all buttons and
     * recreates them if a color has been removed.
     */
    private void refreshColorButtons() {
        if (colors.isEmpty()) {
            ((GridLayout) getLayout()).marginBottom = 25;
        } else {
            ((GridLayout) getLayout()).marginBottom = 0;
        }
        if (colors.size() < buttonMap.size()) {
            // If some colors have been removed, we should removed all buttons to recreate them after.
            for (final Button button : buttonMap.keySet()) {
                button.dispose();
            }
            buttonMap.clear();
        }
        final ArrayList<Button> buttonsList = new ArrayList<>(buttonMap.keySet());
        for (int i = 0; i < colors.size(); i++) {
            final RGB color = colors.get(i);
            Button button;
            if (i < buttonsList.size()) {
                // Override settings of an existing button
                button = buttonsList.get(i);
            } else {
                // Create a new button
                button = createColorButton();
            }
            if (!rgbToImages.containsKey(color)) {
                final InventoryColorDescriptor colorDesc = new InventoryColorDescriptor(color);
                rgbToImages.put(color, colorDesc.createImage());
            }
            final Image image = rgbToImages.get(color);
            final String rgbString = ColorManager.getDefault().rgbToString(color);
            button.setData(BUTTON_COLOR_DATA_KEY, rgbString);
            button.setImage(image);
            button.setToolTipText(rgbString);
            buttonMap.put(button, color);
        }
        layout();
    }

    /**
     * Creates a color button: adds a layoutData, a selectionListener, a doubleClickListener, configure a drag and drop
     * listener.
     * 
     * @return the configured button.
     */
    private Button createColorButton() {
        Button button = new Button(this, SWT.PUSH);
        final GridData buttonData = new GridData(BUTTON_SIZE, BUTTON_SIZE);
        button.setLayoutData(buttonData);

        button.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(final SelectionEvent e1) {
                final String colorString = (String) button.getData(BUTTON_COLOR_DATA_KEY);
                final RGB convertedColor = ColorManager.getDefault().stringToRGB(colorString);
                selectColor(convertedColor);
            }
        });
        button.addListener(SWT.MouseDoubleClick, new Listener() {
            @Override
            public void handleEvent(final Event event) {
                handleDoubleClick(button);
            }
        });

        final int dragOperations = DND.DROP_MOVE | DND.DROP_COPY;
        final DragSource source = new DragSource(button, dragOperations);
        source.setTransfer(TextTransfer.getInstance());
        source.addDragListener(new DragSourceAdapter() {
            @Override
            public void dragSetData(final DragSourceEvent dragEvent) {
                if (button.getData(BUTTON_COLOR_DATA_KEY) instanceof final String draggedColorString) {
                    dragColor(button, draggedColorString, dragEvent);
                }
            }

            @Override
            public void dragFinished(DragSourceEvent event) {
                refreshColorButtons();
            }
        });
        final int dropOperations = DND.DROP_MOVE | DND.DROP_COPY;
        final DropTarget target = new DropTarget(button, dropOperations);
        target.setTransfer(TextTransfer.getInstance());
        target.addDropListener(new DropTargetAdapter() {
            @Override
            public void drop(final DropTargetEvent dropEvent) {
                if (dropEvent.data instanceof final String droppedColorString) {
                    dropColorOnButton(button, droppedColorString);
                }
            }
        });
        return button;
    }

    /**
     * Invoked when a color is dragged.
     * 
     * @param button
     *            the dragged button
     * @param draggedColorString
     *            the RGB string value of the dragged color.
     * @param dragEvent
     *            the {@link DragSourceEvent} managed by the {@link DragSourceListener}.
     */
    protected void dragColor(final Button button, final String draggedColorString, final DragSourceEvent dragEvent) {
        dragEvent.data = draggedColorString;
    }

    /**
     * Invoked when a color is dropped on a {@link Button}.
     * 
     * @param targetButton
     *            the dropped button.
     * @param droppedColorString
     *            the RGB string value of the dropped color.
     */
    protected void dropColorOnButton(final Button targetButton, final String droppedColorString) {
        if (targetButton.getParent() instanceof ColorPaletteComposite && isDroppingAllowed()) {
            final RGB sourceDroppedColor = ColorManager.getDefault().stringToRGB(droppedColorString);
            final String targetColor = (String) targetButton.getData(BUTTON_COLOR_DATA_KEY);
            final RGB targetRGB = ColorManager.getDefault().stringToRGB(targetColor);
            final int targetIndex = colors.indexOf(targetRGB);
            final int sourceIndex = colors.indexOf(sourceDroppedColor);
            if (targetIndex != -1 && !colors.contains(sourceDroppedColor)) {
                addColor(targetIndex, sourceDroppedColor);
            } else if (targetIndex != -1 && sourceIndex != -1) {
                Collections.swap(colors, targetIndex, sourceIndex);
            }
        }
    }

    /**
     * Invoked when a color is dropped on this {@link ColorPaletteComposite}.
     * 
     * @param droppedColorString
     *            the RGB string value of the dropped color.
     */
    protected void dropColorOnPalette(final String droppedColorString) {
        if (isDroppingAllowed()) {
            final RGB sourceDroppedColor = ColorManager.getDefault().stringToRGB(droppedColorString);
            if (!colors.contains(sourceDroppedColor)) {
                addColor(colors.size(), sourceDroppedColor);
            }
        }
    }

    /**
     * Invoked when a color is double-clicked. Empty by default.
     * 
     * @param button
     *            the double-clicked button containing the RGB string value in its data.
     */
    protected void handleDoubleClick(final Button button) {
    }

    /**
     * Invoked when a color is selected in the color palette.
     * 
     * @param selectedColor
     *            the color selected.
     */
    public void selectColor(final RGB selectedColor) {
        setPaletteSelectedColor(selectedColor);
    }

    /**
     * Invoked when a color is removed from the palette.
     * 
     * @param colorToRemove
     *            the color to remove.
     * @return {@code true} if the color has been removed; {@code false} otherwise.
     */
    public boolean removeColor(final RGB colorToRemove) {
        final boolean hasBeenRemoved = basicRemoveColor(colorToRemove);
        if (hasBeenRemoved) {
            refreshColorButtons();
        }
        return hasBeenRemoved;
    }

    /**
     * Basic implementation for removing a color from the palette.
     * 
     * @param colorToRemove
     *            the color to remove.
     * @return {@code true} if the color has been removed; {@code false} otherwise.
     */
    protected boolean basicRemoveColor(final RGB colorToRemove) {
        return colors.remove(colorToRemove);
    }

    /**
     * Invoked when a color is added to the palette.
     * 
     * @param index
     *            the index where the color is to be added to the color list.
     * @param colorToAdd
     *            the color to add.
     * @return {@code true} if the color has been added; {@code false} otherwise.
     */
    public boolean addColor(final int index, final RGB colorToAdd) {
        final boolean alreadyExistingColor = colors.contains(colorToAdd);
        if (!alreadyExistingColor) {
            colors.add(index, colorToAdd);
            refreshColorButtons();
        }
        return !alreadyExistingColor;
    }

    /**
     * Invoked when a color is added to the palette.
     * 
     * @param colorToAdd
     *            the color to add.
     */
    public boolean addColor(final RGB colorToAdd) {
        return addColor(colors.size(), colorToAdd);
    }

    /**
     * Used to sort the color list.
     */
    public void sortColors() {
        final List<RGB> sortedColors = ColorManager.getDefault().sortColors(colors);
        colors.clear();
        colors.addAll(sortedColors);
        refreshColorButtons();
    }

    /**
     * Sets the selected color.
     * 
     * @param colorToSet
     *            the color to set.
     */
    public void setPaletteSelectedColor(final RGB colorToSet) {
        this.paletteSelectedColor = colorToSet;
    }

    /**
     * Returns the maximum number of colors per line.
     * 
     * @return numberOfColumns
     */
    public int getNumberOfColumns() {
        return numberOfColumns;
    }

    /**
     * {@inheritDoc} Overridden to dispose all color buttons.
     */
    @Override
    public void dispose() {
        // Dispose all SWT Buttons
        buttonMap.keySet().forEach(button -> button.dispose());
        // Clear the map
        buttonMap.clear();

        // Dispose all images
        rgbToImages.values().forEach(image -> image.dispose());
        // Clear the map
        rgbToImages.clear();

        super.dispose();
    }

}
