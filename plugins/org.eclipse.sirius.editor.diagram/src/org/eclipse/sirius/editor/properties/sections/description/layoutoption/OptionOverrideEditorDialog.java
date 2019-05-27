/** Copyright (c) 2018, 2019 Obeo
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.editor.properties.sections.description.layoutoption;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.edit.provider.ItemProvider;
import org.eclipse.emf.edit.ui.EMFEditUIPlugin;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.layout.TableColumnLayout;
import org.eclipse.jface.viewers.AbstractTreeViewer;
import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.sirius.diagram.description.BooleanLayoutOption;
import org.eclipse.sirius.diagram.description.CustomLayoutConfiguration;
import org.eclipse.sirius.diagram.description.DescriptionPackage;
import org.eclipse.sirius.diagram.description.DoubleLayoutOption;
import org.eclipse.sirius.diagram.description.EnumLayoutOption;
import org.eclipse.sirius.diagram.description.EnumSetLayoutOption;
import org.eclipse.sirius.diagram.description.IntegerLayoutOption;
import org.eclipse.sirius.diagram.description.LayoutOption;
import org.eclipse.sirius.diagram.description.LayoutOptionTarget;
import org.eclipse.sirius.diagram.description.StringLayoutOption;
import org.eclipse.sirius.editor.Messages;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.PatternFilter;

/**
 * A dialog allowing to select layout option to override.
 * 
 * @author <a href=mailto:pierre.guilet@obeo.fr>Pierre Guilet</a>
 *
 */
public class OptionOverrideEditorDialog extends Dialog {

    /**
     * A column label provider to show layout option information in different columns.
     * 
     * @author <a href=mailto:pierre.guilet@obeo.fr>Pierre Guilet</a>
     *
     */
    private final class LayoutOptionColumnLabelProvider extends CellLabelProvider implements ITableLabelProvider, ILabelProvider {

        @Override
        public Image getColumnImage(Object element, int columnIndex) {
            return null;
        }

        @Override
        public String getColumnText(Object element, int columnIndex) {
            LayoutOption layoutOption = (LayoutOption) element;
            String columnText = "";
            switch (columnIndex) {
            case 1:
                columnText = layoutOption.getLabel();
                break;
            case 2:
                columnText = getTargetLabels(layoutOption);
                break;
            case 3:
                columnText = getTypeLabel(layoutOption);
                break;
            case 4:
                columnText = layoutOption.getLabel();
                switch (layoutOption.eClass().getClassifierID()) {
                case DescriptionPackage.ENUM_LAYOUT_OPTION:
                    columnText = ((EnumLayoutOption) element).getValue().getName();
                    break;
                case DescriptionPackage.ENUM_SET_LAYOUT_OPTION:
                    columnText = ((EnumSetLayoutOption) element).getValues().stream().map((value) -> value.getName()).collect(Collectors.joining(", "));
                    break;
                case DescriptionPackage.STRING_LAYOUT_OPTION:
                    columnText = ((StringLayoutOption) element).getValue();
                    break;
                case DescriptionPackage.BOOLEAN_LAYOUT_OPTION:
                    columnText = String.valueOf(((BooleanLayoutOption) element).isValue());
                    break;
                case DescriptionPackage.DOUBLE_LAYOUT_OPTION:
                    columnText = String.valueOf(((DoubleLayoutOption) element).getValue());
                    break;
                case DescriptionPackage.INTEGER_LAYOUT_OPTION:
                    columnText = String.valueOf(((IntegerLayoutOption) element).getValue());
                    break;
                default:
                    break;
                }
                break;
            default:
                break;
            }
            return columnText;
        }

        @Override
        public void update(ViewerCell cell) {
            Object element = cell.getElement();
            cell.setText(getColumnText(element, cell.getColumnIndex()));
            Image image = getColumnImage(element, cell.getColumnIndex());
            cell.setImage(image);
        }

        @Override
        public Image getImage(Object element) {
            return null;
        }

