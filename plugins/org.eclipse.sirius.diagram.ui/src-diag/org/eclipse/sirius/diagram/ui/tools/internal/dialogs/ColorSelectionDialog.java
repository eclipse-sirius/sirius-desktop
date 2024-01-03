package org.eclipse.sirius.diagram.ui.tools.internal.dialogs;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.diagram.ui.tools.api.color.ColorManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Shell;

public class ColorSelectionDialog extends Dialog {

    private static final String INVALID_COLOR_SUBSTITUTE = "{-1,-1,-1}"; //$NON-NLS-1$

    private Object preferenceToManage;

    private boolean showDisplayedColorsGroup;

    private RGB dialogSelectedColor;

    private String dialogTitle;

    private String displayedColorsGroupLabel;

    private String allColorsGroupLabel;

    private Button removeButton;

    private ColorPaletteComposite displayedColorsPalette;

    private ColorPaletteComposite allColorsPalette;

    public ColorSelectionDialog(Shell parentShell, Object preferenceToManage, boolean showDisplayedColorsGroup, RGB selectedColor, String dialogTitle, String displayedColorsGroupLabel,
            String allColorsGroupLabel) {
        super(parentShell);
        this.preferenceToManage = preferenceToManage;
        this.showDisplayedColorsGroup = showDisplayedColorsGroup;
        this.dialogSelectedColor = selectedColor;
        this.dialogTitle = dialogTitle;
        this.displayedColorsGroupLabel = displayedColorsGroupLabel;
        this.allColorsGroupLabel = allColorsGroupLabel;
    }

    @Override
    protected Control createDialogArea(Composite parent) {
        Composite container = (Composite) super.createDialogArea(parent);
        if (showDisplayedColorsGroup) {
            configureDisplayedColorsGroup(container);
        }
        configureAllColorsGroup(container);
        return container;
    }

    protected Group configureDisplayedColorsGroup(Composite container) {
        Group displayedColorsGroup = new Group(container, SWT.NONE);
        displayedColorsGroup.setLayout(new GridLayout());
        GridData displayedColorsGroupLayoutData = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
        displayedColorsGroup.setLayoutData(displayedColorsGroupLayoutData);
        displayedColorsGroup.setText(this.displayedColorsGroupLabel);

        List<RGB> colorsList4 = new ArrayList<>();
        colorsList4.add(new RGB(120, 0, 0));
        colorsList4.add(new RGB(0, 120, 0));
        colorsList4.add(new RGB(0, 0, 120));
        colorsList4.add(new RGB(120, 0, 120));

        this.displayedColorsPalette = new ColorPaletteComposite(displayedColorsGroup, colorsList4, 10, true) {
            @Override
            public void setPaletteSelectedColor(RGB colorToSet) {
                super.setPaletteSelectedColor(colorToSet);
                dialogSelectedColor = colorToSet;
                removeButton.setEnabled(colorToSet != null && getColors().contains(colorToSet));
            }

            @Override
            protected void dropColor(Button targetButton, String droppedColorString) {
                if (!INVALID_COLOR_SUBSTITUTE.equals(droppedColorString) && (getColors().size() < 10 || getColors().contains(ColorManager.getDefault().stringToRGB(droppedColorString)))) {
                    super.dropColor(targetButton, droppedColorString);
                    removeButton.setEnabled(false);
                }
            }
        };

        configureDisplayedColorsButtons(displayedColorsGroup);
        return displayedColorsGroup;
    }

    protected void configureDisplayedColorsButtons(Composite parent) {
        this.removeButton = new Button(parent, SWT.PUSH);
        this.removeButton.setText(Messages.ColorSelectionDialog_removeButtonLabel);
        this.removeButton.setLayoutData(new GridData(SWT.END, SWT.CENTER, true, true, 1, 1));
        this.removeButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                RGB colorToRemove = displayedColorsPalette.getPaletteSelectedColor();
                displayedColorsPalette.removeColor(colorToRemove);
                removeButton.setEnabled(false);
            }
        });
        this.removeButton.setEnabled(false);
    }

    protected Group configureAllColorsGroup(Composite container) {
        Group allColorsGroup = new Group(container, SWT.NONE);
        allColorsGroup.setLayout(new GridLayout());
        GridData allColorsGroupLayoutData = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
        allColorsGroup.setLayoutData(allColorsGroupLayoutData);
        allColorsGroup.setText(this.allColorsGroupLabel);

        List<RGB> colorsList3 = new ArrayList<>();
        colorsList3.add(new RGB(120, 120, 120));
        colorsList3.add(new RGB(42, 63, 158));
        colorsList3.add(new RGB(120, 120, 0));
        colorsList3.add(new RGB(0, 120, 120));
        colorsList3.add(new RGB(255, 0, 0));
        colorsList3.add(new RGB(0, 255, 0));
        colorsList3.add(new RGB(0, 0, 255));
        colorsList3.add(new RGB(255, 0, 255));
        colorsList3.add(new RGB(255, 255, 255));
        colorsList3.add(new RGB(0, 0, 0));
        colorsList3.add(new RGB(255, 255, 0));
        colorsList3.add(new RGB(0, 255, 255));

        this.allColorsPalette = new ColorPaletteComposite(allColorsGroup, colorsList3, 10, true) {
            @Override
            public void setPaletteSelectedColor(RGB colorToSet) {
                super.setPaletteSelectedColor(colorToSet);
                dialogSelectedColor = colorToSet;
                removeButton.setEnabled(false);
            };

            @Override
            protected void handleDoubleClick(Button button) {
                if (showDisplayedColorsGroup && displayedColorsPalette.getColors().size() < 10) {
                    String colorString = (String) button.getData(BUTTON_COLOR_DATA_KEY);
                    RGB convertedColor = ColorManager.getDefault().stringToRGB(colorString);
                    displayedColorsPalette.addColor(convertedColor);
                }
            }
            @Override
            protected void dragColor(Button button, String draggedColorString, DragSourceEvent dragEvent) {
                if (displayedColorsPalette.getColors().size() < 10) {
                    super.dragColor(button, draggedColorString, dragEvent);
                } else {
                    super.dragColor(button, INVALID_COLOR_SUBSTITUTE, dragEvent);
                }
            }
        };
        return allColorsGroup;
    }

    @Override
    protected void configureShell(Shell newShell) {
        super.configureShell(newShell);
        newShell.setText(this.dialogTitle);
    }

    @Override
    protected Point getInitialSize() {
        return super.getInitialSize();
    }

    /**
     * @return the dialogSelectedColor
     */
    public RGB getSelectedColor() {
        return dialogSelectedColor;
    }
}
