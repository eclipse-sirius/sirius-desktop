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
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.swt.dnd.DragSourceListener;
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
import org.eclipse.swt.widgets.Control;

public class ColorPaletteComposite extends Composite {

    private static final String BUTTON_COLOR_DATA_KEY = "color"; //$NON-NLS-1$

    private static final int BUTTON_SIZE = 25;

    private List<RGB> colors = new ArrayList<>();

    private int columnNumber;

    private RGB selectedColor;

    /**
     * A map associating a RGB color with the corresponding Image.
     */
    private static Map<RGB, Image> rgbToImages = new HashMap<>();

    private Map<RGB, Button> buttonMap = new LinkedHashMap<>();

    protected ColorPaletteComposite(Composite parent, int style, List<RGB> colors, int columnNumber) {
        super(parent, style);
        this.colors = colors;
        this.columnNumber = columnNumber;
        init();
    }

    public static ColorPaletteComposite createColorPaletteComposite(Composite parent, List<RGB> colors, int columnNumber) {
        return new ColorPaletteComposite(parent, SWT.NONE, colors, columnNumber);
    }

    protected void init() {
        GridLayout layout = new GridLayout(this.columnNumber, true);
        setLayout(layout);
        GridData layoutData = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
        setLayoutData(layoutData);

        refreshColorButtons();
    }

    private void dragSource(Control control) {
        int operations = DND.DROP_MOVE | DND.DROP_COPY;
        DragSource source = new DragSource(control, operations);
        source.setTransfer(TextTransfer.getInstance());
        source.addDragListener(new DragSourceListener() {
            @Override
            public void dragStart(DragSourceEvent e) {
            }

            @Override
            public void dragSetData(DragSourceEvent e) {
                // fill the Event object with some useful data
                if (control instanceof Button button) {
                    e.data = button.getData(BUTTON_COLOR_DATA_KEY);
                }
            }

            @Override
            public void dragFinished(DragSourceEvent event) {
                // called after ending the drop.... maybe useful to remove the source, after having created it at drop
                System.out.println();
            }
        });
    }

    private void dropTarget(Control control) {
        int operations = DND.DROP_MOVE | DND.DROP_COPY;
        DropTarget source = new DropTarget(control, operations);
        source.setTransfer(TextTransfer.getInstance());
        source.addDropListener(new DropTargetAdapter() {
            @Override
            public void dragEnter(DropTargetEvent e) {
                // don't understand the purpose of this method
                if (e.detail == DND.DROP_NONE) {
                    e.detail = DND.DROP_LINK;
                }
            }

            @Override
            public void dragOperationChanged(DropTargetEvent e) {
            }

            @Override
            public void drop(DropTargetEvent event) {
                if (event.data == null) {
                    event.detail = DND.DROP_NONE;
                    return;
                }
                if (control instanceof Button button && event.data instanceof String droppedColor) {
                    RGB rgb = ColorManager.getDefault().stringToRGB(droppedColor);
                    String targetExistingColor = (String) button.getData(BUTTON_COLOR_DATA_KEY);
                    RGB rgbToMove = ColorManager.getDefault().stringToRGB(targetExistingColor);
                    int rgbIndex = colors.indexOf(rgb);
                    int rgbToMoveIndex = colors.indexOf(rgbToMove);
                    Collections.swap(colors, rgbIndex, rgbToMoveIndex);
                    refreshColorButtons();
                }
            }
        });
    }

    private void refreshColorButtons() {
        ArrayList<Button> buttonsList = new ArrayList<>(buttonMap.values());
        for (int i = 0; i < colors.size(); i++) {
            RGB color = colors.get(i);
            Button button;
            if (i < buttonsList.size()) {
                // Override settings of an existing button
                button = buttonsList.get(i);
            } else {
                // Create a new button
                button = new Button(this, SWT.PUSH);
                GridData buttonData = new GridData(BUTTON_SIZE, BUTTON_SIZE);
                button.setLayoutData(buttonData);

                button.addSelectionListener(new SelectionAdapter() {
                    @Override
                    public void widgetSelected(SelectionEvent e1) {
                        String colorString = (String) button.getData(BUTTON_COLOR_DATA_KEY);
                        selectedColor = ColorManager.getDefault().stringToRGB(colorString);
                        dispose();
                    }
                });
                dragSource(button); // configure drag
                dropTarget(button); // configure drop
            }
            if (!rgbToImages.containsKey(color)) {
                InventoryColorDescriptor colorDesc = new InventoryColorDescriptor(color);
                rgbToImages.put(color, colorDesc.createImage());
            }
            final Image image = rgbToImages.get(color);
            button.setData(BUTTON_COLOR_DATA_KEY, color.toString().replace("RGB ", "")); //$NON-NLS-1$ //$NON-NLS-2$
            button.setImage(image);
            buttonMap.put(color, button);
        }
    }

}