        @Override
        public String getText(Object element) {
            return getColumnText(element, 1);
        }

    }

    /**
     * The label presenting the table used to choose options to override.
     */
    private static final String TABLE_LABEL = Messages.OptionOverrideEditorDialog_tableLabel;

    /**
     * The name of the column containing the checkbox.
     */
    private static final String CHECKBOX_COLUMN = "";

    private static final String LAYOUT_OPTION_TARGET_COLUMN = Messages.OptionOverrideEditorDialog_optionTargetColumnLabel;

    /**
     * The name of the column containing the layout option name.
     */
    private static final String LAYOUT_OPTION_NAME_COLUMN = Messages.OptionOverrideEditorDialog_optionNameColumnLabel;

    /**
     * The name of the column containing the layout option type.
     */
    private static final String LAYOUT_OPTION_TYPE_COLUMN = Messages.OptionOverrideEditorDialog_optionTypeColumnLabel;

    /**
     * The name of the column containing the layout option default value.
     */
    private static final String LAYOUT_OPTION_DEFAULT_VALUE_COLUMN = Messages.OptionOverrideEditorDialog_optionDefaultValueColumnLabel;

    /**
     * The {@link CustomLayoutConfiguration} from which options will be overriden.
     */
    private CustomLayoutConfiguration customLayoutConfiguration;

    /**
     * The name displayed in the title bar of the dialog.
     */
    private String displayName;

    /**
     * The layout options that can be overridden.
     */
    private List<LayoutOption> layoutOptions;

    /**
     * True if layout option items must be sorted regarding their type and in the ascent order. False otherwise.
     */
    private boolean ascentTypeOrder;

    /**
     * True if layout option items must be sorted regarding their target and in the ascent order. False otherwise.
     */
    private boolean ascentTargetOrder;

    // The array containing the column names.
    private String[] columnNames = new String[] { CHECKBOX_COLUMN, LAYOUT_OPTION_NAME_COLUMN, LAYOUT_OPTION_TARGET_COLUMN, LAYOUT_OPTION_TYPE_COLUMN, LAYOUT_OPTION_DEFAULT_VALUE_COLUMN };

    /**
     * The label for layout option description widget.
     */
    private Label layoutOptionDescriptionLabel;

    /**
     * The group for the layout option description widget.
     */
    private Group descriptionGroupComposite;

    /**
     * The options selected by the user to be overridden.
     */
    private Set<LayoutOption> checkedOptions;

    /**
     * Initialize the dialog.
     * 
     * @param parent
     *            the parent shell.
     * @param customLayoutConfiguration
     *            the {@link CustomLayoutConfiguration} for which layout options will be overridden.
     * @param displayName
     *            The name displayed in the title bar of the dialog.
     * @param layoutOptions
     *            The layout options that can be overridden.
     */
    public OptionOverrideEditorDialog(Shell parent, CustomLayoutConfiguration customLayoutConfiguration, String displayName, List<LayoutOption> layoutOptions) {
        super(parent);
        setShellStyle(getShellStyle() | SWT.RESIZE | SWT.MAX);
        this.customLayoutConfiguration = customLayoutConfiguration;
        this.displayName = displayName;
        this.layoutOptions = layoutOptions;
        this.checkedOptions = new HashSet<>();

    }

    /**
     * Return the label of the type of the given layout option.
     * 
     * @param layoutOption
     *            the layout option from which the type label must be retrieved.
     * @return the label of the type of the given layout option.
     */
    public String getTypeLabel(LayoutOption layoutOption) {
        String result = ""; //$NON-NLS-1$
        switch (layoutOption.eClass().getClassifierID()) {
        case DescriptionPackage.ENUM_LAYOUT_OPTION:
            result = Messages.OptionOverrideEditorDialog_typeEnumLabel;
            break;
        case DescriptionPackage.ENUM_SET_LAYOUT_OPTION:
            result = Messages.OptionOverrideEditorDialog_typeEnumSetLabel;
            break;
        case DescriptionPackage.STRING_LAYOUT_OPTION:
            result = Messages.OptionOverrideEditorDialog_typeStringLabel;
            break;
        case DescriptionPackage.BOOLEAN_LAYOUT_OPTION:
            result = Messages.OptionOverrideEditorDialog_typeBooleanLabel;
            break;
        case DescriptionPackage.DOUBLE_LAYOUT_OPTION:
            result = Messages.OptionOverrideEditorDialog_typeDoubleLabel;
            break;
        case DescriptionPackage.INTEGER_LAYOUT_OPTION:
            result = Messages.OptionOverrideEditorDialog_typeIntegerLabel;
            break;
        default:
            break;
        }
        return result;
    }

