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
 * 
 * @author gplouhine
 */
public class ColorPaletteComposite extends Composite {

    protected static final String BUTTON_COLOR_DATA_KEY = "color"; //$NON-NLS-1$

    private static final int BUTTON_SIZE = 25;

    private int columnNumber;

    private boolean droppingAllowed;

    private RGB paletteSelectedColor;

    private List<RGB> colors = new ArrayList<>();

    /**
     * A map associating a RGB color with the corresponding Image.
     */
    private Map<RGB, Image> rgbToImages = new HashMap<>();

    private Map<Button, RGB> buttonMap = new LinkedHashMap<>();

    protected ColorPaletteComposite(Composite parent, int style, List<RGB> colors, int columnNumber, boolean droppingAllowed) {
        super(parent, style);
        this.colors = colors;
        this.columnNumber = columnNumber;
        this.droppingAllowed = droppingAllowed;
        init();
    }

    public ColorPaletteComposite(Composite parent, List<RGB> colors, int columnNumber, boolean droppingAllowed) {
        this(parent, SWT.NONE, colors, columnNumber, droppingAllowed);
    }

    protected void init() {
        GridLayout layout = new GridLayout(this.columnNumber, true);
        layout.marginHeight = 0;
        layout.horizontalSpacing = 2;
        setLayout(layout);
        GridData layoutData = new GridData(SWT.FILL, SWT.CENTER, true, true, 1, 1);
        setLayoutData(layoutData);

        this.refreshColorButtons();
    }

    /**
     * @return the droppingAllowed
     */
    public boolean isDroppingAllowed() {
        return droppingAllowed;
    }

    /**
     * @return the colors
     */
    public List<RGB> getColors() {
        return colors;
    }

    /**
     * @return the paletteSelectedColor
     */
    public RGB getPaletteSelectedColor() {
        return paletteSelectedColor;
    }

    private void refreshColorButtons() {
        if (colors.size() < buttonMap.size()) {
            // If some colors have been removed, we should removed all buttons to recreate them after.
            for (Button button : buttonMap.keySet()) {
                button.dispose();
            }
            buttonMap.clear();
        }
        ArrayList<Button> buttonsList = new ArrayList<>(buttonMap.keySet());
        for (int i = 0; i < colors.size(); i++) {
            RGB color = colors.get(i);
            Button button;
            if (i < buttonsList.size()) {
                // Override settings of an existing button
                button = buttonsList.get(i);
            } else {
                // Create a new button
                button = createColorButton();
            }
            if (!rgbToImages.containsKey(color)) {
                InventoryColorDescriptor colorDesc = new InventoryColorDescriptor(color);
                rgbToImages.put(color, colorDesc.createImage());
            }
            final Image image = rgbToImages.get(color);
            String rgbString = color.toString().replace("RGB ", ""); //$NON-NLS-1$ //$NON-NLS-2$
            button.setData(BUTTON_COLOR_DATA_KEY, rgbString);
            button.setImage(image);
            button.setToolTipText(rgbString);
            buttonMap.put(button, color);
        }
        layout();
    }

    private Button createColorButton() {
        Button button;
        button = new Button(this, SWT.PUSH);
        GridData buttonData = new GridData(BUTTON_SIZE, BUTTON_SIZE);
        button.setLayoutData(buttonData);

        button.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e1) {
                String colorString = (String) button.getData(BUTTON_COLOR_DATA_KEY);
                RGB convertedColor = ColorManager.getDefault().stringToRGB(colorString);
                setPaletteSelectedColor(convertedColor);
            }
        });
        button.addListener(SWT.MouseDoubleClick, new Listener() {
            @Override
            public void handleEvent(Event event) {
                handleDoubleClick(button);
            }
        });
        int dragOperations = DND.DROP_MOVE | DND.DROP_COPY;
        DragSource source = new DragSource(button, dragOperations);
        source.setTransfer(TextTransfer.getInstance());
        source.addDragListener(new DragSourceAdapter() {
            @Override
            public void dragSetData(DragSourceEvent dragEvent) {
                if (button.getData(BUTTON_COLOR_DATA_KEY) instanceof String draggedColorString) {
                    dragColor(button, draggedColorString, dragEvent);
                }
            }
        });
        int dropOperations = DND.DROP_MOVE | DND.DROP_COPY;
        DropTarget target = new DropTarget(button, dropOperations);
        target.setTransfer(TextTransfer.getInstance());
        target.addDropListener(new DropTargetAdapter() {
            @Override
            public void drop(DropTargetEvent dropEvent) {
                if (dropEvent.data instanceof String droppedColorString) {
                    dropColor(button, droppedColorString);
                }
            }
        });
        return button;
    }

    protected void dragColor(Button button, String draggedColorString, DragSourceEvent dragEvent) {
        dragEvent.data = draggedColorString;
    }

    protected void dropColor(Button targetButton, String droppedColorString) {
        RGB sourceDroppedColor = ColorManager.getDefault().stringToRGB(droppedColorString);
        if (targetButton.getParent() instanceof ColorPaletteComposite && isDroppingAllowed()) {
            String targetColor = (String) targetButton.getData(BUTTON_COLOR_DATA_KEY);
            RGB targetRGB = ColorManager.getDefault().stringToRGB(targetColor);
            int targetIndex = colors.indexOf(targetRGB);
            int sourceIndex = colors.indexOf(sourceDroppedColor);
            if (targetIndex != -1 && !colors.contains(sourceDroppedColor)) {
                colors.add(targetIndex, sourceDroppedColor);
            } else if (targetIndex != -1 && sourceIndex != -1) {
                Collections.swap(colors, targetIndex, sourceIndex);
            }
            refreshColorButtons();
        }
    }

    protected void handleDoubleClick(Button button) {
    }

    public void setPaletteSelectedColor(RGB colorToSet) {
        this.paletteSelectedColor = colorToSet;
    }

    public void removeColor(RGB colorToRemove) {
        boolean hasBeenRemoved = colors.remove(colorToRemove);
        if (hasBeenRemoved) {
            refreshColorButtons();
        }
    }

    public void addColor(RGB colorToAdd) {
        boolean alreadyExistingColor = colors.contains(colorToAdd);
        if (!alreadyExistingColor) {
            colors.add(0, colorToAdd);
            refreshColorButtons();
        }
    }

}