    /**
     * Return the layout option target labels.
     * 
     * @param layoutOption
     *            the layout option from which we want to retrieve the label of the target to which the option applies.
     * @return the layout option target labels.
     */
    public String getTargetLabels(LayoutOption layoutOption) {
        EList<LayoutOptionTarget> targets = layoutOption.getTargets();
        return targets.stream().map(target -> {
            String result = ""; //$NON-NLS-1$
            switch (target) {
            case PARENT:
                result = Messages.OptionOverrideEditorDialog_targetParentLabel;
                break;
            case NODE:
                result = Messages.OptionOverrideEditorDialog_targetNodeLabel;
                break;
            case EDGE:
                result = Messages.OptionOverrideEditorDialog_targetEdgeLabel;
                break;
            case PORTS:
                result = Messages.OptionOverrideEditorDialog_targePortLabel;
                break;
            case LABEL:
                result = Messages.OptionOverrideEditorDialog_targetLabelLabel;
                break;
            default:
                break;
            }
            return result;

        }).collect(Collectors.joining(", ")); //$NON-NLS-1$

    }

    @Override
    protected void configureShell(Shell shell) {
        super.configureShell(shell);
        shell.setText(displayName);
    }

    @Override
    protected Control createDialogArea(Composite parent) {
        Composite contents = (Composite) super.createDialogArea(parent);

        GridLayout contentsGridLayout = (GridLayout) contents.getLayout();
        contentsGridLayout.numColumns = 1;

        GridData contentsGridData = (GridData) contents.getLayoutData();
        contentsGridData.horizontalAlignment = SWT.FILL;
        contentsGridData.verticalAlignment = SWT.FILL;

        Text patternText = initFilterComposite(contents);

        Composite tableComposite = initTableComposites(contents);

        final Table table = new Table(tableComposite, SWT.CHECK | SWT.MULTI | SWT.FULL_SELECTION | SWT.BORDER);
        final TableViewer tableViewer = new TableViewer(table);

        initTable(table, tableViewer);
        initTableViewer(patternText, table, tableViewer);
        initDescriptionComposite(contents);
        return contents;
    }

    private Composite initTableComposites(Composite contents) {
        Composite tableComposite = new Composite(contents, SWT.NONE);
        TableColumnLayout tableLayout = new TableColumnLayout();
        tableComposite.setLayout(tableLayout);
        GridData data = new GridData(SWT.FILL, SWT.FILL, true, true);
        data.horizontalAlignment = SWT.END;
        tableComposite.setLayoutData(data);

        GridLayout layout = new GridLayout();
        data.horizontalAlignment = SWT.FILL;
        layout.marginHeight = 0;
        layout.marginWidth = 0;
        layout.numColumns = 1;
        tableComposite.setLayout(layout);

        Label tableLabel = new Label(tableComposite, SWT.NONE);
        tableLabel.setText(TABLE_LABEL);
        GridData tableLabelGridData = new GridData();
        tableLabelGridData.verticalAlignment = SWT.FILL;
        tableLabelGridData.horizontalAlignment = SWT.FILL;
        tableLabel.setLayoutData(tableLabelGridData);
        return tableComposite;
    }

    private void initTableViewer(Text patternText, final Table table, final TableViewer tableViewer) {
        tableViewer.setUseHashlookup(true);
        tableViewer.setColumnProperties(columnNames);
        LayoutOptionColumnLabelProvider labelProvider = new LayoutOptionColumnLabelProvider();
        tableViewer.setLabelProvider(labelProvider);
        tableViewer.setContentProvider(new AdapterFactoryContentProvider(new AdapterFactoryImpl()));

        final PatternFilter filter = new PatternFilter() {
            @Override
            protected boolean isParentMatch(Viewer viewer, Object element) {
                return viewer instanceof AbstractTreeViewer && super.isParentMatch(viewer, element);
            }

            @Override
            protected boolean isLeafMatch(Viewer viewer, Object element) {
                boolean leafMatch = super.isLeafMatch(viewer, element);
                if (!leafMatch) {
                    String labelText = labelProvider.getColumnText(element, 2);
                    if (labelText != null) {
                        leafMatch = leafMatch || wordMatches(labelText);
                        if (!leafMatch) {
                            labelText = labelProvider.getColumnText(element, 3);
                            if (labelText != null) {
                                leafMatch = leafMatch || wordMatches(labelText);
                            }
                        }
                    }
                }
                return leafMatch;
            }
        };
        tableViewer.addFilter(filter);
        if (patternText != null) {
            patternText.addModifyListener(new ModifyListener() {
                @Override
                public void modifyText(ModifyEvent e) {
                    filter.setPattern(((Text) e.widget).getText());
                    tableViewer.refresh();
                }
            });
        }

        tableViewer.addSelectionChangedListener(new ISelectionChangedListener() {

            @Override
            public void selectionChanged(SelectionChangedEvent event) {
                // This listener update the layout option description each time a new layout option is selected.
                IStructuredSelection selection = (IStructuredSelection) event.getSelection();
                LayoutOption firstElement = (LayoutOption) selection.getFirstElement();
                if (firstElement != null) {
                    String description = LayoutOptionPropertiesUtils.getDescription(customLayoutConfiguration, firstElement);
                    if (description == null) {
                        description = "";
                    }
                    layoutOptionDescriptionLabel.setText(description);
                    descriptionGroupComposite.layout();
                }
            }
        });
        tableViewer.setInput(new ItemProvider(layoutOptions));
    }

    private Composite initDescriptionComposite(Composite contents) {
        descriptionGroupComposite = new Group(contents, SWT.NONE);
        descriptionGroupComposite.setText(Messages.OptionOverrideEditorDialog_optionDescriptionLabel);
        GridLayout layout = new GridLayout(1, true);
        descriptionGroupComposite.setLayout(layout);
        GridData layoutData = new GridData(SWT.FILL, SWT.FILL, true, true);
        layoutData.minimumHeight = 100;
        layoutData.widthHint = 100;
        descriptionGroupComposite.setLayoutData(layoutData);

        layoutOptionDescriptionLabel = new Label(descriptionGroupComposite, SWT.WRAP);
        layoutOptionDescriptionLabel.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        return descriptionGroupComposite;
    }

    private void initTable(final Table table, final TableViewer tableViewer) {
        table.setHeaderVisible(true);
        table.setLinesVisible(true);
        GridData tableGridData = new GridData();
        int displayWidth = Display.getCurrent().getBounds().width;
        tableGridData.widthHint = displayWidth / 5;
        tableGridData.heightHint = Display.getCurrent().getBounds().height / 3;
        tableGridData.verticalAlignment = SWT.FILL;
        tableGridData.horizontalAlignment = SWT.FILL;
        tableGridData.grabExcessHorizontalSpace = true;
        tableGridData.grabExcessVerticalSpace = true;
        table.setLayoutData(tableGridData);

        TableColumn checkColumn = new TableColumn(table, SWT.CENTER, 0);
        checkColumn.setText(columnNames[0]);
        checkColumn.setWidth(30);
        TableColumn nameColumn = new TableColumn(table, SWT.LEFT, 1);
        nameColumn.setWidth(displayWidth / 14);
        nameColumn.setText(columnNames[1]);
        TableColumn targetColumn = new TableColumn(table, SWT.LEFT, 2);
        targetColumn.setWidth(displayWidth / 60);
        targetColumn.setText(columnNames[2]);
        TableColumn typeColumn = new TableColumn(table, SWT.LEFT, 3);
        typeColumn.setWidth(displayWidth / 60);
        typeColumn.setText(columnNames[3]);
        TableColumn defaultValueColumn = new TableColumn(table, SWT.LEFT, 4);
        defaultValueColumn.setWidth(displayWidth / 14);
        defaultValueColumn.setText(columnNames[4]);

        tableViewer.setComparator(new ViewerComparator(Comparator.naturalOrder()));
        // set the listeners to update the sorting according to column click.
        nameColumn.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                Comparator<String> comparator = ascentTypeOrder ? Comparator.naturalOrder() : Comparator.reverseOrder();
                ascentTypeOrder = !ascentTypeOrder;
                tableViewer.setComparator(new ViewerComparator(comparator));
            }
        });
        targetColumn.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                Comparator<String> comparator = ascentTargetOrder ? Comparator.naturalOrder() : Comparator.reverseOrder();
                ascentTargetOrder = !ascentTargetOrder;
                tableViewer.setComparator(new ViewerComparator() {

                    @Override
                    public int compare(Viewer viewer, Object e1, Object e2) {
                        LayoutOption layoutOption1 = (LayoutOption) e1;
                        LayoutOption layoutOption2 = (LayoutOption) e2;
                        // use the comparator to compare the target strings
                        return comparator.compare(getTargetLabels(layoutOption1), getTargetLabels(layoutOption2));

                    }
                });

            }
        });
        typeColumn.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                Comparator<String> comparator = ascentTypeOrder ? Comparator.naturalOrder() : Comparator.reverseOrder();
                ascentTypeOrder = !ascentTypeOrder;
                tableViewer.setComparator(new ViewerComparator() {

                    @Override
                    public int compare(Viewer viewer, Object e1, Object e2) {
                        LayoutOption layoutOption1 = (LayoutOption) e1;
                        LayoutOption layoutOption2 = (LayoutOption) e2;
                        // use the comparator to compare the type strings
                        return comparator.compare(getTypeLabel(layoutOption1), getTypeLabel(layoutOption2));

                    }
                });

            }
        });
        table.addListener(SWT.Selection, new Listener() {
            @Override
            public void handleEvent(Event event) {
                // this listener add a layout option to the result list each time its checkbox is checked and remove it
                // each time its checkbox is unchecked.
                if (event.detail == SWT.CHECK) {
                    TableItem tableItem = (TableItem) event.item;
                    LayoutOption layoutOption = (LayoutOption) tableItem.getData();
                    if (!tableItem.getChecked()) {
                        checkedOptions.remove(layoutOption);
                    } else {
                        checkedOptions.add(layoutOption);
                    }
                }
            }
        });
    }

    private Text initFilterComposite(Composite contents) {
        Text patternText = null;

        Group filterGroupComposite = new Group(contents, SWT.NONE);
        filterGroupComposite.setText(Messages.OptionOverrideEditorDialog_filteringLabel);
        filterGroupComposite.setLayout(new GridLayout(2, false));
        filterGroupComposite.setLayoutData(new GridData(SWT.FILL, SWT.DEFAULT, true, false, 3, 1));

        Label label = new Label(filterGroupComposite, SWT.NONE);
        label.setText(EMFEditUIPlugin.INSTANCE.getString("_UI_Choices_pattern_label"));

        patternText = new Text(filterGroupComposite, SWT.BORDER);
        patternText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        return patternText;
    }

    @Override
    protected void cancelPressed() {
        checkedOptions.clear();
        super.cancelPressed();
    }

    @Override
    public boolean close() {
        layoutOptionDescriptionLabel = null;
        descriptionGroupComposite = null;
        return super.close();
    }

    public Set<LayoutOption> getResult() {
        return checkedOptions;
    }
}
